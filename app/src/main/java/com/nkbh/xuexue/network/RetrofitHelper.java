package com.nkbh.xuexue.network;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by User on 2018/3/29.
 */

public class RetrofitHelper {
    public static ServiceApi getService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ServiceApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        ServiceApi service = retrofit.create(ServiceApi.class);
        return service;
    }
}
