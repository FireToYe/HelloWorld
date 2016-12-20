package tydic.cn.com.helloworld;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
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
    private Button btn,sendNoti;
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
        sendNoti = (Button)findViewById(R.id.btn_send_noti);
        sendNoti.setOnClickListener(v -> {
           // Notification notification  = new Notification();
            Notification.Builder builder = new Notification.Builder(this);

//            notification.icon = R.drawable.image1;
//            notification.tickerText ="Hello World";
//            notification.when = System.currentTimeMillis();
//            notification.flags = Notification.FLAG_AUTO_CANCEL;
            builder.setContentInfo("补充内容");
            builder.setContentText("主内容区");
            builder.setContentTitle("通知标题");
            builder.setSmallIcon(R.drawable.image1);
            builder.setTicker("新消息");
            builder.setAutoCancel(true);
            builder.setWhen(System.currentTimeMillis());
            Intent intent = new Intent(DrawerLayoutActivity.this,IndexActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
            builder.setContentIntent(pendingIntent);
            builder.setAutoCancel(true);
            Notification notification = builder.build();
            NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            manager.notify(1, notification);
        });
        btn.setOnClickListener(v -> mDrawerLayout.openDrawer(Gravity.LEFT));
        textView = (TextView)findViewById(R.id.tv_msg);
        listView.setOnItemClickListener((parent, view, position, id) -> textView.setText(lvTitle[position]));
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
