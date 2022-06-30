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
import java.util.Formatter;

public class DragAndDropView extends SurfaceView implements SurfaceHolder.Callback {

	private DragAndDropThread thread;
//	private ArrayList<Figura> figuras;
	private int figuraActiva;
	private ArrayList<double[][]> arrayList;
	private ArrayList<String> steps;
	private int lap = 0;
	private int extra = 0;

	public DragAndDropView(Context context, ArrayList<double[][]> arrayList, ArrayList<String> steps) {
		super(context);
		this.arrayList = arrayList;
		this.steps = steps;
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
		paint.setTextSize(75f);

		int title_x  = (mitadAncho - 3 * (mitadAncho / 4)) - 100;
		int title_y =  (mitadAlto - 3 * (mitadAlto / 4));
		canvas.drawText("Steps to do the Inverse",title_x, title_y,paint);
		title_y += 100;
		title_x +=100;
		canvas.drawText("of any matrix",title_x, title_y,paint);
		paint.setColor(Color.BLACK);
		paint.setTextSize(35f);
		int count = title_y + 50;
		for (int i = 0; i < arrayList.size(); i++) {
			if (i == 0) {
				int count_x = title_x;
				int temp = 0;
				canvas.drawText("Minors and Cofactors", title_x, count, paint);
				for (int j = 0; j < 9; j++) {
					count += 75;
					Formatter formatter = new Formatter();
					formatter.format("%.2f", Double.parseDouble(steps.get(j)));
					if (temp ==1){
						count_x = title_x + 150;
						count -=75;
					}
					if (temp == 2){
						count_x = title_x + 300;
						count -=75;
					}
					canvas.drawText(formatter.toString(), count_x, count, paint);
					temp ++;
					if (temp > 2){
						temp = 0;
						count_x = title_x;
					}

				}
				count += 75;
				canvas.drawText(display(arrayList.get(i)), title_x, count,paint);
				count += 150;
			} else if (i == 1){
				int count_x = title_x;
				int temp = 0;
				canvas.drawText("adjugate and determinant", title_x, count, paint);
				for (int j = 9; j < steps.size(); j++) {
					count += 75;
					Formatter formatter = new Formatter();
					formatter.format("%.2f", Double.parseDouble(steps.get(j)));
					if (temp ==1){
						count_x = title_x + 150;
						count -=75;
					}
					if (temp == 2){
						count_x = title_x + 300;
						count -=75;
					}
					canvas.drawText(formatter.toString(), count_x, count, paint);
					temp ++;
					if (temp > 2){
						temp = 0;
						count_x = title_x;
					}
				}
				count += 75;
				canvas.drawText(display(arrayList.get(i)), title_x, count,paint);
				count += 150;
			} else {
				canvas.drawText(display(arrayList.get(i)), title_x, count,paint);
				count += 150;
			}

		}

	}


	@Override
	public boolean onTouchEvent(MotionEvent event) {

		return true;
	}

	private String display(double[][] mat){
        String stringOut = "[";
        for (int i = 0; i < 3; i++) {
            stringOut += "[";
            for (int j = 0; j < 3; j++) {
				if (j == 2)
					stringOut += String.format("%.2f ", mat[i][j]);
				else
                	stringOut += String.format("%.2f, ", mat[i][j]);
            }
            stringOut += "]\n";
        }
        stringOut += "]";
        return stringOut;
    }
}