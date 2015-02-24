package br.livro.android.cap17.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ExemploGet extends Activity {

	private static final String URL = "http://10.0.2.2:8080/livro_android/arquivo.txt";
	private static final String CATEGORIA = "livro";

	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		setContentView(R.layout.arquivo	);

		// faz o download
		StringBuffer sb = downloadFile(URL);

		System.out.println(sb.toString());

		TextView text = (TextView) findViewById(R.id.texto);
		text.setText(sb.toString());
	}

	private StringBuffer downloadFile(String link) {
		StringBuffer sb = new StringBuffer();
		try {
			// Cria a URL
			URL url = new URL(link);

			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			
			// seta o metodo
			connection.setRequestProperty("Request-Method","GET"); 
			 
			connection.setDoInput(true);
			connection.setDoOutput(false);

			connection.connect();
			
			// abre a conexão pra input
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			// le ate o final
			String s = "";
			while (null != ((s = br.readLine()))) {
			    sb.append(s);
			}
			br.close();
			
			connection.disconnect();

		} catch (MalformedURLException e) {
			Log.e(CATEGORIA, e.getMessage(),e);
		} catch (IOException e) {
			Log.e(CATEGORIA, e.getMessage(),e);
		}
		return sb;
	}
}
