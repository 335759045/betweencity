package com.betweencity.tm.betweencity.api;



import com.betweencity.tm.betweencity.bean.HttpResult;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Administrator on 2017/12/14.
 */

public interface ApiBasic {
    @FormUrlEncoded
    @POST("api/login/sendCode")
    Observable<HttpResult> getCode(@Field("phone") String phone);
    @FormUrlEncoded
    @POST("api/user/editPhone")
    Observable<HttpResult> changePhone(@FieldMap Map<String, String> map);
    @FormUrlEncoded
    @POST("api/user/editPwd")
    Observable<HttpResult> changePWD(@FieldMap Map<String, String> map);
    @FormUrlEncoded
    @POST("api/user/out")
    Observable<HttpResult> loginOut(@FieldMap Map<String, String> map);
}
