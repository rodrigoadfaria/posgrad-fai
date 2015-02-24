package br.livro.android.cap17.http;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import br.livro.android.cap17.http.calc.ExemploCalculadora;
import br.livro.android.cap17.http.carros.ListaCarrosActivity;

/**
 * Exemplos de Http
 * 
 * @author rlecheta
 * 
 */
public class Menu extends ListActivity {
	private static final String[] ops = new String[] { "Calculadora Socket", "Calculadora HTTP", "Buscar arquivo texto",
			"Buscar imagem da Web", "Carros", "Sair" };

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		this.setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ops));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {

		switch (position) {
		case 0:
			try {
				startActivity(new Intent("CALCULADORA_SOCKET"));
			} catch (Exception e) {
				Toast.makeText(this, "Instale o projeto LivroAndroidCap17-Socket", Toast.LENGTH_SHORT).show();
			}
			break;
		case 1:
			startActivity(new Intent(this, ExemploCalculadora.class));
			break;
		case 2:
			startActivity(new Intent(this, BuscarArquivoTexto.class));
			break;
		case 3:
			startActivity(new Intent(this, BuscarImagemWeb.class));
			break;
		case 4:
			startActivity(new Intent(this, ListaCarrosActivity.class));
			break;
		default:
			finish();
			break;
		}
	}
}