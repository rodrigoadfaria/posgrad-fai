package com.example.aula03.service.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * Service simples que exibe logs no m�todo onCreate, onDestroy
 * 
 * O onCreate cria uma Thread para demonstrar um processamento em background
 * 
 * Demonstra como criar uma thread mas n�o se preocupa com o multi-threading, sendo que os atributos s�o compartilhados pelas threads
 * 
 */
public class ExemploServico extends Service implements Runnable {
	
	private static final int MAX = 50;
	private static final String TAG = "posgrad-fai";
	protected int count;
	protected boolean ativo;
	
	@Override
	public IBinder onBind(Intent i) {
		Log.i(TAG, "ExemploServico.onBind()");
		// Retorna null aqui porque n�o queremos interagir com o service
		return null;
	}
	
	@Override
	public void onCreate() {
		Log.i(TAG, "ExemploServico.onCreate()");
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.i(TAG, "ExemploServico.onStartCommand(): " + startId);
		count = 0;
		// M�todo chamado depois do onCreate(), logo depois que o servi�o � iniciado
		// O par�metro 'startId' representa o identificador deste servi�o
		ativo = true;
		// Delega para uma thread (passamos o nome no construtor para visualizar no debug do eclipse se necess�rio)
		new Thread(this,"ExemploServico-"+startId).start();
		// Chama a implementa��o da classe m�e
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		//Ao encerrar o servi�o, altera o flag para a thread parar
		ativo = false;
		Log.i(TAG, "ExemploServico.onDestroy()");
	}
	
	/**
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		while (ativo && count < MAX) {
			fazAlgumaCoisa();

			Log.i(TAG, "ExemploServico executando... " + count);

			count++;
		}

		Log.i(TAG, "ExemploServico fim.");

		// Auto-Encerra o service quando o processamento terminar
        stopSelf();
	}
	
	private void fazAlgumaCoisa() {
		try {
			//simula algum processamento
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
