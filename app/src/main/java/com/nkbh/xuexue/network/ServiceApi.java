package com.nkbh.xuexue.network;

import com.nkbh.xuexue.bean.ResponseBean;
import com.nkbh.xuexue.bean.UserBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by User on 2018/3/29.
 */

public interface ServiceApi {
    String BASE_URL = "http://10.1.95.99/";

    @FormUrlEncoded
    @POST("/user/login")
    Observable<ResponseBean<UserBean>> login(@FieldMap Map<String, String> params);
}
