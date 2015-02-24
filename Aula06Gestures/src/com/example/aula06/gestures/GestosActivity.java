package com.example.aula06.gestures;

import java.io.File;
import java.util.ArrayList;

import android.app.Activity;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.GestureOverlayView.OnGesturePerformedListener;
import android.gesture.Prediction;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Reconhece um gesto previamente cadastrado na GestureLibrary
 * 
 */
public class GestosActivity extends Activity implements
		OnGesturePerformedListener {

	private GestureLibrary gestureLib;
	private TextView text;
	private ImageView img;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gestos);

		text = (TextView) findViewById(R.id.text);
		img = (ImageView) findViewById(R.id.img);

		GestureOverlayView gestureOverlayView = (GestureOverlayView) findViewById(R.id.gestureView);
		gestureOverlayView.addOnGesturePerformedListener(this);

		// Carrega a biblioteca de gestos, a partir do arquivo salvo em /res/layout/gestures
//		gestureLib = GestureLibraries.fromRawResource(this, R.raw.gestures);
		
		File file = new File(Environment.getExternalStorageDirectory(), "gestures");
		gestureLib = GestureLibraries.fromFile(file);
		// Ou abre direto do arquivo no /sdcard
		if (!gestureLib.load()) {
			Toast.makeText(this, R.string.no_gesture_file, Toast.LENGTH_LONG).show();
			finish();
		}
	}

	@Override
	public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {
		// Faz a biblioteca de gestos reconhecer o movimento
		ArrayList<Prediction> predictions = gestureLib.recognize(gesture);
		Prediction maxScore = null;
		for (Prediction prediction : predictions) {
			// Vamos aceitar somente scores maiores que cinco
			if (prediction.score > 5.0) {
				if (maxScore == null || maxScore.score < prediction.score) {
					maxScore = prediction;
				}
			}
		}
		// Se encontrou algum gesto com score alto, vamos mostrar o texto
		if (maxScore != null) {
			// Se o score é maior que 5
			String desc = maxScore.name + ", score: " + maxScore.score
					+ gesture.getBoundingBox();
			text.setText(desc);

			int w = (int) gesture.getBoundingBox().width();
			int h = (int) gesture.getBoundingBox().height();
			final Bitmap b = gesture.toBitmap(w, h, 8,Color.GREEN);
			img.setImageBitmap(b);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_gestos, menu);
		return true;
	}

}
