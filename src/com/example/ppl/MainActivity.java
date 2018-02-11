package com.example.ppl;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.setContentView(R.layout.activity_main);
        startService(new Intent(this,music.class));
        Button play,highscore,setting,exit;
        play=(Button)findViewById(R.id.play);
        setting=(Button)findViewById(R.id.set);
        exit=(Button)findViewById(R.id.exit);
        highscore=(Button)findViewById(R.id.high);
        play.setOnClickListener(new View.OnClickListener() {		
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,Play.class);
				startActivity(intent);
			}
        });
        setting.setOnClickListener(new View.OnClickListener() {		
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,Settings.class);
				startActivity(intent);
			}
        });
        exit.setOnClickListener(new View.OnClickListener() {		
			public void onClick(View v) {
				finish();	
				System.exit(0);
			}
        });
        highscore.setOnClickListener(new View.OnClickListener() {		
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,Highscore.class);
				startActivity(intent);
			}
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
           case R.id.action_settings:
        	   Intent intent = new Intent(MainActivity.this,Settings.class);
				startActivity(intent);
              default:  
                return super.onOptionsItemSelected(item);
        }  
}
}