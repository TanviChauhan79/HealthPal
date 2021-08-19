package com.tanvi.healthpal.ui.qrscanner.model

data class Packaging(
    val non_recyclable_and_non_biodegradable_materials: Int,
    val packagings: List<PackagingX>,
    val score: Int,
    val value: Int,
    val warning: String
)