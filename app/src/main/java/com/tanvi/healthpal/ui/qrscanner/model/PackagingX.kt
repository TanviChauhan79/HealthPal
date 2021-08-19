package com.tanvi.healthpal.ui.qrscanner.model

data class PackagingX(
    val ecoscore_material_score: Int,
    val ecoscore_shape_ratio: Int,
    val material: String,
    val non_recyclable_and_non_biodegradable: String,
    val shape: String
)