package com.example.aula03.brodcast.receiver;

import com.example.aula03.brodcast.activity.TelaTeste;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Simples BroadcastReceiver registrado no xml
 * 
 * Demonstra como utilizar uma intent com a flag FLAG_ACTIVITY_NEW_TASK para abrir uma Activity
 *
 */
public class ExemploReceiverNewActivity extends BroadcastReceiver {
	
	@Override
	public void onReceive(Context context, Intent intent) {

		Intent it = new Intent(context, TelaTeste.class);
		it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(it);
	}
}