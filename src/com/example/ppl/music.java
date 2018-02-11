package com.example.ppl;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
//import android.widget.Toast;
import android.widget.Toast;

public class music extends Service{
	MediaPlayer myPlayer;
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	public void onCreate(){
		Toast.makeText(this,"Service Created",Toast.LENGTH_LONG).show();
		myPlayer=MediaPlayer.create(this,R.raw.spookymusic);
	}
	public void onStart(Intent intent,int startid){
		//Toast.makeText(this,"Service Started",Toast.LENGTH_LONG).show();
		myPlayer.start();
	}
	public void onDestroy(){
		//Toast.makeText(this,"Service Stopped",Toast.LENGTH_LONG).show();
		myPlayer.stop();
	}
}
