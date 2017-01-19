package tydic.cn.com.Util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by yechenglong on 2017/1/13.
 */

public class MyUtil {
    public static void close(InputStream in){
        if (in!=null){
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void close(OutputStream in){
        if (in!=null){
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
