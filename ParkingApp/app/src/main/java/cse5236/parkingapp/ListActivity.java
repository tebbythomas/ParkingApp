package cse5236.parkingapp;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.location.*;
import android.location.Location;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;


public class ListActivity extends Activity {
    private static final String TAG = "ParkingApp";
    private static final String EXTRA_MESSAGE = "";
    private ListView mainListView ;
    private ArrayAdapter<String> listAdapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        Log.d(TAG, "Got Intent");
        setContentView(R.layout.fragment_list);

        /* Create DB Helper */
        Log.d("Create DB: ", "Creating DB Helper");
        DatabaseHelper db = new DatabaseHelper(this);
        Log.d("DB Created: ", "DB Helper Created");


// Find the ListView resource.
        mainListView = (ListView) findViewById( R.id.zonelist );

       List<cse5236.parkingapp.Location> locList = new ArrayList<cse5236.parkingapp.Location>();

        locList = db.getAllLocations();
       ArrayList<String> locNames = new ArrayList<String>();
       //Spanned desc;
        for (cse5236.parkingapp.Location loc : locList) {
           // desc= Html.fromHtml("<p style=\"font-size: 8pt\">"+loc.getDescription()+"</p>");
            String rate= Double.toString(loc.getPrice());
            if(rate.equals("0.0")){
                rate = "FREE";
            }
            else {rate = "$"+rate;}
            locNames.add(loc.getName() + "\t\t\t"+ "-       " + loc.getDescription() +  "\t\t\t" + "\nDistance: " + "0.5miles" + "\t\t\t" + "   \t                         Rate: " + rate);
        }
        //((TextView)findViewById(R.id.rowTextView)).setText(Html.fromHtml("X<sup>2</sup>"))

        listAdapter = new ArrayAdapter<String>(this, R.layout.simplerow, locNames);

        mainListView.setAdapter( listAdapter );

     /*   // Create and populate a List of planet names.
        String[] planets = new String[] { "Mercury", "Venus", "Earth", "Mars",
                "Jupiter", "Saturn", "Uranus", "Neptune"};
        ArrayList<String> planetList = new ArrayList<String>();
        planetList.addAll( Arrays.asList(planets) );

        // Create ArrayAdapter using the planet list.
        listAdapter = new ArrayAdapter<String>(this, R.layout.simplerow, planetList);

        // Add more planets. If you passed a String[] instead of a List<String>
        // into the ArrayAdapter constructor, you must not add more items.
        // Otherwise an exception will occur.
        listAdapter.add( "Ceres" );
        listAdapter.add( "Pluto" );
        listAdapter.add( "Haumea" );
        listAdapter.add( "Makemake" );
        listAdapter.add( "Eris" );

        // Set the ArrayAdapter as the ListView's adapter.
        mainListView.setAdapter( listAdapter );

        */
        // ListView Item Click Listener
        mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                String  itemValue    = (String) mainListView.getItemAtPosition(position);
                StringTokenizer st = new StringTokenizer(itemValue,"\t\t\t");
                String spotName = st.nextToken();

                details(mainListView, spotName);
               //ListView Clicked item index
              //  int itemPosition     = position;

                // ListView Clicked item value
              //  String  itemValue    = (String) mainListView.getItemAtPosition(position);

                // Show Alert
              //  Toast.makeText(getApplicationContext(),
              //          "Position :"+itemPosition+"  ListItem : " +itemValue , Toast.LENGTH_SHORT)
               //         .show();

            }

        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.list, menu);
        return true;
    }

    public void home(View view) {

        // Send the input string to the DisplayMessageActivity using an intent

        Log.d(TAG, "Home");

        Intent intent = new Intent(this, MainActivity.class);
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = "home" ;
        intent.putExtra(EXTRA_MESSAGE, message);

        startActivity(intent);
    }

    public void details(View view, String message) {

        // Send the input string to the DetailsActivity using an intent

        Log.d(TAG, "Details");

        Intent intent = new Intent(this, Details.class);
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String spotName = message ;
        intent.putExtra("spot", spotName);

        startActivity(intent);
    }

    public void openmapview(View view) {

        // Send the input string to the DisplayMessageActivity using an intent

        Log.d(TAG, "List");

        Intent intent = new Intent(this, MapviewActivity.class);
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = "list" ;
        intent.putExtra(EXTRA_MESSAGE, message);

        startActivity(intent);
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
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_list, container, false);
            return rootView;
        }
    }
}
