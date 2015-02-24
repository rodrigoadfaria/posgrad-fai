package com.example.aula02exemplo03.brasileirao;

import java.util.ArrayList;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Exemplo de um Adapter customizado, exibindo imagens
 *
 */
public class ExemploClubeActivity extends ListActivity {

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		ArrayList<Clube> list = new ArrayList<Clube>();

		list.add(new Clube("Cruzeiro", Clube.CRUZEIRO));
		list.add(new Clube("Internacional", Clube.INTERNACIONAL));
		list.add(new Clube("São Paulo", Clube.SAO_PAULO));
		list.add(new Clube("Grêmio", Clube.GREMIO));

		// Adaptador de lista customizado para cada linha
		setListAdapter(new ClubeAdapter(this, list));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);

		// Pega o Clube selecionado naquela posição
		Clube clube = (Clube) this.getListAdapter().getItem(position);

		// Exibe um alerta
		Toast.makeText(this, "Você selecionou o Clube: " + clube.nome, Toast.LENGTH_SHORT).show();
	}
}
