package com.prototype.jasmine;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class EnterCoordinates extends Activity {
	private boolean rot;
	private boolean chap;
	private boolean newc;
	private boolean clem;
	private boolean oldd;
	private boolean selected = false;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.usercoordinates);

		final TextView tv1 = (TextView) findViewById(R.id.entryLongitude);
		final TextView tv2 = (TextView) findViewById(R.id.entryLatitude);
		final Button button = (Button) findViewById(R.id.ok);
		button.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
//				if(selected == true){
					Intent uvatour = new Intent(EnterCoordinates.this, UvaTour.class);
					double lng = Double.parseDouble(tv1.getText().toString().trim());
					double lat = Double.parseDouble(tv2.getText().toString().trim());
					if(rot == true){
						Toast.makeText(EnterCoordinates.this, "Rotunda", Toast.LENGTH_SHORT).show();
						final Location rotunda = new Location("Rotunda");
						rotunda.setLatitude(38.0356954951106);
						rotunda.setLongitude(-78.5033440589905);
						UvaTour.setLocNum1(0);
						UvaTour.setNextStop1(rotunda);
					}
					else if(chap == true){
						Toast.makeText(EnterCoordinates.this, "Chapel", Toast.LENGTH_SHORT).show();
						final Location chapel = new Location("Chapel");
						chapel.setLatitude(38.0363377167213);
						chapel.setLongitude(-78.5044062137604);
						UvaTour.setLocNum1(1);
						UvaTour.setNextStop1(chapel);
					}
					else if(clem == true){
						Toast.makeText(EnterCoordinates.this, "Clemons", Toast.LENGTH_SHORT).show();
						final Location clemons = new Location("Clemons");
						clemons.setLatitude(38.0365067214724);
						clemons.setLongitude(-78.5060369968414);
						UvaTour.setLocNum1(2);
						UvaTour.setNextStop1(clemons);
					}
					else if(newc == true){
						Toast.makeText(EnterCoordinates.this, "Newcomb", Toast.LENGTH_SHORT).show();
						final Location newcomb = new Location("Newcomb");
						newcomb.setLatitude(38.0356109918478);
						newcomb.setLongitude(-78.5064661502838);
						UvaTour.setLocNum1(3);
						UvaTour.setNextStop1(newcomb);
					}
					else if(oldd == true){
						Toast.makeText(EnterCoordinates.this, "Old Dorms", Toast.LENGTH_SHORT).show();
						final Location olddorms = new Location("Old Dorms");
						olddorms.setLatitude(38.0353828325513);
						olddorms.setLongitude(-78.5114228725433);
						UvaTour.setLocNum1(4);
						UvaTour.setNextStop1(olddorms);
					}

					uvatour.putExtra("key1", lng);
					uvatour.putExtra("key2", lat);
					//uvatour.setClassName("com.prototype.jasmine", "com.prototype.jasmine.UvaTour");
					startActivity(uvatour);
					tv1.setText("");
					tv2.setText("");
					rot = false;
					clem = false;
					chap = false;
					newc = false;
					oldd = false;
					selected = false;
				//}
				//else{
					Toast.makeText(EnterCoordinates.this, "Please choose a target location", Toast.LENGTH_LONG).show();	
				//}
			}
		});
	}
	public void onRotundaClicked(View v) {
		// Perform action on clicks, depending on whether it's now checked
		RadioButton rb = (RadioButton) v;
		rot = true;
		clem = false;
		chap = false;
		newc = false;
		oldd = false;

		selected = true;

	}
	public void onChapelClicked(View v){
		// Perform action on clicks, depending on whether it's now checked
		RadioButton rb = (RadioButton) v;
		chap = true;
		rot = false;
		clem = false;
		newc = false;
		oldd = false;
		selected = true;

	}
	public void onClemonsClicked(View v){
		// Perform action on clicks, depending on whether it's now checked
		RadioButton rb = (RadioButton) v;
		clem = true;
		rot = false;
		chap = false;
		newc = false;
		oldd = false;
		selected = true;
	}
	public void onNewcombClicked(View v){
		// Perform action on clicks, depending on whether it's now checked
		RadioButton rb = (RadioButton) v;
		newc = true;
		rot = false;
		clem = false;
		chap = false;
		oldd = false;
		selected = true;
	}
	public void onOldDormsClicked(View v){
		// Perform action on clicks, depending on whether it's now checked
		RadioButton rb = (RadioButton) v;
		oldd = true;
		rot = false;
		clem = false;
		chap = false;
		newc = false;
		selected = true;

	}
}
