package com.example.aula04.preferences;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;

/**
 * Exemplo que demonstra como utilizar a classe SharedPreferences
 * 
 */
public class ExemploPreferencias extends Activity implements OnClickListener {

	private static final String TAG = "posgrad-fai";

	private static final String PREF_FILE = "posgrad_pref_app";
	private static final String STATUS = "status";
	private SharedPreferences pref;

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		setContentView(R.layout.form_pref);
		pref = getSharedPreferences(PREF_FILE, MODE_PRIVATE);

		// O segundo argumento é o valor default se não encontrar
		boolean marcado = pref.getBoolean(STATUS, false);

		Log.i(TAG,"Status atual: " + marcado);

		CheckBox check = (CheckBox) findViewById(R.id.check);
		check.setChecked(marcado);

		((Button) findViewById(R.id.btSalvar)).setOnClickListener(this);
	}
	
	/**
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	public void onClick(View view) {
		// Abre a preferência para edição
		SharedPreferences.Editor editor = pref.edit();

		CheckBox check = (CheckBox) findViewById(R.id.check);

		boolean isChecked = check.isChecked();

		// Atualiza o valor
		editor.putBoolean(STATUS, isChecked);

		Log.i(TAG,"Status salvo para: " + isChecked);

		//Faz commit para salvar os dados
		editor.commit();
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.i(TAG, "onDestroy()");
	}
}