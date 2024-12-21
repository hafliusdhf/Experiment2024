package com.example.casper.Experiment2024.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.casper.Experiment2024.R;

public class WebViewFragment extends Fragment {
    private WebView webView;

    public WebViewFragment() {
        super(R.layout.fragment_web_view); // 这里需要创建相应的布局文件
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_web_view, container, false);
        webView = view.findViewById(R.id.web_view); // 确保在布局文件中有这个ID
        webView.setWebViewClient(new WebViewClient()); // 使得点击链接时在WebView内打开
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true); // 启用JavaScript
        webView.loadUrl("http://www.baidu.com"); // 加载百度首页
        return view;
    }
}