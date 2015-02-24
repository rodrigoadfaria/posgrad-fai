package com.example.aula03.handler;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

/**
 * Este exemplo demonstra como utilizar um Handler para agendar tarefas com
 * delay...
 * 
 * Para evitar a utilização do Thread.sleep, conforme a recomendação do Android
 * 
 */
public class ThreadSleepHandler extends Activity implements Runnable {

	private static final String TAG = "posgrad-fai";
	
	private int count;
	private TextView text;
	private Handler handler;
	private boolean on;

	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		text = new TextView(this);
		setContentView(text);

		handler = new Handler();
		handler.post(this);
	}

	@Override
	protected void onStart() {
		super.onStart();
		on = true;
		Log.i(TAG, "onStart()");
	}

	public void run() {
		if (on) {
			count++;
			Log.i(TAG, "Handler.run() count: " + count);
			text.setText("Contador: " + count);
			// Repetir depois de 1 segundo
			handler.postDelayed(this, 1000);
		} else {
			Log.i(TAG, "Handler fim.");
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		// Encerrar as mensagens do handler
		Log.i(TAG, "onDestroy()");
		on = false;
	}
}
