package tydic.cn.com.helloworld;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import tydic.cn.com.Thread.ClientThread;

public class SocketActivity extends AppCompatActivity {
    EditText editText;
    TextView textView;
    Button button;
    Handler handler;
    ClientThread clientThread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socket);
        textView = (TextView)findViewById(R.id.showText);
        editText = (EditText)findViewById(R.id.sendText);
        button = (Button)findViewById(R.id.sendBtn);
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if(msg.what==0x123){
                    textView.append("/n"+msg.obj.toString());
                }
            }
        };
        clientThread = new ClientThread(handler);
        new Thread(clientThread).start();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message msg = new Message();
                msg.what=0x456;
                msg.obj = editText.getText().toString();
                clientThread.recHandler.sendMessage(msg);
                editText.setText("");
            }
        });
    }
}
