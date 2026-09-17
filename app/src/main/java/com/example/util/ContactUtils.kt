package com.example.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast

object ContactUtils {
    const val BUSINESS_NAME = "VGA Export"
    const val ADDRESS = "Block L30, Periyar Vegetable Market, 600107, India"
    const val PHONE_NUMBER = "+91 77089 80660"
    const val PHONE_RAW = "+917708980660"
    const val WHATSAPP_RAW = "917708980660"
    const val EMAIL = "kajanveerasabari97@gmail.com"

    fun callBusiness(context: Context) {
        try {
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:$PHONE_RAW")
            }
            context.startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(context, "Unable to launch dialer: $PHONE_NUMBER", Toast.LENGTH_SHORT).show()
        }
    }

    fun openWhatsApp(context: Context, customMessage: String = "Hello VGA Export, I would like to inquire about your fresh agricultural produce export services.") {
        try {
            val encodedMessage = Uri.encode(customMessage)
            val url = "https://wa.me/$WHATSAPP_RAW?text=$encodedMessage"
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse(url)
            }
            context.startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(context, "Unable to launch WhatsApp: $PHONE_NUMBER", Toast.LENGTH_SHORT).show()
        }
    }

    fun sendEmail(
        context: Context,
        subject: String = "Export Inquiry - VGA Export Fresh Produce",
        body: String = "Hello VGA Export Team,\n\nI am interested in sourcing fresh produce for international export.\n\nPlease share your current availability and pricing.\n\nThank you."
    ) {
        try {
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:$EMAIL")
                putExtra(Intent.EXTRA_SUBJECT, subject)
                putExtra(Intent.EXTRA_TEXT, body)
            }
            context.startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(context, "Unable to launch email app: $EMAIL", Toast.LENGTH_SHORT).show()
        }
    }

    fun openDirections(context: Context) {
        try {
            val mapUri = Uri.parse("https://www.google.com/maps/search/?api=1&query=" + Uri.encode(ADDRESS))
            val intent = Intent(Intent.ACTION_VIEW, mapUri)
            context.startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(context, "Unable to open maps for: $ADDRESS", Toast.LENGTH_SHORT).show()
        }
    }
}
