package com.tanvi.healthpal.ui.scanqr.apis;

import com.tanvi.healthpal.HealthApp;
import com.tanvi.healthpal.R;
import com.tanvi.healthpal.ui.qrscanner.model.ApiErrorModel;
import com.tanvi.healthpal.ui.qrscanner.model.ResGetNutritionalInfoFromBarcodeModel;
import com.tanvi.healthpal.utils.ApiConstants;

import org.jetbrains.annotations.NotNull;

import kotlin.jvm.functions.Function2;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class APIServices {

    public void getNutritionalInfo(@NotNull String code, @NotNull Function2 onResult) {
        Object retrofit = APIServiceBuilder.getInstance().buildService(IScanQRApi.class);
        ((IScanQRApi)retrofit).getNutritionalInfo(code).enqueue(new Callback<ResGetNutritionalInfoFromBarcodeModel>() {
            @Override
            public void onResponse(Call<ResGetNutritionalInfoFromBarcodeModel> call, Response<ResGetNutritionalInfoFromBarcodeModel> response) {
                ResGetNutritionalInfoFromBarcodeModel addedUser = response.body();
                if (addedUser == null && response.code() == 401 && response.message().equals(ApiConstants.UNAUTHORIZED)
                ) {
                    ApiErrorModel apiErrorModel = new ApiErrorModel(
                            HealthApp.the().getString(R.string.tv_token_has_expired), "401");
                    onResult.invoke(null, apiErrorModel);
                    return;
                }
                onResult.invoke(addedUser, null);
            }

            @Override
            public void onFailure(Call<ResGetNutritionalInfoFromBarcodeModel> call, Throwable t) {
                ApiErrorModel apiErrorModel;
                if (t.getMessage() != null) {
                    apiErrorModel = new ApiErrorModel(t.getMessage());
                } else if (t.getLocalizedMessage() != null) {
                    apiErrorModel = new ApiErrorModel(t.getLocalizedMessage());
                } else {
                    apiErrorModel = new ApiErrorModel("Something went wrong.");
                }
                onResult.invoke(null, apiErrorModel);
            }
        });
    }

}
