package br.livro.android.cap17.http.calc;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import br.livro.android.cap17.http.helper.Http;

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
public class Calculadora {
	private final String url;
	public Calculadora(String url) throws IOException{
		this.url = url;
	}
	public String somar(int n1, int n2) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("n1", n1);
		params.put("n2", n2);
		String soma = Http.getInstance(Http.NORMAL).doPost(url, params);
		return soma;
	}
}