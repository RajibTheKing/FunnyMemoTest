package com.facebook.android.project;

import com.facebook.android.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class UserInputPage extends Activity
{
	private Button red, blue, green, yellow, pink, cyan;
	private TextView t;
	private int length;
	private int serial[] = new int[50];
	private int key;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user);
		length=startingPoint.sequenceLength;
		key=0;
		
		showButtons();
		showText();
		addActionListener();
		
		
		
	}
	private void showResult() 
	{
		// TODO Auto-generated method stub
		boolean checkResult = true;
		for(int i=0;i<startingPoint.sequenceLength;i++)
		{
			if(startingPoint.sequence[i]!=serial[i])
			{
				checkResult = false;
				break;
			}
		}
		
		if(checkResult)
		{
			System.out.println("Correct");
			startingPoint.score+=(startingPoint.level*10);
			Intent intent = new Intent("com.facebook.android.project.NEXTLEVEL");
			startActivity(intent);
			finish();
		}
		else
		{
			System.out.println("Incorrect");
			startingPoint.life--;
			if(startingPoint.life==0)
			{
				Intent intent = new Intent("com.facebook.android.project.GAMEOVERPAGE");
				startActivity(intent);
				finish();
			}
			else
			{
				Intent intent = new Intent("com.facebook.android.project.GAMEPAGE");
				startActivity(intent);
				finish();
			}
			
			
		}
			
		
		
	}
	
	private void showText() 
	{
		// TODO Auto-generated method stub
		t = (TextView) findViewById(R.id.press);
		t.setText(""+ length +"   Press Remaining");
		t.setX(20);
		t.setY(50);
		
		
	}
	private void showButtons() 
	{
		// TODO Auto-generated method stub
		red = (Button) findViewById(R.id.red);
		blue = (Button) findViewById(R.id.blue);
		green = (Button) findViewById(R.id.green);
		yellow = (Button) findViewById(R.id.yellow);
		pink = (Button) findViewById(R.id.pink);
		cyan = (Button) findViewById(R.id.cyan);
		
		red.setX(50); red.setY(100);
		blue.setX(50); blue.setY(200);
		green.setX(50); green.setY(300);
		yellow.setX(180); yellow.setY(100);
		pink.setX(180); pink.setY(200);
		cyan.setX(180); cyan.setY(300);
		
		
	}
	
	private void addActionListener() 
	{
		red = (Button) findViewById(R.id.red);
		blue = (Button) findViewById(R.id.blue);
		green = (Button) findViewById(R.id.green);
		yellow = (Button) findViewById(R.id.yellow);
		pink = (Button) findViewById(R.id.pink);
		cyan = (Button) findViewById(R.id.cyan);
		
		// TODO Auto-generated method stub
		
		red.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
				serial[key++]=0;
				length--;
				if(length==0) showResult();
				t.setText(""+length+"   Press Remaining");
			}

			
		});
		blue.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
				serial[key++]=1;
				length--;
				if(length==0) showResult();
				t.setText(""+length+"   Press Remaining");
			}
		});
		green.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
				serial[key++]=2;
				length--;
				if(length==0) showResult();
				t.setText(""+length+"   Press Remaining");
			}
		});
		yellow.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
				serial[key++]=3;
				length--;
				if(length==0) showResult();
				t.setText(""+length+"   Press Remaining");
			}
		});
		pink.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
				serial[key++]=4;
				length--;
				if(length==0) showResult();
				t.setText(""+length+"   Press Remaining");
			}
		});
		cyan.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
				serial[key++]=5;
				length--;
				if(length==0) showResult();
				t.setText(""+length+"   Press Remaining");
			}
		});
		
		
	}
	
}
