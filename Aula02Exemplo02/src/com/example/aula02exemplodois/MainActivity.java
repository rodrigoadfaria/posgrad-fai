package com.example.aula02exemplodois;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity  implements OnClickListener {

	
	protected static final String TAG = "posgrad-fai";

	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		Log.i(TAG, getClassName() + ".onCreate() chamado: " + icicle);

		Button b = new Button(this);
		b.setText("Abrir browser");
		b.setOnClickListener(this);
		setContentView(b);
	}
	
	protected void onStart() {
		super.onStart();
		Log.i(TAG, getClassName() + ".onStart() chamado.");
	}
	
	protected void onRestart() {
		super.onRestart();
		Log.i(TAG, getClassName() + ".onRestart() chamado.");
	}
	
	protected void onResume() {
		super.onResume();
		Log.i(TAG, getClassName() + ".onResume() chamado.");
	}
	
	protected void onPause() {
		super.onPause();
		Log.i(TAG, getClassName() + ".onPause() chamado.");
	}
	
	protected void onStop() {
		super.onStop();
		Log.i(TAG, getClassName() + ".onStop() chamado.");
	}
	
	protected void onDestroy() {
		super.onDestroy();
		Log.i(TAG, getClassName() + ".onDestroy() chamado.");
	}
	
	private String getClassName() {
		//Retorna o nome da classe sem o pacote
		String s = getClass().getName();
		return s.substring(s.lastIndexOf("."));
	}
	
	public void onClick(View v) {
		Uri uri = Uri.parse("tel:12345678");
		Intent it = new Intent(Intent.ACTION_CALL, uri);
		startActivity(it);
	}
}
