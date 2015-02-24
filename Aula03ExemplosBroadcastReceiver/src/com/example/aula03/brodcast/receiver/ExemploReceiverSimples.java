package com.example.aula03.brodcast.receiver;

import android.content.Context;
import android.content.Intent;
import android.content.BroadcastReceiver;
import android.widget.Toast;

/**
 * Simples BroadcastReceiver registrado no AndroidManifest.xml
 * 
 */
public class ExemploReceiverSimples extends BroadcastReceiver {
	
	@Override
	public void onReceive(Context c, Intent intent) {
		//O Toast foi utilizado apenas para demonstração
		//Utilizar apenas para processamentos em background
		Toast.makeText(c, "ExemploReceiver simples", Toast.LENGTH_SHORT).show();
	}
}
