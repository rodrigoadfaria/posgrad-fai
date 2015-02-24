package com.example.aula03.brodcast.boot;

import android.content.Context;
import android.content.Intent;
import android.content.BroadcastReceiver;
import android.util.Log;

/**
 * BroadcastReceiver que executa quando o SO termina de iniciar
 * 
 */
public class ExemploBoot extends BroadcastReceiver {
	
	private static final String TAG = "posgrad-fai";
	
	@Override
	public void onReceive(Context context, Intent intent) {
		Log.i(TAG, "O sistema operacional foi iniciado com sucesso!!!");
	}
}
