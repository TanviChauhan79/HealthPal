package com.tanvi.healthpal.ui.scanqr;

import androidx.lifecycle.ViewModel;

import com.tanvi.healthpal.utils.SingleLiveEvent;
import com.tanvi.healthpal.ui.qrscanner.model.ApiErrorModel;
import com.tanvi.healthpal.ui.qrscanner.model.ResGetNutritionalInfoFromBarcodeModel;
import com.tanvi.healthpal.ui.scanqr.apis.APIServices;

import kotlin.jvm.functions.Function2;

public class ScanQRViewModel extends ViewModel {

    SingleLiveEvent showProgressBar = new SingleLiveEvent<Boolean>();
    SingleLiveEvent data = new SingleLiveEvent<ResGetNutritionalInfoFromBarcodeModel>();
    SingleLiveEvent showToast = new SingleLiveEvent<String>();

    void callApi(String code){
        showProgressBar.postValue(true);
        APIServices apiServices = new APIServices();
        apiServices.getNutritionalInfo(code, new Function2() {
            @Override
            public Object invoke(Object o, Object o2) {
                showProgressBar.postValue(false);
                if(o != null){
                    data.postValue(o);
                } else {
                    if(o2 != null){
                        if(((ApiErrorModel)o2).status.equals("401")){
                            showToast.postValue("Token expired.");
                        }
                        showToast.postValue(((ApiErrorModel)o2).getMessage());
                    } else {

                    }
                }
                return null;
            }
        }
        );
    }

}
