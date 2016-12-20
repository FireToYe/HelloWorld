package tydic.cn.com.helloworld;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Scroller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class UrlConnectActivity extends Activity {
    private ImageView imageView;
    private Bitmap bitmap;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==0x123){
                imageView.setImageBitmap(bitmap);
                smootScrolltTo();
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_url_connect);
        ViewGroup viewGroup = (ViewGroup) findViewById(android.R.id.content);
        View view = viewGroup.getChildAt(0);
        imageView = (ImageView)findViewById(R.id.image_show);
        new Thread(){
            @Override
            public void run() {
                try {
                    URL url = new URL("http://pic33.nipic.com/20130916/3420027_192919547000_2.jpg");
                    InputStream is = url.openStream();
                    bitmap = BitmapFactory.decodeStream(is);
                    handler.sendEmptyMessage(0x123);
                    is.close();
                    is = url.openStream();
                    OutputStream os =openFileOutput("mine.png",MODE_PRIVATE);
                    byte[] buff = new byte[1024];
                    int temp;
                    while((temp=is.read(buff))>-1){
                        os.write(buff,0,temp);
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
    private void smootScrolltTo(){
        ObjectAnimator.ofFloat(imageView,"translationY",0,500).setDuration(2000).start();
        imageView.setAnimation(AnimationUtils.loadAnimation(this,R.anim.left_in));

    }

}
