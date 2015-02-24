package com.example.aula06.sensors;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Simples Bússola desenhada em Canvas
 * 
 */
public class BussolaView extends View {

	private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
	private static final double M_PI = 3.14;
	/**
	 * 0 representa o Norte. O angulo em graus (90, 180) etc que difere no
	 * Norte, para corrigir a bússola
	 */
	private int angulo = 180;

	public BussolaView(Context context) {
		super(context);
		init();
	}

	public BussolaView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public BussolaView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	private void init() {

		// Inicia o Paint (pincel)
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeWidth(2);
		paint.setColor(Color.BLUE);
		paint.setTextSize(22);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.getSize(heightMeasureSpec));
	}

	@Override
	protected void onDraw(Canvas canvas) {
		int x = getMeasuredWidth() / 2;
		int y = getMeasuredHeight() / 2;
		float radiusCompass;

		if (x > y) {
			radiusCompass = (float) (y * 0.9);
		} else {
			radiusCompass = (float) (x * 0.9);
		}

		// Desenha um círculo para demarcar a bússola
		canvas.drawCircle(x, y, radiusCompass, paint);

		// Desenha a linha no ângulo
		float x2 = (float) (x + radiusCompass * Math.sin((double) (-angulo) * M_PI / 180));
		float y2 = (float) (y - radiusCompass * Math.cos((double) (-angulo) * 3.14 / 180));
		canvas.drawLine(x, y, x2, y2, paint);

		// Desenha o valor do ângulo em graus
		canvas.drawText(String.valueOf(angulo), x + 10, y, paint);

	}

	// Ângulo em graus
	public void setAngulo(int valor) {
		this.angulo = valor;
	}

}