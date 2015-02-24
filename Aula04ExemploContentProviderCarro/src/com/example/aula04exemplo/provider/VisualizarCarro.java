package com.example.aula04exemplo.provider;

import com.example.aula04exemplobancodados.Carro.Carros;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

/**
 * Activity que pode ser iniciada com uma Intent ACTION_VIEW:
 * 
 * Exemplo:
 * 
 * --------------------------------------------------- 
 * int id = 1; 
 * Uri uri = Carros.getUriId(id);
 * startActivity(new Intent(Intent.ACTION_VIEW,uri));
 * ---------------------------------------------------
 * 
 * Utiliza o TableLayout para visualizar o carro
 * 
 */
public class VisualizarCarro extends Activity {
	
	private static final String TAG = "posgrad-fai";

	// Campos texto
	private TextView campoNome;
	private TextView campoPlaca;
	private TextView campoAno;

	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		setContentView(R.layout.visualizar_carro);

		String nome = null;
		String placa = null;
		int ano = 0;

		campoNome = (TextView) findViewById(R.id.cnome);
		campoPlaca = (TextView) findViewById(R.id.cplaca);
		campoAno = (TextView) findViewById(R.id.cano);

		// Uri que contém o endereço do carro
		Uri uri = getIntent().getData();

		if (uri != null) {
			Log.i(TAG, "Uri para visualizar: " + uri);

			// Busca no ContentProvider.
			Cursor c = getContentResolver().query(uri, null, null, null, null);

			boolean encontrou = c.moveToFirst();

			if (encontrou) {
				nome = c.getString(c.getColumnIndexOrThrow(Carros.NOME));
				placa = c.getString(c.getColumnIndexOrThrow(Carros.PLACA));
				ano = c.getInt(c.getColumnIndexOrThrow(Carros.ANO));
			}
		}

		if (nome != null) {
			campoNome.setText(nome);
		}
		if (placa != null) {
			campoPlaca.setText(placa);
		}
		if (ano != 0) {
			campoAno.setText(String.valueOf(ano));
		}
	}
}