package com.example.aula02exemplo03;

import com.example.aula02exemplo03.brasileirao.ExemploClubeActivity;
import com.example.aula02exemplo03.intent.ExemploAbrirBrowser;
import com.example.aula02exemplo03.intent.LigarParaTelefoneActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ExemploListActivity extends  ListActivity {
	
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		// Array de Strings para visualizar na Lista
		String[] itens = new String[] { "Listar Contatos", "Simple Adapter", "Clubes", "Browser", "Chamada", "Contatos", "Sair" };

		// Utiliza o adaptador ArrayAdapter, para exibir o array de Strings na tela.
		ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, itens);

		setListAdapter(adaptador);
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);

		// Pega o item naquela posição
		Object o = this.getListAdapter().getItem(position);

		String item = o.toString();

		//Exibe um alerta
		Toast.makeText(this, "Você selecionou: " + item, Toast.LENGTH_SHORT).show();
		
		switch (position) {
			case 0:
				//startActivity(new Intent(this, ExemploListContacts.class));
				break;
	
			case 1:
				startActivity(new Intent(this, ExemploSimpleAdapter.class));
				break;

			case 2:
				startActivity(new Intent(this, ExemploClubeActivity.class));
				break;

			case 3:
				startActivity(new Intent(this, ExemploAbrirBrowser.class));
				break;

			case 4:
				startActivity(new Intent(this, LigarParaTelefoneActivity.class));
				break;

			case 5:
				//startActivity(new Intent(this, VisualizarTodosContatos.class));
				break;

			default:
				finish();//Encerra o ciclo de vida desta app, chamando o método onDestroy()
		}
	}

}
