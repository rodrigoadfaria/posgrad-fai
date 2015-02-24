package com.example.aula03.activity;

import com.example.aula03.notification.ExemploCriaNotificacao;
import com.example.aula03.notification.ExemploCriaNotificacaoBigger;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Exemplo de como exibir uma notificação para o usuário
 * 
 */
public class Menu extends ListActivity {

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		String[] mStrings = new String[] { "Criar notificação", 
				"Criar notificação - grande", "Alarm Manager" };

		this.setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, mStrings));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {

		switch (position) {
			case 0:
				startActivity(new Intent(this, ExemploCriaNotificacao.class));
				break;
			case 1:
				startActivity(new Intent(this, ExemploCriaNotificacaoBigger.class));
				break;
				
			case 2:
				startActivity(new Intent(this, ExemploAgendarAlarme.class));
				break;
		}
	}
}