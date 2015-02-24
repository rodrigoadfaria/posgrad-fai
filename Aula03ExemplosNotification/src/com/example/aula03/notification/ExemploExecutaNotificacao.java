package com.example.aula03.notification;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Activity que executa quando o usu�rio selecione a notifica��o na barra de status
 * 
 * Activity simples que apenas exibe uma mensagem na tela
 * 
 * Aqui � feito o cancelamento da notifica��o, para fechar a mesma depois de o usu�rio abrir
 * 
 */
public class ExemploExecutaNotificacao extends Activity {
	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		// Cancela a notifica��o
		NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		
		// Para cancelar precisa utilizar o mesmo id que foi utilizado para criar
		//nm.cancel(R.drawable.ic_notification);

		TextView text = new TextView(this);
		text.setText("Usu�rio selecionou a notifica��o. � poss�vel executar algo agora." +
				"\nNeste momento a notifica��o � cancelada.");
		setContentView(text);
	}
}
