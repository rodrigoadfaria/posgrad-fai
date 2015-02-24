package com.example.aula03.service.service;

import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

/**
 * Service simples que faz bind para a interface Contador
 * Essa classe � que representa o servi�o implementando a interface Contador
 * 
 */
public class ServicoComConexao extends ExemploServico implements Contador {
	
	private final IBinder conexao = new LocalBinder();
	private static final String TAG = "posgrad-fai";
	
	// Implementa��o de IBinder, retorna a refer�ncia da interface para interagir com o servi�o
	public class LocalBinder extends Binder {
		public Contador getContador() {
			// Retorna o servi�o ServicoComConexao para a Activity
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
