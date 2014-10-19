package com.prototype.jasmine;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

public class CongratulationsPage extends Activity {
	private Handler mHandler = new Handler();
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mHandler.postDelayed(new Runnable(){
			public void run() {
				doStuff();
		}}, 10000);
		
	}
	private void doStuff() {
		setContentView(R.layout.congratulations);

		Button menu = (Button) findViewById(R.id.congrats);
		menu.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				Intent myIntent = new Intent(v.getContext(),menu.class);
				startActivityForResult(myIntent, 0);
			}
		});
	}
}
