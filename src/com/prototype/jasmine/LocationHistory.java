package com.prototype.jasmine;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.view.*;
import android.widget.Toast;
public class LocationHistory extends ListActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//				setContentView(R.layout.main);

		String[] loc_menu_array = getResources().getStringArray(R.array.loc_menu_array);
		setListAdapter(new ArrayAdapter<String>(this, R.layout.menu_nav, loc_menu_array));

		ListView lv = getListView();
		lv.setTextFilterEnabled(true);

		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				switch( position )
				{
				case 0:  Intent loc1 = new Intent(LocationHistory.this, Loc1.class);     
				startActivity(loc1);
				break;
				case 1:  Intent loc2 = new Intent(LocationHistory.this, Loc2.class);   
				startActivity(loc2);
				break;
				case 2:	Intent loc3 = new Intent(LocationHistory.this, Loc3.class); 
				startActivity(loc3);
				break;
				case 3:  Intent loc4 = new Intent(LocationHistory.this, Loc4.class);     
				startActivity(loc4);
				break;
				case 4:  Intent loc5 = new Intent(LocationHistory.this, Loc5.class);     
				startActivity(loc5);
				break;

				}
			}
		});
	}


}
