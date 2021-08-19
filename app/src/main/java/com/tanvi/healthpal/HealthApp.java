package com.tanvi.healthpal;

import android.app.Application;
import android.content.SharedPreferences;

import com.tanvi.healthpal.preferences.SharedPrefUtils;

public class HealthApp extends Application {

    static HealthApp instance;
    static SharedPreferences.Editor sharedPrefUtils;

    public static HealthApp the(){
        return instance;
    }

    public static SharedPreferences.Editor getSharedPref(){
        return sharedPrefUtils;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        sharedPrefUtils = SharedPrefUtils.getSharedPrefEditor(getApplicationContext(), getString(R.string.app_name));
    }

}
