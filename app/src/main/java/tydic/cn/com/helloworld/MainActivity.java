package tydic.cn.com.helloworld;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.stetho.Stetho;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tydic.cn.com.ApCallBackListener;
import tydic.cn.com.CountView;
import tydic.cn.com.UserBiz.UserBiz;
import tydic.cn.com.bena.BaseEntity;
import tydic.cn.com.bena.UserInfoEntity;
import tydic.cn.com.db.DBManager;
import android.app.Dialog;
import android.support.v7.app.AlertDialog;

import org.w3c.dom.Text;

import tydic.cn.com.helloworld.R;
import tydic.cn.com.view.BadgeView;
import tydic.cn.com.view.SevenView;

public class MainActivity extends Activity {

    private DBManager manager;
    private ListView listView;
    private TextView tvShow;
    BadgeView badge;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what==0x123){
   //             tvShow.setText(msg.obj.toString());
            }
            super.handleMessage(msg);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_PROGRESS); //去标题栏
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);
        //初始化DBManager
        manager = new DBManager(this);
        tvShow = (TextView) findViewById(R.id.tv_show);
        CountView cvTest = (CountView) findViewById(R.id.cv_test);
        cvTest.showNumberWithAnimation(2000.00f);
//        ImageView target= (ImageView )findViewById(R.id.iv_badge);
//        target.setOnClickListener(v -> {
//            Intent intent = new Intent(this,DemoActivity.class);
//            startActivity(intent);
//        });
//        badge = new BadgeView(this, target);
//        badge.setText("OK");
//        badge.show();
        UserBiz userbize = new UserBiz();
            userbize.getList("15084890539", "2251022057731868917119086224872421513662", new ApCallBackListener<BaseEntity<UserInfoEntity>>() {
                @Override
                public void onSuccess(BaseEntity<UserInfoEntity> userInfoEntityBaseEntity) {
                    UserInfoEntity userInfo = userInfoEntityBaseEntity.getResult();
                    Message message = new Message();
                    message.what=0x123;
                    message.obj =userInfo;
                    handler.sendMessage(message);
                }
            });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        manager.closeDB();
    }
    public void goSendMessenger(View view){
        Intent intent =new Intent(this,AlarmActivity.class);
        startActivity(intent);
    }
    public void goGetPost(View view){
        Intent intent =new Intent(this,UrlGetPostActivity.class);
        startActivity(intent);
    }
    public void urlConnect(View view){
        Intent intent =new Intent(this,UrlConnectActivity.class);
        startActivity(intent);
    }
    public void add(View view){
        List list = new ArrayList<User>();
        User user1 = new User("Ella", "22", "lively girl");
        User user2 = new User("Jenny", "21", "beautiful girl");
        User user3 = new User("Jessica", "23", "sexy girl");
        User user4 = new User("Kelly", "24", "hot baby");
        User user5 = new User("Jane", "11", "a pretty woman");
        list.add(user1);
        list.add(user2);
        list.add(user3);
        list.add(user4);
        list.add(user5);
        manager.add(list);
    }

    public void update(View view){
        User user = new User();
        user.setName("Jane");
        user.setAge("18");
        manager.updateAge(user);
    }
    public void delete(View view){
        User user = new User();
        user.setAge("20");
        manager.deleteOldUser(user);
    }
    public void viewPager(View view){
        Intent intent =new Intent(this,ViewPagerActivity.class);
        startActivity(intent);
    }
    public void drawerLayout(View view){
        Intent intent =new Intent(this,ImageLoaderActivity.class);
        startActivity(intent);
    }
    public void goListView(View view){
        Intent intent =new Intent(this,ImageActivity.class);
        startActivity(intent);
    }

    public void query(View view){
        List<User> list = manager.query();
        List<Map<String,String>> maps = new ArrayList<Map<String,String>>();
        for(User user:list){
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("name", user.getName());
            map.put("info", user.getAge() + " years old, " + user.getInfo());
            maps.add(map);
        }
        SimpleAdapter adapter = new SimpleAdapter(this,maps,android.R.layout.simple_list_item_2,new String[]{"name","info"},new int[]{android.R.id.text1,android.R.id.text2});
        listView.setAdapter(adapter);
    }

    public void queryTheCursor(View view) {
        Cursor c = manager.queryTheCursor();
        startManagingCursor(c);
        CursorWrapper cursorWrapper = new CursorWrapper(c){
            @Override
            public String getString(int columnIndex) {
                //将简介前加上年龄
                if (getColumnName(columnIndex).equals("info")) {
                    String age = getString(getColumnIndex("age"));
                    return age + " years old, " + super.getString(columnIndex);
                }
                return super.getString(columnIndex);
            }
        };
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2,
                cursorWrapper, new String[]{"name", "info"}, new int[]{android.R.id.text1, android.R.id.text2});
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
    }

    public void showDialog(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_arc,null);
        builder.setView(dialogView);
        final Dialog dialog = builder.show();
        dialog.getWindow().setBackgroundDrawableResource(R.color.transparent);
        handler.postDelayed(new Thread(){
            @Override
            public void run() {
                dialog.dismiss();
                super.run();
            }
        },60000);
    }


}