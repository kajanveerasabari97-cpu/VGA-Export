package com.example.ui

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AdminPanelSettings
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.ContactSupport
import androidx.compose.material.icons.filled.FlightTakeoff
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material.icons.filled.Message
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.RequestQuote
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.R
import com.example.ui.components.ProductEditDialog
import com.example.ui.screens.AboutScreen
import com.example.ui.screens.AdminScreen
import com.example.ui.screens.AuthScreen
import com.example.ui.screens.ContactScreen
import com.example.ui.screens.ExportTransportScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.ProductsScreen
import com.example.ui.screens.QuoteScreen
import com.example.ui.theme.ForestGreenPrimary
import com.example.ui.theme.GoldenMangoTertiary
import com.example.ui.theme.OceanBlueSecondary
import com.example.util.ContactUtils
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VgaMainApp(
    viewModel: VgaViewModel = viewModel()
) {
    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }

    val currentTab by viewModel.currentTab.collectAsStateWithLifecycle()
    val filteredProducts by viewModel.filteredProducts.collectAsStateWithLifecycle()
    val allInquiries by viewModel.allInquiries.collectAsStateWithLifecycle()
    val selectedCategory by viewModel.selectedCategoryFilter.collectAsStateWithLifecycle()
    val searchQuery by viewModel.searchQuery.collectAsStateWithLifecycle()
    val isAdminMode by viewModel.isAdminMode.collectAsStateWithLifecycle()
    val editingProduct by viewModel.editingProduct.collectAsStateWithLifecycle()
    val showProductEditDialog by viewModel.showProductEditDialog.collectAsStateWithLifecycle()
    val quoteForm by viewModel.quoteForm.collectAsStateWithLifecycle()
    val currentUser by viewModel.currentUser.collectAsStateWithLifecycle()

    // Listen for toasts/events
    LaunchedEffect(Unit) {
        viewModel.toastEvent.collectLatest { message ->
            snackbarHostState.showSnackbar(message)
        }
    }

    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val isWideScreen = maxWidth > 680.dp

        Scaffold(
            snackbarHost = { SnackbarHost(snackbarHostState) },
            topBar = {
                TopAppBar(
                    title = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = Color.White,
                                modifier = Modifier.size(36.dp)
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.img_vga_logo),
                                    contentDescription = "VGA Export Logo",
                                    modifier = Modifier.padding(2.dp)
                                )
                            }
                            Column {
                                Text(
                                    text = "VGA EXPORT",
                                    style = MaterialTheme.typography.titleMedium.copy(
                                        fontWeight = FontWeight.Black,
                                        letterSpacing = 0.8.sp
                                    ),
                                    color = Color.White
                                )
                                Text(
                                    text = "Fresh Quality. Global Reach.",
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        fontWeight = FontWeight.SemiBold
                                    ),
                                    color = Color(0xFFFFD166),
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        }
                    },
                    actions = {
                        // Direct WhatsApp action button
                        IconButton(
                            onClick = { ContactUtils.openWhatsApp(context) },
                            modifier = Modifier.testTag("topbar_whatsapp")
                        ) {
                            Icon(
                                imageVector = Icons.Default.Message,
                                contentDescription = "WhatsApp",
                                tint = Color(0xFF25D366)
                            )
                        }

                        // Direct Call action button
                        IconButton(
                            onClick = { ContactUtils.callBusiness(context) },
                            modifier = Modifier.testTag("topbar_call")
                        ) {
                            Icon(
                                imageVector = Icons.Default.Call,
                                contentDescription = "Call VGA Export",
                                tint = Color.White
                            )
                        }

                        // Admin Portal toggle button
                        IconButton(
                            onClick = {
                                viewModel.toggleAdminMode()
                                if (!isAdminMode) {
                                    viewModel.navigateTo(NavTab.ADMIN)
                                }
                            },
                            modifier = Modifier.testTag("topbar_admin_toggle")
                        ) {
                            BadgedBox(
                                badge = {
                                    if (isAdminMode) {
                                        Badge(containerColor = GoldenMangoTertiary) {
                                            Text("ON", fontSize = 9.sp, color = Color.White)
                                        }
                                    }
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.AdminPanelSettings,
                                    contentDescription = "Admin Mode",
                                    tint = if (isAdminMode) Color(0xFFFFD166) else Color.White.copy(alpha = 0.85f)
                                )
                            }
                        }

                        // User Account / Sign In button
                        IconButton(
                            onClick = { viewModel.navigateTo(NavTab.AUTH) },
                            modifier = Modifier.testTag("topbar_account")
                        ) {
                            if (currentUser != null) {
                                Surface(
                                    shape = CircleShape,
                                    color = GoldenMangoTertiary,
                                    modifier = Modifier.size(30.dp)
                                ) {
                                    Box(contentAlignment = Alignment.Center) {
                                        Text(
                                            text = currentUser?.name?.take(1)?.uppercase() ?: "U",
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 13.sp,
                                            color = Color.White
                                        )
                                    }
                                }
                            } else {
                                Icon(
                                    imageVector = Icons.Outlined.AccountCircle,
                                    contentDescription = "Sign In / Sign Up",
                                    tint = Color.White
                                )
                            }
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = ForestGreenPrimary,
                        titleContentColor = Color.White,
                        actionIconContentColor = Color.White
                    )
                )
            },
            bottomBar = {
                if (!isWideScreen) {
                    VgaBottomNavigation(
                        currentTab = currentTab,
                        isAdminMode = isAdminMode,
                        onTabSelect = { viewModel.navigateTo(it) }
                    )
                }
            }
        ) { innerPadding ->
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                // Tablet Navigation Rail
                if (isWideScreen) {
                    VgaNavigationRail(
                        currentTab = currentTab,
                        isAdminMode = isAdminMode,
                        onTabSelect = { viewModel.navigateTo(it) }
                    )
                }

                // Active Screen Content
                Box(modifier = Modifier.weight(1f).fillMaxHeight()) {
                    when (currentTab) {
                        NavTab.HOME -> HomeScreen(
                            onNavigateTab = { viewModel.navigateTo(it) },
                            onCategorySelected = { category ->
                                viewModel.setCategoryFilter(category)
                            }
                        )
                        NavTab.PRODUCTS -> ProductsScreen(
                            products = filteredProducts,
                            selectedCategory = selectedCategory,
                            onSelectCategory = { viewModel.setCategoryFilter(it) },
                            searchQuery = searchQuery,
                            onSearchQueryChange = { viewModel.setSearchQuery(it) },
                            onRequestQuote = { product -> viewModel.prefillQuoteForProduct(product) },
                            isAdminMode = isAdminMode,
                            onToggleAvailability = { viewModel.toggleProductAvailability(it) },
                            onEditProduct = { viewModel.openProductEditor(it) },
                            onDeleteProduct = { viewModel.deleteProduct(it) },
                            onAddNewProduct = { viewModel.openProductEditor(null) }
                        )
                        NavTab.EXPORT_TRANSPORT -> ExportTransportScreen(
                            onNavigateToQuote = { viewModel.navigateTo(NavTab.QUOTE) }
                        )
                        NavTab.ABOUT -> AboutScreen()
                        NavTab.CONTACT -> ContactScreen()
                        NavTab.QUOTE -> QuoteScreen(
                            quoteForm = quoteForm,
                            onUpdateForm = { viewModel.updateQuoteForm(it) },
                            onSubmitQuote = { onSuccess -> viewModel.submitQuoteForm(onSuccess) }
                        )
                        NavTab.ADMIN -> AdminScreen(
                            products = filteredProducts,
                            inquiries = allInquiries,
                            onToggleAvailability = { viewModel.toggleProductAvailability(it) },
                            onEditProduct = { viewModel.openProductEditor(it) },
                            onDeleteProduct = { viewModel.deleteProduct(it) },
                            onAddNewProduct = { viewModel.openProductEditor(null) },
                            onUpdateInquiryStatus = { id, st -> viewModel.updateInquiryStatus(id, st) },
                            onDeleteInquiry = { viewModel.deleteInquiry(it) }
                        )
                        NavTab.AUTH -> AuthScreen(
                            currentUser = currentUser,
                            onSignIn = { email, name, isGoogle ->
                                viewModel.signIn(email, name, isGoogle)
                            },
                            onSignUp = { name, email ->
                                viewModel.signUp(name, email)
                            },
                            onSignOut = {
                                viewModel.signOut()
                            },
                            onBackToHome = {
                                viewModel.navigateTo(NavTab.HOME)
                            }
                        )
                    }
                }
            }
        }
    }

    // Product Add / Edit Dialog
    if (showProductEditDialog) {
        ProductEditDialog(
            product = editingProduct,
            onDismiss = { viewModel.closeProductEditor() },
            onSave = { name, cat, desc, packing, variety, minOrder, isAvail, img ->
                viewModel.saveProduct(name, cat, desc, packing, variety, minOrder, isAvail, img)
            }
        )
    }
}

