package tydic.cn.com.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import tydic.cn.com.bena.BaseEntity;
import tydic.cn.com.bena.LoginEntity;
import tydic.cn.com.bena.UserInfoEntity;

/**
 * Created by yechenglong on 2016/12/29.
 */

public interface ApiManager {
    @Headers("Content-Type: application/json")
    @POST("auth/login")
    Call<BaseEntity<UserInfoEntity>> getUsers(@Body LoginEntity entiry);
}
