package com.prototype.jasmine;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.view.*;
public class menu extends ListActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//				setContentView(R.layout.main);

		String[] menu_array = getResources().getStringArray(R.array.menu_array);
		setListAdapter(new ArrayAdapter<String>(this, R.layout.menu_nav, menu_array));

		ListView lv = getListView();
		lv.setTextFilterEnabled(true);

		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				switch( position )
				{
				case 0:  Intent tabLayout = new Intent(menu.this, UvaTour.class);     
				startActivity(tabLayout);
				break;
				case 1:  Intent startGps = new Intent(menu.this, EnterCoordinates.class); //UserGps     
				startActivity(startGps);
				break;
				case 2:	Intent history = new Intent(menu.this, LocationHistory.class); // change this history class file, jasmine
				startActivity(history);
				break;
				case 3:  Intent pageAboutUs = new Intent(menu.this, PageAboutUs.class);     
				startActivity(pageAboutUs);
				break;
				case 4:  Intent corner = new Intent(menu.this, Corner.class);
				startActivity(corner);
				break;
				}
			}
		});
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

}
