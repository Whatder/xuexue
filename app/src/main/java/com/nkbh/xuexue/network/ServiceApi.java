package com.nkbh.xuexue.network;

import com.nkbh.xuexue.bean.CommunityReplyBean;
import com.nkbh.xuexue.bean.CourseBean;
import com.nkbh.xuexue.bean.PlanBean;
import com.nkbh.xuexue.bean.ResponseBean;
import com.nkbh.xuexue.bean.TopicBean;
import com.nkbh.xuexue.bean.UserBean;
import com.nkbh.xuexue.bean.admin.Admin;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by User on 2018/3/29.
 */

public interface ServiceApi {
    String BASE_URL = "http://10.1.95.99/";

    @FormUrlEncoded
    @POST("/user/login")
    Observable<ResponseBean<UserBean>> login(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("/user/logup")
    Observable<ResponseBean<String>> logup(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("/user/password")
    Observable<ResponseBean<String>> changePwd(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("/user/name")
    Observable<ResponseBean<String>> changeName(@FieldMap Map<String, String> params);

    @GET("/plan")
    Observable<ResponseBean<List<PlanBean>>> getPlan(@Query("user_id") int user_id);

    @FormUrlEncoded
    @POST("/plan/add")
    Observable<ResponseBean<String>> addPlan(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("/plan/update")
    Observable<ResponseBean<String>> updatePlan(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("/plan/del")
    Observable<ResponseBean<String>> delPlan(@FieldMap Map<String, String> params);

    @GET("/topic")
    Observable<ResponseBean<List<TopicBean>>> getTopic();

    @FormUrlEncoded
    @POST("/topic/add")
    Observable<ResponseBean<String>> addTopic(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("/topic/like")
    Observable<ResponseBean<String>> likeTopic(@FieldMap Map<String, String> params);

    @GET("/reply")
    Observable<ResponseBean<List<CommunityReplyBean>>> getReplyById(@Query("topic_id") int topic_id);

    @FormUrlEncoded
    @POST("/reply/add")
    Observable<ResponseBean<String>> addReply(@FieldMap Map<String, String> params);

    @GET("/movie/all")
    Observable<ResponseBean<List<CourseBean>>> getAllMovies();

    @Multipart
    @POST("upload/profile_pic")
    Observable<ResponseBean<String>> updateProfilePic(@Part List<MultipartBody.Part> file);

    //管理员模块接口----------------------------------------
    @FormUrlEncoded
    @POST("/admin/login")
    Observable<ResponseBean<Admin>> adminLogin(@FieldMap Map<String, String> params);

}
