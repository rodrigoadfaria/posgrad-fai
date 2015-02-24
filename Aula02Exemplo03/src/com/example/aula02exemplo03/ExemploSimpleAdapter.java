package com.example.aula02exemplo03;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class ExemploSimpleAdapter extends ListActivity {


	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

        // Para customizar o ListView com nosso Layout.
        // Isto n�o � obrigat�rio se o layout do Android agradar voc�.
		// Por exemplo para customizar a cor de fundo
        setContentView(R.layout.layout_listview_contatos);

		ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
		
		// Cada item de uma linha precisa ser um HashMap
		// O HashMap cont�m as chaves e valores necess�rios para preencher os elementos da tela 
		for (int i = 0; i < 10; i++) {
			HashMap<String,String> item = new HashMap<String,String>();
			item.put( "nome","Nome "+i );
			item.put( "fone","Fone "+i );
			list.add(item);
		}

		// Utiliza o adaptador SimpleAdapter, 

		// Array que define as chaves do HashMap
		String[] from = new String[] { "nome","fone" };

		// text1 e text2 s�o padr�es do android para o layout nativo "two_line_list_item"
		int[] to = new int[] { android.R.id.text1, android.R.id.text2 };
		int layoytNativo = android.R.layout.two_line_list_item;

		setListAdapter(new SimpleAdapter(this, list, layoytNativo, from, to));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);

		// Pega o item naquela posi��o
		Object o = this.getListAdapter().getItem(position);

		String item = o.toString();

		//exibe um alerta
		Toast.makeText(this, "Voc� selecionou: " + item, Toast.LENGTH_SHORT).show();
	}
}
