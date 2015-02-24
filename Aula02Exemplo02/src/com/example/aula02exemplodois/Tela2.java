package com.example.aula02exemplodois;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Tela2 extends Activity {

	protected static final String TAG = "posgrad-fai";
	
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		Log.i(TAG, getClassName() + 
				".onCreate() chamado: " + icicle);
		
		TextView view = new TextView(this);
		view.setText("Esta é a tela 2");
		setContentView(view);
		
		Intent it = getIntent();
		if(it != null){ //Se a app foi aberta pelo usuário, o Intent pode ser null
			Bundle params = it.getExtras();
			if (params != null) { //Se nenhum parâmetro foi passado, o Bundle pode ser null
				String msg = params.getString("msg");
				Log.i(TAG, "Mensagem: " + msg);
				view.setText(msg);
			}
		}
	}
	
	protected void onStart() {
		super.onStart();
		Log.i(TAG, getClassName() + ".onStart() chamado.");
	}
	
	protected void onRestart() {
		super.onRestart();
		Log.i(TAG, getClassName() + ".onRestart() chamado.");
	}
	
	protected void onResume() {
		super.onResume();
		Log.i(TAG, getClassName() + ".onResume() chamado.");
	}
	
	protected void onPause() {
		super.onPause();
		Log.i(TAG, getClassName() + ".onPause() chamado.");
	}
	
	protected void onStop() {
		super.onStop();
		Log.i(TAG, getClassName() + ".onStop() chamado.");
	}
	
	protected void onDestroy() {
		super.onDestroy();
		Log.i(TAG, getClassName() + ".onDestroy() chamado.");
	}
	
	private String getClassName() {
		//Retorna o nome da classe sem o pacote
		String s = getClass().getName();
		return s.substring(s.lastIndexOf("."));
	}
}
