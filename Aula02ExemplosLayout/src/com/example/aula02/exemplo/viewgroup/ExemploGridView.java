package com.example.aula02.exemplo.viewgroup;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;

import com.example.aula02.exemplo.R;

public class ExemploGridView extends Activity {
	
	// array com os ids das imagens
	private int[] imagens = { R.drawable.smile1, R.drawable.smile2,
			R.drawable.smile1, R.drawable.smile2, R.drawable.smile1,
			R.drawable.smile2, R.drawable.smile1, R.drawable.smile2 };

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.exemplo_grid);

		GridView grid = (GridView) findViewById(R.id.grid1);
		grid.setAdapter(new AdaptadorImagem(this, imagens));

		grid.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v, int posicao,long id) {
				Toast.makeText(ExemploGridView.this,"Imagem selecionada: " + posicao, Toast.LENGTH_SHORT).show();
			}
		});
	}
}