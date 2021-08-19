package com.tanvi.healthpal.ui.qrscanner.model

data class ResGetNutritionalInfoFromBarcodeModel(
    val code: String,
    val product: Product?,
    val status: Int,
    val status_verbose: String
)