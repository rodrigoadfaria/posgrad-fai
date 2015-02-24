package com.example.aula04exemplo.provider;

import java.util.ArrayList;

import com.example.aula04exemplobancodados.Carro;
import com.example.aula04exemplobancodados.CarroListAdapter;
import com.example.aula04exemplobancodados.Carro.Carros;

import android.content.Intent;
import android.database.Cursor;
import android.view.MenuItem;

/**
 * Activity que demonstra o cadastro de carros:
 * 
 * - ListActivity: para listar os carros
 * - RepositorioCarro para salvar os dados no banco
 * - Carro
 * 
 */
public class CadastroCarros extends com.example.aula04exemplo.provider.provider.api.copy.CadastroCarros {
	
	@Override
	protected void atualizarLista() {
		// Busca os carros do nosso content provider: content://com.example.bancodados.provider.carro/carros
		Cursor cursor = getContentResolver().query(Carros.CONTENT_URI, null, null, null, null);

		// Lê os carros do cursor
		this.carros = new ArrayList<Carro>();
		while (cursor.moveToNext()) {
			Carro c = new Carro();
			c.id 	= cursor.getInt(cursor.getColumnIndex(Carros._ID));
			c.ano 	= cursor.getInt(cursor.getColumnIndex(Carros.ANO));
			c.placa = cursor.getString(cursor.getColumnIndex(Carros.PLACA));
			c.nome 	= cursor.getString(cursor.getColumnIndex(Carros.NOME));
			this.carros.add(c);
		}
		// Fecha o cursor ao terminar de ler
		cursor.close();
		// Informa o adapter para exibir a lista de carros
		setListAdapter(new CarroListAdapter(this, this.carros));
	}

	@Override
	//Sobrescreve para informar a classe correta de EditarCarro e BuscarCarro
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		//Clicou no menu
		switch (item.getItemId()) {
			case INSERIR_EDITAR:
				//Abre a tela com o formulário para adicinoar
				startActivityForResult(new Intent(this, com.example.aula04exemplo.provider.EditarCarro.class), INSERIR_EDITAR);
				break;
			case BUSCAR:
				//Abre a tela para buscar o carro pelo nome
				startActivity(new Intent(this, com.example.aula04exemplo.provider.BuscarCarro.class));
			break;
		}
		return true;
	}

	@Override
	protected void editarCarro(int posicao) {
		// Recupera o carro selecionado
		Carro carro = this.carros.get(posicao);
		
		// Cria a intent para abrir a tela de editar (abre a activity do outro exemplo)
		Intent it = new Intent(this, com.example.aula04exemplo.provider.EditarCarro.class);
		//Passa o id do carro como parâmetro
		it.putExtra(Carros._ID,carro.id);

		//Abre a tela de edição
		startActivityForResult(it, INSERIR_EDITAR);
	}
}