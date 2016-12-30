package tydic.cn.com.Util;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by yechenglong on 2016/12/29.
 */

public class NetUtil {
    private static NetUtil netUtil;
    public static NetUtil getInstance() {
        synchronized (NetUtil.class) {
            if (netUtil == null) {
                netUtil = new NetUtil();
            }
            return netUtil;
        }
    }

    public Retrofit getRetrofit(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.161.157:9003/mapi/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}
