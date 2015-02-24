package com.example.aula03.alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * BroadcastReceiver para receber o alarme depois do tempo especificado
 * 
 */
public class ReceberAlarme extends BroadcastReceiver {
	
	private static final String TAG = "posgrad-fai";

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.i(TAG, "Alarme disparado!");

		// Podemos iniciar uma activity, servi�o ou exibir uma notifica��o ao usu�rio aqui
		Toast.makeText(context, "Alarme disparado!", Toast.LENGTH_SHORT).show();
	}
}