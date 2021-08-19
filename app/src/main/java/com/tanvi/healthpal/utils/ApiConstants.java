package com.tanvi.healthpal.utils;

import org.jetbrains.annotations.NotNull;

public final class ApiConstants {
    @NotNull
    public static final String BASE_URL = "https://world.openfoodfacts.org/api/v0/product/";
    @NotNull
    public static final String UNAUTHORIZED = "Unauthorized";
    @NotNull
    public static final com.tanvi.healthpal.utils.ApiConstants INSTANCE;

    private ApiConstants() {
    }

    static {
        ApiConstants apiConstants = new ApiConstants();
        INSTANCE = apiConstants;
    }
}
