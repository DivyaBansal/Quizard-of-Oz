package com.example.ppl;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;

public class Settings extends Activity implements android.view.View.OnClickListener{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		Switch timer,sound;
		Button high;
		high=(Button)findViewById(R.id.button1);
		sound=(Switch)findViewById(R.id.switch1);
		timer=(Switch)findViewById(R.id.switch2);
		sound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
		    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		    	if (isChecked) {
		    		startService(new Intent(Settings.this,music.class));
		         }
		    	else {
		    	  stopService(new Intent(Settings.this,music.class));	
		    	}
		      }
		    });
		timer.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
		    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		    	if (isChecked) {
		    		SharedPreferences.Editor editor = getSharedPreferences("Mypreff", MODE_PRIVATE).edit();
		    		 editor.putBoolean("timerValue",true);
		    		 editor.commit();
		         }
		    	else {
		    		SharedPreferences.Editor editor = getSharedPreferences("Mypreff", MODE_PRIVATE).edit();
		    		 editor.putBoolean("timerValue",false);
		    		 editor.commit();
			     }
		      }
		    });
		high.setOnClickListener(this);
		
}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.settings, menu);
		return true;
	}


	@Override
	public void onClick(View arg0) {
		SQLiteDatabase quiz=openOrCreateDatabase("Quiz.db",MODE_PRIVATE,null);
		quiz.execSQL("DELETE FROM Players");
	}
}
