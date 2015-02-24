package com.example.aula05.media;

import com.example.aula05.media.audio.ExemploMediaPlayer;
import com.example.aula05.media.audio.ExemploMp3ListCard;
import com.example.aula05.media.audio.ExemploPlayerMp3;
import com.example.aula05.media.audio.ExemploPlayerMp3ComServico;
import com.example.aula05.media.camera.ExemploCamera;
import com.example.aula05.media.camera.ExemploCameraArquivo;
import com.example.aula05.media.video.ExemploPlayerVideo;
import com.example.aula05.media.video.ExemploVideoView;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Exemplos de multimídia com Android
 * 
 */
public class Menu extends ListActivity {

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		String[] mStrings = new String[] {
				"Exemplo simples",
				"Player Mp3",
				"Player Mp3 Card",
				"Player Mp3 - serviço em segundo plano",
				"VideoView",
				"Player Video",
				"Câmera intent - thumb",
				"Câmera intent - sdcard",
				}
				;

		this.setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, mStrings));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {

		switch (position) {
			case 0:
				startActivity(new Intent(this, ExemploMediaPlayer.class));
				break;
			case 1:
				startActivity(new Intent(this, ExemploPlayerMp3.class));
				break;
			case 2:
				startActivity(new Intent(this, ExemploMp3ListCard.class));
				break;
			case 3:
				startActivity(new Intent(this, ExemploPlayerMp3ComServico.class));
				break;
			case 4:
				startActivity(new Intent(this, ExemploVideoView.class));
				break;
			case 5:
				startActivity(new Intent(this, ExemploPlayerVideo.class));
				break;
			case 6:
				startActivity(new Intent(this, ExemploCamera.class));
				break;
			case 7:
				startActivity(new Intent(this, ExemploCameraArquivo.class));
				break;
			
		}
	}
}