@Composable
fun VgaBottomNavigation(
    currentTab: NavTab,
    isAdminMode: Boolean,
    onTabSelect: (NavTab) -> Unit,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        modifier = modifier
            .windowInsetsPadding(WindowInsets.navigationBars)
            .testTag("bottom_nav_bar"),
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface,
        tonalElevation = 6.dp
    ) {
        NavigationBarItem(
            selected = currentTab == NavTab.HOME,
            onClick = { onTabSelect(NavTab.HOME) },
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
            label = { Text("Home", fontSize = 11.sp) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = ForestGreenPrimary,
                selectedTextColor = ForestGreenPrimary,
                indicatorColor = MaterialTheme.colorScheme.primaryContainer
            )
        )
        NavigationBarItem(
            selected = currentTab == NavTab.PRODUCTS,
            onClick = { onTabSelect(NavTab.PRODUCTS) },
            icon = { Icon(Icons.Default.Spa, contentDescription = "Products") },
            label = { Text("Products", fontSize = 11.sp) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = ForestGreenPrimary,
                selectedTextColor = ForestGreenPrimary,
                indicatorColor = MaterialTheme.colorScheme.primaryContainer
            )
        )
        NavigationBarItem(
            selected = currentTab == NavTab.EXPORT_TRANSPORT,
            onClick = { onTabSelect(NavTab.EXPORT_TRANSPORT) },
            icon = { Icon(Icons.Default.LocalShipping, contentDescription = "Export") },
            label = { Text("Export", fontSize = 11.sp) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = OceanBlueSecondary,
                selectedTextColor = OceanBlueSecondary,
                indicatorColor = MaterialTheme.colorScheme.secondaryContainer
            )
        )
        NavigationBarItem(
            selected = currentTab == NavTab.QUOTE,
            onClick = { onTabSelect(NavTab.QUOTE) },
            icon = { Icon(Icons.Default.RequestQuote, contentDescription = "Quote") },
            label = { Text("Quote", fontSize = 11.sp) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = OceanBlueSecondary,
                selectedTextColor = OceanBlueSecondary,
                indicatorColor = MaterialTheme.colorScheme.secondaryContainer
            )
        )
        NavigationBarItem(
            selected = currentTab == NavTab.CONTACT,
            onClick = { onTabSelect(NavTab.CONTACT) },
            icon = { Icon(Icons.Default.Phone, contentDescription = "Contact") },
            label = { Text("Contact", fontSize = 11.sp) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = ForestGreenPrimary,
                selectedTextColor = ForestGreenPrimary,
                indicatorColor = MaterialTheme.colorScheme.primaryContainer
            )
        )
        NavigationBarItem(
            selected = currentTab == NavTab.AUTH,
            onClick = { onTabSelect(NavTab.AUTH) },
            icon = { Icon(Icons.Default.AccountCircle, contentDescription = "Account") },
            label = { Text("Account", fontSize = 11.sp) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = ForestGreenPrimary,
                selectedTextColor = ForestGreenPrimary,
                indicatorColor = MaterialTheme.colorScheme.primaryContainer
            )
        )
        if (isAdminMode) {
            NavigationBarItem(
                selected = currentTab == NavTab.ADMIN,
                onClick = { onTabSelect(NavTab.ADMIN) },
                icon = { Icon(Icons.Default.AdminPanelSettings, contentDescription = "Admin") },
                label = { Text("Admin", fontSize = 11.sp) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = GoldenMangoTertiary,
                    selectedTextColor = GoldenMangoTertiary,
                    indicatorColor = MaterialTheme.colorScheme.tertiaryContainer
                )
            )
        }
    }
}

