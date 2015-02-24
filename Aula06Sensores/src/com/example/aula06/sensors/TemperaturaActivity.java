package com.example.aula06.sensors;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Exemplo com sensor de temperatura
 * 
 */
public class TemperaturaActivity extends Activity implements SensorEventListener {

	@SuppressLint("InlinedApi")
	private static final int TIPO_SENSOR = Sensor.TYPE_AMBIENT_TEMPERATURE;
	private SensorManager sensorManager;
	private Sensor sensor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sensor_xyz);

		sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

		if (sensor != null) {
			sensor = sensorManager.getDefaultSensor(TIPO_SENSOR);
		} else {
			Toast.makeText(this, "Sensor não disponível", Toast.LENGTH_SHORT).show();

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
		// Mudou o status de precisão do cursor
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		float sensorX = event.values[0];

		((TextView) findViewById(R.id.tX)).setText("X: " + sensorX);
	}
}
