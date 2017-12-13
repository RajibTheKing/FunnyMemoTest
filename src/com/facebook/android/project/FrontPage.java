package com.facebook.android.project;



import com.facebook.android.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class FrontPage extends Activity 
{
	private ImageView img;
	private int x, y;
	Button b1, b2, b3, b4;
	MediaPlayer song;
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.frontpage);
		song = MediaPlayer.create(FrontPage.this, R.raw.menupagesound);
		song.start();
		ShowButtons();
		addListenerOnButton();
		
	}
	
	

	private void ShowButtons()
	{
		b1 = (Button) findViewById(R.id.button1);
		b2 = (Button) findViewById(R.id.button2);
		b3 = (Button) findViewById(R.id.button3);
		b4 = (Button) findViewById(R.id.button4);
	}

	
	
	private void addListenerOnButton() 
	{
		b1=(Button) findViewById(R.id.button1);
		b1.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
				song.stop();
				startingPoint.life=3;
				startingPoint.level=1;
				startingPoint.sequenceLength=3;
				startingPoint.score=0;
				
				Intent intent = new Intent("com.facebook.android.project.SHOWLEVELPAGE");
		        startActivity(intent);
		        finish();
		    }
		});
		
		b4=(Button) findViewById(R.id.button4);
		b4.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v)
			{
				song.stop();
				finish();
				System.exit(0);
			}
		});
		
	}
	
}
