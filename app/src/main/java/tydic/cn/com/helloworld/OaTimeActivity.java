package tydic.cn.com.helloworld;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.Call;
import tydic.cn.com.bena.AttendDayBean;

public class OaTimeActivity extends Activity {
    public static final String HOST = "http://oa.tydic.com/";
    private TextView tvText;
    public String htmlStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oa_time);
        tvText = (TextView) findViewById(R.id.text);
    }

    /**
     * 获取登录页面
     */
    private void loginPage() {
        OkHttpUtils.get().url(HOST + "login.jsp").build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Toast.makeText(OaTimeActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String response, int id) {
                analysisLoginPage(response);
            }
        });
    }

    private void analysisLoginPage(String html) {
        Document doc = Jsoup.parse(html);
        String action = doc.select("form").attr("action");
        OkHttpUtils.post()
                .url(HOST + action)
                .addParams("user.indexLayout", "3")
                .addParams("userInfo", "")
                .addParams("user.userCode", "D7248")
                .addParams("user.password", "12345678")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(OaTimeActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if (response.startsWith("<script>")) {
                            String regex = "(?<=\\().*(?=\\))";
                            Matcher m = Pattern.compile(regex).matcher(response);
                            if (m.find()) {
                                String toast = m.group();
                                Toast.makeText(OaTimeActivity.this, toast.substring(1, toast.length() - 2), Toast.LENGTH_SHORT).show();
                            }
                            return;
                        }
                        Log.i("onResponse", response);
                        htmlStr = response;
                        getAttendDay();
                        Log.i("onResponse", getEmployeeId(response));
                    }
                });

    }

    private String getEmployeeId(String html) {
        Document doc = Jsoup.parse(html);
        return doc.select("#userId").attr("value");
    }

    //http://oa.tydic.com/jsp/hr/attend/hr_attend.AttendDayResult_findAttendDayResult.action?functionlistCode=M005_F0030403_1
    private void getAttendDay() {
        OkHttpUtils.post().url(HOST + "jsp/hr/attend/hr_attend.AttendDayResult_findAttendDayResult.action?functionlistCode=M005_F0030403_1")
                .addParams("jsonStr", "{\"main\":{\"hrEmployeeId\":\""+ getEmployeeId(htmlStr)+"\",\"startDate\":\"" + getFirstDay() + "\",\"endDate\":\"" + getLastDay() + "\"},\"sub\":[]}")
                .addParams("page", "1")
                .addParams("pagesize", "31")
                .addParams("sortname", "attendDate desc, orgName,empDisp")
                .addParams("sortorder", "asc")
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Toast.makeText(OaTimeActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String response, int id) {
                AttendDayBean bean = new Gson().fromJson(response, AttendDayBean.class);
                if (bean == null) {
                    tvText.setText("无数据");
                    return;
                }
                List<AttendDayBean.RowsBean> rows = bean.getRows();
                StringBuilder sb = new StringBuilder();
                for (AttendDayBean.RowsBean row : rows) {
                    sb.append(row.getAttendDate()).append("\n");
                    sb.append("上班打卡时间：");
                    sb.append(row.getActualClockTime1());
                    sb.append("\n");
                    sb.append("下班打卡时间：");
                    sb.append(row.getActualClockTime2());
                    sb.append("\n\n");
                }
                tvText.setText(sb.toString());

            }
        });
    }


    private String getFirstDay() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINESE);
        //获取当前月第一天：
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
        String first = format.format(c.getTime());
        System.out.println("===============first:" + first);
        return first;
    }

    private String getLastDay() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINESE);

        //获取当前月最后一天
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        String last = format.format(ca.getTime());
        System.out.println("===============last:" + last);
        return last;
    }



    public void onRequest(View view) {
        loginPage();
    }
}
