package com.example.aula05.media.audio;

import java.io.File;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.aula05.media.R;

/**
 * Simples Player MP3 utilizando a classe MediaPlayer do Android
 * 
 * Monta um ListView com todas as mp3 disponívels no cartão SD em /sdcard
 * 
 */
public class ExemploMp3ListCard extends Activity implements OnItemClickListener {
	
	private static final String TAG = "posgrad-fai";
	
	private String nomes[] = null;
	private PlayerMp3 player = new PlayerMp3();

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		setContentView(R.layout.list_sdcard);

//		File sdcardDir = new File("/mnt/sdcard");
		File sdcardDir = android.os.Environment.getExternalStorageDirectory();
		Log.i(TAG,"sdcard: " + sdcardDir.getAbsolutePath());

		//abre a pasta /sdcard
		if(sdcardDir.exists()){
			File[] files = sdcardDir.listFiles();
			nomes = new String[files.length];
			for (int i = 0; i < files.length; i++) {
				nomes[i] = files[i].getAbsolutePath();
			}
		}

		//recupera o ListView
		ListView listView = (ListView) findViewById(R.id.listView);
		listView.setOnItemClickListener(this);

		//monta a lista com os nomes das mp3
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, nomes);
		listView.setAdapter(arrayAdapter);
	}
	/**
	 * @see android.widget.AdapterView.OnItemClickListener#onItemClick(android.widget.AdapterView, android.view.View, int, long)
	 */
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Toast.makeText(this, nomes[position], Toast.LENGTH_SHORT).show();
		player.start(nomes[position]);
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		player.fechar();
	}
}
