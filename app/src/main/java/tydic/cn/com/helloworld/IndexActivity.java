package tydic.cn.com.helloworld;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class IndexActivity extends Activity {
    private WebView webView;
    private ProgressDialog dialog;
    class MHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==0x123){
                String url="www.baidu.com";
                if(msg.getData().getString("url").contains("http://")){
                    url=msg.getData().getString("url").replace("http://","");
                }
                init(url);
            }
            super.handleMessage(msg);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        Button button = (Button)findViewById(R.id.search);
        final EditText edit = (EditText)findViewById(R.id.url) ;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = getCurrentFocus();
                if (view != null) {
                    ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).
                            hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }
                Message msg = new Message();
                msg.what=0x123;
                Bundle bundle = new Bundle();
                bundle.putString("url",edit.getText().toString());
                msg.setData(bundle);
                MHandler mHandler =new MHandler();
                mHandler.sendMessage(msg);
            }
        });
        // Uri uri = Uri.parse(url); //url为你要链接的地址
        // Intent intent =new Intent(Intent.ACTION_VIEW, uri);
        // startActivity(intent);
    }

    private void init(String url) {
        final ProgressBar bar = (ProgressBar)findViewById(R.id.myProgressBar);
        // TODO Auto-generated method stub
        webView = (WebView) findViewById(R.id.web);
        // WebView加载本地资源
        // webView.loadUrl("file:///android_asset/example.html");
        // WebView加载web资源
        webView.loadUrl("http://"+url);
        // 覆盖WebView默认通过第三方或者是系统浏览器打开网页的行为，使得网页可以在WebVIew中打开
        webView.setWebViewClient(new WebViewClient(){

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是true的时候控制网页在WebView中去打开，如果为false调用系统浏览器或第三方浏览器去打开
                view.loadUrl(url);
                return true;
            }
            //WebViewClient帮助WebView去处理一些页面控制和请求通知

        });
        //启用支持JavaScript
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        //WebView加载页面优先使用缓存加载
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webView.setWebChromeClient(new WebChromeClient(){

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                // TODO Auto-generated method stub
                //newProgress 1-100之间的整数
                if(newProgress==100)
                {
                    //网页加载完毕，关闭ProgressDialog
                    bar.setVisibility(View.INVISIBLE);
                    EditText edit = (EditText)findViewById(R.id.url);
                    edit.setText(webView.getUrl());
                }
                else
                {
                    if (View.INVISIBLE == bar.getVisibility()) {
                        bar.setVisibility(View.VISIBLE);
                    }
                    bar.setProgress(newProgress);
                }
            }

            private void closeDialog() {
                // TODO Auto-generated method stub
                if(dialog!=null&&dialog.isShowing())
                {
                    dialog.dismiss();
                    dialog=null;
                }
            }

            private void openDialog(int newProgress) {
                // TODO Auto-generated method stub
                if(dialog==null)
                {
                    dialog=new ProgressDialog(IndexActivity.this);
                    dialog.setTitle("正在加载");
                    dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    dialog.setProgress(newProgress);
                    dialog.show();

                }
                else
                {
                    dialog.setProgress(newProgress);
                }


            }
        });



    }

    //改写物理按键——返回的逻辑
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if(keyCode==KeyEvent.KEYCODE_BACK)
        {
            //Toast.makeText(this, webView.getUrl(), Toast.LENGTH_SHORT).show();
            if(webView.canGoBack())
            {
                webView.goBack();//返回上一页面
                return true;
            }
            else
            {
                System.exit(0);//退出程序
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
