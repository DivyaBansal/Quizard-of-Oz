package com.example.ppl;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class ResultActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);
		Intent intent=getIntent();
		Bundle bu=intent.getExtras();
		String name=bu.getString("player");
		int Id=bu.getInt("Id");
		int score=bu.getInt("score");
		TextView t=(TextView)findViewById(R.id.h1);
		t.setText(String.valueOf(score));
		SQLiteDatabase quiz=openOrCreateDatabase("Quiz.db",MODE_PRIVATE,null);
		quiz.execSQL("UPDATE Players SET score="+score+" WHERE ID="+Id+";");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.result, menu);
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
