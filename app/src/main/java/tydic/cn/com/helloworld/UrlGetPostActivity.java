package tydic.cn.com.helloworld;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import tydic.cn.com.Util.UrlGetPostUtil;

public class UrlGetPostActivity extends AppCompatActivity {
    Button get,post;
    TextView tvMsg;
    String response;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==0x123){
                tvMsg.setText("");
                tvMsg.setText(response);
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_url_get_post);
        get = (Button)findViewById(R.id.btn_get);
        post = (Button)findViewById(R.id.btn_post);
        tvMsg = (TextView)findViewById(R.id.tv_msg);
        get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(){
                    @Override
                    public void run() {
                        response = UrlGetPostUtil.sendGet("http://192.168.0.102:8080/UploadTest/loginServlet","username=zs&password=zs");
                        handler.sendEmptyMessage(0x123);
                    }
                }.start();
            }
        });
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(){
                    @Override
                    public void run() {
                        response = UrlGetPostUtil.sendPost("http://192.168.0.102:8080/UploadTest/loginServlet","username=zs&password=zs");
                        handler.sendEmptyMessage(0x123);
                    }
                }.start();
            }
        });
    }
}
