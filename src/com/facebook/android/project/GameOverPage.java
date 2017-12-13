package com.facebook.android.project;

import com.facebook.android.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class GameOverPage extends Activity 
{

	Button rt,e, fb_share;
	MediaPlayer song;
	TextView myscore;
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gameoverpagelayout);
		song = MediaPlayer.create(GameOverPage.this, R.raw.gameoversound);
		song.start();
		rt = (Button) findViewById(R.id.rtb);
		rt.setX(10);
		rt.setY(400);
		
		
		
		fb_share = (Button) findViewById(R.id.sare);
		fb_share.setX(110);
		fb_share.setY(300);
		
		e = (Button) findViewById(R.id.eb);
		e.setX(210);
		e.setY(350);
		
		
		
		myscore = (TextView) findViewById(R.id.scoretext);
		myscore.setText("Your Score is: " + startingPoint.score);
		myscore.setTextSize(20);
		myscore.setTextColor(Color.RED);
		myscore.setX(150);
		myscore.setY(90);
		
		
		
		rt.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
				song.stop();
				Intent n = new Intent("com.facebook.android.project.FRONTPAGE" );
				startActivity(n);
				finish();
			}

			
		});
		
		e.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
				song.stop();
				finish();
				System.exit(0);
			}

			
		});
		
		fb_share.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
				song.stop();
				Intent intent = new Intent("com.facebook.android.EXAMPLE");
				startActivity(intent);
				finish();
				System.exit(0);
			}

			
		});
		
		
	}
	

}
