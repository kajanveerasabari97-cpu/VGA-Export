package com.example.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AdminPanelSettings
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.FlightTakeoff
import androidx.compose.material.icons.filled.Inventory2
import androidx.compose.material.icons.filled.Message
import androidx.compose.material.icons.filled.PendingActions
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Public
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.InquiryEntity
import com.example.data.model.ProductEntity
import com.example.ui.theme.ForestGreenPrimary
import com.example.ui.theme.GoldenMangoTertiary
import com.example.ui.theme.OceanBlueSecondary
import com.example.util.ContactUtils
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun AdminScreen(
    products: List<ProductEntity>,
    inquiries: List<InquiryEntity>,
    onToggleAvailability: (ProductEntity) -> Unit,
    onEditProduct: (ProductEntity) -> Unit,
    onDeleteProduct: (ProductEntity) -> Unit,
    onAddNewProduct: () -> Unit,
    onUpdateInquiryStatus: (inquiryId: Int, status: String) -> Unit,
    onDeleteInquiry: (inquiryId: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var selectedTab by remember { mutableIntStateOf(0) } // 0: Products, 1: Inquiries

    val availableCount = products.count { it.isAvailable }
    val pendingInquiriesCount = inquiries.count { it.status.equals("Pending", ignoreCase = true) }

    Box(modifier = modifier.fillMaxSize().testTag("admin_screen")) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp, bottom = 90.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // Admin Portal Header
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Surface(
                        shape = CircleShape,
                        color = OceanBlueSecondary.copy(alpha = 0.12f),
                        modifier = Modifier.size(44.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(
                                imageVector = Icons.Default.AdminPanelSettings,
                                contentDescription = null,
                                tint = OceanBlueSecondary,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }
                    Column {
                        Text(
                            text = "VGA Export Admin Portal",
                            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        Text(
                            text = "Catalog Availability & Customer Inquiry Operations",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
                        )
                    }
                }
            }

            // Summary Metrics Cards
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    MetricCard(
                        title = "Catalog Items",
                        count = "${products.size}",
                        subtitle = "$availableCount Active",
                        color = ForestGreenPrimary,
                        modifier = Modifier.weight(1f)
                    )
                    MetricCard(
                        title = "Quote Inquiries",
                        count = "${inquiries.size}",
                        subtitle = "$pendingInquiriesCount Pending",
                        color = GoldenMangoTertiary,
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            // Tab Selector
            item {
                TabRow(
                    selectedTabIndex = selectedTab,
                    containerColor = MaterialTheme.colorScheme.surface,
                    contentColor = OceanBlueSecondary
                ) {
                    Tab(
                        selected = selectedTab == 0,
                        onClick = { selectedTab = 0 },
                        text = {
                            Text(
                                "Product Catalog (${products.size})",
                                fontWeight = if (selectedTab == 0) FontWeight.Bold else FontWeight.Normal
                            )
                        }
                    )
                    Tab(
                        selected = selectedTab == 1,
                        onClick = { selectedTab = 1 },
                        text = {
                            Text(
                                "Buyer Inquiries (${inquiries.size})",
                                fontWeight = if (selectedTab == 1) FontWeight.Bold else FontWeight.Normal
                            )
                        }
                    )
                }
            }

            // --- TAB 0: PRODUCT CATALOG MANAGEMENT ---
            if (selectedTab == 0) {
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Products (${products.size})",
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                        )
                        Button(
                            onClick = onAddNewProduct,
                            colors = ButtonDefaults.buttonColors(containerColor = ForestGreenPrimary),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Icon(Icons.Default.Add, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Add Product")
                        }
                    }
                }

                items(products, key = { it.id }) { product ->
                    Card(
                        modifier = Modifier.fillMaxWidth().testTag("admin_item_${product.id}"),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                    ) {
                        Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = product.name,
                                        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
                                    )
                                    Text(
                                        text = "${product.category} • Min: ${product.minOrder}",
                                        style = MaterialTheme.typography.labelSmall,
                                        color = OceanBlueSecondary
                                    )
                                }

                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                                ) {
                                    Text(
                                        text = if (product.isAvailable) "Available" else "Unavailable",
                                        style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.SemiBold),
                                        color = if (product.isAvailable) ForestGreenPrimary else Color(0xFFC62828)
                                    )
                                    Switch(
                                        checked = product.isAvailable,
                                        onCheckedChange = { onToggleAvailability(product) }
                                    )
                                }
                            }

                            Text(
                                text = product.packingDetails,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                            )

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.End,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                OutlinedButton(
                                    onClick = { onEditProduct(product) },
                                    shape = RoundedCornerShape(6.dp)
                                ) {
                                    Icon(Icons.Default.Edit, contentDescription = null, modifier = Modifier.size(14.dp))
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text("Edit", style = MaterialTheme.typography.labelSmall)
                                }
                                Spacer(modifier = Modifier.width(8.dp))
                                IconButton(onClick = { onDeleteProduct(product) }) {
                                    Icon(Icons.Default.Delete, contentDescription = "Delete", tint = Color(0xFFC62828))
                                }
                            }
                        }
                    }
                }
            }

            // --- TAB 1: CUSTOMER INQUIRIES MANAGEMENT ---
            if (selectedTab == 1) {
                if (inquiries.isEmpty()) {
                    item {
                        Card(
                            modifier = Modifier.fillMaxWidth().padding(24.dp),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Column(
                                modifier = Modifier.padding(24.dp).fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Icon(Icons.Default.PendingActions, contentDescription = null, tint = OceanBlueSecondary, modifier = Modifier.size(40.dp))
                                Text("No Inquiries Logged Yet", fontWeight = FontWeight.Bold)
                                Text("Inquiries submitted through the quote form will appear here.", style = MaterialTheme.typography.bodySmall)
                            }
                        }
                    }
                } else {
                    items(inquiries, key = { it.id }) { inq ->
                        InquiryCard(
                            inquiry = inq,
                            onUpdateStatus = { status -> onUpdateInquiryStatus(inq.id, status) },
                            onDelete = { onDeleteInquiry(inq.id) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun MetricCard(
    title: String,
    count: String,
    subtitle: String,
    color: Color,
    modifier: Modifier = Modifier
) {
    ElevatedCard(
        modifier = modifier,
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(text = title, style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f))
            Text(text = count, style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Black), color = color)
            Text(text = subtitle, style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold), color = color)
        }
    }
}

@Composable
fun InquiryCard(
    inquiry: InquiryEntity,
    onUpdateStatus: (String) -> Unit,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var statusMenuExpanded by remember { mutableStateOf(false) }
    val statuses = listOf("Pending", "Contacted", "Quoted", "Completed")

    val dateFormat = remember { SimpleDateFormat("dd MMM yyyy, hh:mm a", Locale.getDefault()) }
    val dateStr = remember(inquiry.timestamp) { dateFormat.format(Date(inquiry.timestamp)) }

    Card(
        modifier = modifier.fillMaxWidth().testTag("inquiry_item_${inquiry.id}"),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            // Header: Buyer & Status Chip
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = inquiry.customerName,
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                    )
                    if (inquiry.companyName.isNotBlank()) {
                        Text(
                            text = inquiry.companyName,
                            style = MaterialTheme.typography.bodySmall,
                            color = OceanBlueSecondary
                        )
                    }
                    Text(
                        text = dateStr,
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                    )
                }

                // Status dropdown anchor
                Box {
                    Surface(
                        modifier = Modifier.clickable { statusMenuExpanded = true },
                        color = when (inquiry.status) {
                            "Completed" -> Color(0xFFE8F5E9)
                            "Quoted" -> Color(0xFFE1F5FE)
                            "Contacted" -> Color(0xFFFFF8E1)
                            else -> Color(0xFFFFEBEE)
                        },
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            text = "Status: ${inquiry.status} ▾",
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp),
                            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                            color = when (inquiry.status) {
                                "Completed" -> ForestGreenPrimary
                                "Quoted" -> OceanBlueSecondary
                                "Contacted" -> GoldenMangoTertiary
                                else -> Color(0xFFC62828)
                            }
                        )
                    }

                    DropdownMenu(
                        expanded = statusMenuExpanded,
                        onDismissRequest = { statusMenuExpanded = false }
                    ) {
                        statuses.forEach { st ->
                            DropdownMenuItem(
                                text = { Text(st) },
                                onClick = {
                                    onUpdateStatus(st)
                                    statusMenuExpanded = false
                                }
                            )
                        }
                    }
                }
            }

            // Inquiry Details
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
            ) {
                Column(modifier = Modifier.padding(10.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(
                        text = "Requested: ${inquiry.productName} (${inquiry.category})",
                        style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                        color = ForestGreenPrimary
                    )
                    Text(
                        text = "Quantity: ${inquiry.quantity} • Target: ${inquiry.country}",
                        style = MaterialTheme.typography.labelSmall
                    )
                    if (inquiry.preferredDeliveryDate.isNotBlank()) {
                        Text(
                            text = "Target Date: ${inquiry.preferredDeliveryDate}",
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                    if (inquiry.additionalRequirements.isNotBlank()) {
                        Text(
                            text = "Requirements: ${inquiry.additionalRequirements}",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            // Quick Actions to reply to Buyer
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = {
                        val reply = "Hello ${inquiry.customerName}, this is VGA Export regarding your inquiry for ${inquiry.productName} (${inquiry.quantity})."
                        ContactUtils.openWhatsApp(context, reply)
                    },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF25D366))
                ) {
                    Icon(Icons.Default.Message, contentDescription = null, tint = Color.White, modifier = Modifier.size(14.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("WhatsApp", color = Color.White, style = MaterialTheme.typography.labelSmall)
                }

                if (inquiry.phone.isNotBlank()) {
                    Button(
                        onClick = {
                            try {
                                val intent = android.content.Intent(android.content.Intent.ACTION_DIAL).apply {
                                    data = android.net.Uri.parse("tel:${inquiry.phone}")
                                }
                                context.startActivity(intent)
                            } catch (e: Exception) {}
                        },
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = ForestGreenPrimary)
                    ) {
                        Icon(Icons.Default.Call, contentDescription = "Call Buyer", modifier = Modifier.size(14.dp))
                    }
                }

                IconButton(onClick = onDelete) {
                    Icon(Icons.Default.Delete, contentDescription = "Delete Inquiry", tint = Color(0xFFC62828))
                }
            }
        }
    }
}
