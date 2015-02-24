package com.example.aula05.media.video;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;

/**
 * Classe para encapsular o acesso ao MediaPlayer para reproduzir um v�deo
 * com os controles (callback) necess�rios para gerenciar o SurfaceView
 * 
 */
public class PlayerVideo implements OnBufferingUpdateListener, OnCompletionListener, OnPreparedListener, Callback  {
	
	private static final String TAG = "posgrad-fai";
	
	private static final int NOVO 		= 0;
	private static final int INICIADO 	= 1;
	private static final int PAUSADO 	= 2;
	private static final int PARADO 	= 3;
	// Come�a o status zerado
	private int status = NOVO;
	private MediaPlayer player;
	private final String video;

	@SuppressWarnings("deprecation")
	public PlayerVideo(SurfaceHolder holder, String video) {
		this.video = video;
		// Isto parece redimensionar o video para ocupar a tela inteira
		holder.setFixedSize(10, 10);
		holder.addCallback(this);
		// Isto � deprecated, mas necess�rio antes do Android 3.x, ent�o vamos deixar aqui
		holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		Log.i(TAG,"Construtor PlayerVideo ok: " + this.video);
	}
	// Faz pause
	public void pause() {
		player.pause();
		status = PAUSADO;
	}
	// Para a reprodu��o
	public void stop() {
		player.stop();
		status = PARADO;
	}
	// Para a reprodu��o, e libera recursos
	public void fechar() {
		stop();
		player.release();
		player = null;
	}
	// Inicia a reprodu��o do v�deo
	public void start() {
		try {
			switch (status) {
				case INICIADO:
					player.stop();
				case PARADO:
					player.reset();
				case NOVO:
					player.setDataSource(video);
					// Ao chamar o prepare() o listener "onPrepared(mediaPlayer)" ser� chamado
					player.prepare();
				case PAUSADO:
					player.start();
					break;
			}

			status = INICIADO;
		} catch (Exception e) {
			Log.e(TAG, e.getMessage(), e);
		}catch (Throwable e) {
			Log.e(TAG, e.getMessage(), e);
		}
	}
	//Cria o MediaPlayer e inicia o v�deo somente quando o SurfaceView/SurfaceHolder estiverem ok
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		Log.i(TAG,"surfaceChanged: " + format + " width: " + width + ", height: " + height);
		Log.i(TAG,"Video: " + video);

		player = new MediaPlayer();
		player.setDisplay(holder);
		player.setOnBufferingUpdateListener(this);
		player.setOnCompletionListener(this);
		player.setOnPreparedListener(this);
		player.setAudioStreamType(AudioManager.STREAM_MUSIC);

		//Inicia a reprodu��o automaticamente
		start();
	}
	public void surfaceCreated(SurfaceHolder surfaceholder) {
		Log.i(TAG,"surfaceCreated");
	}
	public void surfaceDestroyed(SurfaceHolder surfaceholder) {
		Log.i(TAG,"surfaceDestroyed");		
	}
	public void onBufferingUpdate(MediaPlayer mediaplayer, int i) {
		Log.i(TAG,"onBufferingUpdate");
	}
	public void onCompletion(MediaPlayer mediaplayer) {
		Log.i(TAG,"onCompletion - Fim do video");
	}
	//Inicia o MediaPlayer.start() somente quando o v�deo estiver pronto para reproduzir
	public void onPrepared(MediaPlayer mediaplayer) {
		Log.i(TAG,"onPrepared");

        int videoWidth = player.getVideoWidth();
        int videoHeight = player.getVideoHeight();
        Log.d(TAG, "videoWidth: " + videoWidth);
        Log.d(TAG, "videoHeight: " + videoHeight);
	}
}
