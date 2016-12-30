package tydic.cn.com;

import java.net.SocketTimeoutException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import tydic.cn.com.bena.BaseEntity;
import tydic.cn.com.helloworld.MainActivity;

/**
 * Created by yechenglong on 2016/12/29.
 */

public abstract class ApCallBackListener<T extends BaseEntity> implements retrofit2.Callback<T>{
    private MainActivity mContext;

    public ApCallBackListener() {
    }

    public ApCallBackListener(MainActivity mContext) {
        this.mContext = mContext;
    }


    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.code() == 200) {
            if (response.body().isSuccess()) {
                onSuccess(response.body());
            } else {
                onFailure(call, new RuntimeException(response.body().getErrorMsg()));
            }
        } else onFailure(call, new RuntimeException(response.message()));
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        String msg = t.getMessage();
        if (t instanceof SocketTimeoutException) {
            msg = "服务器连接超时，请稍后重试";
        }
    }


    public abstract void onSuccess(T t);
}
