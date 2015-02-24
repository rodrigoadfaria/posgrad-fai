package com.example.aula06.sensors;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Exemplo com aceler�metro
 */
public class AcelerometroActivity extends Activity implements SensorEventListener {

	private static final int TIPO_SENSOR = Sensor.TYPE_ACCELEROMETER;
	private SensorManager sensorManager;
	private Sensor sensor;
	protected long time;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sensor_xyz);

		sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

		sensor = sensorManager.getDefaultSensor(TIPO_SENSOR);
		if (sensor == null) {
			Toast.makeText(this, "Sensor n�o dispon�vel.", Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (sensor != null) {
			sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_FASTEST);
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
		// SENSOR_STATUS_ACCURACY_LOW, SENSOR_STATUS_ACCURACY_MEDIUM,
		// SENSOR_STATUS_ACCURACY_HIGH, or SENSOR_STATUS_UNRELIABLE.
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		long now = System.currentTimeMillis();
		if(now - time  > 1000) {
			time = now;

			float values[] = SensorUtil.fixAcelerometro(this, event);
			
			float sensorX = values[0];
			float sensorY = values[1];
			float sensorZ = values[2];
			
			TextView tx = (TextView) findViewById(R.id.tX);
			TextView tY = (TextView) findViewById(R.id.tY);
			TextView tZ = (TextView) findViewById(R.id.tZ);
			TextView tMsg = (TextView) findViewById(R.id.tMsg);
			if (tx != null) {
				tx.setText("X: " + sensorX);
				tY.setText("Y: " + sensorY);
				tZ.setText("Z: " + sensorZ);
				tMsg.setText("Rota��o: " + SensorUtil.getRotationString(this));
			}
		}
	}
}
