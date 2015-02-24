package com.example.aula02exemplo03.intent;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.example.aula02exemplo03.R;

/**
 * Abrir o browser no endere�o indicado
 * 
 */
public class ExemploAbrirBrowser extends Activity implements OnClickListener {

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		setContentView(R.layout.form_browser);

		final Button button = (Button) findViewById(R.id.botaoOk);
		button.setOnClickListener(this);
	}
	/**
	 * @see android.view.View$OnClickListener#onClick(android.view.View)
	 */
	public void onClick(View view) {

		EditText campoEndereco = (EditText) findViewById(R.id.campoEndereco);

		String endereco = campoEndereco.getText().toString();

		// Representa o endere�o que desejamos abrir em formato de URI (Unified Rsource Identifier)
		Uri uri = Uri.parse(endereco);

		// Cria a Intent com o endere�o
		Intent intent = new Intent(Intent.ACTION_VIEW, uri);

		// Envia a mensagem ao sistema operacional
		startActivity(intent);
	}
}
