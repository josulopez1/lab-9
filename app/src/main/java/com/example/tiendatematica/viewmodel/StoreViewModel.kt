package com.example.tiendatematica.viewmodel

import androidx.lifecycle.ViewModel
import com.example.tiendatematica.model.AssociatedProfile
import com.example.tiendatematica.model.Product
import com.example.tiendatematica.model.StoreUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class StoreViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(
        StoreUiState(
            products = listOf(
                Product(
                    id = "keyboard",
                    name = "Teclado Nova 75",
                    description = "Teclado mecánico compacto e inalámbrico.",
                    price = 749.99,
                    profileId = "nova",
                    technicalDetails = """
                        Switches mecánicos lineales
                        Conexión USB-C y Bluetooth
                        Iluminación RGB
                        Batería de hasta 80 horas
                    """.trimIndent()
                ),
                Product(
                    id = "mouse",
                    name = "Mouse Pulse X",
                    description = "Mouse ultraligero para videojuegos competitivos.",
                    price = 429.99,
                    profileId = "pulse",
                    technicalDetails = """
                        Sensor óptico de 26,000 DPI
                        Peso de 59 gramos
                        Seis botones programables
                        Conexión inalámbrica
                    """.trimIndent()
                ),
                Product(
                    id = "headphones",
                    name = "Audífonos Nova Air",
                    description = "Audífonos con sonido envolvente y micrófono.",
                    price = 599.99,
                    profileId = "nova",
                    technicalDetails = """
                        Sonido envolvente virtual
                        Micrófono desmontable
                        Almohadillas de espuma
                        Batería de hasta 40 horas
                    """.trimIndent()
                )
            ),
            profiles = listOf(
                AssociatedProfile(
                    id = "nova",
                    name = "Nova Technologies",
                    role = "Fabricante de periféricos",
                    location = "Ciudad de Guatemala",
                    description = "Empresa guatemalteca dedicada al diseño de accesorios tecnológicos."
                ),
                AssociatedProfile(
                    id = "pulse",
                    name = "Pulse Gaming",
                    role = "Marca especializada en gaming",
                    location = "Antigua Guatemala",
                    description = "Marca enfocada en periféricos para jugadores competitivos."
                )
            )
        )
    )

    val uiState: StateFlow<StoreUiState> = _uiState.asStateFlow()

    fun toggleFavorite(productId: String) {
        _uiState.update { currentState ->
            val updatedFavorites =
                if (productId in currentState.favoriteProductIds) {
                    currentState.favoriteProductIds - productId
                } else {
                    currentState.favoriteProductIds + productId
                }

            currentState.copy(
                favoriteProductIds = updatedFavorites
            )
        }
    }
}
