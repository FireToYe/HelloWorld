package tydic.cn.com.helloworld;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class DrawerLayoutActivity extends AppCompatActivity {
    private String[] lvTitle = new String[]{"菜单一","菜单二","菜单三"};
    private boolean isOpen;
    private ListView listView;
    private DrawerLayout mDrawerLayout;
    private TextView textView;
    ActionBarDrawerToggle toggle;
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_layout);
        initView();
    }

    private void initView(){
        listView = (ListView)findViewById(R.id.lv_items);
        ArrayAdapter adapter = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,lvTitle);
        mDrawerLayout = (DrawerLayout)findViewById(R.id.activity_drawer_layout);
        listView.setAdapter(adapter);
        btn =(Button)findViewById(R.id.btn_openDraw);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(Gravity.LEFT);
            }
        });
        textView = (TextView)findViewById(R.id.tv_msg);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                textView.setText(lvTitle[position]);
            }
        });
        mDrawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(View drawerView) {
            }

            @Override
            public void onDrawerClosed(View drawerView) {
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
    }
}
