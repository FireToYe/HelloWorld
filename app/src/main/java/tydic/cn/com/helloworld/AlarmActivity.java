package tydic.cn.com.helloworld;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.HashMap;


public class AlarmActivity extends AppCompatActivity {
    public Calendar currentTime = Calendar.getInstance();
    Button setTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        setTime = (Button) findViewById(R.id.setTime);
        setTime.setOnClickListener(new View.OnClickListener() {
                                       public void onClick(View v) {
                                       }
                                   });
//            @Override
//            public void onClick(View v) {
//                Calendar currentTime = Calendar.getInstance();
//                new TimePickerDialog(AlarmActivity.this, 0, new TimePickerDialog.OnTimeSetListener() {
//                    @Override
//                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//                        Intent intent = new Intent(AlarmActivity.this, MainActivity.class);
//                        PendingIntent pendingIntent=PendingIntent.getActivity(AlarmActivity.this,0,intent,0);
//                        Calendar c = Calendar.getInstance();
////                        c.setTimeInMillis(System.currentTimeMillis());
////                        c.set(Calendar.HOUR,hourOfDay);
////                        c.set(Calendar.MINUTE,minute);
//                        c.add(Calendar.SECOND,10);
//                        AlarmManager manager = (AlarmManager)getSystemService(ALARM_SERVICE);
//                        manager.set(AlarmManager.RTC_WAKEUP,c.getTimeInMillis(),pendingIntent);
////                        manager.setExact(AlarmManager.RTC_WAKEUP,c.getTimeInMillis(),pendingIntent);
//                        Toast.makeText(AlarmActivity.this,"闹钟设置成功",Toast.LENGTH_SHORT).show();
//                    }
//                }, currentTime.get(Calendar.HOUR_OF_DAY), currentTime.get(Calendar.MINUTE), false).show();
//                RegisterPage registerPage = new RegisterPage();
//                registerPage.setRegisterCallback(new EventHandler() {
//                    public void afterEvent(int event, int result, Object data) {
//// 解析注册结果
//                        if (result == SMSSDK.RESULT_COMPLETE) {
//                            @SuppressWarnings("unchecked")
//                            HashMap<String,Object> phoneMap = (HashMap<String, Object>) data;
//                            String country = (String) phoneMap.get("country");
//                            String phone = (String) phoneMap.get("phone");
//
//// 提交用户信息（此方法可以不调用）
//                            //registerUser(country, phone);
//                        }
//                    }
//                });
//                registerPage.show(AlarmActivity.this);
//            }
//        });
    }
}
