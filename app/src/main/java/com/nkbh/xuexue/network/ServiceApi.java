package com.nkbh.xuexue.network;

import com.nkbh.xuexue.bean.PlanBean;
import com.nkbh.xuexue.bean.ResponseBean;
import com.nkbh.xuexue.bean.UserBean;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by User on 2018/3/29.
 */

public interface ServiceApi {
    String BASE_URL = "http://10.1.95.99/";

    @FormUrlEncoded
    @POST("/user/login")
    Observable<ResponseBean<UserBean>> login(@FieldMap Map<String, String> params);

    @GET("/plan")
    Observable<ResponseBean<List<PlanBean>>> getPlan(@Query("user_id") int user_id);

    @FormUrlEncoded
    @POST("/plan/add")
    Observable<ResponseBean<String>> addPlan(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("/plan/update")
    Observable<ResponseBean<String>> updatePlan(@FieldMap Map<String, String> params);
}
