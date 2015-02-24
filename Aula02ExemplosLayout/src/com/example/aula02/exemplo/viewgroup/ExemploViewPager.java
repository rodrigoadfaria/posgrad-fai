package com.example.aula02.exemplo.viewgroup;

import com.example.aula02.exemplo.R;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.widget.Toast;

/**
 * ViewFlipper pode animar as Views que foram adicionadas a ele
 * 
 * Exemplo que demonstra como carregar a View pr�xima/anterior, e animar Views.
 * 
 * 
 */
public class ExemploViewPager extends Activity {
	// Planetas
	private int[] imagens = { R.drawable.mercurio, R.drawable.venus,
			R.drawable.terra, R.drawable.marte, R.drawable.jupiter,
			R.drawable.saturno, R.drawable.urano, R.drawable.netuno,
			R.drawable.plutao };

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.exemplo_view_pager);

		ViewPager g = (ViewPager) findViewById(R.id.viewPager);
		g.setAdapter(new AdaptadorImagem_ViewPager(this, imagens));

		g.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				Toast t = Toast.makeText(getBaseContext(), "Imagem: " + position, Toast.LENGTH_SHORT);
				t.show();
			}

			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
			}
			
			@Override
			public void onPageScrollStateChanged(int state) {
			}
		});
	}		
}