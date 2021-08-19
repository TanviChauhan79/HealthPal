package com.tanvi.healthpal.ui.scanqr.apis;

import com.tanvi.healthpal.utils.ApiConstants;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class APIServiceBuilder {

    private HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
    public OkHttpClient.Builder httpClient;
    public static APIServiceBuilder instance;
    public Retrofit retrofit;

    private APIServiceBuilder () {
        httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @NotNull
            @Override
            public Response intercept(@NotNull Chain chain) throws IOException {
                return chain.proceed(chain.request().newBuilder().build());
            }
        });

        httpClient.addInterceptor(logging);

        retrofit = new Retrofit.Builder().baseUrl(ApiConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(

                ))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClient.build())
                .build();

    }

    public static synchronized APIServiceBuilder getInstance() {
        if (instance == null) {
            instance = new APIServiceBuilder();
        }
        return instance;
    }

    public final Object buildService(@NotNull Class service) {
        return retrofit.create(service);
    }

}
