package com.facebook.android.project;

import com.facebook.android.R;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class NextLevel extends Activity 
{

	Button nextl;
	MediaPlayer song;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		startingPoint.level++;
		startingPoint.sequenceLength+=1;
		//System.out.println("Before Content view");
		setContentView(R.layout.nextlevellayout);
		song = MediaPlayer.create(NextLevel.this, R.raw.victory);
		song.start();
		nextl = (Button) findViewById(R.id.nextlevelbutton);
		//System.out.println("Before sety");
		nextl.setY(400);
		
		nextl.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
				System.out.println("Inside nextl");
				Intent intent = new Intent("com.facebook.android.project.SHOWLEVELPAGE");
				startActivity(intent);
				finish();
			}
		});
		
	}
}
