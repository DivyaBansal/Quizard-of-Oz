package com.example.ppl;

import com.example.ppl.Play_view.abc;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class Highscore extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.setContentView(R.layout.activity_highscore);
        SQLiteDatabase quiz=openOrCreateDatabase("Quiz.db",MODE_PRIVATE,null);
		TextView h1=(TextView)findViewById(R.id.h1);
		TextView s1=(TextView)findViewById(R.id.s1);
		TextView h2=(TextView)findViewById(R.id.h2);
		TextView s2=(TextView)findViewById(R.id.s2);
		TextView h3=(TextView)findViewById(R.id.h3);
		TextView s3=(TextView)findViewById(R.id.s3);
		Cursor cursor =quiz.rawQuery("SELECT * FROM Players ORDER BY score DESC LIMIT 3", null);
		if (cursor.moveToFirst()) {
		    h1.setText(cursor.getString(1));
			s1.setText(String.valueOf(cursor.getInt(2)));
			cursor.moveToNext();
			h2.setText(cursor.getString(1));
			s2.setText(String.valueOf(cursor.getInt(2)));
			cursor.moveToNext();
			h3.setText(cursor.getString(1));
			s3.setText(String.valueOf(cursor.getInt(2)));
        }
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.highscore, menu);
		return true;
	}
	
    public boolean onOptionsItemSelected(MenuItem item) {  
        switch (item.getItemId()) {  
            case R.id.soundOn:
            	startService(new Intent(this,music.class));                    	
            return true;
           case R.id.soundOff:  
        	   stopService(new Intent(this,music.class));
        	   return true;     
              default:  
                return super.onOptionsItemSelected(item);  
        }  
}
}
