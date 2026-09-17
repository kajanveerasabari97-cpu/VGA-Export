package com.example.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.data.model.ProductCategory
import com.example.data.model.ProductEntity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductEditDialog(
    product: ProductEntity?,
    onDismiss: () -> Unit,
    onSave: (
        name: String,
        category: String,
        description: String,
        packingDetails: String,
        variety: String,
        minOrder: String,
        isAvailable: Boolean,
        imageType: String
    ) -> Unit
) {
    var name by remember(product) { mutableStateOf(product?.name ?: "") }
    var category by remember(product) { mutableStateOf(product?.category ?: ProductCategory.VEGETABLES.displayName) }
    var description by remember(product) { mutableStateOf(product?.description ?: "") }
    var packingDetails by remember(product) { mutableStateOf(product?.packingDetails ?: "5 kg / 10 kg Export Cartons") }
    var variety by remember(product) { mutableStateOf(product?.variety ?: "") }
    var minOrder by remember(product) { mutableStateOf(product?.minOrder ?: "500 Kg") }
    var isAvailable by remember(product) { mutableStateOf(product?.isAvailable ?: true) }
    var imageType by remember(product) { mutableStateOf(product?.imageType ?: "veg") }

    var categoryExpanded by remember { mutableStateOf(false) }
    val categories = listOf(
        ProductCategory.VEGETABLES.displayName,
        ProductCategory.FRUITS.displayName,
        ProductCategory.LEAFY_GREENS.displayName
    )

    val scrollState = rememberScrollState()

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = if (product != null) "Edit Product" else "Add New Export Product",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
            )
        },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(scrollState)
                    .padding(vertical = 6.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Product Name *") },
                    modifier = Modifier.fillMaxWidth().testTag("edit_product_name"),
                    singleLine = true,
                    shape = RoundedCornerShape(8.dp)
                )

                // Category dropdown
                ExposedDropdownMenuBox(
                    expanded = categoryExpanded,
                    onExpandedChange = { categoryExpanded = !categoryExpanded }
                ) {
                    OutlinedTextField(
                        value = category,
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Category") },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = categoryExpanded) },
                        modifier = Modifier.fillMaxWidth().menuAnchor(),
                        shape = RoundedCornerShape(8.dp)
                    )
                    ExposedDropdownMenu(
                        expanded = categoryExpanded,
                        onDismissRequest = { categoryExpanded = false }
                    ) {
                        categories.forEach { cat ->
                            DropdownMenuItem(
                                text = { Text(cat) },
                                onClick = {
                                    category = cat
                                    categoryExpanded = false
                                }
                            )
                        }
                    }
                }

                OutlinedTextField(
                    value = variety,
                    onValueChange = { variety = it },
                    label = { Text("Variety / Grading") },
                    modifier = Modifier.fillMaxWidth().testTag("edit_product_variety"),
                    singleLine = true,
                    shape = RoundedCornerShape(8.dp)
                )

                OutlinedTextField(
                    value = packingDetails,
                    onValueChange = { packingDetails = it },
                    label = { Text("Packaging Specifications") },
                    modifier = Modifier.fillMaxWidth().testTag("edit_product_packing"),
                    singleLine = true,
                    shape = RoundedCornerShape(8.dp)
                )

                OutlinedTextField(
                    value = minOrder,
                    onValueChange = { minOrder = it },
                    label = { Text("Minimum Order Quantity") },
                    modifier = Modifier.fillMaxWidth().testTag("edit_product_min_order"),
                    singleLine = true,
                    shape = RoundedCornerShape(8.dp)
                )

                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Description") },
                    modifier = Modifier.fillMaxWidth().testTag("edit_product_desc"),
                    minLines = 3,
                    shape = RoundedCornerShape(8.dp)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = if (isAvailable) "Available for Export" else "Mark as Unavailable",
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold)
                    )
                    Switch(
                        checked = isAvailable,
                        onCheckedChange = { isAvailable = it },
                        modifier = Modifier.testTag("switch_product_availability")
                    )
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (name.isNotBlank()) {
                        val img = when (category) {
                            ProductCategory.VEGETABLES.displayName -> "veg"
                            ProductCategory.FRUITS.displayName -> "fruit"
                            else -> "greens"
                        }
                        onSave(name, category, description, packingDetails, variety, minOrder, isAvailable, img)
                    }
                },
                modifier = Modifier.testTag("btn_save_product")
            ) {
                Text("Save Product")
            }
        },
        dismissButton = {
            OutlinedButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}
