package com.example.aula03.brodcast.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Simples BroadcastReceiver registrado no xml
 * 
 * Demonstra como ocorre o erro Android Not Responding (ANR)
 * caso o Broadcast demore mais do que 10 segundos para executar
 * 
 */
public class ExemploReceiverANR extends BroadcastReceiver {
	
	private static final String TAG = "posgrad-fai";
	
	@Override
	public void onReceive(Context context, Intent intent) {
		try {
			Log.i(TAG,"Teste ANR sleep ...");
			Thread.sleep(15000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}