package com.example.aula02.exemplo.viewgroup;

import com.example.aula02.exemplo.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

/**
 * Exemplo de ScrollView, adicionando vários TextView dinamicamente no loop para fazer scroll
 * 
 */
public class ExemploScrollView extends Activity {
	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		setContentView(R.layout.exemplo_scrollview);

		LinearLayout layout = (LinearLayout) findViewById(R.id.layout1);

		for (int i = 0; i < 100; i++) {

			TextView text = new TextView(this);
			//é obrigatório o layout_width e layout_height
			text.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
			text.setText("Texto: " + i);

			layout.addView(text);
		}
	}
}
