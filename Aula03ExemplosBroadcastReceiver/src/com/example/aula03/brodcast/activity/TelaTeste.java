package com.example.aula03.brodcast.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class TelaTeste extends Activity {

	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		TextView text = new TextView(this);
		text.setText("Esta é a Tela 1 aberta pela ação ABRIR_TELA_TESTE.");
		setContentView(text);
	}
}
