package com.example.aula04exemplo.provider.teste;

import java.util.ArrayList;

import com.example.aula04exemplobancodados.Carro;
import com.example.aula04exemplobancodados.Carro.Carros;
import com.example.aula04exemplobancodados.CarroListAdapter;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Demonstra a busca no content provider dos carros utilizando as classes do projeto 'Aula04ExemploBancoDados-Library'
 * 
 * Assim podemos utilizar até o adapter para listar os carros
 * 
 * 
 */
public class ListarCarros extends ListActivity {
	private ArrayList<Carro> carros;

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		// Busca os carros content provider
		Cursor cursor = getContentResolver().query(Carros.CONTENT_URI, null, null, null, null);

		// Lê os carros do cursor
		carros = new ArrayList<Carro>();
		while (cursor.moveToNext()) {
			Carro c = new Carro();
			c.ano = cursor.getInt(cursor.getColumnIndex(Carros.ANO));
			c.placa = cursor.getString(cursor.getColumnIndex(Carros.PLACA));
			c.nome = cursor.getString(cursor.getColumnIndex(Carros.NOME));
			this.carros.add(c);
		}
		// Fecha o cursor ao terminar de ler
		cursor.close();
		// Informa o adapter para exibir a lista de carros
		setListAdapter(new CarroListAdapter(this, this.carros));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long itemId) {
		super.onListItemClick(l, v, position, itemId);

		Carro c = carros.get(position);

		Toast.makeText(this, "Carro selecionado: Id: " + c.id + ", Nome: " + c.nome + ", Placa: " + c.placa + ", Ano: " + c.ano,
				Toast.LENGTH_SHORT).show();

	}
}
