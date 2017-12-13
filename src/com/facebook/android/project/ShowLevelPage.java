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

public class ShowLevelPage extends Activity
{

	private TextView st;
	Button showlevelbutton;
	MediaPlayer song;
	@Override
	
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.showlevelpagelayout);
		song = MediaPlayer.create(ShowLevelPage.this, R.raw.startinglevelsound);
		song.start();
		st = (TextView) findViewById(R.id.showleveltext);
		st.setText("Level " + startingPoint.level);
		st.setTextSize(40);
		st.setTextColor(Color.BLACK);
		st.setX(100);
		st.setY(300);
		
		showlevelbutton = (Button) findViewById(R.id.sl);
		showlevelbutton.setX(100);
		showlevelbutton.setY(400);
		
		
		showlevelbutton.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
				song.stop();
				Intent intent = new Intent("com.facebook.android.project.GAMEPAGE");
		        startActivity(intent);
		        finish();
			}
		});
		
		
		
	}
	
}
