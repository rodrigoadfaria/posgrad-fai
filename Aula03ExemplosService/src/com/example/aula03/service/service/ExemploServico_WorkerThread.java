package com.example.aula03.service.service;

import java.util.ArrayList;
import java.util.List;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * Service simples que exibe logs no m�todo onCreate, onDestroy
 * 
 * O onCreate cria uma Thread para demonstrar um processamento em background
 * Cada vez que o ExemploServico_WorkerThread � chamado, uma nova Thread �
 * criada dentro do onStartCommand com um id (startId) que se autoincrementa que � utilizado
 * para interromper esse servi�o no final da execu��o com a chamada do stopSelf(startId)
 * 
 */
public class ExemploServico_WorkerThread extends Service {
	
	private static final int MAX = 10;
	private static final String TAG = "posgrad-fai";
	private List<WorkerThread> threads = new ArrayList<WorkerThread>();

	@Override
	public IBinder onBind(Intent i) {
		Log.i(TAG, "ExemploServico_WorkerThread.onBind()");
		// Retorna null aqui porque n�o queremos interagir com o service
		return null;
	}

	@Override
	public void onCreate() {
		// Chamado apenas uma vez (independente de quantas vezes � chamado o startService(intent)
		Log.i(TAG, "ExemploServico_WorkerThread.onCreate()");
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// Chamado todas as vezes que o � chamado startService(intent)
		Log.i(TAG, "ExemploServico_WorkerThread.onStartCommand(): " + startId);
		// Delega para uma thread (criamos o nome para visualizar no debug do eclipse se necess�rio)
		WorkerThread workerThread = new WorkerThread(startId);
		threads.add(workerThread);
		workerThread.start();

		// O int retornado aqui � uma constante que pode ser 3 valores:
		// START_NOT_STICKY - Indica que, caso o sistema encerre o service, ele n�o ser� recriado
		// START_STICKY - Indica que, caso o sistema encerre o service, ele ser� recriado.
		// Neste caso a intent ser� nula
		// START_REDELIVER_INTENT - Mesma coisa do START_STICKY, por�m, a mesma intent que iniciou
		// o service ser� entregue novamente
		return super.onStartCommand(intent, flags, startId);
	}
	
	// Uma inner class pode acessar todos os atributos da classe principal
	class WorkerThread extends Thread {
		private boolean ativo;
		private int startId;
		private int count;

		public WorkerThread(int startId) {
			super("ExemploServico-"+startId);
			this.startId = startId;
			ativo = true;
		}

		/**
		 * @see java.lang.Runnable#run()
		 */
		public void run() {
			while (ativo && this.count < MAX) {
				try {
					// Simula algum processamento (chamada para um web service ou banco de dados)
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				Log.i(TAG, startId + ": ExemploServico executando... " + this.count);

				this.count++;
			}

			Log.i(TAG, "ExemploServico fim ("+startId+")");

			// Auto-Encerra o service quando o processamento terminar
			stopSelf(startId);
		}
	}
	
	@Override
	public void onDestroy() {
		Log.i(TAG, "ExemploServico_WorkerThread.onDestroy()");

		// Esta chamada � �nica para todas as threads (cada chamado ao startService)
		// Ao encerrar o servi�o, altera o flag para as threads pararem
		for (WorkerThread workerThread : threads) {
			workerThread.ativo = false;
		}
		threads.clear();

	}
}
