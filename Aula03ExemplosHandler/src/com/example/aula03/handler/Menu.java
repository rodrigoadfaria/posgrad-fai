package com.example.aula03.handler;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Exemplos de Handler para usar com uma Thread
 * 
 */
public class Menu extends ListActivity {

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		String[] mStrings = new String[] {
				"Hello Handler",
				"Activity de Soma",
				"Soma: Erro Thread", 
				"Soma: Thread + Handler message",
				"Soma: Thread + Handler runnable",
				"Soma: AsyncTask",
				"Soma: AsyncTask Progress",
				"Thread.sleep com Handler",
				"Sair"
				};

		this.setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, mStrings));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {

		switch (position) {
			case 0:
				startActivity(new Intent(this, ExemploHandler.class));
				break;
			case 1:
				startActivity(new Intent(this, SomaSimples.class));
				break;
			case 2:
				startActivity(new Intent(this, SomaThread.class));
				break;
			case 3:
				startActivity(new Intent(this, SomaComHandler.class));
				break;
			case 4:
				startActivity(new Intent(this, SomaComHandlerRunnable.class));
				break;
			case 5:
				startActivity(new Intent(this, SomaComAsyncTask.class));
				break;
			case 6:
				//startActivity(new Intent(this, SomaComAsyncTaskProgress.class));
				break;
			case 7:
				startActivity(new Intent(this, ThreadSleepHandler.class));
				break;
			default:
				finish();
		}
	}
}