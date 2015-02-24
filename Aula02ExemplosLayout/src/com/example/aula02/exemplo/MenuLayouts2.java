package com.example.aula02.exemplo;

import com.example.aula02.exemplo.viewgroup.ExemploGallery;
import com.example.aula02.exemplo.viewgroup.ExemploGridView;
import com.example.aula02.exemplo.viewgroup.ExemploImageSwitcher;
import com.example.aula02.exemplo.viewgroup.ExemploViewPager;
import com.example.aula02.exemplo.viewgroup.ExemploWebView;
import com.example.aula02.exemplo.viewgroup.tab.ExemploTabHost;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MenuLayouts2 extends ListActivity {

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		String[] items = new String[] { 
				"GridView",
				"Gallery",
				"ImageSwitcher",
				"TabHost",
				"WebView",
				"ViewPager - support library v4"
				};

		this.setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, items));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {

		switch (position) {
			case 0:
				startActivity(new Intent(this, ExemploGridView.class));
				break;
			case 1:
				startActivity(new Intent(this, ExemploGallery.class));
				break;
			case 2:
				startActivity(new Intent(this, ExemploImageSwitcher.class));
				break;
			case 3:
				startActivity(new Intent(this, ExemploTabHost.class));
				break;
			case 4:
				startActivity(new Intent(this, ExemploWebView.class));
				break;
			case 5:
				startActivity(new Intent(this, ExemploViewPager.class));
				break;
			default:
				finish();
				break;
		}
	}
}