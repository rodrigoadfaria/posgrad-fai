package com.example.aula02.exemplo.viewgroup;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.aula02.exemplo.R;

/**
 * O deprecated foi feito pelo Google. Utilize o ViewPager quando precisar de uma view desse tipo.
 *
 */
@SuppressWarnings("deprecation")
public class ExemploGallery extends Activity {
	
	// Planetas
	private int[] imagens = { R.drawable.mercurio, R.drawable.venus,
			R.drawable.terra, R.drawable.marte, R.drawable.jupiter,
			R.drawable.saturno, R.drawable.urano, R.drawable.netuno,
			R.drawable.plutao };

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.exemplo_gallery);

		Gallery g = (Gallery) findViewById(R.id.gallery);
		g.setAdapter(new AdaptadorImagem(this, imagens));

		g.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v, int posicao,long id) {
				ImageView imgView = new ImageView(ExemploGallery.this);
				imgView.setImageResource(imagens[posicao]);
				Toast t = new Toast(ExemploGallery.this);
				t.setView(imgView);
				t.show();
			}
		});
	}
}