package com.example.intentlink;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;

import java.util.HashMap;
import java.util.Map;

import javax.xml.transform.Result;

public class MainActivity extends AppCompatActivity {
    WebView webView;
    final String orderInfo = "";
    //    final String orderInfo = info;   // 订单信息
    private Handler mHandler = new Handler() {
        public void handleMessage(final Message msg) {

            Log.v("hank", "" + msg.obj.toString());
        }

        ;
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.webview);
        setMessageInfo();
//        initWebView();

    }

    //    app_id=2015052600090779&
//    biz_content=
//    &charset=utf-8
//    &method=alipay.trade.app.pay
//    sign_type=RSA2&timestamp=2016-08-15%2012%3A12%3A15&version=1.0
//    sign=MsbylYkCzlfYLy9PeRwUUIg9nZPeN9SfXPNavUCroGKR5Kqvx0nEnd3eRmKxJuthNUx4ERCXe552EV9PfwexqW%2B1wbKOdYtDIb4%2B7PL3Pc94RZL0zKaWcaY3tSL89%2FuAVUsQuFqEJdhIukuKygrXucvejOUgTCfoUdwTi7z%2BZzQ%3D
    private void setMessageInfo() {


        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(MainActivity.this);
                Map<String,String> result = alipay.payV2(orderInfo,true);

                Message msg = new Message();
                msg.obj = result;
                mHandler.sendMessage(msg);

            }
        };

        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    private void initWebView() {
        webView.loadUrl("http://wap.ljz789.com/");
        WebViewClient webViewClient = new WebViewClient();//網頁視野客戶端處理物件
        webView.setWebViewClient(webViewClient);//設定網頁客戶端使用(webViewClient ),這樣使用我們自己的view到下一頁

        WebSettings Settings = webView.getSettings();//webView.取得設定物件(回傳WebSettings)
        Settings.setJavaScriptEnabled(true); //設定js是否開啟(bollean是/否)
    }


}
