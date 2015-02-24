package com.example.aula05.media.video;

import com.example.aula05.media.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Simples Player de V�deo
 * 
 */
public class ExemploPlayerVideoSurface extends Activity {
	
	private static final String TAG = "posgrad-fai";
	
	private static final int START = 1;
	private static final int STOP = 2;
	private static final int PAUSE = 3;
	// Classe que encapsula o MediaPlayer
	private PlayerVideo player;
	private SurfaceHolder holder;
	private SurfaceView surface;
	private String video;

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		setContentView(R.layout.video);

		this.video = getIntent().getStringExtra("video");
		Log.i(TAG, "Video: " + video);

		// Recupera o SurfaceView
		surface = (SurfaceView) findViewById(R.id.surfaceView);
		holder = surface.getHolder();

		player = new PlayerVideo(holder,video);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		menu.add(0, START, 0, "Start");
		menu.add(0, STOP, 0, "Stop");
		menu.add(0, PAUSE, 0, "Pause");
		return true;
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		// Clicou no menu
		switch (item.getItemId()) {
			case START:
				player.start();
			break;
			case PAUSE:
				player.pause();
				break;
			case STOP:
				player.stop();
				break;
		}
		return true;
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		// Libera recursos do MediaPlayer
		player.fechar();
	}
}
