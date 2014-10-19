package com.prototype.jasmine;

import java.util.List;

import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class UserGps extends MapActivity {
	@Override
	protected boolean isRouteDisplayed() {
		return false;

	}
	int visited = 0;
	static Location l2 = new Location("l2");
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.usergps);


		MapView mapView = (MapView) findViewById(R.id.mapview);
		mapView.setBuiltInZoomControls(true);
		
		MapController mc = mapView.getController();
		//MapController mc = mapView.getController();
		
		List<Overlay> mapOverlays = mapView.getOverlays();
		Drawable mapIcon = this.getResources().getDrawable(R.drawable.cavmanfacesmall);
		mapIcon.setBounds(0,0,10,10);
		MapLocationsOverlay mapLocs = new MapLocationsOverlay(mapIcon, this);


		//UVa Rotunda
		GeoPoint point = new LatLonPoint(38.036363,-78.502219);
		OverlayItem overlayitem = new OverlayItem(point, "Wahoo!", "I'm at the UVa Rotunda!");
		
		//rotunda
		if(visited == 0){
			l2.setLatitude(38.036363);
			l2.setLongitude(-78.502219);
		}
			
		//add item
		mapLocs.addOverlay(overlayitem);
		mapOverlays.add(mapLocs);


	}


	public static Location getL2() {
		return l2;
	}


	public static void setL2(Location l2) {
		UserGps.l2 = l2;
	}


	//latlong method for geopoint
	private static final class LatLonPoint extends GeoPoint {
		public LatLonPoint(double latitude, double longitude) {
			super((int) (latitude * 1E6), (int) (longitude * 1E6));
		}
	}

}