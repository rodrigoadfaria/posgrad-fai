package com.example.aula05.maps.v2;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Exemplos de Google Maps Android API v2
 * 
 * 
 */
public class Menu extends ListActivity {

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		String[] mStrings = new String[] { 
				"MapFragment por XML",
				"MapFragment por API",
				"MapFragment por XML + GPS",
				"Sair"
		};
		this.setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, mStrings));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		switch (position) {
			case 0:
				startActivity(new Intent(this,ExemploMapaV2.class));
				break;
			case 1:
				startActivity(new Intent(this,ExemploMapaV2_API.class));
				break;
			case 2:
				startActivity(new Intent(this,ExemploMapaV2_GPS.class));
				break;
			default:
				finish();
		}
	}
}