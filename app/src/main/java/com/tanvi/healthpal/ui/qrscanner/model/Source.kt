package com.tanvi.healthpal.ui.qrscanner.model

data class Source(
    val fields: List<String>,
    val id: String,
    val images: List<Any>,
    val import_t: Int,
    val manufacturer: Any,
    val name: String,
    val url: String
)