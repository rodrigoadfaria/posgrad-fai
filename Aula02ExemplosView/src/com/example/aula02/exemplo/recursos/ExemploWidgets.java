package com.example.aula02.exemplo.recursos;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class ExemploWidgets extends Activity implements OnClickListener{
	
	private final static String TAG = "posgrad-fai";
	private boolean firstTime = true;
	
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		setContentView(R.layout.widgets);
		
		//Button listener
		Button btn = (Button) findViewById(R.id.button1);
		if (btn != null) {
			btn.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// Fa�a alguma coisa aqui
					Toast.makeText(ExemploWidgets.this, "Voc� clicou no bot�o", Toast.LENGTH_SHORT).show();
				}
			});
		}
		
		//Setando o Listener para o OnClickListener implementado pela classe
		findViewById(R.id.imageButton1).setOnClickListener(this);
		
		//CrianDo um listener para o spinner. Entra em a��o toda vez que um item � selecionado na lista
		((Spinner) findViewById(R.id.spinner1)).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> spinner, View arg1, int arg2, long arg3) {
				if (!firstTime) {
					AlertDialog.Builder builder = new AlertDialog.Builder(ExemploWidgets.this);
					builder.setMessage("Voc� selecionou: "+ spinner.getSelectedItem().toString());
					builder.setNeutralButton(android.R.string.ok, null);
					AlertDialog dialog = builder.create();
					dialog.setTitle("P�s FAI Dialog");
					dialog.show();
				} else {
					firstTime = false;
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				//Do nothing
			}
		});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.button1:
				Log.i(TAG, "N�o vamos fazer nada porque o bot�o j� foi tratado inline");
				break;

			case R.id.imageButton1:
				Toast.makeText(ExemploWidgets.this, "Voc� clicou no ImageButton. Estamos no m�todo onClick da " +
						"implementa��o do OnClickListener pela Activity", Toast.LENGTH_LONG).show();
				break;
	
			default:
				break;
		}
	}
}
