package com.tanvi.healthpal.ui.scanqr.apis.entity.elements;

import com.google.gson.annotations.SerializedName;

public class Nutrients {
    private String id;
    private Double calcium;
    private Double calcium_100g;
    private Double calcium_serving;
    private String calcium_unit;
    private Double calcium_value;
    private Double carbohydrates;
    private Double carbohydrates_100g;
    private Double carbohydrates_serving;
    private String carbohydrates_unit;
    private Double carbohydrates_value;
    private Double cholesterol;
    private Double cholesterol_100g;
    private Double cholesterol_serving;
    private String cholesterol_unit;
    private Double cholesterol_value;
    private Double energy;
    private Double energy_kcal;
    @SerializedName("energy-kcal_100g")
    private Double energy_kcal_100g;
    @SerializedName("energy-kcal_serving")
    private Double energy_kcal_serving;
    @SerializedName("energy-kcal_unit")
    private String energy_kcal_unit;
    @SerializedName("energy-kcal_value")
    private Double energy_kcal_value;
    private Double energy_100g;
    private Double energy_serving;
    private String energy_unit;
    private Double energy_value;
    private Double fat;
    private Double fat_100g;
    private Double fat_serving;
    private String fat_unit;
    private Double fat_value;
    private Double fiber;
    private Double fiber_100g;
    private Double fiber_serving;
    private String fiber_unit;
    private Double fiber_value;
    private Double fruits_vegetables_nuts_estimate_from_ingredients_100g;
    private Double iron;
    private Double iron_100;
    private Double iron_serving;
    private String iron_unit;
    private Double iron_value;
    private Double nova_group;
    private Double nova_group_100g;
    private Double nova_group_serving;
    private Double nutrition_score_fr;
    private Double nutrition_score_fr_100g;
    private Double proteins;
    private Double proteins_100g;
    private Double proteins_serving;
    private String proteins_unit;
    private Double proteins_value;
    private Double salt;
    private Double salt_100g;
    private Double salt_serving;
    private String salt_unit;
    private Double salt_value;
    private Double saturated_fat;
    @SerializedName("saturated-fat_100g")
    private Double saturated_fat_100g;
    @SerializedName("saturated-fat_serving")
    private Double saturated_fat_serving;
    @SerializedName("saturated-fat_unit")
    private String saturated_fat_unit;
    @SerializedName("saturated-fat_value")
    private Double saturated_fat_value;
    private Double sodium;
    private Double sodium_100g;
    private Double sodium_serving;
    private String sodium_unit;
    private Double sodium_value;
    private Double sugars;
    private Double sugars_100g;
    private Double sugars_serving;
    private String sugars_unit;
    private Double sugars_value;
    private Double trans_fat;
    @SerializedName("trans-fat_100g")
    private Double trans_fat_100g;
    @SerializedName("trans-fat_serving")
    private Double trans_fat_serving;
    @SerializedName("trans-fat_unit")
    private String trans_fat_unit;
    @SerializedName("trans-fat_value")
    private Double trans_fat_value;
    private Double vitamin_a;
    @SerializedName("vitamin-a_100g")
    private Double vitamin_a_100g;
    @SerializedName("vitamin-a_serving")
    private Double vitamin_a_serving;
    @SerializedName("vitamin-a_unit")
    private String vitamin_a_unit;
    @SerializedName("vitamin-a_value")
    private Double vitamin_a_value;
    private Double vitamin_c;
    @SerializedName("vitamin-c_100g")
    private Double vitamin_c_100g;
    @SerializedName("vitamin-c_serving")
    private Double vitamin_c_serving;
    @SerializedName("vitamin-c_unit")
    private String vitamin_c_unit;
    @SerializedName("vitamin-c_value")
    private Double vitamin_c_value;
    private String product_name;
    private String product_iamge;
    private Boolean isChecked;


    public Double getCalcium() {
        return calcium;
    }

    public void setCalcium(Double calcium) {
        this.calcium = calcium;
    }

