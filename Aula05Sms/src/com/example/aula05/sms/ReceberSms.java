package com.example.aula05.sms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

/**
 * BroadcastReceiver que exibe um alerta Toast quando um SMS e recebido
 * 
 */
public class ReceberSms extends BroadcastReceiver {

	private static final String TAG = "posgrad-fai";

	@Override
	public void onReceive(Context context, Intent intent) {
		Sms sms = new Sms();
		//Lê a mensagem
		SmsMessage msg = sms.receberMensagem(intent);
		String celular = msg.getDisplayOriginatingAddress();
		String mensagem = msg.getDisplayMessageBody();

		String texto = "BroadcastReceberSms: recebeu sms[" + celular + "] -> " + mensagem;
		Log.i(TAG, texto);

		Toast.makeText(context, texto, Toast.LENGTH_LONG).show();
	}
}
