package com.example.aula03.brodcast.receiver;

import com.example.aula03.brodcast.Menu;

import android.content.Context;
import android.content.Intent;
import android.content.BroadcastReceiver;
import android.widget.Toast;

/**
 * BroadcastReceiver registrado pela API
 * @see Menu#onCreate(android.os.Bundle)
 * 
 */
public class ExemploReceiverDinamico extends BroadcastReceiver {
	
	@Override
	public void onReceive(Context c, Intent intent) {
		//O Toast foi utilizado apenas para demonstração
		//Utilizar apenas para processamentos em background
		Toast.makeText(c, "BroadcastReceiver dinâmico registrado na API !", Toast.LENGTH_SHORT).show();
	}
}
