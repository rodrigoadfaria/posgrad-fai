package com.example.aula03.service;

import com.example.aula03.service.service.Contador;
import com.example.aula03.service.service.ServicoComConexao;
import com.example.aula03.service.service.ServicoComConexao.LocalBinder;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Exemplo de utiliza��o do bindService, para interagir com um Service que
 * utiliza a interface CountService
 * 
 */
public class ExemploBindServiceActivity extends Activity implements ServiceConnection {
	
	private static final String TAG = "posgrad-fai";
	private Contador contador;

	// A classe implementa a interface ServiceConnection
	final ServiceConnection conexao = this;
	
	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		setContentView(R.layout.form_bind);

		// Iniciar o servi�o
		// O param Context.BIND_AUTO_CREATE indica que o servi�o pode iniciar automaticamente,
		// caso ainda n�o tenha sido iniciado 
		Button bIniciar = (Button) findViewById(R.id.btIniciar);
		bIniciar.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				Log.i(TAG, "Iniciar servi�o - bindService");
				bindService(new Intent(ExemploBindServiceActivity.this, ServicoComConexao.class), conexao, Context.BIND_AUTO_CREATE);
			}
		});

		// Parar o service, usa a connection
		Button bParar = (Button) findViewById(R.id.btParar);
		bParar.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				Log.i(TAG, "Parar servi�o - unbindService");
				unbindService(conexao);
			}
		});

		// Ver o retorno do m�todo "count" da interface do servi�o
		Button b = (Button) findViewById(R.id.btContador);
		b.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				int count = contador.count();
				Log.i(TAG, "Contador: " + count);
				Toast.makeText(ExemploBindServiceActivity.this, "Contador: " + count, Toast.LENGTH_SHORT).show();
			}
		});
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		//unbindService(conexao);
	}
	
	/**
	 * Esse m�todo � invocado para informar que uma conex�o foi establecida, ou seja,
	 * quando o bindService foi invocado
	 */
	public void onServiceConnected(ComponentName className, IBinder service) {
		// Recupera a interface para interagir com o servi�o
		LocalBinder binder = (LocalBinder) service;
		contador = binder.getContador();
	}

	/**
	 * Esse m�todo � invocado para informar que uma conex�o foi encerrada, ou seja,
	 * quando o unbindService foi invocado
	 */
	public void onServiceDisconnected(ComponentName className) {
		contador = null;
	}
}
