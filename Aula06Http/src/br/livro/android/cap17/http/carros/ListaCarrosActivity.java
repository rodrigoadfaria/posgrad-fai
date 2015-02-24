package br.livro.android.cap17.http.carros;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Exemplo de um Adapter customizado, exibindo imagens de carros vindo da web
 * 
 * Uma requisiçao HTTP é efetuada para buscar os carros de um Servlet
 * 
 * E retornado o id do carro, nome e uma imagem
 * 
 * @author ricardo
 * 
 */
public class ListaCarrosActivity extends ListActivity implements Runnable {
	private static final String CATEGORIA = "livro";
	private static final String URL = "http://10.0.2.2:8080/livro_android/listaCarros";

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		
		new Thread(this).start();
	}
	
	@Override
	public void run() {
		final List<Carro> carros = downloadCarros(URL);
		
		final Context context = this;
		
		// Atualiza utilizando handler para sincronizar esta thread com a interface
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				setListAdapter(new CarroAdapter(context, carros));				
			}
		});
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		// Pega o carro naquela posição
		Carro c = (Carro) this.getListAdapter().getItem(position);
		// Exibe um alerta
		Toast.makeText(this, "Você selecionou o Carro: " + c.getNome(), Toast.LENGTH_SHORT).show();
	}

	// Busca os carros do Servlet
	private List<Carro> downloadCarros(String url) {
		Log.i(CATEGORIA, "downloadCarros: " + url);
		try {
			// Cria a URL
			URL u = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) u.openConnection();
			conn.setRequestMethod("GET");
			conn.setDoInput(true);
			conn.setDoOutput(false);

			conn.connect();

			InputStream in = conn.getInputStream();
			DataInputStream dataIn = new DataInputStream(in);

			int quantidade = dataIn.readInt();
			Log.i(CATEGORIA, "Lendo " + quantidade + " carros...");
			List<Carro> carros = new ArrayList<Carro>(quantidade);
			for (int i = 0; i < quantidade; i++) {
				Carro c = new Carro();
				c.deserialize(dataIn);
				Log.i(CATEGORIA, "Carro: " + c.getNome());
				carros.add(c);
			}

			dataIn.close();
			in.close();

			return carros;
		} catch (MalformedURLException e) {
			Log.e(CATEGORIA, e.getMessage(), e);
		} catch (IOException e) {
			Log.e(CATEGORIA, e.getMessage(), e);
		}
		return null;
	}
}
