package com.example.aula06.sensors;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Pressure in hPa (pressao do ambiente do ar)
 * 
 * Medida em hPa or mbar
 * 
 */
public class PressaoActivity extends Activity implements SensorEventListener {

	private static final int TIPO_SENSOR = Sensor.TYPE_PRESSURE;
	private SensorManager sensorManager;
	private Sensor sensor;
	private SeekBar progress;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sensor_progressbar);

		progress = (SeekBar) findViewById(R.id.progress);

		sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

		sensor = sensorManager.getDefaultSensor(TIPO_SENSOR);
		if (sensor != null) {

			// Define o valor maximo no ProgressBar
			float max = sensor.getMaximumRange();
			progress.setMax((int) max);

			Toast.makeText(this, "Sensor max " + max, Toast.LENGTH_SHORT).show();
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
		float valor = event.values[0];

		((TextView) findViewById(R.id.tX)).setText("Pressao: " + valor);

		progress.setProgress((int) valor);
	}
}
