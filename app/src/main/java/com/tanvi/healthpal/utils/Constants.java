package com.tanvi.healthpal.utils;

import org.jetbrains.annotations.NotNull;

public final class Constants {
    @NotNull
    public static final String NUTRIMENTS_INFO = "NUTRIMENTS_INFO";
    @NotNull
    public static final String NUTRIMENTS_INFO_LIST = "NUTRIMENTS_INFO_LIST";
    @NotNull
    public static final String FROM_WISHLIST = "FROM_WISHLIST";
    @NotNull
    public static final String PRODUCT_IMAGE = "PRODUCT_IMAGE";
    @NotNull
    public static final String PRODUCT_NAME = "PRODUCT_NAME";
    @NotNull
    public static final String WISHLIST = "wishlist";
    @NotNull
    public static final String USERS = "users";
    @NotNull
    public static final String IS_LOGGED_IN = "IS_LOGGED_IN";
    @NotNull
    public static final String USER_ID = "USER_ID";
    @NotNull
    public static final String SIGN_UP_MODEL = "SIGN_UP_MODEL";
    @NotNull
    public static final String IS_FORGOT_PASSWORD = "IS_FORGOT_PASSWORD";
    @NotNull
    public static final String FIREBASE_ID = "FIREBASE_ID";
    @NotNull
    public static final String FROM_HOME = "FROM_HOME";
    @NotNull
    public static final Constants INSTANCE;

    private Constants() {
    }

    static {
        Constants var0 = new Constants();
        INSTANCE = var0;
    }
}
