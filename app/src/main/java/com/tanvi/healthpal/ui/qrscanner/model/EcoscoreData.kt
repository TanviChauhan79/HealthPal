package com.tanvi.healthpal.ui.qrscanner.model

data class EcoscoreData(
    val adjustments: Adjustments,
    val agribalyse: Agribalyse,
    val missing: Missing,
    val missing_agribalyse_match_warning: Int,
    val status: String
)