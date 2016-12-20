package tydic.cn.com.helloworld;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import tydic.cn.com.Service.MessengerService;

public class MessengerActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static final int MSG_SUM = 0x110;

    private Button mBtnAdd;
    private LinearLayout mLyContainer;
    //显示连接状态
    private TextView mTvState;

    private Messenger mService;
    private boolean isConn;

    private Messenger mMessenger = new Messenger(new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what ==MSG_SUM){
                TextView tv = (TextView) mLyContainer.findViewById(msg.arg1);
                tv.setText(msg.arg2);
            }
        }
    });
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mService = new Messenger(service);
            isConn = true;
            mTvState.setText("connected");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mService = null;
            isConn = false;
            mTvState.setText("notconnected");
        }
    };
    private int mA;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InvokebindService();
        mTvState = (TextView) findViewById(R.id.id_tv_callback);
        mBtnAdd = (Button) findViewById(R.id.id_btn_add);
        mLyContainer = (LinearLayout) findViewById(R.id.id_ll_container);

        mBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = mA++;
                int b = (int) (Math.random() * 100);
                TextView tv = new TextView(MessengerActivity.this);
                tv.setText(a + " + " + b + " = caculating ...");
                tv.setId(a);
                mLyContainer.addView(tv);
                Message toServer = Message.obtain(null, MSG_SUM, a, b);
                toServer.replyTo = mMessenger;
                try {
                    mService.send(toServer);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void InvokebindService(){
        Intent intent = new Intent("com.helloWorld.service");
        bindService(intent,connection,Context.BIND_AUTO_CREATE);

    }
}
