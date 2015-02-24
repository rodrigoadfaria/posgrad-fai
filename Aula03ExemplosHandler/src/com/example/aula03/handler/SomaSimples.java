package com.example.aula03.handler;

import android.widget.TextView;

public class SomaSimples extends ExemploBaseSomaActivity {

	protected void somar(int i1, int i2) {
		int soma = i1 + i2;
		
		TextView somaText = (TextView) findViewById(R.id.labelSoma);
		somaText.setText(String.valueOf("Soma: " + soma));
	}
}
