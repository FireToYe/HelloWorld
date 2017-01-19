package tydic.cn.com.helloworld;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import tydic.cn.com.adapter.ListUpdateAdapter;

public class ListUpdateActivity extends Activity{
   private ListView listView;
    private List<String> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_update);
        init();
    }
    private void init(){
//        listView = (ListView)findViewById(R.id.lv_update);
        ScrollView sc = (ScrollView)findViewById(R.id.sv_update);
//        list = new ArrayList<String>();
//        for(int i = 0;i<40;i++){
//            list.add("点击测试");
//        }
//        ListUpdateAdapter adapter = new ListUpdateAdapter(this,list);
//        listView.setAdapter(adapter);
//        listView.setOnItemClickListener((parent, view, position, id) -> {
//            adapter.updateView(view,position,"测试成功");
//        });
        View childView   = sc.getChildAt(0);
        sc.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_UP:
                        if (childView  != null && childView .getMeasuredHeight()<= sc.getScrollY() + sc.getHeight()) {
                            ((Button)findViewById(R.id.btn_test)).setText("成功");
                            Toast.makeText(ListUpdateActivity.this,"滑动到底部啦",Toast.LENGTH_SHORT).show();
                        } else if (sc.getScrollY() == 0) {

                        }
                        break;
                }
                return false;
            }
        });
    }
}
