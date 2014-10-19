package com.prototype.jasmine;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class splash extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        Thread logoTimer = new Thread() {
        	@Override
			public void run() {
        		try {
        			sleep(5000);
        			Intent menuStart = new Intent("com.prototype.jasmine.MENU");
        			startActivity(menuStart);
        		} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		finally {
        			finish();
        		}
        		
        	}
        };
        logoTimer.start();
    }
}