package com.facebook.android.project;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;


public class startingPoint extends Activity 
{
    /** Called when the activity is first created. */
	public static int sequenceLength, level, life, score;
	public static int sequence[] = new int[50];
    @Override
    
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent("com.facebook.android.project.FRONTPAGE");
        //Intent intent = new Intent("com.facebook.android.project.GAMEOVERPAGE");
        //Intent intent = new Intent("com.facebook.android.project.GAMEPAGE");
        startActivity(intent);
        finish();
        
    }
}