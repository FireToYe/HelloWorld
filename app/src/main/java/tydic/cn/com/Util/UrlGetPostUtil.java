package tydic.cn.com.Util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/7.
 */
public class UrlGetPostUtil {
    public static  String sendGet(String urlStr,String params){
        String result = "";
        BufferedReader br = null;
        String urlName = urlStr+"?"+params;
        try {
            URL url = new URL(urlName);
            URLConnection conn = url.openConnection();
            conn.setRequestProperty("accept","*/*");
            conn.setRequestProperty("connection","KEEP-Alive");
            conn.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1");
            conn.connect();

            Map<String,List<String>> map = conn.getHeaderFields();
            for(String key:map.keySet()){
                System.out.println(key+"-->"+map.get(key));
            }
            br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String content;
            while((content=br.readLine())!=null){
                result +="\n"+content;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
                try {
                    if(br!=null){
                        br.close();
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        return result;
    }
    public static String sendPost(String urlStr,String params){
        String result = "";
        PrintWriter out = null;
        BufferedReader br = null;
        String urlName = urlStr+"?"+params;
        try{
            URL url = new URL(urlName);
            URLConnection conn = url.openConnection();
            conn.setRequestProperty("accept","*/*");
            conn.setRequestProperty("connection","KEEP-Alive");
            conn.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.connect();
            out = new PrintWriter(conn.getOutputStream());
            out.print(params);
            out.flush();
            br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = null;
            while ((line = br.readLine())!=null){
                result += "\n"+line;
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
                try {
                    if(br!=null) {
                        br.close();
                    }
                    if (out!=null){
                        out.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        return result;
    }
}
