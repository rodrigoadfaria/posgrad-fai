package com.example.aula05.media.audio;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.util.Log;

/**
 * Classe para encapsular o acesso ao MediaPlayer
 * 
 */
public class PlayerMp3 implements OnCompletionListener {
	
	private static final String TAG = "posgrad-fai";
	
	private static final int NOVO 		= 0;
	private static final int INICIADO 	= 1;
	private static final int PAUSADO 	= 2;
	private static final int PARADO 	= 3;
	// come�a o status zerado
	private int status = NOVO;
	private MediaPlayer player;
	private String mp3;

	public PlayerMp3() {
		// Cria o MediaPlayer
		player = new MediaPlayer();

		//executa o listener quando terminar a m�sica
		player.setOnCompletionListener(this);
	}

	public void start(String mp3) {
		this.mp3 = mp3;

		try {
			switch (status) {
				case INICIADO:
					player.stop();
				case PARADO:
					player.reset();
				case NOVO:
					player.setDataSource(mp3);
					player.prepare();
				case PAUSADO:
					player.start();
					break;
			}

			status = INICIADO;
		} catch (Exception e) {
			Log.e(TAG,e.getMessage(),e);
		}
	}

	public void pause() {
		player.pause();
		status = PAUSADO;
	}

	public void stop() {
		player.stop();
		status = PARADO;
	}
	//fecha o MediaPlayer e libera a mem�ria
	public void fechar() {
		stop();
		player.release();
		player = null;
	}
	/**
	 * @see android.media.MediaPlayer.OnCompletionListener#onCompletion(android.media.MediaPlayer)
	 */
	public void onCompletion(MediaPlayer mp) {
		//quando terminar a reprodu��o...
		Log.i(TAG, "Fim da m�sica: " + mp3);
	}
}
