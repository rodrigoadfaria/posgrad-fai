package com.example.aula05.sms;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;

/**
 * Classe que encapsula a lógica para enviar e receber um SMS
 * 
 */
public class Sms {
	private static final String TAG = "posgrad-fai";

	//Envia um sms para o numero indicado
	public void enviarSms(Context context, String destino, String mensagem) {
		try {
			SmsManager smsManager = SmsManager.getDefault();
			PendingIntent pIntent = PendingIntent.getBroadcast(context, 0, new Intent(), 0);
			
			smsManager.sendTextMessage(destino, null, mensagem, pIntent, null);
		} catch (Exception e) {
			Log.e(TAG, "Erro ao enviar o SMS: " + e.getMessage(),e);
		}
	}

	//Lê uma mensagem da Intent. A Intent é recebida por um IntentFilter
	//configurado para a ação "android.provider.Telephony.SMS_RECEIVED"
	public SmsMessage receberMensagem(Intent intent) {
		SmsMessage[] mensagens = getMessagesFromIntent(intent);
		if (mensagens != null) {
			return mensagens[0];
		}
		
		return null;
	}

	/**
	 * O método para ler um SMS foi removido da classe Telephony na versão 1.0
	 * Essa implementação é a original do Google das classes que foram descompiladas 
	 * pela comunidade de desenvolvedores e não há suporte algum sobre essa parte
	 * da API.
	 * 
	 * Inclusive há um bug para tratar deste assunto no Google.
	 * 
	 * @param intent
	 * @return
	 */
	private SmsMessage[] getMessagesFromIntent(Intent intent) {
		Object messages[] = (Object[]) (Object[]) intent.getSerializableExtra("pdus");
		if (messages == null)
			return null;
		
		byte pduObjs[][] = new byte[messages.length][];
		for (int i = 0; i < messages.length; i++)
			pduObjs[i] = (byte[]) (byte[]) messages[i];

		byte pdus[][] = new byte[pduObjs.length][];
		int pduCount = pdus.length;
		if (pduCount == 0) {
			return null;
		}
		
		SmsMessage msgs[] = new SmsMessage[pduCount];
		for (int i = 0; i < pduCount; i++) {
			pdus[i] = pduObjs[i];
			msgs[i] = SmsMessage.createFromPdu(pdus[i]);
			
			String celular = msgs[0].getDisplayOriginatingAddress();
			String mensagem = msgs[0].getDisplayMessageBody();
			Log.d(TAG, "Sms.Mensagem: " + celular + " -> " + mensagem);
		}
		
		return msgs;
	}
}
