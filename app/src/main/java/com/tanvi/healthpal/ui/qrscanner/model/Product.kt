package com.tanvi.healthpal.ui.qrscanner.model

data class Product(
    val _id: String? = null,
    val _keywords: List<String>? = null,
    val added_countries_tags: List<Any>? = null,
    val additives_debug_tags: List<Any>? = null,
    val additives_n: Double? = null,
    val additives_old_n: Double? = null,
    val additives_old_tags: List<String>? = null,
    val additives_original_tags: List<String>? = null,
    val additives_prev_original_tags: List<String>? = null,
    val additives_tags: List<String>? = null,
    val additives_tags_n: Any? = null,
    val allergens: String? = null,
    val allergens_from_ingredients: String? = null,
    val allergens_from_user: String? = null,
    val allergens_hierarchy: List<Any>? = null,
    val allergens_tags: List<Any>? = null,
    val amino_acids_prev_tags: List<Any>? = null,
    val amino_acids_tags: List<Any>? = null,
    val brand_owner: String? = null,
    val brand_owner_imported: String? = null,
    val brands: String? = null,
    val brands_debug_tags: List<Any>? = null,
    val brands_tags: List<String>? = null,
    val categories: String? = null,
    val categories_hierarchy: List<String>? = null,
    val categories_imported: String? = null,
    val categories_lc: String? = null,
    val categories_old: String? = null,
    val categories_properties: CategoriesProperties? = null,
    val categories_properties_tags: List<String>? = null,
    val categories_tags: List<String>? = null,
    val category_properties: CategoryProperties? = null,
    val checkers: List<Any>? = null,
    val checkers_tags: List<Any>? = null,
    val ciqual_food_name_tags: List<String>? = null,
    val cities_tags: List<Any>? = null,
    val code: String? = null,
    val codes_tags: List<String>? = null,
    val compared_to_category: String? = null,
    val complete: Double? = null,
    val completed_t: Double? = null,
    val completeness: Double? = null,
    val correctors: List<String>? = null,
    val correctors_tags: List<String>? = null,
    val countries: String? = null,
    val countries_debug_tags: List<Any>? = null,
    val countries_hierarchy: List<String>? = null,
    val countries_imported: String? = null,
    val countries_lc: String? = null,
    val countries_tags: List<String>? = null,
    val created_t: Double? = null,
    val creator: String? = null,
    val data_quality_bugs_tags: List<Any>? = null,
    val data_quality_errors_tags: List<Any>? = null,
    val data_quality_info_tags: List<String>? = null,
    val data_quality_tags: List<String>? = null,
    val data_quality_warnings_tags: List<String>? = null,
    val data_sources: String? = null,
    val data_sources_imported: String? = null,
    val data_sources_tags: List<String>? = null,
    val debug_param_sorted_langs: List<String>? = null,
    val ecoscore_data: EcoscoreData? = null,
    val ecoscore_grade: String? = null,
    val ecoscore_tags: List<String>? = null,
    val editors: List<String>? = null,
    val editors_tags: List<String>? = null,
    val emb_codes: String? = null,
    val emb_codes_20141016: String? = null,
    val emb_codes_debug_tags: List<Any>? = null,
    val emb_codes_orig: String? = null,
    val emb_codes_tags: List<Any>? = null,
    val entry_dates_tags: List<String>? = null,
    val expiration_date: String? = null,
    val expiration_date_debug_tags: List<Any>? = null,
    val fruits_vegetables_nuts_100g_estimate: Double? = null,
    val generic_name: String? = null,
    val generic_name_en: String? = null,
    val generic_name_en_debug_tags: List<Any>? = null,
    val id: String? = null,
    val image_front_small_url: String? = null,
    val image_front_thumb_url: String? = null,
    val image_front_url: String? = null,
    val image_ingredients_small_url: String? = null,
    val image_ingredients_thumb_url: String? = null,
    val image_ingredients_url: String? = null,
    val image_nutrition_small_url: String? = null,
    val image_nutrition_thumb_url: String? = null,
    val image_nutrition_url: String? = null,
    val image_small_url: String? = null,
    val image_thumb_url: String? = null,
    val image_url: String? = null,
    val images: Images? = null,
    val informers: List<String>? = null,
    val informers_tags: List<String>? = null,
    val ingredients: List<Ingredient>? = null,
    val ingredients_analysis_tags: List<String>? = null,
    val ingredients_debug: List<Any>? = null,
    val ingredients_from_or_that_may_be_from_palm_oil_n: Double? = null,
    val ingredients_from_palm_oil_n: Double? = null,
    val ingredients_from_palm_oil_tags: List<Any>? = null,
    val ingredients_hierarchy: List<String>? = null,
    val ingredients_ids_debug: List<String>? = null,
    val ingredients_n: Double? = null,
    val ingredients_n_tags: List<String>? = null,
    val ingredients_original_tags: List<String>? = null,
    val ingredients_percent_analysis: Double? = null,
    val ingredients_tags: List<String>? = null,
    val ingredients_text: String? = null,
    val ingredients_text_debug: String? = null,
    val ingredients_text_en: String? = null,
    val ingredients_text_en_debug_tags: List<Any>? = null,
    val ingredients_text_en_imported: String? = null,
    val ingredients_text_with_allergens: String? = null,
    val ingredients_text_with_allergens_en: String? = null,
    val ingredients_that_may_be_from_palm_oil_n: Double? = null,
    val ingredients_that_may_be_from_palm_oil_tags: List<Any>? = null,
    val interface_version_created: String? = null,
    val interface_version_modified: String? = null,
    val known_ingredients_n: Double? = null,
    val labels: String? = null,
    val labels_hierarchy: List<String>? = null,
    val labels_lc: String? = null,
    val labels_old: String? = null,
    val labels_tags: List<String>? = null,
    val lang: String? = null,
    val lang_debug_tags: List<Any>? = null,
    val languages: Languages? = null,
    val languages_codes: LanguagesCodes? = null,
    val languages_hierarchy: List<String>? = null,
    val languages_tags: List<String>? = null,
    val last_edit_dates_tags: List<String>? = null,
    val last_editor: String? = null,
    val last_image_dates_tags: List<String>? = null,
    val last_image_t: Double? = null,
    val last_modified_by: String? = null,
    val last_modified_t: Double? = null,
    val lc: String? = null,
    val lc_imported: String? = null,
    val link: String? = null,
    val link_debug_tags: List<Any>? = null,
    val main_countries_tags: List<Any>? = null,
    val manufacturing_places: String? = null,
    val manufacturing_places_debug_tags: List<Any>? = null,
    val manufacturing_places_tags: List<Any>? = null,
    val max_imgid: String? = null,
    val minerals_prev_tags: List<Any>? = null,
    val minerals_tags: List<Any>? = null,
    val misc_tags: List<String>? = null,
    val new_additives_n: Double? = null,
    val no_nutrition_data: String? = null,
    val nova_group: Double? = null,
    val nova_group_debug: String? = null,
    val nova_groups: String? = null,
    val nova_groups_tags: List<String>? = null,
    val nucleotides_prev_tags: List<Any>? = null,
    val nucleotides_tags: List<Any>? = null,
    val nutrient_levels: NutrientLevels? = null,
    val nutrient_levels_tags: List<String>? = null,
    val nutriments: Nutriments? = null,
    val nutriscore_data: NutriscoreData? = null,
    val nutriscore_grade: String? = null,
    val nutriscore_score: Double? = null,
    val nutriscore_score_opposite: Double? = null,
    val nutrition_data: String? = null,
    val nutrition_data_per: String? = null,
    val nutrition_data_per_debug_tags: List<Any>? = null,
    val nutrition_data_per_imported: String? = null,
    val nutrition_data_prepared: String? = null,
    val nutrition_data_prepared_per: String? = null,
    val nutrition_data_prepared_per_debug_tags: List<Any>? = null,
    val nutrition_data_prepared_per_imported: String? = null,
    val nutrition_grade_fr: String? = null,
    val nutrition_grades: String? = null,
    val nutrition_grades_tags: List<String>? = null,
    val nutrition_score_beverage: Double? = null,
    val nutrition_score_warning_fruits_vegetables_nuts_estimate_from_ingredients: Double? = null,
    val nutrition_score_warning_fruits_vegetables_nuts_estimate_from_ingredients_value: Double? = null,
    val origins: String? = null,
    val origins_hierarchy: List<String>? = null,
    val origins_lc: String? = null,
    val origins_old: String? = null,
    val origins_tags: List<String>? = null,
    val other_nutritional_substances_tags: List<Any>? = null,
    val packaging: String? = null,
    val packaging_debug_tags: List<Any>? = null,
    val packaging_tags: List<String>? = null,
    val packagings: List<PackagingXX>? = null,
    val photographers: List<String>? = null,
    val photographers_tags: List<String>? = null,
    val pnns_groups_1: String? = null,
    val pnns_groups_1_tags: List<String>? = null,
    val pnns_groups_2: String? = null,
    val pnns_groups_2_tags: List<String>? = null,
    val popularity_key: Long? = null,
    val popularity_tags: List<String>? = null,
    val product_name: String? = null,
    val product_name_en: String? = null,
    val product_name_en_debug_tags: List<Any>? = null,
    val product_name_en_imported: String? = null,
    val product_quantity: Double? = null,
    val purchase_places: String? = null,
    val purchase_places_debug_tags: List<Any>? = null,
    val purchase_places_tags: List<Any>? = null,
    val quantity: String? = null,
    val quantity_debug_tags: List<Any>? = null,
    val removed_countries_tags: List<Any>? = null,
    val rev: Double? = null,
    val scans_n: Double? = null,
    val selected_images: SelectedImages? = null,
    val serving_quantity: Double? = null,
    val serving_size: String? = null,
    val serving_size_debug_tags: List<Any>? = null,
    val serving_size_imported: String? = null,
    val sortkey: Double? = null,
    val sources: List<Source>? = null,
    val sources_fields: SourcesFields? = null,
    val states: String? = null,
    val states_hierarchy: List<String>? = null,
    val states_tags: List<String>? = null,
    val stores: String? = null,
    val stores_debug_tags: List<Any>? = null,
    val stores_tags: List<Any>? = null,
    val traces: String? = null,
    val traces_debug_tags: List<Any>? = null,
    val traces_from_ingredients: String? = null,
    val traces_from_user: String? = null,
    val traces_hierarchy: List<String>? = null,
    val traces_tags: List<String>? = null,
    val unique_scans_n: Double? = null,
    val unknown_ingredients_n: Double? = null,
    val unknown_nutrients_tags: List<Any>? = null,
    val update_key: String? = null,
    val vitamins_prev_tags: List<Any>? = null,
    val vitamins_tags: List<Any>
)