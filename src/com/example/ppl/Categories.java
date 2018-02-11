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

public class Categories extends Activity implements android.view.View.OnClickListener{
Button snu,got,kid90s,gk;
String name;
int id;
@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.setContentView(R.layout.activity_categories);
		Intent i=getIntent();
		Bundle b=i.getExtras();
		name=b.getString("player");
		id=b.getInt("Id");
		snu=(Button)findViewById(R.id.op4);
		snu.setOnClickListener(this);
		gk=(Button)findViewById(R.id.op2);
		gk.setOnClickListener(this);
		kid90s=(Button)findViewById(R.id.op3);
		kid90s.setOnClickListener(this);
		got=(Button)findViewById(R.id.op1);
		got.setOnClickListener(this);
		
	}
		public void onClick(View aa){
		  switch(aa.getId()){
		  case R.id.op4:
	      {
	         Intent i=new Intent(Categories.this,Play_view.class);
	         Bundle bu=new Bundle();
	         bu.putString("cat","snu");
	         bu.putString("player",name);
	         bu.putInt("Id",id );
	         i.putExtras(bu);
	         startActivity(i);
	         break;
	      }
		  case R.id.op2:
		  {      Intent i=new Intent(Categories.this,Play_view.class);
		         Bundle bu=new Bundle();
		         bu.putString("cat","gk");
		         bu.putString("player",name);
		         bu.putInt("Id",id );
		         i.putExtras(bu);
		         startActivity(i);
		         break;
		  }    
		  case R.id.op1:
		  {      Intent i=new Intent(Categories.this,Play_view.class);
		         Bundle bu=new Bundle();
		         bu.putString("cat","got");
		         bu.putString("player",name);
		         bu.putInt("Id",id );
		         i.putExtras(bu);
		         startActivity(i);
		         break;
		  }
		  case R.id.op3:
		  {      Intent i=new Intent(Categories.this,Play_view.class);
		         Bundle bu=new Bundle();
		         bu.putString("cat","90s kid");
		         bu.putString("player",name);
		         bu.putInt("Id",id );
		         i.putExtras(bu);
		         startActivity(i);
		         break;
		  }      
		 }
	   }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.categories, menu);
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
