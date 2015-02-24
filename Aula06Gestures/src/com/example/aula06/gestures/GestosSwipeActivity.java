package com.example.aula06.gestures;

import android.app.Activity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Reconhece um gesto de swipe lateral
 * 
 */
public class GestosSwipeActivity extends Activity {

	TextView text;
	private GestureDetector gestureDetector;
	private ImageView img;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gestos_swipe);

		text = (TextView) findViewById(R.id.text);
		text.setText("Faça um gesto");
		
		img = (ImageView) findViewById(R.id.img);

		gestureDetector = new GestureDetector(this, new MyFlingGestureDetector());
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// Aqui está o segredo. Delega o tratamento do touch para a classe GestureDetector
		boolean tratouEvento = gestureDetector.onTouchEvent(event);
		if(tratouEvento) {
			return tratouEvento;
		}
		return super.onTouchEvent(event);
	}

	class MyFlingGestureDetector extends SimpleOnGestureListener {
		/**
		 * Distância feita do movimento em pixels
		 */
		private float swipeMinDistance = 100;
		/**
		 * Velocidade em pixels por segundo
		 */
		private float swipeThreasholdVelocity = 200;

		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
				float velocityY) {
			try {
				// right to left swipe
				if (e1.getX() - e2.getX() > swipeMinDistance && Math.abs(velocityX) > swipeThreasholdVelocity) {
					text.setText("<<< Left");
					mover(-100);
				} else if (e2.getX() - e1.getX() > swipeMinDistance
						&& Math.abs(velocityX) > swipeThreasholdVelocity) {
					text.setText("Right >>>");
					mover(+100);
				}
			} catch (Exception e) {
			}
			return false;
		}

		private void mover(int distancia) {
			// Cria uma animação do tipo Translate
			TranslateAnimation anim = new TranslateAnimation(
					Animation.RELATIVE_TO_SELF	, 0, 			// x incial 
					Animation.ABSOLUTE			, distancia,	// x final (mover 100 pixels)
					Animation.RELATIVE_TO_SELF	,0, 			// y inicial (zero para não mover no y)
					Animation.RELATIVE_TO_SELF	, 0				// y final	 (zero para não mover no y)
					);
			anim.setFillAfter(true);
			anim.setDuration(2000);
			img.startAnimation(anim);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_gestos, menu);
		return true;
	}

}