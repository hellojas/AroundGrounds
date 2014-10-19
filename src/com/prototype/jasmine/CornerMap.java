package com.prototype.jasmine;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class CornerMap extends MapActivity {

	private MapView mapView;
	private MyLocationOverlay myLocationOverlay;
	private double distance = -1;
	private double lastDistance = -1;
	final private double range = 20;
	private GeoPoint myLocationGeoPoint;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// main.xml contains a MapView
		setContentView(R.layout.cornermap); 

		// extract MapView from layout
		mapView = (MapView) findViewById(R.id.mapview);
		mapView.setBuiltInZoomControls(true);

		// create an overlay that shows our current location
		myLocationOverlay = new FixedMyLocationOverlay(this, mapView);

		// add this overlay to the MapView and refresh it
		mapView.getOverlays().add(myLocationOverlay);
		mapView.postInvalidate();

		// call convenience method that zooms map on our location
		zoomToMyLocation();

		final Location bodos = new Location("The Corner");
		bodos.setLatitude(38.03525);
		bodos.setLongitude(-78.50078);
		GeoPoint point1 = new GeoPoint( (int) (bodos.getLatitude() * 1E6), (int) (bodos.getLongitude() * 1E6));
		
		List<Overlay> mapOverlays = mapView.getOverlays();
		Drawable mapIcon = this.getResources().getDrawable(R.drawable.cavmanfacesmall);
		mapIcon.setBounds(0,0,10,10);
		MapLocationsOverlay mapLocs = new MapLocationsOverlay(mapIcon, this);
		OverlayItem overlayitem = new OverlayItem(point1, "Wahoo!", "The Corner!");
		mapLocs.addOverlay(overlayitem);
		mapOverlays.add(mapLocs);

		final LocationManager manager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);

		LocationListener listener = new LocationListener(){

			@Override
			public void onLocationChanged(Location location) {
				// TODO Auto-generated method stub
				zoomToMyLocation();

				distance = location.distanceTo(bodos);

				if( (location.distanceTo(bodos) <= range )){
					Toast.makeText( getApplicationContext(), "Hot! You made it to: " + bodos.getProvider(), 
							Toast.LENGTH_LONG).show();
					Toast.makeText( getApplicationContext(), "Distance: " + Double.toString(location.distanceTo(bodos)), 
							Toast.LENGTH_SHORT).show();
					arrivedAtCorner();
					//should return location with next place
					Toast.makeText( getApplicationContext(), "", 
							Toast.LENGTH_LONG).show();
				}
				else if( location.distanceTo(bodos) < lastDistance ){
					Toast.makeText( getApplicationContext(), "Distance: " + Double.toString(location.distanceTo(bodos)), 
							Toast.LENGTH_SHORT).show();
					Toast.makeText( getApplicationContext(), "Getting warmer...", 
							Toast.LENGTH_SHORT).show();
					lastDistance = location.distanceTo(bodos);
				}
				else if( location.distanceTo(bodos) > lastDistance ){ //make five a field
					Toast.makeText( getApplicationContext(), "Distance: " + Double.toString(location.distanceTo(bodos)), 
							Toast.LENGTH_SHORT).show();
					Toast.makeText( getApplicationContext(), "Cold...", 
							Toast.LENGTH_SHORT).show();
					lastDistance = location.distanceTo(bodos);
				}
				else if ((location.distanceTo(bodos)) - (lastDistance) <= .25){
					Toast.makeText( getApplicationContext(), "You have not moved", 
							Toast.LENGTH_SHORT).show();
				}

			}



			@Override
			public void onProviderDisabled(String provider) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onProviderEnabled(String provider) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onStatusChanged(String provider, int status,
					Bundle extras) {
				// TODO Auto-generated method stub

			}
		};



		manager.requestLocationUpdates( LocationManager.GPS_PROVIDER, 5500000L , 0F, listener);

	}

	@Override
	protected void onResume() {
		super.onResume();
		// when our activity resumes, we want to register for location updates
		myLocationOverlay.enableMyLocation();
		myLocationOverlay.enableCompass();
	}

	@Override
	protected void onPause() {
		super.onPause();
		// when our activity pauses, we want to remove listening for location updates
		myLocationOverlay.disableMyLocation();
		myLocationOverlay.disableCompass();
	}

	/**
	 * This method zooms to the user's location with a zoom level of 10.
	 */
	private void zoomToMyLocation() {
		Intent uvatour = getIntent();
		if(uvatour.hasExtra("key1")){
			double lng = this.getIntent().getDoubleExtra("key1", 0.0) *1000000;
			double lat = uvatour.getDoubleExtra("key2", 0.0) * 1000000;

			myLocationGeoPoint = new GeoPoint((int) lat, (int) lng);
		}
		else{
			myLocationGeoPoint = myLocationOverlay.getMyLocation();
		}
		if(myLocationGeoPoint != null ) {

			mapView.getController().animateTo(myLocationGeoPoint);
			mapView.getController().setZoom(15);
		}
		else {
			//Toast.makeText(this, "Cannot determine location", Toast.LENGTH_SHORT).show();
		}
	}

	private void arrivedAtCorner() {
		//		Iterator<Location> itr = landmarks.iterator();
		//		if( itr.hasNext()){
		//			bodos = itr.next();
		//			Toast.makeText(this, "Next Stop! LAT:" + bodos.getLatitude() + ", LON: " + bodos.getLongitude() + "\nPLACE: "+  bodos.getProvider(), Toast.LENGTH_SHORT).show();
		//		}
		Toast.makeText(this, "Congratulations, you have reached the corner, now go and enjoy some tasty food."
				,Toast.LENGTH_LONG).show();
	}


	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

}
