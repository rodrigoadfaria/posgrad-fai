package com.example.aula02.exemplo.intentfilter.filter;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Tela4 extends Activity {

	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		//chamada pela CATEGORIA_DUPLICADA com a action "ABRIR_ACTIVITY"
		
		TextView text = new TextView(this);
		text.setText("Esta � a tela 4");
		setContentView(text);
	}
}
