package com.example.aula03.activity;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ExemploAgendarAlarme extends Activity {

	private static final String TAG = "posgrad-fai";
	private static final int segundos = 5;
	
	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		TextView text = new TextView(this);
		text.setText("Alarme agendado para daqui a " + segundos + " segundos.");
		setContentView(text);

		// 5 segundos
		agendar(segundos);
	}

	private void agendar(int seconds) {
		// Intent para disparar o broadcast
		Intent it = new Intent("ABRIR_RECEIVER_SIMPLES");
		PendingIntent p = PendingIntent.getBroadcast(ExemploAgendarAlarme.this, 0, it, 0);
	
		// Para executar o alarme depois de x segundos a partir de agora
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(System.currentTimeMillis());
		c.add(Calendar.SECOND, seconds);
	
		// Agenda o alarme
		AlarmManager alarme = (AlarmManager) getSystemService(ALARM_SERVICE);
		long time = c.getTimeInMillis();
		alarme.set(AlarmManager.RTC_WAKEUP, time, p);
	
		Log.i(TAG, "Alarme agendado para daqui a " + seconds + " segundos.");
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.i(TAG, "onDestroy() - alarme cancelado.");
		Intent it = new Intent("ABRIR_RECEIVER_SIMPLES");
		PendingIntent p = PendingIntent.getBroadcast(ExemploAgendarAlarme.this, 0, it, 0);

		//Cancela o alarme
		AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
		am.cancel(p);
	}
	
}
