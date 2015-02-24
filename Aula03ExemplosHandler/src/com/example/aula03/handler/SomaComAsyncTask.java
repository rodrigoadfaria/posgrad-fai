package com.example.aula03.handler;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

/**
 * Demonstra a classe AsyncTask
 * 
 */
public class SomaComAsyncTask extends ExemploBaseSomaActivity  {
	
	private static final String TAG = "posgrad-fai";
	
	@Override
	protected void somar(final int n1, final int n2) {
		final TextView somaText = (TextView) findViewById(R.id.labelSoma);

		// Cria uma AsyncTask<Params, Progress, Result>
		AsyncTask<Integer, Integer, Integer> task = new AsyncTask<Integer,Integer, Integer>() {
			@Override
			protected void onPreExecute() {
				// Chamado antes de iniciar a thread
				// Você pode utilizar este método para exibir uma mensagem de aguarde ao usuário
			}
			@Override
			protected Integer doInBackground(Integer... params) {
				int a = params[0];
				int b = params[1];
				// Este código executa automaticamente em uma thread
				// Faz o processamento em background
				int soma = a + b;
				for (int i = 1; i <= 10; i++) {
					// Informa o método onProgressUpdate sobre o progresso da execução
					publishProgress(i);
					Log.i(TAG,"set progress > " + i);
					try {
						// utilizado apenas para simular algo demorado
						Thread.sleep(200);
					} catch (InterruptedException e) {
					}
				}
				return soma;
			}
			@Override
			protected void onProgressUpdate(Integer... values) {
				super.onProgressUpdate(values);
				Integer progress = values[0];
				Log.i(TAG,"progress " + progress);
				somaText.setText(String.valueOf("Aguarde: " + progress));
			}
			protected void onPostExecute(Integer soma) {
				// Este código executa automaticamente na thread principal (utilize para atualizar as views)
				//recebe a mensagem para atualizar a view
				somaText.setText(String.valueOf("Soma AsyncTaskParams: " + soma));
			};
			
		};
		task.execute(n1,n2);
	}
}
