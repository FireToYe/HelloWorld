package tydic.cn.com.helloworld;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import tydic.cn.com.Thread.ClientThread;

public class MySocketActivity extends AppCompatActivity {
    private TextView tvShow;
    private EditText etSend;
    public Handler handler;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_socket);
        tvShow = (TextView) findViewById(R.id.tv_show);
        etSend = (EditText) findViewById(R.id.et_send);
        btn = (Button)findViewById(R.id.btn_send);
        btn.setOnClickListener(v -> {
            String content = etSend.getText().toString();
            etSend.setText("");
            handler.obtainMessage(0x456,content);
        });
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if(msg.what==0x123){
                    tvShow.append("\n"+msg.obj.toString());
                }
                super.handleMessage(msg);
            }
        };
    }

}
