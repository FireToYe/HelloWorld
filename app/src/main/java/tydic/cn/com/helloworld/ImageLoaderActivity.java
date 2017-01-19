package tydic.cn.com.helloworld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

import tydic.cn.com.adapter.ImageLoaderAdapter;

public class ImageLoaderActivity extends AppCompatActivity {
    List<String> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_loader);
        GridView gridView = (GridView) findViewById(R.id.gv);
        ImageLoaderAdapter adapter = new ImageLoaderAdapter(this);
        list = new ArrayList<String>();
            String url ="http://m2.quanjing.com/2m/alamyrf005/b1fw89.jpg";
        String url1 ="http://pic36.nipic.com/20131128/11748057_141932278338_2.jpg";
        String url2 = "http://pic2.cxtuku.com/00/02/31/b945758fd74d.jpg";
        String url3 = "http://img3.imgtn.bdimg.com/it/u=3771844440,2967512647&fm=23&gp=0.jpg";
            list.add(url);
        list.add(url2);
        list.add(url1);
        list.add(url3);
        adapter.setmUrlList(list);
        gridView.setAdapter(adapter);
    }
}
