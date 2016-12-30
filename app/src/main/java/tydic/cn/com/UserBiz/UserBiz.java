package tydic.cn.com.UserBiz;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import tydic.cn.com.ApCallBackListener;
import tydic.cn.com.Util.NetUtil;
import tydic.cn.com.api.ApiManager;
import tydic.cn.com.bena.BaseEntity;
import tydic.cn.com.bena.LoginEntity;
import tydic.cn.com.bena.UserInfoEntity;

/**
 * Created by yechenglong on 2016/12/29.
 */

public class UserBiz {

    public  void getList(String name ,String password ,ApCallBackListener<BaseEntity<UserInfoEntity>> listener){
        Retrofit retrofit = NetUtil.getInstance().getRetrofit();
        ApiManager manager  =retrofit.create(ApiManager.class);
        Call<BaseEntity<UserInfoEntity>> call = manager.getUsers(new LoginEntity(name,password));
        call.enqueue(listener);
    }
}
