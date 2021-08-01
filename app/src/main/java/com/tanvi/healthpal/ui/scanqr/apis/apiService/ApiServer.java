package com.tanvi.healthpal.ui.scanqr.apis.apiService;

import com.tanvi.healthpal.ui.scanqr.apis.entity.elements.NutritionalInfoFromApi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiServer {

    @GET("{code}.json")
    Call<NutritionalInfoFromApi> getNutritionInfo(@Path("code") String code);
}
