package com.example.aula06.sensors;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Exemplos de Sensores
 * 
 */
public class Menu extends ListActivity {

	private static final String[] ops = new String[] {
		"Listar Sensores", "Temperatura", "Luminosidade", "Pressao", 
		"Orientacao - Bússula", "Proximidade", "Acelerômetro",
		"Acelerômetro View", "Shake","Giroscopio", "Sair" };

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		int layout = android.R.layout.simple_list_item_1;
		ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, layout, ops);
		this.setListAdapter(adaptador);
		
//		startActivity(new Intent(this, AcelerometroViewActivity.class));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {

		switch (position) {
		case 0:
			startActivity(new Intent(this, ListaSensoresActivity.class));
			break;
		case 1:
			startActivity(new Intent(this, TemperaturaActivity.class));
			break;
		case 2:
			startActivity(new Intent(this, LuminosidadeActivity.class));
			break;
		case 3:
			startActivity(new Intent(this, PressaoActivity.class));
			break;
		case 4:
			startActivity(new Intent(this, BussulaActivity.class));
			break;
		case 5:
			startActivity(new Intent(this, ProximidadeActivity.class));
			break;
		case 6:
			startActivity(new Intent(this, AcelerometroActivity.class));
			break;
		case 7:
			startActivity(new Intent(this, AcelerometroViewActivity.class));
			break;
		case 8:
			startActivity(new Intent(this, ShakeActivity.class));
			break;
		case 9:
			startActivity(new Intent(this, GiroscopioActivity.class));
			break;
		default:
			finish();
		}
	}
}