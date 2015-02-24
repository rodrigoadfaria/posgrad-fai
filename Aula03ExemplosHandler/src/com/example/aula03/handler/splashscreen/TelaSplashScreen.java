package com.example.aula03.handler.splashscreen;

import com.example.aula03.handler.Menu;
import com.example.aula03.handler.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

/**
 * Exemplo de Splash Screen
 * 
 */
public class TelaSplashScreen extends Activity {

	private static final int FECHAR_SPLASH = 1;

	//3 segundos
	private final int DELAY = 3000;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.layout_splash);

		Message msg = new Message();
		msg.what = FECHAR_SPLASH;
		handler.sendMessageDelayed(msg, DELAY);
	}

	/** Handler utilizado para atualizar a view **/
	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			//o msg.what permite identificar a mensagem
			switch (msg.what) {
				case FECHAR_SPLASH:
					// Inicia a Activity do Menu: MenuHandler
					startActivity(new Intent(TelaSplashScreen.this, Menu.class));

					Toast.makeText(TelaSplashScreen.this, R.string.end_splash, Toast.LENGTH_SHORT).show();
					
					finish();
	
				default:
					break;
			}
		}
	};
}
