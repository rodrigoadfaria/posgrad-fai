package com.example.aula06.gestures;

import java.io.File;

import android.app.Activity;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.GestureOverlayView.OnGesturePerformedListener;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Salva um gesto na GestureLibrary
 * 
 */
public class SalvarGestoActivity extends Activity implements OnGesturePerformedListener {

	private TextView text;
	private GestureLibrary gestureLib;
	
	private final File mStoreFile = new File(Environment.getExternalStorageDirectory(), "gestures");

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_salvar_gesto);
		
		text = (TextView) findViewById(R.id.text);

		GestureOverlayView gestureOverlayView = (GestureOverlayView) findViewById(R.id.gestureView);
		gestureOverlayView.addOnGesturePerformedListener(this);

		// Carrega a biblioteca de gestos, a partir do arquivo salvo em /res/layout/gestures
		gestureLib = GestureLibraries.fromFile(mStoreFile);
//		if (!gestureLib.load()) {
//			finish();
//		}
	}
	
	@Override
	public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {
		String nome = text.getText().toString();
		
		overlay.setGesture(gesture);

		gestureLib.addGesture(nome, gesture);
		gestureLib.save();
		
		 final String path = new File(Environment.getExternalStorageDirectory(),
                 "gestures").getAbsolutePath();
		Toast.makeText(this, "gesto salvo: " + nome + ", path: " + path, Toast.LENGTH_SHORT).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_salvar_gesto, menu);
		return true;
	}

}
