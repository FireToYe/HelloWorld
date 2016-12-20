package tydic.cn.com.Service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;

/**
 * Created by Administrator on 2016/11/9.
 */
public class MessengerService extends Service {

    private static final int MSG_SUM = 0x110;
    private Messenger messenger = new Messenger(new Handler(){

        @Override
        public void handleMessage(Message msg) {
            Message toClient = Message.obtain(msg);
            if(toClient.what==MSG_SUM){
                toClient.arg2 = msg.arg1+msg.arg2;
                try {
                    msg.replyTo.send(toClient);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
            super.handleMessage(msg);
        }
    });

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return messenger.getBinder();
    }
}
