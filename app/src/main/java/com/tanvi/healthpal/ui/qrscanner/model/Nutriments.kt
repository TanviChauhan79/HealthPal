package com.tanvi.healthpal.ui.qrscanner.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Nutriments(
    var id: String? = null,
    var userId: String? = null,
    var calcium: Double? = null,
    var calcium_100g: Double? = null,
    var calcium_serving: Double? = null,
    var calcium_unit: String? = null,
    var calcium_varue: Double? = null,
    var carbohydrates: Double? = null,
    var carbohydrates_100g: Double? = null,
    var carbohydrates_serving: Double? = null,
    var carbohydrates_unit: String? = null,
    var carbohydrates_varue: Double? = null,
    var cholesterol: Double? = null,
    var cholesterol_100g: Double? = null,
    var cholesterol_serving: Double? = null,
    var cholesterol_unit: String? = null,
    var cholesterol_varue: Double? = null,
    var energy: Double? = null,
    var energy_kcal: Double? = null,
    @SerializedName("energy-kcal_100g")
    var energy_kcal_100g: Double? = null,
    @SerializedName("energy-kcal_serving")
    var energy_kcal_serving: Double? = null,
    @SerializedName("energy-kcal_unit")
    var energy_kcal_unit: String? = null,
    @SerializedName("energy-kcal_varue")
    var energy_kcal_varue: Double? = null,
    var energy_100g: Double? = null,
    var energy_serving: Double? = null,
    var energy_unit: String? = null,
    var energy_varue: Double? = null,
    var fat: Double? = null,
    var fat_100g: Double? = null,
    var fat_serving: Double? = null,
    var fat_unit: String? = null,
    var fat_varue: Double? = null,
    var fiber: Double? = null,
    var fiber_100g: Double? = null,
    var fiber_serving: Double? = null,
    var fiber_unit: String? = null,
    var fiber_varue: Double? = null,
    var fruits_vegetables_nuts_estimate_from_ingredients_100g: Double? = null,
    var iron: Double? = null,
    var iron_100g: Double? = null,
    var iron_serving: Double? = null,
    var iron_unit: String? = null,
    var iron_varue: Double? = null,
    var nova_group: Double? = null,
    var nova_group_100g: Double? = null,
    var nova_group_serving: Double? = null,
    var nutrition_score_fr: Double? = null,
    var nutrition_score_fr_100g: Double? = null,
    var proteins: Double? = null,
    var proteins_100g: Double? = null,
    var proteins_serving: Double? = null,
    var proteins_unit: String? = null,
    var proteins_varue: Double? = null,
    var salt: Double? = null,
    var salt_100g: Double? = null,
    var salt_serving: Double? = null,
    var salt_unit: String? = null,
    var salt_varue: Double? = null,
    var saturated_fat: Double? = null,
    @SerializedName("saturated-fat_100g")
    var saturated_fat_100g: Double? = null,
    @SerializedName("saturated-fat_serving")
    var saturated_fat_serving: Double? = null,
    @SerializedName("saturated-fat_unit")
    var saturated_fat_unit: String? = null,
    @SerializedName("saturated-fat_varue")
    var saturated_fat_varue: Double? = null,
    var sodium: Double? = null,
    var sodium_100g: Double? = null,
    var sodium_serving: Double? = null,
    var sodium_unit: String? = null,
    var sodium_varue: Double? = null,
    var sugars: Double? = null,
    var sugars_100g: Double? = null,
    var sugars_serving: Double? = null,
    var sugars_unit: String? = null,
    var sugars_varue: Double? = null,
    var trans_fat: Double? = null,
    @SerializedName("trans-fat_100g")
    var trans_fat_100g: Double? = null,
    @SerializedName("trans-fat_serving")
    var trans_fat_serving: Double? = null,
    @SerializedName("trans-fat_unit")
    var trans_fat_unit: String? = null,
    @SerializedName("trans-fat_varue")
    var trans_fat_varue: Double? = null,
    var vitamin_a: Double? = null,
    @SerializedName("vitamin-a_100g")
    var vitamin_a_100g: Double? = null,
    @SerializedName("vitamin-a_serving")
    var vitamin_a_serving: Double? = null,
    @SerializedName("vitamin-a_unit")
    var vitamin_a_unit: String? = null,
    @SerializedName("vitamin-a_varue")
    var vitamin_a_varue: Double? = null,
    var vitamin_c: Double? = null,
    @SerializedName("vitamin-c_100g")
    var vitamin_c_100g: Double? = null,
    @SerializedName("vitamin-c_serving")
    var vitamin_c_serving: Double? = null,
    @SerializedName("vitamin-c_unit")
    var vitamin_c_unit: String? = null,
    @SerializedName("vitamin-c_varue")
    var vitamin_c_varue: Double? = null,
    var product_image: String? = "",
    var product_name: String? = "",
    var isChecked: Boolean = false
): Parcelable