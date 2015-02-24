package br.livro.android.cap17.socket;

import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Calculadora em Android, mas faz a soma chamando um Socket que está no
 * servidor
 * 
 * @author ricardo
 * 
 */
public class ExemploCalculadora extends Activity implements OnClickListener, Runnable {
	private static final String CATEGORIA = "livro";
	private static final String IP = "10.0.2.2";
	private static final int PORTA = 7777;

	@Override
	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);

		setContentView(R.layout.soma);

		Button b = (Button) findViewById(R.id.btSomar);
		b.setOnClickListener(this);
	}

	public void onClick(View view) {
		new Thread(this).start();
	}

	@Override
	public void run() {
		EditText textn1 = (EditText) findViewById(R.id.n1);
		EditText textn2 = (EditText) findViewById(R.id.n2);
		final TextView textSoma = (TextView) findViewById(R.id.soma);

		int n1 = Integer.parseInt(textn1.getText().toString());
		int n2 = Integer.parseInt(textn2.getText().toString());

		try {
			Log.i(CATEGORIA, "Socket conectnado no endereço: " + IP);
			Calculadora calculadora = new Calculadora(IP, PORTA);

			int soma = calculadora.somar(n1, n2);

			final String textoSoma = "SOMA: " + soma;

			Log.i(CATEGORIA, String.valueOf(textoSoma));

			// Atalho para um Handler
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					textSoma.setText(textoSoma);
					textSoma.setVisibility(View.VISIBLE);					
				}
			});
		} catch (IOException e) {
			Toast.makeText(this, "Erro: " + e.getMessage(), Toast.LENGTH_SHORT).show();
		}
	}
}