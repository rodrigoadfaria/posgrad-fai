package com.example.aula03.service;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Exemplo de como utilizar os m�todos startService(intent) e
 * stopService(intent)
 * 
 * Service est� configurado com um filtro para a a��o "SERVICE_UM"
 * 
 * <service android:name=".service.ExemploStartService"> <intent-filter>
 * <action android:name="SERVICE_UM" /> <category
 * android:name="android.intent.category.DEFAULT" /> </intent-filter> </service>
 * 
 */
public class ExemploStartServiceActivity extends Activity {
	
	private static final String TAG = "posgrad-fai";

	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		setContentView(R.layout.form_start_stop);

		// mesma intent � utilizada para iniciar e parar
		final Intent it = new Intent("SERVICE_UM");

		Button bIniciar = (Button) findViewById(R.id.btIniciar);
		bIniciar.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				// iniciar o servi�o
				startService(it);
			}
		});

		Button bParar = (Button) findViewById(R.id.btParar);
		bParar.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				// parar o servi�o
				stopService(it);
			}
		});
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.i(TAG, "ExemploIniciarServico.onDestroy()");
	}
}
