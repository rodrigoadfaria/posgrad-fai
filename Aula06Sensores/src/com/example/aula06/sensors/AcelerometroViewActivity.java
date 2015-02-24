package com.example.aula06.sensors;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.hardware.SensorEvent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;

/**
 * Simples demo para mover uma view pela tela, ao utilizar o aceler�metro.
 * 
 * Vamos utilizar apenas os valores dos eixos X e Y
 * 
 */
public class AcelerometroViewActivity extends AcelerometroActivity {

	// Posi��es para desenhar a imagem
	private int dx, dy;
	private MyView myView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		myView = new MyView(this);
		setContentView(myView);
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		// L� os valores retornados pelo aceler�metro
		float values[] = SensorUtil.fixAcelerometro(this, event);
		float sensorX = values[0];
		float sensorY = values[1];

		// Vai incrementando os valores de x e y, para o objeto se mover
		int newdx = dx + (int) sensorX * 10;
		int newdy = dy + (int) sensorY * 10;
		
		int imgW = myView.drawable.getIntrinsicWidth();
		int imgH = myView.drawable.getIntrinsicHeight();

		// N�o deixa o valor ficar negativo, ou maior que o tamanho da tela
		DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
		if (! (newdx < 0 || newdx + imgW > displayMetrics.widthPixels)) {
			dx = newdx;
		}
		int actionBarH = displayMetrics.heightPixels-myView.getHeight();
		if (! (newdy < 0 || newdy + imgH > displayMetrics.heightPixels - actionBarH)) {
			dy = newdy;
		}

		// Redesenha a view
		myView.invalidate();
	}

	public class MyView extends View {
		private Paint paint = new Paint();
		private Drawable drawable;

		public MyView(Context context) {
			super(context);
			// Configura o fundo cinza, e cria a imagem
			paint.setColor(Color.LTGRAY);
			drawable = context.getResources().getDrawable(R.drawable.ic_launcher);
			drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
		}

		@Override
		protected void onDraw(Canvas canvas) {
			super.onDraw(canvas);

			// Desenha o fundo da view (um quadrado cinza)
			paint.setColor(Color.LTGRAY);
			canvas.drawRect(0, 0, getWidth(), getHeight(), paint);
			
//			paint.setColor(Color.BLACK);
//			canvas.drawText("x: " + dx + ", y: " + dy, 10, 10, paint);

			// Desenha a imagem da posi��o x e y
			canvas.translate(dx, dy);
			drawable.draw(canvas);
		}
	}
}
