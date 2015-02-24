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
 * Exemplo com giroscópio
 */
public class GiroscopioActivity extends Activity implements SensorEventListener {

	@SuppressLint("InlinedApi")
	private static final int TIPO_SENSOR = Sensor.TYPE_ROTATION_VECTOR ;
	private SensorManager sensorManager;
	private Sensor sensor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sensor_xyz);

		sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

		sensor = sensorManager.getDefaultSensor(TIPO_SENSOR);
		if (sensor == null) {
			Toast.makeText(this, "Sensor TYPE_GYROSCOPE não disponível.", Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		// SENSOR_DELAY_NORMAL = delay 200.000 microssegundos
		// SENSOR_DELAY_UI = delay 60.000 microssegundos
		// SENSOR_DELAY_GAME = delay 20.000 microssegundos
		// SENSOR_DELAY_FASTEST = delay 0.0 microssegundos
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
		// SENSOR_STATUS_ACCURACY_LOW, SENSOR_STATUS_ACCURACY_MEDIUM,
		// SENSOR_STATUS_ACCURACY_HIGH, or SENSOR_STATUS_UNRELIABLE.
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		float values[] = SensorUtil.fixAcelerometro(this, event);
		
		float sensorX = values[0];
		float sensorY = values[1];
		float sensorZ = values[2];
		String debugText = "";

		((TextView) findViewById(R.id.tX)).setText("X: " + sensorX);
		((TextView) findViewById(R.id.tY)).setText("Y: " + sensorY);
		((TextView) findViewById(R.id.tZ)).setText("Z: " + sensorZ);
		((TextView) findViewById(R.id.tMsg)).setText(debugText);
	}
}
