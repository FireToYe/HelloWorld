package tydic.cn.com.helloworld;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class ZCCarActivity extends AppCompatActivity {
    private ViewPager mPager;//页卡内容
    private List<View> listViews; // Tab页面列表
    private ImageView cursor;// 动画图片
    private LinearLayout line1, line2;
    private int offset = 0;// 动画图片偏移量
    private int currIndex = 0;// 当前页卡编号
    private int bmpW;// 动画图片宽度

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zc_car);
        line1 = (LinearLayout) findViewById(R.id.jiben);
        line2 = (LinearLayout) findViewById(R.id.renzheng);
        line1.setOnClickListener(new MyOnClickListener(1));
        line2.setOnClickListener(new MyOnClickListener(2));
        InitViewPager();
    }
    private void InitViewPager(){
        mPager = (ViewPager)findViewById(R.id.pager);
        listViews = new ArrayList<>();
        LayoutInflater mInflater = getLayoutInflater();
        listViews.add(mInflater.inflate(R.layout.main2,null));
        listViews.add(mInflater.inflate(R.layout.main3,null));
        mPager.setAdapter(new MyPageAdapter(listViews));
    }

    public class MyOnClickListener implements View.OnClickListener {
        private int index = 0;

        public MyOnClickListener(int i) {
            index = i;
        }

        @Override
        public void onClick(View v) {
            mPager.setCurrentItem(index);
        }
    }
}
