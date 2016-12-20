package tydic.cn.com.Thread;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.Socket;
import java.net.SocketTimeoutException;

/**
 * Created by Administrator on 2016/9/2.
 */
public class ClientThread implements Runnable{
    private Socket s;
    BufferedReader br;
    OutputStream os;
    private Handler handler;
    public Handler recHandler;

    public ClientThread(Handler handler){
        this.handler= handler;
    }
    @Override
    public void run() {
        try {
            s = new Socket("127.0.0.1",8010);
            br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            os = s.getOutputStream();
            new Thread(){
                @Override
                public void run() {
                    String content = null;
                    try {
                        while((content =br.readLine())!=null){
                            Message message = new Message();
                            message.what = 0x123;
                            message.obj = content;
                            handler.sendMessage(message);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
            Looper.prepare();
            recHandler = new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    if (msg.what==0x456){
                        try {
                            os.write((msg.obj.toString()+"\r\n").getBytes("utf-8"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
            Looper.loop();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
