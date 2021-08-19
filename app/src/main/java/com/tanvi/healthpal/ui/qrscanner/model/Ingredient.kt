package com.tanvi.healthpal.ui.qrscanner.model

data class Ingredient(
    val has_sub_ingredients: String,
    val id: String,
    val percent_estimate: Double,
    val percent_max: Double,
    val percent_min: Double,
    val processing: String,
    val rank: Double,
    val text: String,
    val vegan: String,
    val vegetarian: String
)