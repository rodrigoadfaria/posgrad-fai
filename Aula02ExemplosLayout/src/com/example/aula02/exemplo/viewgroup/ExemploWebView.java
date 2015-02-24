package com.example.aula02.exemplo.viewgroup;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

@SuppressLint("SetJavaScriptEnabled")
public class ExemploWebView extends Activity {
	
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		WebView web = new WebView(this);

		WebSettings webSettings = web.getSettings();
		webSettings.setSavePassword(false);
		webSettings.setSaveFormData(false);
		webSettings.setJavaScriptEnabled(true);
		webSettings.setSupportZoom(false);

		web.loadUrl("http://m.uol.com.br");

		setContentView(web);
	}
}