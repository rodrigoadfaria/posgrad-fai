package com.example.aula03.service.service;

import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

/**
 * Service simples que faz bind para a interface Contador
 * Essa classe é que representa o serviço implementando a interface Contador
 * 
 */
public class ServicoComConexao extends ExemploServico implements Contador {
	
	private final IBinder conexao = new LocalBinder();
	private static final String TAG = "posgrad-fai";
	
	// Implementação de IBinder, retorna a referência da interface para interagir com o serviço
	public class LocalBinder extends Binder {
		public Contador getContador() {
			// Retorna o serviço ServicoComConexao para a Activity
			return ServicoComConexao.this;
		}
	}

	@Override
	public void onCreate() {
		Log.i(TAG, "ServicoComConexao.onCreate()");
		count = 0;
		ativo = true;
		new Thread(this).start();
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		Log.i(TAG, "ServicoComConexao.onBind() conexao");
		// Retorna o Binder para a Activity utilizar
		return conexao;
	}

	/**
	 * @see com.example.aula03.service.service.Contador#count()
	 */
	public int count() {
		// Retorna um contador
		return count;
	}
}
