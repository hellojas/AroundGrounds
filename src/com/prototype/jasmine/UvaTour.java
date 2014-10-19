package com.prototype.jasmine;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.location.GpsStatus.Listener;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class UvaTour extends MapActivity {

	private MapView mapView;
	private MyLocationOverlay myLocationOverlay;
	private double distance = -1;
	private double lastDistance = -1;
	private Location nextStop; //extend location class
	private ArrayList<Location> landmarks = new ArrayList<Location>();
	final private double range = 20;
	int locNum;
	static int locNum1;
	private GeoPoint myLocationGeoPoint;
	private static Location nextStop1;
	private final static Location rotunda = new Location("Rotunda");
	private long currentTime; //Initialize this to 0 somewhere.
	private boolean paused; //Intialize this to false somewhere.
	private long startTimePoint;


	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// main.xml contains a MapView
		setContentView(R.layout.uvatour);  //needs to be set to uvatour

		// extract MapView from layout
		mapView = (MapView) findViewById(R.id.mapview);
		mapView.setBuiltInZoomControls(true);

		// create an overlay that shows our current location
		myLocationOverlay = new FixedMyLocationOverlay(this, mapView);

		// add this overlay to the MapView and refresh it
		mapView.getOverlays().add(myLocationOverlay);
		mapView.postInvalidate();
		
		currentTime = 0;
		startTimePoint = System.currentTimeMillis();
		paused = false;
		// call convenience method that zooms map on our location
		zoomToMyLocation();
	
			final Location rotunda = new Location("Rotunda");
			rotunda.setLatitude(38.0356954951106);
			rotunda.setLongitude(-78.5033440589905);
		// use an arraylist put here
		landmarks.add(rotunda);
		GeoPoint point1 = new GeoPoint( (int) (rotunda.getLatitude() * 1E6), (int) (rotunda.getLongitude() * 1E6));
		nextStop = landmarks.get(0);
		
		final Location chapel = new Location("Chapel");
		chapel.setLatitude(38.0363377167213);
		chapel.setLongitude(-78.5044062137604);
		GeoPoint point2 = new GeoPoint( (int) (chapel.getLatitude() * 1E6), (int) (chapel.getLongitude() * 1E6));
		landmarks.add(chapel);

		final Location clemons = new Location("Clemons");
		clemons.setLatitude(38.0365067214724);
		clemons.setLongitude(-78.5060369968414);
		GeoPoint point3 = new GeoPoint( (int) (clemons.getLatitude() * 1E6), (int) (clemons.getLongitude() * 1E6));
		landmarks.add(clemons);

		final Location newcomb = new Location("Newcomb");
		newcomb.setLatitude(38.0356109918478);
		newcomb.setLongitude(-78.5064661502838);
		GeoPoint point4 = new GeoPoint( (int) (newcomb.getLatitude() * 1E6), (int) (newcomb.getLongitude() * 1E6));
		landmarks.add(newcomb);

		final Location olddorms = new Location("Old Dorms");
		olddorms.setLatitude(38.0353828325513);
		olddorms.setLongitude(-78.5114228725433);
		GeoPoint point5 = new GeoPoint( (int) (olddorms.getLatitude() * 1E6), (int) (olddorms.getLongitude() * 1E6));
		landmarks.add(olddorms);
		final LocationManager manager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
		locNum = 0;
		
		List<Overlay> mapOverlays = mapView.getOverlays();
		Drawable mapIcon = this.getResources().getDrawable(R.drawable.cavmanfacesmall);
		mapIcon.setBounds(0,0,10,10);
		MapLocationsOverlay mapLocs = new MapLocationsOverlay(mapIcon, this);
		OverlayItem overlayitem = new OverlayItem(point1, "Wahoo!", "UVa Rotunda!");
		OverlayItem overlayitem2 = new OverlayItem(point2, "Wahoo!", "UVa Chapel!");
		OverlayItem overlayitem3 = new OverlayItem(point3, "Wahoo!", "Clemons!");
		OverlayItem overlayitem4 = new OverlayItem(point4, "Wahoo!", "Newcomb Hall!");
		OverlayItem overlayitem5 = new OverlayItem(point5, "Wahoo!", "Old dorms!");
		mapLocs.addOverlay(overlayitem);
		mapLocs.addOverlay(overlayitem2);
		mapLocs.addOverlay(overlayitem3);
		mapLocs.addOverlay(overlayitem4);
		mapLocs.addOverlay(overlayitem5);
		mapOverlays.add(mapLocs);

		LocationListener listener = new LocationListener(){

			@Override
			public void onLocationChanged(Location location) {
				// TODO Auto-generated method stub
				zoomToMyLocation();

				distance = location.distanceTo(nextStop);
				
				/*if( (location.distanceTo(nextStop) <= range ) && locNum < 5 ){
					Toast.makeText( getApplicationContext(), "Hot! You made it to: " + nextStop.getProvider(), 
							Toast.LENGTH_LONG).show();
					Toast.makeText( getApplicationContext(), "Distance: " + location.distanceTo(nextStop), 
							Toast.LENGTH_SHORT).show();
					showHistory(locNum);
					locNum++;
					arrivedAtNextStop(locNum);
				}
				else if( location.distanceTo(nextStop) > 35 ){ //make five a field
					Toast.makeText( getApplicationContext(), "Distance: " + Double.toString(location.distanceTo(nextStop)), 
							Toast.LENGTH_LONG).show();
					Toast.makeText( getApplicationContext(), "Cold...", 
							Toast.LENGTH_SHORT).show();
					lastDistance = location.distanceTo(nextStop);
				}
				else if( (location.distanceTo(nextStop) < 35) && (distance > range)  ){
					Toast.makeText( getApplicationContext(), Double.toString(location.distanceTo(nextStop)), 
							Toast.LENGTH_LONG).show();
					Toast.makeText( getApplicationContext(), "Getting warmer...", 
							Toast.LENGTH_SHORT).show();
					lastDistance = location.distanceTo(nextStop);
				}*/
				if( (location.distanceTo(nextStop) <= range ) && locNum < 5 ){
					Toast.makeText( getApplicationContext(), "Hot! You made it to: " + nextStop.getProvider(), 
							Toast.LENGTH_LONG).show();
					showHistory(locNum);
					locNum++;
					arrivedAtNextStop(locNum);
					//should return location with next place
				}
				else if( location.distanceTo(nextStop) < lastDistance ){
					Toast.makeText( getApplicationContext(), "Getting warmer...", 
							Toast.LENGTH_SHORT).show();
					lastDistance = location.distanceTo(nextStop);
				}
				else if( location.distanceTo(nextStop) > lastDistance ){ //make five a field
					Toast.makeText( getApplicationContext(), "Cold...", 
							Toast.LENGTH_SHORT).show();
					lastDistance = location.distanceTo(nextStop);
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
			//I tried to rotate the map so that the top of the screen is where you want to go, but I got nowhere.
			//			public void onSensorChanged (int sensor, float[] values) {
			//				LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
			//				Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
			//
			//				float azimuth = values [0];// get azimuth from the orientation sensor 
			//						// convert radians to degrees
			//						azimuth = azimuth * 360 / (2 * (float) Math.PI);
			//				GeomagneticField geoField = new GeomagneticField(
			//						Double.valueOf(location.getLatitude()).floatValue(),
			//						Double.valueOf(location.getLongitude()).floatValue(),
			//						Double.valueOf(location.getAltitude()).floatValue(),
			//						System.currentTimeMillis());
			//				azimuth += geoField.getDeclination(); // converts magnetic north into true north
			//				float bearing = location.bearingTo(nextStop); // (it's already in degrees)
			//				float direction = azimuth - bearing;
			//				Canvas canvas = new Canvas();
			//				canvas.save(Canvas.MATRIX_SAVE_FLAG);
			//				canvas.rotate(-direction);
			//			}
		};
		
		manager.requestLocationUpdates( LocationManager.GPS_PROVIDER, 15500000L , 0F, listener);
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
			Location myLoc = new Location ("myLoc");
			myLoc.setLongitude(lng/1E6);
			myLoc.setLatitude(lat/1E6);
			
			List<Overlay> mapOverlays = mapView.getOverlays();
			Drawable mapIcon = this.getResources().getDrawable(R.drawable.marker);
			mapIcon.setBounds(0,0,10,10);
			MapLocationsOverlay mapLocs1 = new MapLocationsOverlay(mapIcon, this);
			OverlayItem overlay = new OverlayItem(myLocationGeoPoint, "Wahoo!", "You are here!");
			mapLocs1.addOverlay(overlay);
			mapOverlays.add(mapLocs1);
			
			nextStop1 = getNextStop1();
			distance = myLoc.distanceTo(nextStop1);///1E7;
			lastDistance = distance;
			locNum1 = getLocNum1();
			
			if( (distance <= range ) && locNum1 < 5 ){
				Toast.makeText( getApplicationContext(), "Hot! You made it to: " + nextStop1.getProvider(), 
						Toast.LENGTH_LONG).show();
				showHistory(locNum1);
				//should return location with next place
				/*Toast.makeText( getApplicationContext(), "", 
						Toast.LENGTH_LONG).show();**/
			}
			else if( distance > 30 ){ //make five a field
				Toast.makeText( getApplicationContext(), "Cold...", 
						Toast.LENGTH_SHORT).show();
				lastDistance = distance;
			}
			else if( (distance < 30) && (distance > range)  ){
				Toast.makeText( getApplicationContext(), "Getting warmer...", 
						Toast.LENGTH_SHORT).show();
				lastDistance = distance;
			}
			/*else if (distance == lastDistance ){
				/*Toast.makeText( getApplicationContext(), "You have not moved", 
						Toast.LENGTH_SHORT).show();
			};**/
			
			//radio buttons
		}
		else{
			myLocationGeoPoint = myLocationOverlay.getMyLocation();
		}
		if(myLocationGeoPoint != null ) {

			mapView.getController().animateTo(myLocationGeoPoint);
			mapView.getController().setZoom(18);
		}
		else {
			//Toast.makeText(this, "Cannot determine location", Toast.LENGTH_SHORT).show();
		}
	}
	
	
	public static Location getRotunda() {
		rotunda.setLatitude(38.0356954951106);
		rotunda.setLongitude(-78.5033440589905);
		
		return rotunda;
	}

	public static void setNextStop1(Location nextStop) {
		nextStop1 = nextStop;
	}

	public Location getNextStop1() {
		return nextStop1;
	}

	public int getLocNum1() {
		return locNum1;
	}

	public static void setLocNum1(int locNum) {
		locNum1 = locNum;
	}
	public GeoPoint getMyLocationGeoPoint() {
		return myLocationGeoPoint;
	}
	public long pauseCounting(){
		currentTime = System.currentTimeMillis() - startTimePoint;
		paused = true;
		return currentTime;
	}
	/*public void startCounting(){
		if( paused ) {
			   startTimePoint = System.currentTimeMillis() - currentTime();
			   paused = false;
			}
	}*/
	public String currentTimeString() {
		  long interval;

		  if( !paused )
		    interval = System.currentTimeMillis() - startTimePoint;
		  else
		    interval = currentTime;

		  int tens = (int) interval;
		  int seconds = (int) interval / 1000;
		  int minutes = seconds / 60;
		  int hours = minutes / 60;
		  tens = tens % 10;
		  seconds = seconds % 60;

		  return String.format("%d:%02d:%02d.%d", hours, minutes, seconds, tens);
		}

	private void showHistory(int locNum) {
		if (locNum == 0) {
			Intent rotunda = new Intent(this, Loc5.class);
			startActivityForResult(rotunda, 0);
		}

		if (locNum == 1) {
			Intent chapel = new Intent(this, Loc4.class);
			startActivityForResult(chapel, 0);
		}

		if (locNum == 2) {
			Intent clemons = new Intent(this, Loc3.class);
			startActivityForResult(clemons, 0);
		}

		if (locNum == 3) {
			Intent newcomb = new Intent(this, Loc2.class);
			startActivityForResult(newcomb, 0);
		}

		if (locNum == 4) {
			Intent olddorms = new Intent(this, Loc1.class);
			startActivityForResult(olddorms, 0);
		}
	}
	
	private void arrivedAtNextStop(int locNum) {
		//		Iterator<Location> itr = landmarks.iterator();
		//		if( itr.hasNext()){
		//			nextStop = itr.next();
		//			Toast.makeText(this, "Next Stop! LAT:" + nextStop.getLatitude() + ", LON: " + nextStop.getLongitude() + "\nPLACE: "+  nextStop.getProvider(), Toast.LENGTH_SHORT).show();
		//		}
		if (locNum < 5) {
			nextStop = landmarks.get(locNum);
			Toast.makeText(this, "Next Stop! LAT:" +
					nextStop.getLatitude() + ", \nLON: " +
					nextStop.getLongitude() + "\nPLACE: "+
					nextStop.getProvider(), Toast.LENGTH_LONG).show();
		}
		else {
			Toast.makeText(this, "Congratulations, you have reached the end of the tour"
					,Toast.LENGTH_LONG).show();
			Intent congrats = new Intent(this, CongratulationsPage.class);
			startActivityForResult(congrats, 0);
			long time = pauseCounting();
			Toast.makeText(this, "You finished in: " + currentTimeString(), Toast.LENGTH_LONG).show();
		}
		lastDistance = -1;
	}
	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
}