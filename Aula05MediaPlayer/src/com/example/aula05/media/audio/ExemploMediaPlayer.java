package com.example.aula05.media.audio;

import java.io.File;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Hello World que toca uma mp3 localizada na pasta /res/raw ou /data/local/tmp/ ou no /mnt/sdcard
 * 
 */
public class ExemploMediaPlayer extends Activity {
	
	private static final String TAG = "posgrad-fai";
	private MediaPlayer player;

	@Override
	protected void onCreate(Bundle b) {
		super.onCreate(b);

		// Se utilizar o MediaPlayer.create(), não precisa chamar o prepare
		// posteriormente, pois este método já o chama internamente
//		player = MediaPlayer.create(this, R.raw.linkin_park1);
//		player.start();

		try {
			/**
			 * Para testar no sdcard no emulador copie o arquivo linkin_park1.mp3 da pasta /res/raw
			 * para o sdcard do emulador.
			 * Isso pode ser feito na perspectiva DDMS, na janela File Explorer.
			 */

			player = new MediaPlayer();
//			player.setDataSource("/data/local/tmp/linkin_park1.mp3");

			File sdcardDir = android.os.Environment.getExternalStorageDirectory();
			File file = new File(sdcardDir, "vertigo.mp3");
			Toast.makeText(this, file.getAbsolutePath(), Toast.LENGTH_SHORT).show();
			player.setDataSource(file.getAbsolutePath());

			player.prepare();
			player.start();
		} catch (Exception e) {
			Toast.makeText(this, "Erro: " + e.getMessage(), Toast.LENGTH_SHORT).show();
			Log.e(TAG,e.getMessage(),e);
		}

		TextView text = new TextView(this);
		text.setText("Exemplo mp3.");
		setContentView(text);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (player != null) {
			player.stop();
			player.release();
		}
	}
}
