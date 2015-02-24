package com.example.aula05.media.audio;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.aula05.media.R;
import com.example.aula05.media.audio.service.InterfaceMp3;
import com.example.aula05.media.audio.service.ServicoMp3;
import com.example.aula05.media.audio.service.ServicoMp3.ConexaoInterfaceMp3;

/**
 * Simples Player MP3 utilizando a classe MediaPlayer do Android
 * 
 */
public class ExemploPlayerMp3ComServico extends Activity implements OnClickListener {
	
	private static final String TAG = "posgrad-fai";

	//Interface para se comunicar com o serviço em segundo plano
	private InterfaceMp3 interfaceMp3;

	private ImageButton btStart;
	private ImageButton btPause;
	private ImageButton btStop;
	private Button btStopService;
	private EditText text;

	private ServiceConnection conexao = new ServiceConnection() {
		public void onServiceConnected(ComponentName className, IBinder service) {
			Log.i(TAG,"onServiceConnected, serviço conectado");
			//Recupera a interface para interagir com o serviço
			ConexaoInterfaceMp3 conexao = (ConexaoInterfaceMp3) service;
			Log.i(TAG,"Recuperada a interface para controlar o mp3.");
			interfaceMp3 = conexao.getService();
		}
		public void onServiceDisconnected(ComponentName className) {
			Log.i(TAG,"onServiceDisconnected, liberando recursos.");
			interfaceMp3 = null;
		}
	};

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		setContentView(R.layout.form_mp3_service);

		text = (EditText) findViewById(R.id.arquivo);

		btStart = (ImageButton) findViewById(R.id.start);
		btStart.setOnClickListener(this);

		btPause = (ImageButton) findViewById(R.id.pause);
		btPause.setOnClickListener(this);

		btStop = (ImageButton) findViewById(R.id.stop);
		btStop.setOnClickListener(this);

		btStopService = (Button) findViewById(R.id.stopService);
		btStopService.setOnClickListener(this);

		Log.i(TAG,"Chamando startService()...");

		//inicia o service
		startService(new Intent(this,ServicoMp3.class));

		Log.i(TAG,"Chamando bindService()...");

		boolean b = bindService(new Intent(this, ServicoMp3.class), conexao, Context.BIND_AUTO_CREATE);

		Log.i(TAG,"bindService retorno: " + b);
	}

	/**
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	public void onClick(View view) {
		try {
			if (view == btStart) {
				String mp3 = text.getText().toString();
				interfaceMp3.start(mp3);
			} else if (view == btPause) {
				interfaceMp3.pause();
			} else if (view == btStop) {
				interfaceMp3.stop();
			}else if (view == btStopService) {
				Log.i(TAG, "Parando o mp3 e o serviço.");
				//para o player
				interfaceMp3.stop();
				//para o Service, com a mesma Intent que o criou
				stopService(new Intent(this,ServicoMp3.class));
			}
		} catch (Exception e) {
			Log.e(TAG, e.getMessage(), e);
			Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
		}
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.i(TAG, "Activity destruida! Mas a musica continua...");
		unbindService(conexao);
	}
}
