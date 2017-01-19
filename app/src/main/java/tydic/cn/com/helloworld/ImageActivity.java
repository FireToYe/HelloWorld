package tydic.cn.com.helloworld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import tydic.cn.com.Util.BitampUtil;

public class ImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        ImageView im = (ImageView)findViewById(R.id.iv_image2);
        im.setImageBitmap(BitampUtil.decodeSampledBitmapFromResource(getResources(),R.drawable.image1,50,50));
    }
}
