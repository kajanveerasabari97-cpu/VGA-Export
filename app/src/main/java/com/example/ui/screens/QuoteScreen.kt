package com.example.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.FlightTakeoff
import androidx.compose.material.icons.filled.Message
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.Scale
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import com.example.data.model.ProductCategory
import com.example.ui.QuoteFormState
import com.example.ui.components.QualityGuaranteeBadge
import com.example.ui.theme.ForestGreenPrimary
import com.example.ui.theme.OceanBlueSecondary
import com.example.util.ContactUtils

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuoteScreen(
    quoteForm: QuoteFormState,
    onUpdateForm: ((QuoteFormState) -> QuoteFormState) -> Unit,
    onSubmitQuote: (onSuccess: (InquiryEntity) -> Unit) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()

    var submittedInquiry by remember { mutableStateOf<InquiryEntity?>(null) }
    var showConfirmationDialog by remember { mutableStateOf(false) }

    var categoryExpanded by remember { mutableStateOf(false) }
    val categories = listOf(
        ProductCategory.VEGETABLES.displayName,
        ProductCategory.FRUITS.displayName,
        ProductCategory.LEAFY_GREENS.displayName
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp)
            .testTag("quote_screen"),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        QualityGuaranteeBadge(modifier = Modifier.fillMaxWidth())

        Text(
            text = "Request Export Quote",
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onBackground
        )

        Text(
            text = "Submit your international produce requirement below. Our export sales team will review order specifications, container volume, and packaging criteria to provide an accurate commercial quote.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f)
        )

        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(
                modifier = Modifier.padding(18.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                // Section 1: Product Specifications
                Text(
                    text = "1. Product Specifications",
                    style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.primary
                )

                // Category Selector
                ExposedDropdownMenuBox(
                    expanded = categoryExpanded,
                    onExpandedChange = { categoryExpanded = !categoryExpanded }
                ) {
                    OutlinedTextField(
                        value = quoteForm.category,
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Product Category *") },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = categoryExpanded) },
                        leadingIcon = { Icon(Icons.Default.Category, contentDescription = null) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .menuAnchor()
                            .testTag("input_quote_category"),
                        shape = RoundedCornerShape(10.dp)
                    )

                    ExposedDropdownMenu(
                        expanded = categoryExpanded,
                        onDismissRequest = { categoryExpanded = false }
                    ) {
                        categories.forEach { cat ->
                            DropdownMenuItem(
                                text = { Text(cat) },
                                onClick = {
                                    onUpdateForm { it.copy(category = cat) }
                                    categoryExpanded = false
                                }
                            )
                        }
                    }
                }

                // Product Name
                OutlinedTextField(
                    value = quoteForm.productName,
                    onValueChange = { value -> onUpdateForm { it.copy(productName = value) } },
                    label = { Text("Product Name * (e.g., Small Onion, Robusta Banana, Moringa)") },
                    leadingIcon = { Icon(Icons.Default.ShoppingBag, contentDescription = null) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("input_quote_product_name"),
                    singleLine = true,
                    shape = RoundedCornerShape(10.dp)
                )

                // Quantity & Unit
                OutlinedTextField(
                    value = quoteForm.quantity,
                    onValueChange = { value -> onUpdateForm { it.copy(quantity = value) } },
                    label = { Text("Required Quantity (e.g., 500 Kg, 2 Metric Tons, 300 Cartons)") },
                    leadingIcon = { Icon(Icons.Default.Scale, contentDescription = null) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("input_quote_quantity"),
                    singleLine = true,
                    shape = RoundedCornerShape(10.dp)
                )

                // Destination Country
                OutlinedTextField(
                    value = quoteForm.country,
                    onValueChange = { value -> onUpdateForm { it.copy(country = value) } },
                    label = { Text("Destination Country / Port (e.g., UAE, Singapore, UK, Malaysia)") },
                    leadingIcon = { Icon(Icons.Default.Public, contentDescription = null) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("input_quote_country"),
                    singleLine = true,
                    shape = RoundedCornerShape(10.dp)
                )

                // Preferred Delivery Date
                OutlinedTextField(
                    value = quoteForm.preferredDeliveryDate,
                    onValueChange = { value -> onUpdateForm { it.copy(preferredDeliveryDate = value) } },
                    label = { Text("Preferred Delivery Date / Target Dispatch") },
                    leadingIcon = { Icon(Icons.Default.CalendarToday, contentDescription = null) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("input_quote_delivery_date"),
                    singleLine = true,
                    shape = RoundedCornerShape(10.dp)
                )

                Spacer(modifier = Modifier.height(6.dp))

                // Section 2: Buyer & Company Contact Details
                Text(
                    text = "2. Buyer Contact Details",
                    style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.primary
                )

                OutlinedTextField(
                    value = quoteForm.customerName,
                    onValueChange = { value -> onUpdateForm { it.copy(customerName = value) } },
                    label = { Text("Customer / Contact Name *") },
                    leadingIcon = { Icon(Icons.Default.Person, contentDescription = null) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("input_quote_customer_name"),
                    singleLine = true,
                    shape = RoundedCornerShape(10.dp)
                )

                OutlinedTextField(
                    value = quoteForm.companyName,
                    onValueChange = { value -> onUpdateForm { it.copy(companyName = value) } },
                    label = { Text("Company Name / Importer Entity") },
                    leadingIcon = { Icon(Icons.Default.Business, contentDescription = null) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("input_quote_company"),
                    singleLine = true,
                    shape = RoundedCornerShape(10.dp)
                )

                OutlinedTextField(
                    value = quoteForm.mobile,
                    onValueChange = { value -> onUpdateForm { it.copy(mobile = value) } },
                    label = { Text("Mobile / WhatsApp Number *") },
                    leadingIcon = { Icon(Icons.Default.Phone, contentDescription = null) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("input_quote_phone"),
                    singleLine = true,
                    shape = RoundedCornerShape(10.dp)
                )

                OutlinedTextField(
                    value = quoteForm.email,
                    onValueChange = { value -> onUpdateForm { it.copy(email = value) } },
                    label = { Text("Email Address *") },
                    leadingIcon = { Icon(Icons.Default.Email, contentDescription = null) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("input_quote_email"),
                    singleLine = true,
                    shape = RoundedCornerShape(10.dp)
                )

                OutlinedTextField(
                    value = quoteForm.additionalRequirements,
                    onValueChange = { value -> onUpdateForm { it.copy(additionalRequirements = value) } },
                    label = { Text("Additional Requirements (Packing specs, cooling, custom counts)") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("input_quote_requirements"),
                    minLines = 3,
                    shape = RoundedCornerShape(10.dp)
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Submit Button
                Button(
                    onClick = {
                        onSubmitQuote { inquiry ->
                            submittedInquiry = inquiry
                            showConfirmationDialog = true
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                        .testTag("btn_submit_quote"),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = OceanBlueSecondary)
                ) {
                    Icon(
                        imageVector = Icons.Default.FlightTakeoff,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Request Export Quote",
                        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
    }

    // Confirmation & Instant Forwarding Dialog
    if (showConfirmationDialog && submittedInquiry != null) {
        val inq = submittedInquiry!!
        AlertDialog(
            onDismissRequest = { showConfirmationDialog = false },
            icon = {
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = null,
                    tint = ForestGreenPrimary,
                    modifier = Modifier.size(36.dp)
                )
            },
            title = {
                Text(
                    text = "Quote Inquiry Received!",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )
            },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        text = "Thank you, ${inq.customerName}. Your export inquiry for ${inq.productName} (${inq.quantity}) to ${inq.country} has been logged successfully.",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = "Would you also like to immediately send these inquiry details to VGA Export via WhatsApp or Email?",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.75f)
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        val whatsappMessage = "Hello VGA Export,\n\nI have requested an export quote:\n" +
                                "Product: ${inq.productName} (${inq.category})\n" +
                                "Quantity: ${inq.quantity}\n" +
                                "Destination: ${inq.country}\n" +
                                "Buyer Name: ${inq.customerName}\n" +
                                "Company: ${inq.companyName}\n" +
                                "Phone: ${inq.phone}\n" +
                                "Email: ${inq.email}\n" +
                                "Requirements: ${inq.additionalRequirements}"
                        ContactUtils.openWhatsApp(context, whatsappMessage)
                        showConfirmationDialog = false
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF25D366))
                ) {
                    Icon(Icons.Default.Message, contentDescription = null, tint = Color.White, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Send on WhatsApp", color = Color.White)
                }
            },
            dismissButton = {
                OutlinedButton(onClick = { showConfirmationDialog = false }) {
                    Text("Done")
                }
            }
        )
    }
}
