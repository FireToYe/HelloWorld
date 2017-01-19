package tydic.cn.com.application;

import android.app.Application;
import android.content.Context;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.cookie.CookieJarImpl;
import com.zhy.http.okhttp.cookie.store.PersistentCookieStore;
import com.zhy.http.okhttp.log.LoggerInterceptor;

import okhttp3.OkHttpClient;

public class MyApplication extends Application {


    static Context sContext;

    public MyApplication() {
        sContext = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        CookieJarImpl cookieJar = new CookieJarImpl(new PersistentCookieStore(this));
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .cookieJar(cookieJar)
                .addInterceptor(new LoggerInterceptor("tydic"))
                //其他配置
                .build();

        OkHttpUtils.initClient(okHttpClient);
    }

    @Deprecated
    public static void initialize(Context context) {
        sContext = context;
    }

    public static Context getContext() {
        if (sContext == null) {
            throw new RuntimeException("context未初始化");
        }
        return sContext;
    }

}