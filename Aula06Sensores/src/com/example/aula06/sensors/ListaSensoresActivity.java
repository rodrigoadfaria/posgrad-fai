package com.example.aula06.sensors;

import java.util.List;

import android.app.ListActivity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Lista todos os sensores disponíveis
 * 
 */
public class ListaSensoresActivity extends ListActivity {
	private List<Sensor> sensorList;

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		// Lê a lista de sensores
		SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		List<Sensor> sensorList = sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER);
		// Cria o array com o nome de cada sensor
		String array[] = new String[sensorList.size()];
		for (int i = 0; i < array.length; i++) {
			Sensor s = sensorList.get(i);
			array[i] = s.getName() + " - " + s.getVendor() + " - " + s.getType();
		}
		// Cria o adapter para popular o ListView
		int layout = android.R.layout.simple_list_item_1;
		ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, layout, array);
		this.setListAdapter(adaptador);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// Recupera o sensor selecionado
		Sensor s = sensorList.get(position);
		String msg = s.getName() + " - " + s.getVendor() + " - " + s.getType();
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
	}
}