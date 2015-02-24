package com.example.aula03.service.service.aidl;

import com.example.aula03.service.service.ExemploServico;

import android.content.Intent;
import android.os.DeadObjectException;
import android.os.IBinder;
import android.util.Log;

/**
 * Service simples que faz bind para a interface ContadorRemoto
 * 
 */
public class ServicoComConexaoRemota extends ExemploServico {
	
	private static final String TAG = "posgrad-fai";
	
	// Implementa interface definida no arquivo *.aidl
	private final ContadorRemoto.Stub conexao = new ContadorRemoto.Stub() {
		private int count;

		// Implementa o método da interface ContadorRemoto
		public int count() throws DeadObjectException {
			count++;
			return count;
		}
	};

	@Override
	public IBinder onBind(Intent intent) {
		Log.i(TAG, "ServicoComConexaoRemota.onBind() conexao");
		// Retorna o Binder para a Activity utilizar
		return conexao;
	}
}
