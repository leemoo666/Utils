package com.ycx.former.webactivity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.ycx.former.R;

/**
 * Created by 李小明 on 17/5/18.
 * 邮箱:287907160@qq.com
 */

public class WebActivity extends Activity {

    public WebView webView;//网页

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        initView();
        webView.loadUrl("file:///android_asset/index.html");

        webView.setWebChromeClient(new WebChromeClient());
    }

    /**
     * 初始化控件
     */
    private void initView() {

        //初始化webView
        webView = (WebView) findViewById(R.id.wvWebActivity);

//        webView.setVerticalScrollBarEnabled(true);
//        webView.setHorizontalScrollBarEnabled(false);
//
//        webView.getSettings().setJavaScriptEnabled(true);
//
//
//        //支持屏幕缩放
//        webView.getSettings().setSupportZoom(false);
//        webView.getSettings().setBuiltInZoomControls(false);
//
//        //不显示webView缩放按钮
//        webView.getSettings().setDisplayZoomControls(false);
//
//        webView.getSettings().setDomStorageEnabled(true);
//        webView.requestFocus();


        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAllowContentAccess(true);
        webView.getSettings().setAppCacheEnabled(false);
        webView.getSettings().setBuiltInZoomControls(false);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);


    }

}
