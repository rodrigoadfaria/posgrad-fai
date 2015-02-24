package br.livro.android.cap17.http.calc;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import android.util.Log;

/**
 * Simples Calculadora que utiliza uma HttpURLConnection.
 * 
 * Recebe os números pelo construtor na DataInputStream
 * 
 * e envia a resposta com o método enviar(DataOutputStream)
 * 
 * @author ricardo
 *
 */
public class CalculadoraStream {
	private static final String TAG = "ID";

	private int soma;

	private DataOutputStream out;
	private DataInputStream in;

	private HttpURLConnection connection;

	public CalculadoraStream(String urlString) throws IOException{
		Log.i(TAG,"Criando URL " + urlString);

		URL url = new URL(urlString);

		connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("POST");

		connection.setDoInput(true);
		connection.setDoOutput(true);		

		connection.connect();

        Log.i(TAG,"HttpURLConnection criada.");
	}

	public int somar(int n1, int n2) throws IOException {

		try {
			Log.i(TAG,"enviando numeros... " + n1 + " + " + n2);

			out 	= new DataOutputStream(connection.getOutputStream());

			//envia os numeros
			out.writeInt(n1);
			out.writeInt(n2);

			//envia
			out.flush();

			Log.i(TAG,"lendo resposta...");

			int code 	= connection.getResponseCode();
			String msg 	= connection.getResponseMessage();
			Log.i(TAG,"lendo resposta["+code+"] - " + msg);

			//se deu OK
			if (code == 200) {
				in = new DataInputStream(connection.getInputStream());
				//le a resposta
				soma = in.readInt();
				Log.i(TAG, "soma: " + soma);
			}
			return soma;
		} finally {
			// sempre fecha as streams
			close();
		}
	}

	public void close() throws IOException  {
		if(out != null){
			out.close();
		}
		if(in != null){
			in.close();
		}
		if(connection != null){
			connection.disconnect();
		}
	}
}
