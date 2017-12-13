package com.facebook.android.project;

import com.facebook.android.R;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.view.ActionMode.Callback;
import android.view.View;

public class DrawGamePage extends View
{
	private int size, i;
	
	Bitmap A1, A2, B1, B2, C1, C2, D1, D2, E1, E2, F1, F2;
	private boolean flag=false, stt=false;
	private NextPage np;
	
	public DrawGamePage(Context context) 
	{
		super(context);
		A1 = BitmapFactory.decodeResource(getResources(), R.drawable.red1);
		A2 = BitmapFactory.decodeResource(getResources(), R.drawable.red2);
		
		B1 = BitmapFactory.decodeResource(getResources(), R.drawable.blue1);
		B2 = BitmapFactory.decodeResource(getResources(), R.drawable.blue2);
		
		C1 = BitmapFactory.decodeResource(getResources(), R.drawable.green1);
		C2 = BitmapFactory.decodeResource(getResources(), R.drawable.green2);
		
		D1 = BitmapFactory.decodeResource(getResources(), R.drawable.yellow1);
		D2 = BitmapFactory.decodeResource(getResources(), R.drawable.yellow2);
		
		E1 = BitmapFactory.decodeResource(getResources(), R.drawable.pink1);
		E2 = BitmapFactory.decodeResource(getResources(), R.drawable.pink2);
		
		F1 = BitmapFactory.decodeResource(getResources(), R.drawable.cyan1);
		F2 = BitmapFactory.decodeResource(getResources(), R.drawable.cyan2);
		
		size=10;
		i=0;
		CreateRandomNumbers(startingPoint.sequenceLength);
		
		
		
		
		
		
		
	}
	public boolean check()
	{
		return flag;
	}
	
	

	private void CreateRandomNumbers(int len) 
	{
		for(int j=0;j<len;j++)
		{
			int n = (int) Math.ceil(Math.random()*3331);
			//sequence[j]=n%6;
			int temp=n%6;
			if(j!=0 && startingPoint.sequence[j-1]==temp)
			{
				temp++;
				startingPoint.sequence[j]=temp%6;
				continue;
				
			}
			startingPoint.sequence[j]=temp;
		}
		startingPoint.sequence[len]=77;
		
	}

	protected void onDraw(Canvas canvas) 
	{
		
		Paint paint = new Paint(); 
		paint.setColor(Color.GREEN); 
		paint.setTextSize(20); 
		canvas.drawText("Watch Carefully.....", 10, 50, paint);
		
		canvas.drawBitmap(A1, 50, 100, null);
		canvas.drawBitmap(B1, 50, 200, null);
		canvas.drawBitmap(C1, 50, 300, null);
		canvas.drawBitmap(D1, 180, 100, null);
		canvas.drawBitmap(E1, 180, 200, null);
		canvas.drawBitmap(F1, 180, 300, null);
		
	
		
		if(startingPoint.sequence[i]==0)
			canvas.drawBitmap(A2, 50, 100, null);
		if(startingPoint.sequence[i]==1)
			canvas.drawBitmap(B2, 50, 200, null);
		if(startingPoint.sequence[i]==2)
			canvas.drawBitmap(C2, 50, 300, null);
		if(startingPoint.sequence[i]==3)
			canvas.drawBitmap(D2, 180, 100, null);
		if(startingPoint.sequence[i]==4)
			canvas.drawBitmap(E2, 180, 200, null);
		if(startingPoint.sequence[i]==5)
			canvas.drawBitmap(F2, 180, 300, null);
		
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(i);
		i++;
		if(i<=startingPoint.sequenceLength)
				invalidate();
		if(i==startingPoint.sequenceLength+1)
		{
			flag=true;

			//Intent intent = new Intent("com.project.source.FRONTPAGE");
			
		}
		
		
	}

}
