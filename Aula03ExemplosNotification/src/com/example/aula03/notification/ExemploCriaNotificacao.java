package com.example.aula03.notification;

import com.example.aula03.notification.utils.NotificationUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Exemplo de Activity que cria uma notifica��o
 * 
 */
public class ExemploCriaNotificacao extends Activity {

	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		TextView text = new TextView(this);
		text.setText("Uma notifica��o foi disparada.");
		setContentView(text);

		// Texto com a chamada para a notifica��o (barra de status)
		String tickerText = "Nova mensagem";

		// Detalhes da mensagem, quem enviou e o texto
		String titulo = "Pos Mobile FAI";
		String mensagem = "Exemplo de notifica��o";

		// Exibe a notifica��o para abrir a RecebeuMensagemActivity
		criarNotificacao(tickerText, titulo, mensagem, ExemploExecutaNotificacao.class);
	}

	// Exibe a notifica��o
	protected void criarNotificacao(String tickerText, String title, String message, Class<?> activity) {
		Intent intent=  new Intent(this, activity);
		//Intent intent=  new Intent("ABRIR_TELA_TESTE");
		NotificationUtil.create(this, tickerText, title, message, R.drawable.ic_notification_small, R.drawable.ic_notification, intent);
	}
}
