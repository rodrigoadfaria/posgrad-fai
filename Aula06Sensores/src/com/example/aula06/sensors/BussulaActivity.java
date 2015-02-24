package com.example.aula06.sensors;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Activity que utiliza o sensor TYPE_ORIENTATION para criar uma b�ssola
 * 
 */
public class BussulaActivity extends Activity implements SensorEventListener {

	/**
	 * Deprecado no Android 2.2 e agora falam para utilizar o m�todo
	 * getOrientation()
	 * 
	 * Mas este sensor continua sendo muito bom e simples para implementar
	 * b�ssolas
	 */
	@SuppressWarnings("deprecation")
	private static final int TIPO_SENSOR = Sensor.TYPE_ORIENTATION;
	private SensorManager sensorManager;
	private Sensor sensor;
	private BussolaView bussola;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_bussola);
		
		bussola = (BussolaView) findViewById(R.id.bussola);

		sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

		sensor = sensorManager.getDefaultSensor(TIPO_SENSOR);
		if (sensor == null) {
			Toast.makeText(this, "Sensor n�o dispon�vel", Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (sensor != null) {
			sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		sensorManager.unregisterListener(this);
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// Mudou o status de precis�o do cursor
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		// �ngulo em graus para o Norte
		float valor = event.values[0];
		// Atualiza a b�ssola
		bussola.setAngulo((int) valor);
		bussola.invalidate();
	}
}
