package com.example.tiendatematica.model

data class Product(
    val id: String,
    val name: String,
    val description: String,
    val price: Double,
    val profileId: String,
    val technicalDetails: String
)

data class AssociatedProfile(
    val id: String,
    val name: String,
    val role: String,
    val location: String,
    val description: String
)

data class StoreUiState(
    val products: List<Product> = emptyList(),
    val profiles: List<AssociatedProfile> = emptyList(),
    val favoriteProductIds: Set<String> = emptySet()
)
