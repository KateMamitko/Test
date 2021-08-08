package com.example.test.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.example.test.R;

public class ActivityWebView extends AppCompatActivity {
    private WebView webView;
    private String URl = "https://www.google.com/search";
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        Intent intent = getIntent();

        progressBar = findViewById(R.id.progressBarWeb);
        progressBar.setVisibility(View.VISIBLE);
        progressBar.setIndeterminate(true);

        webView = findViewById(R.id.WebView);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.loadUrl(URl);

    }
}