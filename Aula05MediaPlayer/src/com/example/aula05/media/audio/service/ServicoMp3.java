package com.example.aula05.media.audio.service;


import com.example.aula05.media.audio.PlayerMp3;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

/**
 * Service que fica tocando um som em background
 * 
 */
public class ServicoMp3 extends Service implements InterfaceMp3 {
	
	private static final String TAG = "posgrad-fai";
	
	private PlayerMp3 player = new PlayerMp3();
	private final ConexaoInterfaceMp3 conexao = new ConexaoInterfaceMp3();

	public class ConexaoInterfaceMp3 extends Binder {
		public InterfaceMp3 getService() {
			// retorna a interface para controlar o Service
			return ServicoMp3.this;
		}
	}

	@Override
	public IBinder onBind(Intent intent) {
		// retorna a classe ConexaoInterfaceMp3 para a activity utilizar
		Log.i(TAG, "ServicoMp3 onBind(). Aqui retorna o IBinder.");
		return conexao;
	}

	@Override
	public void onCreate() {
		Log.i(TAG, "ServicoMp3 onCreate()");
	}

	@Override
	public void onStart(Intent intent, int startId) {
		Log.i(TAG, "ServicoMp3 onStart()");
	}

	// InterfaceMp3.start(mp3)
	public void start(String mp3) {
		try {
			player.start(mp3);
		} catch (Exception e) {
			Log.e(TAG, e.getMessage(), e);
		}
	}

	// InterfaceMp3.pause()
	public void pause() {
		player.pause();
	}

	// InterfaceMp3.stop()
	public void stop() {
		player.stop();
	}

	@Override
	public void onDestroy() {
		Log.i(TAG, "ServicoMp3 onDestroy(). Aqui o MediaPlayer também foi encerrado.");
		player.fechar();
	}
}
