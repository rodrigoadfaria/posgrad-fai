package com.example.aula02.exemplo.viewgroup.tab;

import com.example.aula02.exemplo.R;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabContentFactory;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

/**
 * Deprecated: O Google para utilizar Fragments no lugar desta classe.
 * Mas você precisa aprender muitas coisas antes de Fragments.
 * 
 */
@SuppressWarnings("deprecation")
public class ExemploTabHost extends TabActivity implements OnTabChangeListener, TabContentFactory {
	private static final String TAG = "posgrad-fai";

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		// getTabHost() é da TabActivity
		TabHost tabHost = getTabHost();
		tabHost.setOnTabChangedListener(this);

		// Tab 1 (abrir com Intent -> Activity Tab1.class)
		TabSpec tab1 = tabHost.newTabSpec("Tab 1");
		tab1.setIndicator("Tab 1", getResources().getDrawable(R.drawable.smile1));
		tab1.setContent(new Intent(this, Tab1.class));
		tabHost.addTab(tab1);

		// Tab 2 - Utiliza o método TabContentFactory.createTabContent(String tabId)
		TabSpec tab2 = tabHost.newTabSpec("Tab 2");
		tab2.setIndicator("Tab 2", getResources().getDrawable(R.drawable.smile2));
		tab2.setContent(this);// TabContentFactory.createTabContent(String tabId)
		tabHost.addTab(tab2);
	}

	public View createTabContent(String tabId) {
		TextView tv = new TextView(this);
		tv.setText("Utilizando uma factory para criar a aba: " + tabId);
		return tv;
	}

	public void onTabChanged(String tabId) {
		Log.i(TAG, "Trocou aba: " + tabId);
	}
}