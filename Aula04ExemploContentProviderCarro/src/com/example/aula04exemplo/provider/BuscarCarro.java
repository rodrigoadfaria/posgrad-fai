package com.example.aula04exemplo.provider;

import com.example.aula04exemplobancodados.Carro;
import com.example.aula04exemplobancodados.Carro.Carros;

import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

/**
 * Buscar o Carro.
 * 
 */
public class BuscarCarro extends com.example.aula04exemplo.provider.provider.api.copy.BuscarCarro {
	
	private static final String TAG = "posgrad-fai";

	// Sobrescreve para buscar com um content provider
	protected Carro buscarCarro(String nome) {

		Log.i(TAG, "Buscando carro: " + nome);

		Uri uri = Carros.CONTENT_URI;

		String where = Carros.NOME + "=?";
		String[] whereArgs = new String[] { nome };
		Cursor c = getContentResolver().query(uri, null, where, whereArgs, null);

		if (c.moveToFirst()) {

			long id = c.getLong(c.getColumnIndexOrThrow(Carros._ID));
			nome = c.getString(c.getColumnIndexOrThrow(Carros.NOME));
			String placa = c.getString(c.getColumnIndexOrThrow(Carros.PLACA));
			int ano = c.getInt(c.getColumnIndexOrThrow(Carros.ANO));

			Log.i(TAG, "Carro encontrado com id: " + id);

			Carro carro = new Carro(nome, placa, ano);

			return carro;
		}

		return null;
	}
}