    public Double getCalcium_100g() {
        return calcium_100g;
    }

    public void setCalcium_100g(Double calcium_100g) {
        this.calcium_100g = calcium_100g;
    }

    public Double getCalcium_serving() {
        return calcium_serving;
    }

    public void setCalcium_serving(Double calcium_serving) {
        this.calcium_serving = calcium_serving;
    }

    public String getCalcium_unit() {
        return calcium_unit;
    }

    public void setCalcium_unit(String calcium_unit) {
        this.calcium_unit = calcium_unit;
    }

    public Double getCalcium_value() {
        return calcium_value;
    }

    public void setCalcium_value(Double calcium_value) {
        this.calcium_value = calcium_value;
    }

    public Double getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(Double carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public Double getCarbohydrates_100g() {
        return carbohydrates_100g;
    }

    public void setCarbohydrates_100g(Double carbohydrates_100g) {
        this.carbohydrates_100g = carbohydrates_100g;
    }

    public Double getCarbohydrates_serving() {
        return carbohydrates_serving;
    }

    public void setCarbohydrates_serving(Double carbohydrates_serving) {
        this.carbohydrates_serving = carbohydrates_serving;
    }

    public String getCarbohydrates_unit() {
        return carbohydrates_unit;
    }

    public void setCarbohydrates_unit(String carbohydrates_unit) {
        this.carbohydrates_unit = carbohydrates_unit;
    }

    public Double getCarbohydrates_value() {
        return carbohydrates_value;
    }

    public void setCarbohydrates_value(Double carbohydrates_value) {
        this.carbohydrates_value = carbohydrates_value;
    }

    public Double getCholesterol() {
        return cholesterol;
    }

    public void setCholesterol(Double cholesterol) {
        this.cholesterol = cholesterol;
    }

    public Double getCholesterol_100g() {
        return cholesterol_100g;
    }

    public void setCholesterol_100g(Double cholesterol_100g) {
        this.cholesterol_100g = cholesterol_100g;
    }

    public Double getCholesterol_serving() {
        return cholesterol_serving;
    }

    public void setCholesterol_serving(Double cholesterol_serving) {
        this.cholesterol_serving = cholesterol_serving;
    }

    public String getCholesterol_unit() {
        return cholesterol_unit;
    }

    public void setCholesterol_unit(String cholesterol_unit) {
        this.cholesterol_unit = cholesterol_unit;
    }

    public Double getCholesterol_value() {
        return cholesterol_value;
    }

    public void setCholesterol_value(Double cholesterol_value) {
        this.cholesterol_value = cholesterol_value;
    }

    public Double getEnergy() {
        return energy;
    }

    public void setEnergy(Double energy) {
        this.energy = energy;
    }

    public Double getEnergy_kcal() {
        return energy_kcal;
    }

    public void setEnergy_kcal(Double energy_kcal) {
        this.energy_kcal = energy_kcal;
    }

    public Double getEnergy_kcal_100g() {
        return energy_kcal_100g;
    }

    public void setEnergy_kcal_100g(Double energy_kcal_100g) {
        this.energy_kcal_100g = energy_kcal_100g;
    }

    public Double getEnergy_kcal_serving() {
        return energy_kcal_serving;
    }

    public void setEnergy_kcal_serving(Double energy_kcal_serving) {
        this.energy_kcal_serving = energy_kcal_serving;
    }

    public String getEnergy_kcal_unit() {
        return energy_kcal_unit;
    }

    public void setEnergy_kcal_unit(String energy_kcal_unit) {
        this.energy_kcal_unit = energy_kcal_unit;
    }

    public Double getEnergy_kcal_value() {
        return energy_kcal_value;
    }

    public void setEnergy_kcal_value(Double energy_kcal_value) {
        this.energy_kcal_value = energy_kcal_value;
    }

    public Double getEnergy_100g() {
        return energy_100g;
    }

    public void setEnergy_100g(Double energy_100g) {
        this.energy_100g = energy_100g;
    }

    public Double getEnergy_serving() {
        return energy_serving;
    }

    public void setEnergy_serving(Double energy_serving) {
        this.energy_serving = energy_serving;
    }

    public String getEnergy_unit() {
        return energy_unit;
    }

    public void setEnergy_unit(String energy_unit) {
        this.energy_unit = energy_unit;
    }

    public Double getEnergy_value() {
        return energy_value;
    }

    public void setEnergy_value(Double energy_value) {
        this.energy_value = energy_value;
    }

    public Double getFat() {
        return fat;
    }

    public void setFat(Double fat) {
        this.fat = fat;
    }

    public Double getFat_100g() {
        return fat_100g;
    }

    public void setFat_100g(Double fat_100g) {
        this.fat_100g = fat_100g;
    }

    public Double getFat_serving() {
        return fat_serving;
    }

    public void setFat_serving(Double fat_serving) {
        this.fat_serving = fat_serving;
    }

    public String getFat_unit() {
        return fat_unit;
    }

    public void setFat_unit(String fat_unit) {
        this.fat_unit = fat_unit;
    }

    public Double getFat_value() {
        return fat_value;
    }

    public void setFat_value(Double fat_value) {
        this.fat_value = fat_value;
    }

    public Double getFiber() {
        return fiber;
    }

    public void setFiber(Double fiber) {
        this.fiber = fiber;
    }

    public Double getFiber_100g() {
        return fiber_100g;
    }

    public void setFiber_100g(Double fiber_100g) {
        this.fiber_100g = fiber_100g;
    }

    public Double getFiber_serving() {
        return fiber_serving;
    }

    public void setFiber_serving(Double fiber_serving) {
        this.fiber_serving = fiber_serving;
    }

    public String getFiber_unit() {
        return fiber_unit;
    }

    public void setFiber_unit(String fiber_unit) {
        this.fiber_unit = fiber_unit;
    }

    public Double getFiber_value() {
        return fiber_value;
    }

    public void setFiber_value(Double fiber_value) {
        this.fiber_value = fiber_value;
    }

    public Double getFruits_vegetables_nuts_estimate_from_ingredients_100g() {
        return fruits_vegetables_nuts_estimate_from_ingredients_100g;
    }

    public void setFruits_vegetables_nuts_estimate_from_ingredients_100g(Double fruits_vegetables_nuts_estimate_from_ingredients_100g) {
        this.fruits_vegetables_nuts_estimate_from_ingredients_100g = fruits_vegetables_nuts_estimate_from_ingredients_100g;
    }

    public Double getIron() {
        return iron;
    }

    public void setIron(Double iron) {
        this.iron = iron;
    }

    public Double getIron_100() {
        return iron_100;
    }

    public void setIron_100(Double iron_100) {
        this.iron_100 = iron_100;
    }

    public Double getIron_serving() {
        return iron_serving;
    }

    public void setIron_serving(Double iron_serving) {
        this.iron_serving = iron_serving;
    }

    public String getIron_unit() {
        return iron_unit;
    }

    public void setIron_unit(String iron_unit) {
        this.iron_unit = iron_unit;
    }

    public Double getIron_value() {
        return iron_value;
    }

    public void setIron_value(Double iron_value) {
        this.iron_value = iron_value;
    }

    public Double getNova_group() {
        return nova_group;
    }

    public void setNova_group(Double nova_group) {
        this.nova_group = nova_group;
    }

    public Double getNova_group_100g() {
        return nova_group_100g;
    }

    public void setNova_group_100g(Double nova_group_100g) {
        this.nova_group_100g = nova_group_100g;
    }

    public Double getNova_group_serving() {
        return nova_group_serving;
    }

    public void setNova_group_serving(Double nova_group_serving) {
        this.nova_group_serving = nova_group_serving;
    }

    public Double getNutrition_score_fr() {
        return nutrition_score_fr;
    }

    public void setNutrition_score_fr(Double nutrition_score_fr) {
        this.nutrition_score_fr = nutrition_score_fr;
    }

    public Double getNutrition_score_fr_100g() {
        return nutrition_score_fr_100g;
    }

    public void setNutrition_score_fr_100g(Double nutrition_score_fr_100g) {
        this.nutrition_score_fr_100g = nutrition_score_fr_100g;
    }

    public Double getProteins() {
        return proteins;
    }

    public void setProteins(Double proteins) {
        this.proteins = proteins;
    }

    public Double getProteins_100g() {
        return proteins_100g;
    }

    public void setProteins_100g(Double proteins_100g) {
        this.proteins_100g = proteins_100g;
    }

    public Double getProteins_serving() {
        return proteins_serving;
    }

    public void setProteins_serving(Double proteins_serving) {
        this.proteins_serving = proteins_serving;
    }

    public String getProteins_unit() {
        return proteins_unit;
    }

    public void setProteins_unit(String proteins_unit) {
        this.proteins_unit = proteins_unit;
    }

    public Double getProteins_value() {
        return proteins_value;
    }

    public void setProteins_value(Double proteins_value) {
        this.proteins_value = proteins_value;
    }

    public Double getSalt() {
        return salt;
    }

    public void setSalt(Double salt) {
        this.salt = salt;
    }

    public Double getSalt_100g() {
        return salt_100g;
    }

    public void setSalt_100g(Double salt_100g) {
        this.salt_100g = salt_100g;
    }

    public Double getSalt_serving() {
        return salt_serving;
    }

    public void setSalt_serving(Double salt_serving) {
        this.salt_serving = salt_serving;
    }

    public String getSalt_unit() {
        return salt_unit;
    }

    public void setSalt_unit(String salt_unit) {
        this.salt_unit = salt_unit;
    }

    public Double getSalt_value() {
        return salt_value;
    }

    public void setSalt_value(Double salt_value) {
        this.salt_value = salt_value;
    }

    public Double getSaturated_fat() {
        return saturated_fat;
    }

    public void setSaturated_fat(Double saturated_fat) {
        this.saturated_fat = saturated_fat;
    }

    public Double getSaturated_fat_100g() {
        return saturated_fat_100g;
    }

    public void setSaturated_fat_100g(Double saturated_fat_100g) {
        this.saturated_fat_100g = saturated_fat_100g;
    }

    public Double getSaturated_fat_serving() {
        return saturated_fat_serving;
    }

    public void setSaturated_fat_serving(Double saturated_fat_serving) {
        this.saturated_fat_serving = saturated_fat_serving;
    }

    public String getSaturated_fat_unit() {
        return saturated_fat_unit;
    }

    public void setSaturated_fat_unit(String saturated_fat_unit) {
        this.saturated_fat_unit = saturated_fat_unit;
    }

    public Double getSaturated_fat_value() {
        return saturated_fat_value;
    }

    public void setSaturated_fat_value(Double saturated_fat_value) {
        this.saturated_fat_value = saturated_fat_value;
    }

    public Double getSodium() {
        return sodium;
    }

    public void setSodium(Double sodium) {
        this.sodium = sodium;
    }

    public Double getSodium_100g() {
        return sodium_100g;
    }

    public void setSodium_100g(Double sodium_100g) {
        this.sodium_100g = sodium_100g;
    }

    public Double getSodium_serving() {
        return sodium_serving;
    }

    public void setSodium_serving(Double sodium_serving) {
        this.sodium_serving = sodium_serving;
    }

    public String getSodium_unit() {
        return sodium_unit;
    }

    public void setSodium_unit(String sodium_unit) {
        this.sodium_unit = sodium_unit;
    }

    public Double getSodium_value() {
        return sodium_value;
    }

    public void setSodium_value(Double sodium_value) {
        this.sodium_value = sodium_value;
    }

    public Double getSugars() {
        return sugars;
    }

    public void setSugars(Double sugars) {
        this.sugars = sugars;
    }

    public Double getSugars_100g() {
        return sugars_100g;
    }

    public void setSugars_100g(Double sugars_100g) {
        this.sugars_100g = sugars_100g;
    }

    public Double getSugars_serving() {
        return sugars_serving;
    }

    public void setSugars_serving(Double sugars_serving) {
        this.sugars_serving = sugars_serving;
    }

    public String getSugars_unit() {
        return sugars_unit;
    }

    public void setSugars_unit(String sugars_unit) {
        this.sugars_unit = sugars_unit;
    }

    public Double getSugars_value() {
        return sugars_value;
    }

    public void setSugars_value(Double sugars_value) {
        this.sugars_value = sugars_value;
    }

    public Double getTrans_fat() {
        return trans_fat;
    }

    public void setTrans_fat(Double trans_fat) {
        this.trans_fat = trans_fat;
    }

    public Double getTrans_fat_100g() {
        return trans_fat_100g;
    }

    public void setTrans_fat_100g(Double trans_fat_100g) {
        this.trans_fat_100g = trans_fat_100g;
    }

    public Double getTrans_fat_serving() {
        return trans_fat_serving;
    }

    public void setTrans_fat_serving(Double trans_fat_serving) {
        this.trans_fat_serving = trans_fat_serving;
    }

    public String getTrans_fat_unit() {
        return trans_fat_unit;
    }

    public void setTrans_fat_unit(String trans_fat_unit) {
        this.trans_fat_unit = trans_fat_unit;
    }

    public Double getTrans_fat_value() {
        return trans_fat_value;
    }

    public void setTrans_fat_value(Double trans_fat_value) {
        this.trans_fat_value = trans_fat_value;
    }

    public Double getVitamin_a() {
        return vitamin_a;
    }

    public void setVitamin_a(Double vitamin_a) {
        this.vitamin_a = vitamin_a;
    }

    public Double getVitamin_a_100g() {
        return vitamin_a_100g;
    }

    public void setVitamin_a_100g(Double vitamin_a_100g) {
        this.vitamin_a_100g = vitamin_a_100g;
    }

    public Double getVitamin_a_serving() {
        return vitamin_a_serving;
    }

    public void setVitamin_a_serving(Double vitamin_a_serving) {
        this.vitamin_a_serving = vitamin_a_serving;
    }

    public String getVitamin_a_unit() {
        return vitamin_a_unit;
    }

    public void setVitamin_a_unit(String vitamin_a_unit) {
        this.vitamin_a_unit = vitamin_a_unit;
    }

    public Double getVitamin_a_value() {
        return vitamin_a_value;
    }

    public void setVitamin_a_value(Double vitamin_a_value) {
        this.vitamin_a_value = vitamin_a_value;
    }

    public Double getVitamin_c() {
        return vitamin_c;
    }

    public void setVitamin_c(Double vitamin_c) {
        this.vitamin_c = vitamin_c;
    }

    public Double getVitamin_c_100g() {
        return vitamin_c_100g;
    }

    public void setVitamin_c_100g(Double vitamin_c_100g) {
        this.vitamin_c_100g = vitamin_c_100g;
    }

    public Double getVitamin_c_serving() {
        return vitamin_c_serving;
    }

    public void setVitamin_c_serving(Double vitamin_c_serving) {
        this.vitamin_c_serving = vitamin_c_serving;
    }

    public String getVitamin_c_unit() {
        return vitamin_c_unit;
    }

    public void setVitamin_c_unit(String vitamin_c_unit) {
        this.vitamin_c_unit = vitamin_c_unit;
    }

    public Double getVitamin_c_value() {
        return vitamin_c_value;
    }

    public void setVitamin_c_value(Double vitamin_c_value) {
        this.vitamin_c_value = vitamin_c_value;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_iamge() {
        return product_iamge;
    }

    public void setProduct_iamge(String product_iamge) {
        this.product_iamge = product_iamge;
    }

    public Boolean getChecked() {
        return isChecked;
    }

    public void setChecked(Boolean checked) {
        isChecked = checked;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
