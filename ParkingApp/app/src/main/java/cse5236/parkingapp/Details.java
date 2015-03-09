package cse5236.parkingapp;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.StringTokenizer;


public class Details extends Activity implements LocationListener {

    private static final String TAG = "" ;
    private static final String EXTRA_MESSAGE = "" ;
    private ListView detailsListView ;
    private ArrayAdapter<String> listAdapter ;
    protected LocationManager locationManager;
    protected LocationListener locationListener;
    protected String latitude, longitude;
    protected String coord1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,this);
        String spotName = intent.getStringExtra("spot");

        Log.d(TAG, "Details View received the " + spotName);

        setContentView(R.layout.activity_details);
        TextView txt1 = (TextView) findViewById(R.id.zonenameindetails);
        TextView txt2 = (TextView) findViewById(R.id.zonedescription);
        TextView curDay = (TextView) findViewById(R.id.currentday);
        TextView curTimeFrame = (TextView) findViewById(R.id.timeframe);
        TextView curPrice = (TextView) findViewById(R.id.pricetoday);
        txt1.setText(spotName);

        /* Create DB Helper */
        Log.d("Create DB: ", "Creating DB Helper");
        DatabaseHelper db = new DatabaseHelper(this);
        Log.d("DB Created: ", "DB Helper Created");

        detailsListView = (ListView) findViewById(R.id.daylist);
        cse5236.parkingapp.Location loc1 = new cse5236.parkingapp.Location();
        loc1 = db.getLocation(spotName);
        coord1 = loc1.getCoord();
        txt2.setText(loc1.getDescription());

        ArrayList<String> days = new ArrayList<String>();


            // desc= Html.fromHtml("<p style=\"font-size: 8pt\">"+loc.getDescription()+"</p>");
               days.add("Sunday"+"\t\t\t"+loc1.getDay1s()+"-"+loc1.getDay1e()+"\t\t\t"+"$"+Double.toString(loc1.getPrice()));
        days.add("Monday   "+"\t\t\t"+loc1.getDay2s()+"-"+loc1.getDay2e()+"\t\t\t"+"$"+Double.toString(loc1.getPrice()));
        days.add("Tuesday  "+"\t\t\t"+loc1.getDay3s()+"-"+loc1.getDay3e()+"\t\t\t"+"$"+Double.toString(loc1.getPrice()));
        days.add("Wednesday"+"\t\t\t"+loc1.getDay4s()+"-"+loc1.getDay4e()+"\t\t\t"+"$"+Double.toString(loc1.getPrice()));
        days.add("Thursday "+"\t\t\t"+loc1.getDay5s()+"-"+loc1.getDay5e()+"\t\t\t"+"$"+Double.toString(loc1.getPrice()));
        days.add("Friday   "+"\t\t\t"+loc1.getDay6s()+"-"+loc1.getDay6e()+"\t\t\t"+"$"+Double.toString(loc1.getPrice()));
        days.add("Saturday "+"\t\t\t"+loc1.getDay7s()+"-"+loc1.getDay7e()+"\t\t\t"+"$"+Double.toString(loc1.getPrice()));
            //days.add(loc1.getName() + "\t\t\t"+ loc1.getDescription() +  "\t\t\t" + "Distance: " + "0.5miles" + "\t\t\t" + "Rate: " + Double.toString(loc1.getPrice()));
                //((TextView)findViewById(R.id.rowTextView)).setText(Html.fromHtml("X<sup>2</sup>"))
        for (int i=0; i<7; i++){
            StringTokenizer ssd = new StringTokenizer(days.get(i),"\t\t\t");
            while(ssd.hasMoreTokens()){
                String tok1 = ssd.nextToken();
                String tok2 = ssd.nextToken();
                String tok3 = ssd.nextToken();

                if(tok2.equals("99:99-99:99")){
                    tok2="NO PARKING ";
                    tok3="NA";
                }
                if(tok3.equals("$0.0")){
                    tok3="FREE";
                }
                days.set(i,tok1+"\t\t\t"+tok2+"\t\t\t"+tok3);
            }
        }

        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        StringTokenizer ssd2 = new StringTokenizer(days.get(day-1),"\t\t\t");
        curDay.setText(ssd2.nextToken());
        curTimeFrame.setText(ssd2.nextToken());
        curPrice.setText(ssd2.nextToken());


        listAdapter = new ArrayAdapter<String>(this, R.layout.simplerow, days);

        detailsListView.setAdapter(listAdapter);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.details, menu);
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

    @Override
    public void onLocationChanged(android.location.Location location) {
        latitude  = Double.toString(location.getLatitude());
        longitude = Double.toString(location.getLongitude());
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.d("Coordinates","Changed");
    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.d("Coordinates","Enabled");
    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.d("Coordinates","Disabled");
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_details, container, false);

            return rootView;
        }
    }

    public void opentimer(View view) {

        // Send the input string to the DisplayMessageActivity using an intent

        Log.d(TAG, "Timer");

        Intent intent = new Intent(this, TimerActivity.class);
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = "timer" ;
        intent.putExtra(EXTRA_MESSAGE, message);

        startActivity(intent);
    }

    public void opengmaps(View view) {

        // Send the input string to the DisplayMessageActivity using an intent

        Log.d(TAG, "Gmaps");

        //Intent intent = new Intent(this, TimerActivity.class);

        Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("http://maps.google.com/maps?saddr="+latitude+","+longitude+" &daddr="+coord1));
                startActivity(intent);




    }

    public void home(View view) {

        // Send the input string to the DisplayMessageActivity using an intent

        Log.d(TAG, "home");

        Intent intent = new Intent(this, MainActivity.class);
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = "home" ;
        intent.putExtra(EXTRA_MESSAGE, message);

        startActivity(intent);
    }

    public void list(View view) {

        // Send the input string to the DisplayMessageActivity using an intent

        Log.d(TAG, "List");

        Intent intent = new Intent(this, ListActivity.class);
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = "list" ;
        intent.putExtra(EXTRA_MESSAGE, message);

        startActivity(intent);
    }
}
