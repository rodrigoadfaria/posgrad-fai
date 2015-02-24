package com.example.aula03.notification;

import com.example.aula03.notification.utils.NotificationUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Exemplo de Activity que cria uma notificação
 * 
 */
public class ExemploCriaNotificacaoBigger extends Activity {

	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		// Texto com a chamada para a notificação (barra de status)
		String tickerText = "Mensagem recebida";

		// Detalhes da mensagem, quem enviou e o texto
		String titulo = "Pos Mobile FAI";
		String mensagem = "Exemplo de notificação";

		// Exibe a notificação para abrir a RecebeuMensagemActivity
		criarNotificacao(tickerText, titulo, mensagem, ExemploExecutaNotificacao.class);
	}

	// Exibe a notificação
	protected void criarNotificacao(String tickerText, String title, String message, Class<?> activity) {
		Intent intent=  new Intent(this, activity);
		String lines[] = new String []{"Linha 1", "Linha 2", "Linha 3",
				"Linha 1", "Linha 2", "Linha 3","Linha 1", "Linha 2", "Linha 3"
				,"Linha 1", "Linha 2", "Linha 3",
				"Linha 1", "Linha 2", "Linha 3",
				"Linha 1", "Linha 2", "Linha 3",
				"Linha 1", "Linha 2", "Linha 3","Linha 1", "Linha 2", "Linha 3",
				"Linha 1", "Linha 2", "Linha 3",
				"Linha 1", "Linha 2", "Linha 3"};
		
		TextView text = new TextView(this);
		String msg = "Uma notificação foi disparada.";
		
		int apiLevel = Build.VERSION.SDK_INT;
		if (apiLevel >= 11)
			NotificationUtil.create_v17_big(this, tickerText, title, message, lines, R.drawable.ic_notification_small, R.drawable.ic_notification, intent);
		else
			msg = "Utilize um emulador API level 11 (versão 3.x) ou superior para que esse Notification possa funcionar";

		text.setText(msg);
		setContentView(text);
	}
}
