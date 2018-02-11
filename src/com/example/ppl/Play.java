package com.example.ppl;


import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Play extends Activity implements android.view.View.OnClickListener{
private static final String Player = "Players";
private static final String ID = "id";
private static final String Username="Username";
EditText t;
Button b;
@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.setContentView(R.layout.activity_play);
		t=(EditText)findViewById(R.id.editText1);
		b=(Button)findViewById(R.id.next);
	    b.setOnClickListener(this);
    }
	public void onClick(View arg0) {
		//try{
		String name;
		int id=0;
		name=t.getText().toString();
		//Toast.makeText(this,"Enterring:"+name,Toast.LENGTH_SHORT).show();
		SQLiteDatabase quiz=openOrCreateDatabase("Quiz.db",MODE_PRIVATE,null);
		//quiz.execSQL("DROP TABLE IF EXISTS " + Player);
		quiz.execSQL("CREATE TABLE IF NOT EXISTS "+Player+"("+ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+Username+" VARCHAR,"+"score INTEGER);");
		//quiz.execSQL("INSERT INTO "+Player+ " VALUES(1,'"+name+"',0);");
  		 ContentValues newValues = new ContentValues();
         // Assign values for each column.
    	 //newValues.put(ID,1);
    	 newValues.put(Username,name);
         newValues.put("score",0);
         quiz.insert(Player,null,newValues);
         Cursor cursor =quiz.rawQuery("SELECT * FROM "+Player+" ORDER BY ID DESC LIMIT 1", null);
	     if (cursor.moveToFirst()) {
				    id=cursor.getInt(0);
	     }	
         Intent intent = new Intent(this,Categories.class);
         Bundle b=new Bundle();
         b.putString("player",name);
         b.putInt("Id",id);
         intent.putExtras(b);
         startActivity(intent);
		 finish();
		 /*//Cursor c=quiz.query(Player,null,null,null,null,null,null);
         Cursor c = quiz.rawQuery("Select * from "+Player,null);
         //c.moveToFirst();
         //String username = resultSet.getString(1);
         //String password = resultSet.getString(2);
         if(c.moveToFirst())
         {
         	while(c.isAfterLast()==false)
         	{
         		s=c.getString(1);
         	    Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
              	c.moveToNext();
         	}
         }*/
         	//}
		//catch(Exception e){
	    	//Toast.makeText(this,"Invalid",Toast.LENGTH_LONG).show();
		//}
	}		 

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.play, menu);
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
