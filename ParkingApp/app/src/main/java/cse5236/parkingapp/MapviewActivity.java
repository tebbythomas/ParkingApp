package cse5236.parkingapp;

import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.google.android.gms.maps.*;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import java.util.List;
import java.io.IOException;
import android.location.Address;
import android.location.Geocoder;
import android.os.SystemClock;
import android.content.Intent;
import android.net.Uri;
import java.util.StringTokenizer;
import com.google.android.gms.maps.*;

public class MapviewActivity extends Activity implements GoogleMap.OnMarkerClickListener {
    private static final String EXTRA_MESSAGE = "";
    /**
     * Local variables *
     */
    GoogleMap googleMap;
    private static final String TAG = "MapviewActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent1 = getIntent();
        String message = intent1.getStringExtra(MainActivity.EXTRA_MESSAGE);
        Log.d(TAG, "Got Intent");
        setContentView(R.layout.mapview);
        createMapView();
        double coordinates[];
        coordinates = addMarker(message);
        /*SystemClock.sleep(10000);
        Intent intent2 = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse("http://maps.google.com/maps?saddr="+Double.toString(coordinates[0])+","+Double.toString(coordinates[1])+"&daddr="+Double.toString(coordinates[2])+","+Double.toString(coordinates[3])));
        startActivity(intent2);*/
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Initialises the mapview
     */
    private void createMapView() {
        /**
         * Catch the null pointer exception that
         * may be thrown when initialising the map
         */
        try {
            if (null == googleMap) {
                googleMap = ((MapFragment) getFragmentManager().findFragmentById(
                        R.id.mapView)).getMap();

                /**
                 * If the map is still null after attempted initialisation,
                 * show an error to the user
                 */
                if (null == googleMap) {
                    Toast.makeText(getApplicationContext(),
                            "Error creating map", Toast.LENGTH_SHORT).show();
                }
            }
        } catch (NullPointerException exception) {
            Log.e("ParkingApp", exception.toString());
        }
    }
    /**
     * Adds a marker to the map
     */
    private double[] addMarker(String message){
        double coordinates[] = new double[8];
        /** Make sure that the map has been initialised **/
        if(null != googleMap){
            double latitude = 0.0;
            double longitude = 0.0;
            List<Address> geocodeMatches = null;
            List<Address> geocodeMatches2 = null;
            List<Address> geocodeMatches3 = null;
            List<Address> geocodeMatches4 = null;
            List<Address> geocodeMatches5 = null;

            String Address1;
            String Address2;
            String State;
            String Zipcode;
            String completeAddress[] = new String[4];
            for(int i=0;i<4;i++)
            {
                completeAddress[i]="";
            }
            try {
                Log.d(TAG, message);
                StringTokenizer st = new StringTokenizer(message,";");
                geocodeMatches = new Geocoder(this).getFromLocationName(st.nextToken(), 1);
                geocodeMatches3 = new Geocoder(this).getFromLocationName("1542 N High St Columbus, OH 43201", 1);
                geocodeMatches4 = new Geocoder(this).getFromLocationName("152 East 11th Avenue Columbus, OH 43201", 1);
                geocodeMatches5 = new Geocoder(this).getFromLocationName("3021 Stadium Drive, Columbus, OH 43202", 1);

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            if (!geocodeMatches.isEmpty() || !geocodeMatches3.isEmpty() || !geocodeMatches4.isEmpty() || !geocodeMatches5.isEmpty())
            {
                coordinates[0] = geocodeMatches.get(0).getLatitude();
                coordinates[1] = geocodeMatches.get(0).getLongitude();
                coordinates[2] = geocodeMatches3.get(0).getLatitude();
                coordinates[3] = geocodeMatches3.get(0).getLongitude();
                coordinates[4] = geocodeMatches4.get(0).getLatitude();
                coordinates[5] = geocodeMatches4.get(0).getLongitude();
                coordinates[6] = geocodeMatches5.get(0).getLatitude();
                coordinates[7] = geocodeMatches5.get(0).getLongitude();

            }
            try {
                geocodeMatches =
                        new Geocoder(this).getFromLocation(coordinates[0], coordinates[1], 1);
                geocodeMatches =
                        new Geocoder(this).getFromLocation(coordinates[2], coordinates[3], 1);
                geocodeMatches =
                        new Geocoder(this).getFromLocation(coordinates[4], coordinates[5], 1);
                geocodeMatches =
                        new Geocoder(this).getFromLocation(coordinates[6], coordinates[7], 1);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (!geocodeMatches.isEmpty() || !geocodeMatches3.isEmpty() || !geocodeMatches4.isEmpty() || !geocodeMatches5.isEmpty())
            {
                Address1 = geocodeMatches.get(0).getAddressLine(0);
                Address2 = geocodeMatches.get(0).getAddressLine(1);
                State = geocodeMatches.get(0).getAdminArea();
                Zipcode = geocodeMatches.get(0).getPostalCode();
                completeAddress[0] += Address1 + " "+ Address2 + " "+ State + " " + Zipcode;
                Address1 = geocodeMatches3.get(0).getAddressLine(0);
                Address2 = geocodeMatches3.get(0).getAddressLine(1);
                State = geocodeMatches3.get(0).getAdminArea();
                Zipcode = geocodeMatches3.get(0).getPostalCode();
                completeAddress[1] += Address1 + " "+ Address2 + " "+ State + " " + Zipcode;
                Address1 = geocodeMatches4.get(0).getAddressLine(0);
                Address2 = geocodeMatches4.get(0).getAddressLine(1);
                State = geocodeMatches4.get(0).getAdminArea();
                Zipcode = geocodeMatches4.get(0).getPostalCode();
                completeAddress[2] += Address1 + " "+ Address2 + " "+ State + " " + Zipcode;
                Address1 = geocodeMatches5.get(0).getAddressLine(0);
                Address2 = geocodeMatches5.get(0).getAddressLine(1);
                State = geocodeMatches5.get(0).getAdminArea();
                Zipcode = geocodeMatches5.get(0).getPostalCode();
                completeAddress[3] += Address1 + " "+ Address2 + " "+ State + " " + Zipcode;
            }
            Location loc[] = new Location[4];
            for(int i=0, k=0;i<8;i+=2,k++)
            {
                loc[k] = new Location("");
                loc[k].setLatitude(coordinates[i]);
                loc[k].setLongitude(coordinates[i+1]);
            }
            for(int k=1; k<4;k++)
            {
                float distance = loc[0].distanceTo(loc[k]);
                completeAddress[k] += " \nDistance from searched location = " + Float.toString(distance);
                Log.i(TAG,Float.toString(distance));
            }
            googleMap.addMarker(new MarkerOptions()
                            .position(new LatLng(coordinates[0], coordinates[1]))
                            .title(completeAddress[0])
                            .draggable(true)
            );
            googleMap.addMarker(new MarkerOptions()
                            .position(new LatLng(coordinates[2], coordinates[3]))
                            .title(completeAddress[1])
                            .draggable(true)
            );
            googleMap.addMarker(new MarkerOptions()
                            .position(new LatLng(coordinates[4], coordinates[5]))
                            .title(completeAddress[2])
                            .draggable(true)
            );
            googleMap.addMarker(new MarkerOptions()
                            .position(new LatLng(coordinates[6], coordinates[7]))
                            .title(completeAddress[3])
                            .draggable(true)
            );
            LatLng ltln = new LatLng(coordinates[0],coordinates[1]);
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ltln,13));
            ///SystemClock.sleep(1000);
            ///Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
            ///        Uri.parse("http://maps.google.com/maps?saddr="+Double.toString(coordinates[0])+","+Double.toString(coordinates[0])+"&daddr="+Double.toString(coordinates[2])+","+Double.toString(coordinates[3])));
            ///startActivity(intent);
            return coordinates;
        }
        return coordinates;
    }

    @Override
    public boolean onMarkerClick(Marker marker) {

        return false;
    }

    public void home(View view) {

        // Send the input string to the DisplayMessageActivity using an intent

        Log.d(TAG, "Home");

        Intent intent = new Intent(this, MainActivity.class);
        String message = "home" ;
        intent.putExtra(EXTRA_MESSAGE, message);

        startActivity(intent);
    }

    public void list(View view) {

        // Send the input string to the DisplayMessageActivity using an intent

        Log.d(TAG, "List");

        Intent intent = new Intent(this, ListActivity.class);
        String message = "list" ;
        intent.putExtra(EXTRA_MESSAGE, message);

        startActivity(intent);
    }

}
