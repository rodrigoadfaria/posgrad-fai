package com.example.aula05.sms;

import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * Listener para receber ligação
 * 
 * TelephonyManager t = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
 * t.listen(new ReceberLigacaoListener(), PhoneStateListener.LISTEN_CALL_STATE);
 * 
 */
public class ReceberLigacaoListener extends PhoneStateListener {

	private static final String TAG = "posgrad-fai";

	@Override
	public void onCallStateChanged(int state, String incomingNumber) {
		Log.v(TAG, "listener >>  Incoming Number: " + incomingNumber);

		super.onCallStateChanged(state, incomingNumber);

		switch (state) {
			case TelephonyManager.CALL_STATE_IDLE:
				Log.i(TAG, "CALL_STATE_IDLE");
				break;
			case TelephonyManager.CALL_STATE_OFFHOOK:
				Log.i(TAG, "CALL_STATE_OFFHOOK");
				break;
			case TelephonyManager.CALL_STATE_RINGING:
				//Está tocacdo
				Log.i(TAG, "Recebeu ligação: " + incomingNumber);
				break;
			default:
				Log.w(TAG, "unknown call state: " + state);
		}
	}
}
