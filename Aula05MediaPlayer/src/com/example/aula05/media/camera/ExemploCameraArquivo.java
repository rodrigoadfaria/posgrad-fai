package com.example.aula05.media.camera;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.aula05.media.R;
import com.example.aula05.media.utils.ImageUtils;
import com.example.aula05.media.utils.SDCardUtils;

public class ExemploCameraArquivo extends Activity {
	// Caminho para salvar o arquivo
	private File file;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.camera);

		ImageButton b = (ImageButton) findViewById(R.id.btAbrirCamera);
		b.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// Cria o caminho do arquivo no sdcard
				file = SDCardUtils.getSdCardFile("posgrad-fai", "foto.jpg");
				// Chama a intent informando o arquivo para salvar a foto
				Intent i = new Intent("android.media.action.IMAGE_CAPTURE");
				i.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
				startActivityForResult(i, 0);
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (resultCode == RESULT_OK) {
			// Além de redimensionar, este método faz um tratamento correto da memória e automaticamente corrige a orientação da imagem
			Bitmap bitmap = ImageUtils.getResizedImage(Uri.fromFile(file), 480, 360);
			Toast.makeText(this, "w/h:" + bitmap.getWidth() + "/" + bitmap.getHeight(), Toast.LENGTH_SHORT).show();
			// Atualiza a imagem na tela
			ImageView img = (ImageView) findViewById(R.id.imagem);
			img.setImageBitmap(bitmap);
		}
	}
}