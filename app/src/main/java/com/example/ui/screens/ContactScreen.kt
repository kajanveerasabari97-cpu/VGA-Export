package com.example.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Message
import androidx.compose.material.icons.filled.Navigation
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledTonalButton
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
import com.example.ui.components.BrandLogoHeader
import com.example.ui.components.QualityGuaranteeBadge
import com.example.ui.theme.ForestGreenPrimary
import com.example.ui.theme.OceanBlueSecondary
import com.example.util.ContactUtils

@Composable
fun ContactScreen(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()

    var contactName by remember { mutableStateOf("") }
    var contactCompany by remember { mutableStateOf("") }
    var contactPhone by remember { mutableStateOf("") }
    var contactEmail by remember { mutableStateOf("") }
    var contactMessage by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp)
            .testTag("contact_screen"),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        BrandLogoHeader()

        QualityGuaranteeBadge(modifier = Modifier.fillMaxWidth())

        Text(
            text = "Get in Touch with VGA Export",
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onBackground
        )

        Text(
            text = "Direct communication for overseas buyers, importers, and agricultural wholesalers. Reach out through any of our channels below.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.75f)
        )

        // Contact Info Cards
        ContactInfoCard(
            title = "Export Headquarters & Facility",
            detail = ContactUtils.ADDRESS,
            icon = Icons.Default.LocationOn,
            buttonLabel = "Get Directions",
            onAction = { ContactUtils.openDirections(context) },
            tint = ForestGreenPrimary,
            tag = "contact_address"
        )

        ContactInfoCard(
            title = "Mobile & Direct Calling",
            detail = ContactUtils.PHONE_NUMBER,
            icon = Icons.Default.Call,
            buttonLabel = "Call Now",
            onAction = { ContactUtils.callBusiness(context) },
            tint = ForestGreenPrimary,
            tag = "contact_mobile"
        )

        ContactInfoCard(
            title = "Official WhatsApp Business",
            detail = ContactUtils.PHONE_NUMBER,
            icon = Icons.Default.Message,
            buttonLabel = "Chat on WhatsApp",
            onAction = { ContactUtils.openWhatsApp(context) },
            tint = Color(0xFF25D366),
            tag = "contact_whatsapp"
        )

        ContactInfoCard(
            title = "Export Inquiry Email",
            detail = ContactUtils.EMAIL,
            icon = Icons.Default.Email,
            buttonLabel = "Send Email",
            onAction = { ContactUtils.sendEmail(context) },
            tint = OceanBlueSecondary,
            tag = "contact_email"
        )

        // Interactive Contact Message Form
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(
                modifier = Modifier.padding(18.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "Send an Inquiry Message",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.onSurface
                )

                OutlinedTextField(
                    value = contactName,
                    onValueChange = { contactName = it },
                    label = { Text("Your Name") },
                    leadingIcon = { Icon(Icons.Default.Person, contentDescription = null) },
                    modifier = Modifier.fillMaxWidth().testTag("input_contact_name"),
                    singleLine = true,
                    shape = RoundedCornerShape(10.dp)
                )

                OutlinedTextField(
                    value = contactCompany,
                    onValueChange = { contactCompany = it },
                    label = { Text("Company Name (Optional)") },
                    leadingIcon = { Icon(Icons.Default.Business, contentDescription = null) },
                    modifier = Modifier.fillMaxWidth().testTag("input_contact_company"),
                    singleLine = true,
                    shape = RoundedCornerShape(10.dp)
                )

                OutlinedTextField(
                    value = contactPhone,
                    onValueChange = { contactPhone = it },
                    label = { Text("Mobile / WhatsApp") },
                    leadingIcon = { Icon(Icons.Default.Phone, contentDescription = null) },
                    modifier = Modifier.fillMaxWidth().testTag("input_contact_phone"),
                    singleLine = true,
                    shape = RoundedCornerShape(10.dp)
                )

                OutlinedTextField(
                    value = contactEmail,
                    onValueChange = { contactEmail = it },
                    label = { Text("Email Address") },
                    leadingIcon = { Icon(Icons.Default.Email, contentDescription = null) },
                    modifier = Modifier.fillMaxWidth().testTag("input_contact_email"),
                    singleLine = true,
                    shape = RoundedCornerShape(10.dp)
                )

                OutlinedTextField(
                    value = contactMessage,
                    onValueChange = { contactMessage = it },
                    label = { Text("Your Message or Requirement") },
                    modifier = Modifier.fillMaxWidth().testTag("input_contact_message"),
                    minLines = 3,
                    shape = RoundedCornerShape(10.dp)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Button(
                        onClick = {
                            val msg = "Hello VGA Export,\nName: $contactName\nCompany: $contactCompany\nPhone: $contactPhone\nEmail: $contactEmail\nMessage: $contactMessage"
                            ContactUtils.openWhatsApp(context, msg)
                        },
                        modifier = Modifier.weight(1f).testTag("btn_contact_send_whatsapp"),
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF25D366))
                    ) {
                        Icon(Icons.Default.Message, contentDescription = null, tint = Color.White, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Send via WhatsApp", color = Color.White, style = MaterialTheme.typography.labelSmall)
                    }

                    Button(
                        onClick = {
                            val subject = "Produce Inquiry from $contactName (${if (contactCompany.isNotBlank()) contactCompany else "Buyer"})"
                            val body = "Name: $contactName\nCompany: $contactCompany\nPhone: $contactPhone\nEmail: $contactEmail\n\nMessage:\n$contactMessage"
                            ContactUtils.sendEmail(context, subject, body)
                        },
                        modifier = Modifier.weight(1f).testTag("btn_contact_send_email"),
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = OceanBlueSecondary)
                    ) {
                        Icon(Icons.Default.Email, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Send via Email", style = MaterialTheme.typography.labelSmall)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Composable
fun ContactInfoCard(
    title: String,
    detail: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    buttonLabel: String,
    onAction: () -> Unit,
    tint: Color,
    tag: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onAction() }
            .testTag(tag),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                shape = CircleShape,
                color = tint.copy(alpha = 0.12f),
                modifier = Modifier.size(44.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(imageVector = icon, contentDescription = null, tint = tint, modifier = Modifier.size(22.dp))
                }
            }

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold),
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.65f)
                )
                Text(
                    text = detail,
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            FilledTonalButton(
                onClick = onAction,
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(text = buttonLabel, style = MaterialTheme.typography.labelSmall)
            }
        }
    }
}
