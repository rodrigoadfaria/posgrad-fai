package com.example.aula02.exemplo.intentfilter.filter;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Tela1 extends Activity {
	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		//chamada pela category default do android com Action "ACAO_TESTE"

		Bundle extras = getIntent().getExtras();
		if(extras != null){
			String msg = extras.getString("mensagem");
	
			TextView text = new TextView(this);
			text.setText("Esta � a tela 1\nMensagem: " + msg);
			setContentView(text);
		} else {
	
			TextView text = new TextView(this);
			text.setText("Esta � a tela 1");
			setContentView(text);
		}
	}
}
