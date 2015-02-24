package com.example.aula05.sms;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Exemplos de envio e receber Sms
 * 
 */
public class Menu extends ListActivity {

	protected static final String TAG = "posgrad-fai";

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		String[] ops = new String[] {"Enviar Sms"};
		this.setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, ops));

		// Listener para receber liga��o
		//TelephonyManager t = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		//t.listen(new ReceberLigacaoListener(), PhoneStateListener.LISTEN_CALL_STATE);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		switch (position) {
		case 0:
			startActivity(new Intent(this,EnviarSmsActivity.class));
			break;
		default:
			finish();
		}
	}
}