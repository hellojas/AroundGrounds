package com.prototype.jasmine;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class Corner extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.corner);

		Button cornermap = (Button) findViewById(R.id.corner);
		cornermap.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent myIntent = new Intent(v.getContext(), CornerMap.class);
				startActivityForResult(myIntent, 0);
			}
		});
	}
}
