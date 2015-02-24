package com.example.aula03.service;

import com.example.aula03.service.service.aidl.ExemploBindServiceRemoto;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Exemplos de Service em background
 * 
 * 
 */
public class Menu extends ListActivity {

	private static final String[] ops = new String[] {
		"Exemplo Start Service","Exemplo Bind Service - Local","Exemplo Bind Service - AIDL (remoto)",
		"Sair"};

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		this.setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, ops));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {

		switch (position) {
			case 0:
				startActivity(new Intent(this, ExemploStartServiceActivity.class));
				break;
			case 1:
				startActivity(new Intent(this, ExemploBindServiceActivity.class));
				break;
			case 2:
				startActivity(new Intent(this, ExemploBindServiceRemoto.class));
				break;
			default:
				finish();
		}
	}
}