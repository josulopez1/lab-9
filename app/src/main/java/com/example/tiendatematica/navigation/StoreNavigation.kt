package com.example.tiendatematica.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.tiendatematica.model.StoreUiState
import com.example.tiendatematica.ui.screens.CatalogScreen
import com.example.tiendatematica.ui.screens.DetailScreen
import com.example.tiendatematica.ui.screens.ProfileScreen

@Composable
fun StoreNavigation(
    uiState: StoreUiState,
    onToggleFavorite: (String) -> Unit
) {
    val backStack = rememberNavBackStack(StoreNavKey.Catalog)

    fun goBack() {
        if (backStack.size > 1) {
            backStack.removeLastOrNull()
        }
    }

    BackHandler(enabled = backStack.size > 1) {
        goBack()
    }

    NavDisplay(
        backStack = backStack,
        onBack = {
            goBack()
        },
        entryProvider = entryProvider {
            entry<StoreNavKey.Catalog> {
                CatalogScreen(
                    products = uiState.products,
                    favoriteProductIds = uiState.favoriteProductIds,
                    onProductSelected = { productId ->
                        backStack.add(StoreNavKey.Detail(productId))
                    },
                    onFavoriteClick = onToggleFavorite
                )
            }

            entry<StoreNavKey.Detail> { route ->
                val product = uiState.products.find {
                    it.id == route.productId
                }

                if (product != null) {
                    val profile = uiState.profiles.find {
                        it.id == product.profileId
                    }

                    DetailScreen(
                        product = product,
                        profile = profile,
                        isFavorite = product.id in uiState.favoriteProductIds,
                        onFavoriteClick = {
                            onToggleFavorite(product.id)
                        },
                        onProfileClick = { profileId ->
                            backStack.add(StoreNavKey.Profile(profileId))
                        },
                        onBack = {
                            goBack()
                        }
                    )
                }
            }

            entry<StoreNavKey.Profile> { route ->
                val profile = uiState.profiles.find {
                    it.id == route.profileId
                }

                if (profile != null) {
                    ProfileScreen(
                        profile = profile,
                        onBack = {
                            goBack()
                        }
                    )
                }
            }
        }
    )
}
