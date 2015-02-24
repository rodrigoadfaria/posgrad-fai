package com.example.aula03.handler;

import java.util.Calendar;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Simples exemplo que utiliza um Handler para atualizar um texto da View
 * 
 * Ao clicar no botão, uma mensagem é enviada com delay de 3 seg.
 * 
 * O handler recebe a mensagem e atualiza o TextView
 * 
 */
public class ExemploHandler extends Activity {
	
	protected static final int MENSAGEM_TESTE = 1;
	private Handler handler = new TesteHandler();

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		setContentView(R.layout.exemplo_handler);

		Button b = (Button) findViewById(R.id.btEnviar);
		b.setText(R.string.update_text);
		b.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				// Cria a mensagem com delay de 3 seg
				Message msg = new Message();
				msg.what = MENSAGEM_TESTE;
				// Envia a mensagem
				handler.sendMessageDelayed(msg, 3000);
			}
		});
	}

	/** Handler utilizado para receber a mensagem **/
	@SuppressLint("HandlerLeak")
	private class TesteHandler extends Handler {
		@Override
		public void handleMessage(Message msg) {
			// O atributo msg.what permite identificar a mensagem
			switch (msg.what) {
			case MENSAGEM_TESTE:
				TextView t = (TextView) findViewById(R.id.mytext);
				t.setText(R.string.msg_received + 
						" "+ Calendar.getInstance().getTime());
				Toast.makeText(ExemploHandler.this, 
						R.string.msg_received, Toast.LENGTH_SHORT).show();
				break;
			}
		}
	};
}
