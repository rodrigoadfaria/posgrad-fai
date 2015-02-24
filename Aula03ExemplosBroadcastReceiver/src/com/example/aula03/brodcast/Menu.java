package com.example.aula03.brodcast;

import com.example.aula03.brodcast.receiver.ExemploReceiverDinamico;

import android.app.ListActivity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Exemplos de broadcast de uma intent que vai ser interceptada por um BroadcastReceiver
 * 
 */
public class Menu extends ListActivity {
	
	private ExemploReceiverDinamico dynamicReceiver;
	
	private static final String[] nomes = new String[] {
			"Exemplo 1 - Simples",
			"Exemplo 2 - Dinâmico",
			"Exemplo 3 - Erro ANR",
			"Exemplo 4 - Iniciar activity"
			};

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		this.setListAdapter(new ArrayAdapter<String>
		(this,android.R.layout.simple_list_item_1, nomes));
		
		//Registra um BroadcastReceiver pela API
		dynamicReceiver = new ExemploReceiverDinamico(); 
		registerReceiver(dynamicReceiver, new IntentFilter("ABRIR_RECEIVER_DINAMICO"));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		
		switch (position) {
			case 0:
				//Chamar o BroadcastReceiverSimples
				sendBroadcast(new Intent("ABRIR_RECEIVER_SIMPLES"));
				break;
				
			case 1:
				//Chamar o BroadcastReceiverDinamico
				sendBroadcast(new Intent("ABRIR_RECEIVER_DINAMICO"));
				break;
				
			case 2:
				//Chamar o BroadcastReceiver2
				sendBroadcast(new Intent("TESTE_ANR"));
				break;
				
			case 3:
				//Chamar o BroadcastReceiver e abrir a activity TelaTeste
				sendBroadcast(new Intent("ABRIR_TELA_TESTE"));
				break;

			default:
				finish();
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		//Remove o BroadcastReceiver. Necessário somente quando tiver
		//registrado o receiver dinamicamente
		unregisterReceiver(dynamicReceiver);
	}
}