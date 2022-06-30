package com.upv.pm_2022.iti_27849_u2_monreal_romero_juan_carlos;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class DragAndDropView extends SurfaceView implements SurfaceHolder.Callback {

	private DragAndDropThread thread;
//	private ArrayList<Figura> figuras;
	private int figuraActiva;

	private int lap = 0;
	private int extra = 0;

	public DragAndDropView(Context context, ArrayList<double[][]> arrayList) {
		super(context);
		getHolder().addCallback(this);
	}

	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		// nothing here
	}

	@Override
	public void surfaceCreated(SurfaceHolder arg0) {
		int id = 0;
		int x = getWidth();//OBTENER ANCHO
		int y = getHeight();//OBTENER ALTO


//		figuras = new ArrayList<Figura>();
//		figuras.add(new Circulo(id++,x/2,y/2,25, "BLUE"));
//		figuras.add(new Rectangulo(id++,200,500,100,100));
//		figuras.add(new Circulo(id++,x/2-(x/4),y/2+(y/4),25, "BLUE"));
//		figuras.add(new Circulo(id++,x/2+(x/4),y/2+(y/4),25, "BLUE"));
//		figuraActiva = -1;

		thread = new DragAndDropThread(getHolder(), this);
		thread.setRunning(true);
		thread.start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {
		boolean retry = true;
		thread.setRunning(false);
		while (retry) {
			try {
				thread.join();
				retry = false;
			} catch (InterruptedException e) {

			}
		}
	}

	@Override
	public void onDraw(final Canvas canvas) {

		lap++;//CONTADOR

		// Dibujar antes..


		int x = getWidth();//OBTENER ANCHO
		int y = getHeight();//OBTENER ALTO
		Paint paint = new Paint();
		paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3f);
		paint.setColor(Color.WHITE);
		canvas.drawPaint(paint);
		// mitad de ancho
		int mitadAncho = canvas.getWidth() / 2;
		// mitad de alto
		int mitadAlto = canvas.getHeight() / 2;

		//Paint p = new Paint();
		paint.setAntiAlias(true);
		//canvas.drawColor(Color.WHITE);
		paint.setColor(Color.BLACK);
		paint.setTextSize(35f);

		int title_x  = (mitadAncho - 3 * (mitadAncho / 4));
		int title_y =  (mitadAlto - 3 * (mitadAlto / 4));
		canvas.drawText("Graficado de una matrix",title_x, title_y,paint);
		paint.setColor(Color.WHITE);

	}


	@Override
	public boolean onTouchEvent(MotionEvent event) {

		return true;
	}
}