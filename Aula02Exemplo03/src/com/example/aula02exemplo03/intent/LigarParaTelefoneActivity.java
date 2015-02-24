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
 * Fazer uma liga��o para um n�mero indicado
 * 
 */
public class LigarParaTelefoneActivity extends Activity implements OnClickListener {

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		setContentView(R.layout.form_telefone);

		final Button button = (Button) findViewById(R.id.botaoOk);
		button.setOnClickListener(this);
	}

	/**
	 * @see android.view.View$OnClickListener#onClick(android.view.View)
	 */
	public void onClick(View view) {

		EditText campoTelefone = (EditText) findViewById(R.id.campoTelefone);

		String telefone = campoTelefone.getText().toString();

		//URI do n�mero do telefone
		Uri uri = Uri.parse("tel:"+telefone);
		
		//Cria a Intent com o endere�o
		
		//Precisa de permiss�o: <uses-permission android:name="android.permission.CALL_PHONE"/>
		Intent intent = new Intent(Intent.ACTION_CALL,uri);
//		Intent intent = new Intent(Intent.ACTION_DIAL,uri); 

		//Envia a mensagem ao sistema operacional
		startActivity(intent); 


	}

}
