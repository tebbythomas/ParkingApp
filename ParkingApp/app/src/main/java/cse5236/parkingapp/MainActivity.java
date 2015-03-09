package cse5236.parkingapp;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.CheckBox;
import android.widget.EditText;


import android.util.Log;
import android.widget.TextView;



import static cse5236.parkingapp.R.layout.*;

public class MainActivity extends Activity {

    private static final String TAG = "ParkingApp";
    private String option_one = "";
    private String option_two = "";
    public final static String EXTRA_MESSAGE = "cse5236.parkingapp.MESSAGE";
    public View rootView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(main);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
        Log.d(TAG, "onCreate-Main");

        /* Create DB Helper */
        Log.d("Create DB: ", "Creating DB Helper");
        DatabaseHelper db = new DatabaseHelper(this);
        Log.d("DB Created: ", "DB Helper Created");

     /* // ADD LOCATIONS TO DATABASE. MUST ENSURE ONLY EXECUTED ON INSTALLATION OF APP ???
        Log.d("DB Initialization Begin: ","Logging Database Insertions...");

        db.addLocation(new Location(100001,"N High St","40.000560, -83.008190", "From Union to Wexner Arts Center", 0.00, "1",
                "99:99", "99:99", "10:00", "23:59", "10:00", "23:59", "10:00", "23:59", "10:00", "23:59", "10:00", "23:59", "10:00", "23:59",
                "Street Sweeping on Second Thursday and Second Friday"));
        db.addLocation(new Location(100002,"E 14th St","39.998880, -83.007286", "From Starbucks to Indianola", 0.75, "2",
                "08:00", "22:00", "08:00", "22:00", "08:00", "22:00", "08:00", "22:00", "08:00", "22:00", "08:00", "22:00", "08:00", "22:00",
                "Street Sweeping on Second Tuesday and Second Wednesday"));

        Log.d("DB Initialization End: ","...Database Insertions Logged");
        */
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        Log.d(TAG, "onCreateOptionsMenu");
        return true;
    }

    @Override
    public void onResume(){
        super.onResume();
        View view1 = findViewById(R.id.checkbox_street);
        View view2 = findViewById(R.id.checkbox_meter);
        onCheckboxClicked(view1);
        onCheckboxClicked(view2);
        Log.d(TAG, "onResume-Main");
    }
    @Override
    public void onPause(){
        super.onPause();
        Log.d(TAG,"onPause-Main");
    }
    @Override
      public void onStop(){
        super.onStop();
        Log.d(TAG,"onStop-Main");
    }
    @Override
    public void onStart(){
        super.onStart();
        Log.d(TAG,"onStart-Main");
    }

    @Override
    public void onRestart(){
        super.onRestart();
        Log.d(TAG,"onRestart-Main");
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d(TAG,"onStop-Main");
    }

/*
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
*/
    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
           View rootView = inflater.inflate(fragment_main, container, false);
           return rootView;
        }

    }
    public void openmapview(View view) {

        // Send the input string to the DisplayMessageActivity using an intent

        Log.d(TAG, "openmapview");

        Intent intent = new Intent(this, MapviewActivity.class);
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = editText.getText().toString() + ";" + option_one + ";" + option_two;
        intent.putExtra(EXTRA_MESSAGE, message);
        if (option_one=="" && option_two=="" ){
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("Parking Options");
            alertDialog.setMessage("Please select atleast one option");
            alertDialog.setButton("Close", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
// here you can add functions
                }
            });

            alertDialog.show();
        }
        else {
            startActivity(intent);}
    }

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.checkbox_street:
                if (checked){
                    option_one = " Street Parking ";
                }
                else
                {
                    option_one = "";
                }

                break;
            case R.id.checkbox_meter:
                if (checked)
                {
                    option_two = " Metered Parking ";
                }
                else
                {
                    option_two = "";
                }
                break;


        }
    }





}
