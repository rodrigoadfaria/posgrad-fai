package com.example.aula03.notification.utils;

import com.example.aula03.notification.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;

/**
 * Classe auxiliar para criar uma notificação
 * 
 */
public class NotificationUtil {

	@SuppressWarnings("deprecation")
	@SuppressLint("NewApi")
	public static void create(Context context, String tickerText, String title, String message, int icon, int id, Intent intent) {
		// PendingIntent para executar a intent ao selecionar a notificação
		// Além da Activity, o PendingIntent pode invocar um Broadcast ou Service
		PendingIntent p = PendingIntent.getActivity(context, 0, intent, 0);
		//PendingIntent p = PendingIntent.getBroadcast(context, 0, intent, 0);
		
		Notification n = null;

		int apiLevel = Build.VERSION.SDK_INT;
		if (apiLevel >= 11) {
			Builder builder = new Notification.Builder(context);
			builder.setContentTitle(title);
			builder.setTicker(tickerText);
			builder.setContentText(message);
			builder.setSmallIcon(icon);
			builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_notification));
			builder.setContentIntent(p);

			if (apiLevel >= 17) {
				// Android 4.2
				n = builder.build();
			} else {
				// Android 3.x
				n = builder.getNotification();
			}
		} else {
			// Android 2.2
			n = new Notification(icon, tickerText, System.currentTimeMillis());

			// Informações
			n.setLatestEventInfo(context, title, message, p);
		}

		// id (número único) que identifica esta notificação.
		// Utilizado também para remover (cancelar) a notificação posteriormente.
		// Para disparar a notificação você deve utilizar um serviço (NotificationManager) do SO
		NotificationManager nm = (NotificationManager) 
				context.getSystemService(Activity.NOTIFICATION_SERVICE);
		nm.notify(id, n);
	}

	/**
	 * API Level 17 - Android 4.2 ou superior
	 */
	@SuppressLint("NewApi")
	public static void create_v17(Context context, String tickerText, String title, String message, int icon, int id, Intent intent) {
		// PendingIntent para executar a intent ao selecionar a notificação
		// Além da Activity, o PendingIntent pode invocar um Broadcast ou Service
		PendingIntent p = PendingIntent.getActivity(context, 0, intent, 0);

		Notification.Builder builder = new Notification.Builder(context);
		builder.setContentTitle(title);
		builder.setTicker(tickerText);
		builder.setContentText(message);
		builder.setSmallIcon(icon);
		builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_notification));
		builder.setContentIntent(p);

		// Android 4.2
		Notification n = builder.build();

		// id (numero único) que identifica esta notificação
		NotificationManager nm = (NotificationManager) context.getSystemService(Activity.NOTIFICATION_SERVICE);
		nm.notify(id, n);
	}

	/**
	 * API Level 17 - Android 4.2 ou superior
	 */
	@SuppressLint("NewApi")
	public static void create_v17_big(Context context, String tickerText, String title, String message, String[] lines, int icon, int id,Intent intent) {
		// PendingIntent para executar a intent ao selecionar a notificação
		// Além da Activity, o PendingIntent pode invocar um Broadcast ou Service
		PendingIntent p = PendingIntent.getActivity(context, 0, intent, 0);
		
		Notification.Builder builder = new Notification.Builder(context);
		builder.setContentTitle(title);
		builder.setTicker(tickerText);
		builder.setContentText(message);
		builder.setSmallIcon(icon);
		builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_notification));
		builder.setContentIntent(p);

		// Cria o estilo detalhado
		Notification.InboxStyle style = new Notification.InboxStyle();
		style.setBigContentTitle(title);

		for (String line : lines) {
			style.addLine(line);
		}

		// Informa o estilo
		builder.setStyle(style);
		//o setProgress pode ser utilizado caso esteja executando algo em background
		// os parâmetros são: max tempo executando, progress que é o progresso atual
		// o parâmetro booleano, se true, ignora os anteriores e uma animação
		//infinita é exibida.
		//builder.setProgress(100, 50, false);
		//builder.setProgress(0, 0, true);

		// Android 4.2
		Notification n = builder.build();

		// id (numero único) que identifica esta notificação
		NotificationManager nm = (NotificationManager) context.getSystemService(Activity.NOTIFICATION_SERVICE);
		nm.notify(id, n);
	}

	public static void cancell(Context context, int id) {
		NotificationManager nm = (NotificationManager) context.getSystemService(Activity.NOTIFICATION_SERVICE);
		nm.cancel(id);
	}
}
