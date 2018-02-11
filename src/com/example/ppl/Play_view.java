package com.example.ppl;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Play_view extends Activity {
Boolean hasFinished=false,timerWanted=false;
TextView t,timer;
String name;
int no;
Button op1,op2,op3,op4,flip,hint;
int ans,score=0,q=1,id;
SQLiteDatabase quiz;
ArrayList<Integer> arr=new ArrayList();
private CountDownTimer countDownTimer;
private final long startTime = 2*60* 1000;
private final long interval = 1 * 1000;
 String hints;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		try{	
			SharedPreferences prefs = getSharedPreferences("Mypreff", MODE_PRIVATE); 
			timerWanted=prefs.getBoolean("timerValue",false);
			}
			catch(Exception e){
			timerWanted=false;
			}
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.setContentView(R.layout.activity_play_view);
		Intent i=getIntent();
		Bundle bu=i.getExtras();
		String v=bu.getString("cat");
		name=bu.getString("player");
		no=bu.getInt("Id");
		quiz=openOrCreateDatabase("quiz.db",MODE_PRIVATE,null);
		t=(TextView)findViewById(R.id.textView1);
		timer=(TextView)findViewById(R.id.timer);
        if(timerWanted==false){
		   timer.setVisibility(View.GONE);	
	    }
        else{
           timer.setVisibility(View.VISIBLE);	
           hasFinished=false;
		   countDownTimer = new MyCountDownTimer(startTime, interval);
		   countDownTimer.start();
        }
        op1=(Button)findViewById(R.id.option1);
		op2=(Button)findViewById(R.id.option2);
		op3=(Button)findViewById(R.id.option3);
		op4=(Button)findViewById(R.id.option4);
		flip=(Button)findViewById(R.id.button1);
		hint=(Button)findViewById(R.id.button2);
		if(v.equals("snu")){
			Cursor cursor =quiz.rawQuery("SELECT * FROM snu ORDER BY RANDOM() LIMIT 1", null);
			if (cursor.moveToFirst()) {
				    id=Integer.parseInt(cursor.getString(0));
					arr.add(id);
				    t.setText(cursor.getString(1));
					ans=Integer.parseInt(cursor.getString(6));
					op1.setText(cursor.getString(2));
					op2.setText(cursor.getString(3));
					op3.setText(cursor.getString(4));
					op4.setText(cursor.getString(5));
					hints=cursor.getString(7);
					op1.setOnClickListener(new abc("snu"));
					op2.setOnClickListener(new abc("snu"));
					op3.setOnClickListener(new abc("snu"));
					op4.setOnClickListener(new abc("snu"));
					flip.setOnClickListener(new abc("snu"));
					hint.setOnClickListener(new hin());
			}
		}
		else if(v.equals("got")){
			Cursor cursor =quiz.rawQuery("SELECT * FROM got ORDER BY RANDOM() LIMIT 1", null);
			if (cursor.moveToFirst()) {
				    id=Integer.parseInt(cursor.getString(0));
					arr.add(id);
				    t.setText(cursor.getString(1));
					ans=Integer.parseInt(cursor.getString(6));
					op1.setText(cursor.getString(2));
					op2.setText(cursor.getString(3));
					op3.setText(cursor.getString(4));
					op4.setText(cursor.getString(5));
					hints=cursor.getString(7);
					op1.setOnClickListener(new abc("got"));
					op2.setOnClickListener(new abc("got"));
					op3.setOnClickListener(new abc("got"));
					op4.setOnClickListener(new abc("got"));
					flip.setOnClickListener(new abc("got"));
					hint.setOnClickListener(new hin());
		   }
		}
		else if(v.equals("gk")){
			Cursor cursor =quiz.rawQuery("SELECT * FROM gk ORDER BY RANDOM() LIMIT 1", null);
			if (cursor.moveToFirst()) {
				    id=Integer.parseInt(cursor.getString(0));
					arr.add(id);
				    t.setText(cursor.getString(1));
					ans=Integer.parseInt(cursor.getString(6));
					op1.setText(cursor.getString(2));
					op2.setText(cursor.getString(3));
					op3.setText(cursor.getString(4));
					op4.setText(cursor.getString(5));
					hints=cursor.getString(7);
					op1.setOnClickListener(new abc("gk"));
					op2.setOnClickListener(new abc("gk"));
					op3.setOnClickListener(new abc("gk"));
					op4.setOnClickListener(new abc("gk"));
					flip.setOnClickListener(new abc("gk"));
					hint.setOnClickListener(new hin());
					cursor.close();
		   }
		}
		else if(v.equals("90s kid")){
			Cursor cursor =quiz.rawQuery("SELECT * FROM kid90s ORDER BY RANDOM() LIMIT 1", null);
			if (cursor.moveToFirst()) {
				    id=Integer.parseInt(cursor.getString(0));
					arr.add(id);
				    t.setText(cursor.getString(1));
					ans=Integer.parseInt(cursor.getString(6));
					op1.setText(cursor.getString(2));
					op2.setText(cursor.getString(3));
					op3.setText(cursor.getString(4));
					op4.setText(cursor.getString(5));
					hints=cursor.getString(7);
					op1.setOnClickListener(new abc("kid90s"));
					op2.setOnClickListener(new abc("kid90s"));
					op3.setOnClickListener(new abc("kid90s"));
					op4.setOnClickListener(new abc("kid90s"));
					flip.setOnClickListener(new abc("kid90s"));
					hint.setOnClickListener(new hin());
					cursor.close();
		   }
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.play_view, menu);
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
    class hin implements OnClickListener{
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			final Dialog dialog = new Dialog(Play_view.this);
			dialog.setContentView(R.layout.custom);
			dialog.setTitle("HINT!");
			TextView text = (TextView) dialog.findViewById(R.id.text);
			text.setText(hints);
			ImageView image = (ImageView)dialog.findViewById(R.id.image);
			image.setImageResource(R.drawable.hint);
			Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
			// if button is clicked, close the custom dialog
			dialogButton.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					dialog.dismiss();
				}
			});

			dialog.show();
		}
    	    }
	class abc implements OnClickListener{
		String table;
		abc(String t){
		table=t;
		}
		private int getRowCount(){
			Cursor c= quiz.rawQuery("SELECT  * FROM " + table, null);
		    int cnt = c.getCount();
		    c.close();
		    return cnt;	
		}
		public void onClick(View a) {
		switch(a.getId()){
		case R.id.option1:
			if(ans==1){
			score+=10;
			}
			else{
				score-=5;
			}
			break;
		case R.id.option2:
			if(ans==2){
			score+=10;
			}
			else{
				score-=5;
			}
			break;
		case R.id.option3:
			if(ans==3){
			score+=10;
			}
			else{
				score-=5;
			}
			break;
		case R.id.option4:
			if(ans==4){
			score+=10;
			}
			else{
				score-=5;
			}
			break;
		case R.id.button1:
				break;
		}
		Toast.makeText(Play_view.this,id+"="+q+" "+score,Toast.LENGTH_SHORT).show();
		if(timerWanted==true&&(hasFinished==true||q>=getRowCount())){
			Intent intent = new Intent(Play_view.this,ResultActivity.class);
			Bundle b=new Bundle();
	         b.putInt("score",score);
	         b.putString("player",name);
	         b.putInt("Id",no);
	         intent.putExtras(b);
	         startActivity(intent);
			 finish();
		}
		else if(timerWanted==false&&q>=10){
			Intent intent = new Intent(Play_view.this,ResultActivity.class);
			Bundle b=new Bundle();
	         b.putInt("score",score);
	         b.putString("player",name);
	         b.putInt("Id",no);
	         intent.putExtras(b);
	         startActivity(intent);
			 finish();
		 }
		else{
		Cursor cursor =quiz.rawQuery("SELECT * FROM "+table+" ORDER BY RANDOM() LIMIT 1", null);
		if (cursor.moveToFirst()) {
			  id=Integer.parseInt(cursor.getString(0));
			   while(arr.contains(id)){
		    		cursor.close();
		    		cursor =quiz.rawQuery("SELECT * FROM "+table+" ORDER BY RANDOM() LIMIT 1", null);
		    		cursor.moveToFirst();
		    		id=Integer.parseInt(cursor.getString(0));
		        }
	        	t.setText(cursor.getString(1));
				ans=Integer.parseInt(cursor.getString(6));
				op1.setText(cursor.getString(2));
				op2.setText(cursor.getString(3));
				op3.setText(cursor.getString(4));
				op4.setText(cursor.getString(5));
				hints=cursor.getString(7);
				arr.add(id);
		}
		q++;	
	}	
	}
	}
	public class MyCountDownTimer extends CountDownTimer {
		  public MyCountDownTimer(long startTime, long interval) {
		   super(startTime, interval);
		  }
		  @Override
		  public void onFinish() {
		  hasFinished=true;
		  }
		  @Override
		  public void onTick(long millisUntilFinished) {
		   String time=String.format("%02d:%02d",TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished)-TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)));  
		   timer.setText(time);
		  }
		 }
}