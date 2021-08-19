package com.tanvi.healthpal.ui.scanqr.apis;

import com.tanvi.healthpal.ui.qrscanner.model.ResGetNutritionalInfoFromBarcodeModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IScanQRApi {

    @GET("{code}.json")
    Call<ResGetNutritionalInfoFromBarcodeModel> getNutritionalInfo(@Path("code") String code);

}
