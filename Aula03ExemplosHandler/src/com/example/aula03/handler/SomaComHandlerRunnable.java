package com.example.aula03.handler;

import android.os.Handler;
import android.widget.TextView;

public class SomaComHandlerRunnable extends ExemploBaseSomaActivity  {
	
	private Handler handler = new Handler();

	@Override
	protected void somar(final int n1, final int n2) {
		// Cria uma thread
		new Thread() {
			public void run() {
				// Faz o processamento em background
				final int soma = n1 + n2;

				// Atualiza a interface da tela com o atalho para o handler
				handler.post(new Runnable(){
					public void run() {
						//recebe a mensagem para atualizar a view
						TextView somaText = (TextView) findViewById(R.id.labelSoma);
						somaText.setText(String.valueOf("Soma: " + soma));			
					}
				});
			}
		}.start();
	}
}
