package com.facebook.android.project;

import com.facebook.android.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Gallery.LayoutParams;

public class GamePage extends Activity
{
	Button go;
	DrawGamePage DG;
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gamepage);
		
		DG = new DrawGamePage(GamePage.this);
		ViewGroup.LayoutParams params =  new ViewGroup.LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT);
		addContentView(DG, params);
		showButtons();
		addActionListener();
	}

	private void addActionListener() 
	{
		go = (Button) findViewById(R.id.gonext);
		go.setOnClickListener(new OnClickListener() 
		{
			
		public void onClick(View v) 
		{
			if(DG.check()==true)
			{
				
				Intent intent = new Intent("com.facebook.android.project.USERINPUTPAGE");
				//Intent intent = new Intent("com.project.source.SHOWLEVELPAGE");
				startActivity(intent);
				finish();
			}
	        
	    }
	});
		
	}

	private void showButtons() 
	{
		go = (Button) findViewById(R.id.gonext);
		go.setX(100);
		go.setY(380);
		
	}
}
