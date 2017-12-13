package com.facebook.android.project;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Picture;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.view.View;
import android.widget.ImageView;

public class CustomDrawableView extends View
{
	private Picture i;
	private int y;
	boolean flag=false;
	public CustomDrawableView(Context context) 
	{
		super(context);
		y=500;
		
		// TODO Auto-generated constructor stub
	}
	
	protected void onDraw(Canvas canvas)
	{
		
        
        canvas.drawPicture(i);
		
		update();
		try
		{
			Thread.sleep(30);
		}catch (InterruptedException e){
			
		}
		invalidate();
	}

	private void update() 
	{
		if(flag==false)
		{
			y+=5;
			if(y>100) flag=true;
		}
		if(flag==true)
		{
			y-=5;
			if(y<10) flag=false;
			
		}
		
	}

}
