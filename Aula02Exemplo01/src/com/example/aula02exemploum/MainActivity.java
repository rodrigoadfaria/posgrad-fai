package com.example.aula02exemploum;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	protected static final String TAG = "posgrad-fai";

	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		Log.i(TAG, getClassName() + ".onCreate() " +
				"chamado: " + icicle);

		TextView t = new TextView(this);
		t.setText("Exemplo do ciclo de vida.\nConsulte " +
				"os logs no LogCat.");
		setContentView(t);
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
