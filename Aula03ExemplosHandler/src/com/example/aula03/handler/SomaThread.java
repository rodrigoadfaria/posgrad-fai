package com.example.aula03.handler;

import android.widget.TextView;

/**
 * Implementa a soma em uma Thread separada, mas isto da erro.
 * Mensagem: "Only the original thread that created a view hierarchy can touch its views."
 * 
 * Como foi comentado em sala, não pode-se atualizar componentes
 * que estão executando na UI Thread se estivermos processando algo em
 * uma Thread separada.
 * 
 * Ver exemplo do {@link SomaComHandler} handler que corrige isto.
 * 
 */
public class SomaThread extends ExemploBaseSomaActivity {
	@Override
	protected void somar(final int n1, final int n2) {
		new Thread(){
			public void run() {
				int soma = n1 + n2;
				TextView t = (TextView) findViewById(R.id.labelSoma);
				t.setText(String.valueOf("Soma: " + soma));
			}
		}.start();
	}
}
