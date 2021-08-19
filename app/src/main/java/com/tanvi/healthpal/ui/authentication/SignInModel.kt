package com.tanvi.healthpal.ui.authentication

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SignInModel(
    val password: String,
    val emailId: String
): Parcelable
