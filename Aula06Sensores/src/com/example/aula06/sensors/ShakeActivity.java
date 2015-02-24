package com.example.aula06.sensors;

import android.content.Context;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.os.Vibrator;
import android.util.Log;

/**
 * Verifica se fez shake no device, isto é, se houve uma mudança brusca de
 * aceleração
 * 
 */
public class ShakeActivity extends AcelerometroActivity implements SensorEventListener {

	private float mAccel; 			// aceleração sem gravidade já com filtro
	private float mAccelCurrent; 	// aceleração com gravidade
	private float mAccelLast; 		// última aceleração
	int count = 3;

	@Override
	public void onSensorChanged(SensorEvent se) {
		// super.onSensorChanged(se);

		float x = se.values[0];
		float y = se.values[1];
		float z = se.values[2];

		// calcula a aceleração
		mAccelLast = mAccelCurrent;
		mAccelCurrent = (float) Math.sqrt((double) (x * x + y * y + z * z));

		// verifica a diferença
		float delta = mAccelCurrent - mAccelLast;

		// Aplica o filtro (low-cut filter)
		mAccel = mAccel * 0.9f + delta;

		if (mAccel > 5) {
			count++;
			Log.i("shake", " > " + count);
			// Precisa mexer 3 vezes
			if(count >= 3) {
				count = 0;
				// Se mudou bruscamente
				Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
			    vibrator.vibrate(2000);
			}
		}
	}
}
