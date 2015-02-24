package com.example.aula06.gestures;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Exemplos de Gestos
 * 
 */
public class Menu extends ListActivity {

	private static final String[] ops = new String[] {
		"Detectar Gesto",
		"Salvar Gesto",
		"Gesto por Swipe lateral",
		"Gesto Pinch/Zoom"};

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		int layout = android.R.layout.simple_list_item_1;
		ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,layout, ops);
		this.setListAdapter(adaptador);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {

		switch (position) {
			case 0:
				startActivity(new Intent(this, GestosActivity.class));
				break;
			case 1:
				startActivity(new Intent(this, SalvarGestoActivity.class));
				break;
			case 2:
				startActivity(new Intent(this, GestosSwipeActivity.class));
				break;
			case 3:
				startActivity(new Intent(this, GestosZoomActivity.class));
				break;
			default:
				finish();
		}
	}
}