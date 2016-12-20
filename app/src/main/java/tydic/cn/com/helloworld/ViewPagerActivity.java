package tydic.cn.com.helloworld;

import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import tydic.cn.com.adapter.ImageAdapter;

public class ViewPagerActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private List<View> viewList;
    private ImageView[] points;
    private View view1,view2,view3,view4;
    private int currentPage;
    private int images[]={R.drawable.image1,R.drawable.image2,R.drawable.image3,R.drawable.image4};
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==0x123){
                viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        initView();
        initPoint();
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(0x123);
            }
        },0,5000);
    }
    private void initPoint(){
        LinearLayout  linearLayout=(LinearLayout) findViewById(R.id.ll_show);
        points=new ImageView[4];
        for(int i = 0;i<4;i++){
            points[i] =(ImageView) linearLayout.getChildAt(i);
            points[i].setEnabled(true);
            points[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int a =(int)v.getTag()+viewPager.getCurrentItem()/4*4;
                    viewPager.setCurrentItem(a);
                }
            });
            points[i].setTag(i);
        }
        currentPage=0;
        points[currentPage].setEnabled(false);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                position=position%4;
                points[position].setEnabled(false);
                points[currentPage].setEnabled(true);
                currentPage = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setCurrentItem(viewList.size()*500);
    }
    private void initView(){
        viewPager = (ViewPager)findViewById(R.id.vp_view);
        viewList=new ArrayList<View>();
        for(int i=0;i<images.length;i++){
                      ImageView imageView=new ImageView(this);
                         imageView.setLayoutParams(new ViewPager.LayoutParams());
                         imageView.setScaleType(ImageView.ScaleType.FIT_XY);//设置缩放样式
                         imageView.setImageResource(images[i]);
                         viewList.add(imageView);
            }
        ImageAdapter adapter = new ImageAdapter(viewList);
        viewPager.setAdapter(adapter );
    }


}