@Composable
fun VgaNavigationRail(
    currentTab: NavTab,
    isAdminMode: Boolean,
    onTabSelect: (NavTab) -> Unit,
    modifier: Modifier = Modifier
) {
    NavigationRail(
        modifier = modifier.testTag("navigation_rail"),
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface
    ) {
        Spacer(modifier = Modifier.height(12.dp))
        NavigationRailItem(
            selected = currentTab == NavTab.HOME,
            onClick = { onTabSelect(NavTab.HOME) },
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
            label = { Text("Home") }
        )
        NavigationRailItem(
            selected = currentTab == NavTab.PRODUCTS,
            onClick = { onTabSelect(NavTab.PRODUCTS) },
            icon = { Icon(Icons.Default.Spa, contentDescription = "Products") },
            label = { Text("Products") }
        )
        NavigationRailItem(
            selected = currentTab == NavTab.EXPORT_TRANSPORT,
            onClick = { onTabSelect(NavTab.EXPORT_TRANSPORT) },
            icon = { Icon(Icons.Default.LocalShipping, contentDescription = "Export") },
            label = { Text("Export") }
        )
        NavigationRailItem(
            selected = currentTab == NavTab.ABOUT,
            onClick = { onTabSelect(NavTab.ABOUT) },
            icon = { Icon(Icons.Default.Info, contentDescription = "About") },
            label = { Text("About") }
        )
        NavigationRailItem(
            selected = currentTab == NavTab.CONTACT,
            onClick = { onTabSelect(NavTab.CONTACT) },
            icon = { Icon(Icons.Default.Phone, contentDescription = "Contact") },
            label = { Text("Contact") }
        )
        NavigationRailItem(
            selected = currentTab == NavTab.AUTH,
            onClick = { onTabSelect(NavTab.AUTH) },
            icon = { Icon(Icons.Default.AccountCircle, contentDescription = "Account") },
            label = { Text("Account") }
        )
        NavigationRailItem(
            selected = currentTab == NavTab.QUOTE,
            onClick = { onTabSelect(NavTab.QUOTE) },
            icon = { Icon(Icons.Default.RequestQuote, contentDescription = "Quote") },
            label = { Text("Quote") }
        )
        if (isAdminMode) {
            NavigationRailItem(
                selected = currentTab == NavTab.ADMIN,
                onClick = { onTabSelect(NavTab.ADMIN) },
                icon = { Icon(Icons.Default.AdminPanelSettings, contentDescription = "Admin") },
                label = { Text("Admin") }
            )
        }
    }
}
