package com.example.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.filled.AirplanemodeActive
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.FactCheck
import androidx.compose.material.icons.filled.FlightTakeoff
import androidx.compose.material.icons.filled.Inventory
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material.icons.filled.Thermostat
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.ui.NavTab
import com.example.ui.components.QualityGuaranteeBadge
import com.example.ui.components.QuickContactButtonsRow
import com.example.ui.theme.ForestGreenPrimary
import com.example.ui.theme.OceanBlueSecondary

@Composable
fun ExportTransportScreen(
    onNavigateToQuote: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .testTag("export_transport_screen")
    ) {
        // Hero Header with Shipping Image
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_export_shipping),
                contentDescription = "International Produce Export Shipping",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Black.copy(alpha = 0.35f),
                                Color.Black.copy(alpha = 0.85f)
                            )
                        )
                    )
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                verticalArrangement = Arrangement.Bottom
            ) {
                Surface(
                    color = OceanBlueSecondary,
                    shape = RoundedCornerShape(6.dp)
                ) {
                    Text(
                        text = "GLOBAL PRODUCE LOGISTICS",
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.sp
                        ),
                        color = Color.White
                    )
                }

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "International Transport & Export",
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Black),
                    color = Color.White
                )

                Text(
                    text = "Coordinated transportation for fresh agricultural produce orders",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.White.copy(alpha = 0.9f)
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            QualityGuaranteeBadge(modifier = Modifier.fillMaxWidth())

            // Core Service Explanation
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
            ) {
                Column(
                    modifier = Modifier.padding(18.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(
                        text = "Export Coordination",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.primary
                    )

                    Text(
                        text = "VGA Export provides fresh vegetables, fruits, and leafy greens for international customers and coordinates transportation for export orders.\n\nFrom our hub at the Periyar Vegetable Market, we ensure produce is prepared, packaged with aerated and insulated cartons, and handed over for prompt dispatch to international destinations.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.85f),
                        lineHeight = 22.sp
                    )
                }
            }

            // 5-Step Export Process
            Text(
                text = "Our 5-Step Export Process",
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onBackground
            )

            ProcessStepCard(
                stepNumber = "1",
                stepTitle = "Product Selection",
                stepDescription = "Farm-fresh vegetables, fruits, and leafy greens are carefully sourced and chosen at optimal maturity for export readiness.",
                icon = Icons.Default.Spa,
                stepColor = ForestGreenPrimary
            )

            ProcessStepCard(
                stepNumber = "2",
                stepTitle = "Quality Checking",
                stepDescription = "Thorough grading, cleaning, and inspection to guarantee first-quality produce that meets clean and fresh international standards.",
                icon = Icons.Default.FactCheck,
                stepColor = ForestGreenPrimary
            )

            ProcessStepCard(
                stepNumber = "3",
                stepTitle = "Packing",
                stepDescription = "Product-specific export packing utilizing corrugated fiberboard boxes, ventilation slots, moisture barriers, and temperature cooling aids.",
                icon = Icons.Default.Inventory,
                stepColor = OceanBlueSecondary
            )

            ProcessStepCard(
                stepNumber = "4",
                stepTitle = "Transport",
                stepDescription = "Carefully coordinated local transport to cargo staging points and terminals to preserve freshness and reduce dwell times.",
                icon = Icons.Default.LocalShipping,
                stepColor = OceanBlueSecondary
            )

            ProcessStepCard(
                stepNumber = "5",
                stepTitle = "International Export",
                stepDescription = "Coordinated dispatch for overseas shipments, connecting our fresh agricultural inventory to international customers.",
                icon = Icons.Default.Public,
                stepColor = ForestGreenPrimary
            )

            // Packaging & Handling Standards
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Column(
                    modifier = Modifier.padding(18.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Thermostat,
                            contentDescription = null,
                            tint = OceanBlueSecondary
                        )
                        Text(
                            text = "Packaging & Handling Integrity",
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    Text(
                        text = "• Corrugated export cartons with proper bursting strength\n• Aerated and perforated ventilation for perishables\n• Thermocol chilled packaging with gel ice packs for leafy keerai\n• Foam netting cushioning for sensitive fruits (mangoes, custard apple, sapota)\n• Breathable mesh bags for onions, coconuts, and root crops",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.85f),
                        lineHeight = 22.sp
                    )
                }
            }

            // Call to Action
            Button(
                onClick = onNavigateToQuote,
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("btn_export_inquiry"),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = OceanBlueSecondary)
            ) {
                Icon(
                    imageVector = Icons.Default.FlightTakeoff,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Request Export Shipping Quote",
                    style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold)
                )
            }

            // Contact Actions
            QuickContactButtonsRow(
                whatsappMessage = "Hello VGA Export, I would like to inquire about international transportation and export of fresh agricultural produce."
            )

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
fun ProcessStepCard(
    stepNumber: String,
    stepTitle: String,
    stepDescription: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    stepColor: Color,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(14.dp),
            verticalAlignment = Alignment.Top
        ) {
            Surface(
                shape = CircleShape,
                color = stepColor.copy(alpha = 0.12f),
                modifier = Modifier.size(44.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text(
                        text = stepNumber,
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Black),
                        color = stepColor
                    )
                }
            }

            Column(modifier = Modifier.weight(1f)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = stepColor,
                        modifier = Modifier.size(18.dp)
                    )
                    Text(
                        text = stepTitle,
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = stepDescription,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f),
                    lineHeight = 18.sp
                )
            }
        }
    }
}
