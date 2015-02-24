package com.example.aula05.media.audio;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.aula05.media.R;

/**
 * Simples Player MP3 utilizando a classe MediaPlayer do Android
 * 
 */
public class ExemploPlayerMp3 extends Activity implements OnClickListener {
	
	private static final String TAG = "posgrad-fai";
	
	//Classe que encapsula o MediaPlayer
	private PlayerMp3 player = new PlayerMp3();
	private ImageButton btStart;
	private ImageButton btPause;
	private ImageButton btStop;
	private EditText text;
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		setContentView(R.layout.form_mp3);

		text = (EditText) findViewById(R.id.arquivo);
		text.setOnClickListener(this);
		
		btStart = (ImageButton) findViewById(R.id.start);
		btStart.setOnClickListener(this);

		btPause = (ImageButton) findViewById(R.id.pause);
		btPause.setOnClickListener(this);

		btStop = (ImageButton) findViewById(R.id.stop);
		btStop.setOnClickListener(this);
	}
	/**
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	public void onClick(View view) {
		try {
			if (view == btStart) {
				String mp3 = text.getText().toString();
				player.start(mp3);
			} else if (view == btPause) {
				player.pause();
			} else if (view == btStop) {
				player.stop();
			} else if (view == text) {
				InputMethodManager imm = (InputMethodManager)getSystemService(
					      Context.INPUT_METHOD_SERVICE);
					imm.hideSoftInputFromWindow(text.getWindowToken(), 0);
			}
		} catch (Exception e) {
			Log.e(TAG, e.getMessage(), e);
			Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
		}
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		//libera recursos do MediaPlayer
		player.fechar();
	}
}
