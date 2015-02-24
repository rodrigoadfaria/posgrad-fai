package com.example.aula03.handler;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Activity que exibe o formul�rio de soma mas deixa a implementa��o do m�todo somar
 * para as subclasses
 * 
 */
public abstract class ExemploBaseSomaActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		setContentView(R.layout.layout_soma);

		Button b = (Button) findViewById(R.id.btOk);
		b.setOnClickListener(new Button.OnClickListener(){
			public void onClick(View v) {
				EditText n1 = (EditText) findViewById(R.id.n1);
				EditText n2 = (EditText) findViewById(R.id.n2);

				int i1  = Integer.parseInt(n1.getText().toString());
				int i2  = Integer.parseInt(n2.getText().toString());

				somar(i1, i2);
			}
		});
	}
	
	//M�todo abstrato que as subclasses devem implementar como quiserem
	//Esta classe apenas define a tela com o formul�rio para somar os n�meros
	protected abstract void somar(int n1, int n2);
}
