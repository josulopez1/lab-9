package com.example.tiendatematica

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tiendatematica.navigation.StoreNavigation
import com.example.tiendatematica.ui.theme.TiendaTematicaTheme
import com.example.tiendatematica.viewmodel.StoreViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            TiendaTematicaTheme {
                StoreApp()
            }
        }
    }
}

@Composable
private fun StoreApp(
    storeViewModel: StoreViewModel = viewModel()
) {
    val uiState = storeViewModel.uiState.collectAsStateWithLifecycle()

    StoreNavigation(
        uiState = uiState.value,
        onToggleFavorite = storeViewModel::toggleFavorite
    )
}
