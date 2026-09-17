package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.data.local.InitialData
import com.example.util.ContactUtils
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class ExampleRobolectricTest {

  @Test
  fun `read string from context`() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    val appName = context.getString(R.string.app_name)
    assertEquals("VGA Export", appName)
  }

  @Test
  fun `initial products catalog contains vegetables fruits and leafy greens`() {
    val defaultProducts = InitialData.getDefaultProducts()
    assertTrue(defaultProducts.isNotEmpty())
    assertTrue(defaultProducts.any { it.category == "Vegetables" })
    assertTrue(defaultProducts.any { it.category == "Fruits" })
    assertTrue(defaultProducts.any { it.category == "Leafy Greens" })
    assertTrue(defaultProducts.any { it.name.contains("Small Onion", ignoreCase = true) })
    assertTrue(defaultProducts.any { it.name.contains("Banana", ignoreCase = true) })
    assertTrue(defaultProducts.any { it.name.contains("Siru Keerai", ignoreCase = true) })
  }

  @Test
  fun `contact details are correctly formatted`() {
    assertEquals("+91 77089 80660", ContactUtils.PHONE_NUMBER)
    assertEquals("kajanveerasabari97@gmail.com", ContactUtils.EMAIL)
    assertTrue(ContactUtils.ADDRESS.contains("Block L30, Periyar Vegetable Market"))
  }

  @Test
  fun `nav tab contains account auth tab`() {
    val tabs = com.example.ui.NavTab.values()
    assertTrue(tabs.any { it.name == "AUTH" })
    val authTab = com.example.ui.NavTab.AUTH
    assertEquals("Account", authTab.title)
  }
}
