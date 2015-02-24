package com.example.aula03.service.service.aidl;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.aula03.service.R;
import com.example.aula03.service.service.aidl.ContadorRemoto;

/**
 * Exemplo de utiliza��o da interface AIDL.
 * 
 * Note a implementa��o do m�todo onServiceConnected:
 * 
 * public void onServiceConnected(ComponentName className, IBinder service) {
 * //recupera a interface para interagir com o service contador =
 * ContadorRemoto.Stub.asInterface(service); }
 */
public class ExemploBindServiceRemoto extends Activity implements ServiceConnection {
	
	private static final String TAG = "posgrad-fai";
	private ContadorRemoto contador;

	// A classe implementa a interface ServiceConnection
	final ServiceConnection conexao = this;
	
	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		setContentView(R.layout.form_bind);

		// Iniciar o servi�o
		Button bIniciar = (Button) findViewById(R.id.btIniciar);
		bIniciar.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				Log.i(TAG, "Iniciar servi�o - bindService");
				Intent it = new Intent(ExemploBindServiceRemoto.this, ServicoComConexaoRemota.class);
				bindService(it, conexao, Context.BIND_AUTO_CREATE);
			}
		});

		// Parar o servi�o, usa a conex�o
		Button bParar = (Button) findViewById(R.id.btParar);
		bParar.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				Log.i(TAG, "Parar Service - unbindService");
				unbindService(conexao);
			}
		});

		// Ver o retorno do m�todo "count" da interface do servi�o
		Button b = (Button) findViewById(R.id.btContador);
		b.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				int count;
				try {
					count = contador.count();
					Log.i(TAG, "Contador: " + count);
					Toast.makeText(ExemploBindServiceRemoto.this, "Contador: " + count, Toast.LENGTH_SHORT).show();
				} catch (RemoteException e) {
					// DeadObjectException: pode acontecer se o o processo que criou o objeto n�o existe mais
					e.printStackTrace();
				}
			}
		});
	}

	public void onServiceConnected(ComponentName className, IBinder service) {
		// Recupera a interface para interagir com o servi�o
		contador = ContadorRemoto.Stub.asInterface(service);
	}

	public void onServiceDisconnected(ComponentName className) {
		contador = null;
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		unbindService(conexao);
	}
}
