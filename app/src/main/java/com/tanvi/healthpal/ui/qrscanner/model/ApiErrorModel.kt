package com.tanvi.healthpal.ui.qrscanner.model

data class ApiErrorModel @JvmOverloads constructor (
    var message: String = "",
    @JvmField
    val status: String = ""
)
