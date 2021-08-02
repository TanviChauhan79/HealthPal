package com.tanvi.healthpal.ui.scanqr.apis.entity;

import android.os.Bundle;
import android.os.Parcelable;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.navigation.Navigation;

import com.tanvi.healthpal.R;
import com.tanvi.healthpal.ui.scanqr.ScanQRFragment;
import com.tanvi.healthpal.ui.scanqr.apis.apiService.ApiServer;
import com.tanvi.healthpal.ui.scanqr.apis.entity.elements.NutritionalInfoFromApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class QRScannerViewModel extends ViewModel {
    Retrofit retrofit;
    public final static String URL="https://world.openfoodfacts.org/api/v0/product/";
    public MutableLiveData<NutritionalInfoFromApi>nutritionalInfoFromApiMutableLiveData=new MutableLiveData<>();
    public void ApiCall(String code){
        retrofit=new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiServer apiServer=retrofit.create(ApiServer.class);

        Call<NutritionalInfoFromApi> callQr=apiServer.getNutritionInfo(code);
        callQr.enqueue(new Callback<NutritionalInfoFromApi>() {
            @Override
            public void onResponse(Call<NutritionalInfoFromApi> call, Response<NutritionalInfoFromApi> response) {

                NutritionalInfoFromApi nutritionalInfoFromApi=response.body();
                setNutritionalInfoFromApiMutableLiveData(nutritionalInfoFromApi);
            }

            @Override
            public void onFailure(Call<NutritionalInfoFromApi> call, Throwable t) {

            }
        });
    }
    public MutableLiveData<NutritionalInfoFromApi> getNutritionalInfoFromApiMutableLiveData() {
        return nutritionalInfoFromApiMutableLiveData;
    }
    public void setNutritionalInfoFromApiMutableLiveData(NutritionalInfoFromApi nutritionalInfoFromApi) {
        nutritionalInfoFromApiMutableLiveData.postValue(nutritionalInfoFromApi);
    }
}
