package cse5236.parkingapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "ParkingApp.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "MasterKey";
    private Context context;
    private SQLiteDatabase db;
    private SQLiteStatement insertStmt;
    private static final String INSERT = "insert into " + TABLE_NAME + "(id, name, coord, description, price, type, day1s, day1e, day2s, day2e, day3s, day3e, day4s, day4e, day5s, day5e, day6s, day6e, day7s, day7e, restriction) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    /* MasterKey Table Column Names */
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_COORD = "coord";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_PRICE = "price";
    private static final String KEY_TYPE = "type";
    private static final String KEY_DAY1S = "day1s";
    private static final String KEY_DAY1E = "day1e";
    private static final String KEY_DAY2S = "day2s";
    private static final String KEY_DAY2E = "day2e";
    private static final String KEY_DAY3S = "day3s";
    private static final String KEY_DAY3E = "day3e";
    private static final String KEY_DAY4S = "day4s";
    private static final String KEY_DAY4E = "day4e";
    private static final String KEY_DAY5S = "day5s";
    private static final String KEY_DAY5E = "day5e";
    private static final String KEY_DAY6S = "day6s";
    private static final String KEY_DAY6E = "day6e";
    private static final String KEY_DAY7S = "day7s";
    private static final String KEY_DAY7E = "day7e";
    private static final String KEY_RESTRICTION = "restriction";

    DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_MASTERKEY_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME +
                " TEXT," + KEY_COORD + " TEXT," + KEY_DESCRIPTION + " TEXT," + KEY_PRICE + " DOUBLE," + KEY_TYPE +
                " TEXT," + KEY_DAY1S + " TEXT," + KEY_DAY1E + " TEXT," + KEY_DAY2S + " TEXT," + KEY_DAY2E + " TEXT," +
                KEY_DAY3S + " TEXT," + KEY_DAY3E + " TEXT," + KEY_DAY4S + " TEXT," + KEY_DAY4E + " TEXT," + KEY_DAY5S + " TEXT," +
                KEY_DAY5E + " TEXT," + KEY_DAY6S + " TEXT," + KEY_DAY6E + " TEXT," + KEY_DAY7S + " TEXT," + KEY_DAY7E + " TEXT," +
                KEY_RESTRICTION + " TEXT" + ")";
        db.execSQL(CREATE_MASTERKEY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w("Example", "Upgrading database; this will drop and recreate the tables.");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Adding new location
    void addLocation(Location location) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, location.getId()); // Location Id
        values.put(KEY_NAME, location.getName()); // Location Name
        values.put(KEY_COORD, location.getCoord()); // Location Coord
        values.put(KEY_DESCRIPTION, location.getDescription()); // Location Description
        values.put(KEY_PRICE, location.getPrice()); // Location Price
        values.put(KEY_TYPE, location.getType()); // Location Type
        values.put(KEY_DAY1S, location.getDay1s()); // Location Day1s
        values.put(KEY_DAY1E, location.getDay1e()); // Location Day1e
        values.put(KEY_DAY2S, location.getDay2s()); // Location Day2s
        values.put(KEY_DAY2E, location.getDay2e()); // Location Day2e
        values.put(KEY_DAY3S, location.getDay3s()); // Location Day3s
        values.put(KEY_DAY3E, location.getDay3e()); // Location Day3e
        values.put(KEY_DAY4S, location.getDay4s()); // Location Day4s
        values.put(KEY_DAY4E, location.getDay4e()); // Location Day4e
        values.put(KEY_DAY5S, location.getDay5s()); // Location Day5s
        values.put(KEY_DAY5E, location.getDay5e()); // Location Day5e
        values.put(KEY_DAY6S, location.getDay6s()); // Location Day6s
        values.put(KEY_DAY6E, location.getDay6e()); // Location Day6e
        values.put(KEY_DAY7S, location.getDay7s()); // Location Day7s
        values.put(KEY_DAY7E, location.getDay7e()); // Location Day7e
        values.put(KEY_RESTRICTION, location.getRestriction()); // Location Restriction

        // Inserting Row
        db.insert(TABLE_NAME, null, values);
        db.close(); // Closing database connection
    }

    /*// Getting single location using unique id
    public Location getLocation(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, new String[]{ KEY_ID, KEY_NAME, KEY_COORD, KEY_DESCRIPTION, KEY_PRICE, KEY_TYPE,
                        KEY_DAY1S, KEY_DAY1E, KEY_DAY2S, KEY_DAY2E, KEY_DAY3S, KEY_DAY3E,
                        KEY_DAY4S, KEY_DAY4E, KEY_DAY5S, KEY_DAY5E, KEY_DAY6S, KEY_DAY6E,
                        KEY_DAY7S, KEY_DAY7E, KEY_RESTRICTION}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Location location = new Location(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3), Double.parseDouble(cursor.getString(4)),
                cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8), cursor.getString(9),
                cursor.getString(10), cursor.getString(11), cursor.getString(12), cursor.getString(13), cursor.getString(14),
                cursor.getString(15), cursor.getString(16), cursor.getString(17), cursor.getString(18), cursor.getString(19),
                cursor.getString(20));
        // return contact
        return location;
    }*/

    // Getting single location using name
    public Location getLocation(String name) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, new String[]{ KEY_ID, KEY_NAME, KEY_COORD, KEY_DESCRIPTION, KEY_PRICE, KEY_TYPE,
                        KEY_DAY1S, KEY_DAY1E, KEY_DAY2S, KEY_DAY2E, KEY_DAY3S, KEY_DAY3E,
                        KEY_DAY4S, KEY_DAY4E, KEY_DAY5S, KEY_DAY5E, KEY_DAY6S, KEY_DAY6E,
                        KEY_DAY7S, KEY_DAY7E, KEY_RESTRICTION}, KEY_NAME + "=?",
                new String[]{String.valueOf(name)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Location location = new Location(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3), Double.parseDouble(cursor.getString(4)),
                cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8), cursor.getString(9),
                cursor.getString(10), cursor.getString(11), cursor.getString(12), cursor.getString(13), cursor.getString(14),
                cursor.getString(15), cursor.getString(16), cursor.getString(17), cursor.getString(18), cursor.getString(19),
                cursor.getString(20));
        // return contact
        return location;
    }

    // Getting All Locations
    public List<Location> getAllLocations() {
        List<Location> locationList = new ArrayList<Location>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Location location = new Location();
                location.setId(Integer.parseInt(cursor.getString(0)));
                location.setName(cursor.getString(1));
                location.setCoord(cursor.getString(2));
                location.setDescription(cursor.getString(3));
                location.setPrice(Double.parseDouble(cursor.getString(4)));
                location.setType(cursor.getString(5));
                location.setDay1s(cursor.getString(6));
                location.setDay1e(cursor.getString(7));
                location.setDay2s(cursor.getString(8));
                location.setDay2e(cursor.getString(9));
                location.setDay3s(cursor.getString(10));
                location.setDay3e(cursor.getString(11));
                location.setDay4s(cursor.getString(12));
                location.setDay4e(cursor.getString(13));
                location.setDay5s(cursor.getString(14));
                location.setDay5e(cursor.getString(15));
                location.setDay6s(cursor.getString(16));
                location.setDay6e(cursor.getString(17));
                location.setDay7s(cursor.getString(18));
                location.setDay7e(cursor.getString(19));
                location.setRestriction(cursor.getString(20));
                // Adding contact to list
                locationList.add(location);
            } while (cursor.moveToNext());
        }

        // return location list
        return locationList;
    }

    // Updating single location
    public int updateLocation(Location location) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, location.getName());
        values.put(KEY_COORD, location.getCoord());
        values.put(KEY_DESCRIPTION, location.getDescription());
        values.put(KEY_PRICE, location.getPrice());
        values.put(KEY_TYPE, location.getType());
        values.put(KEY_DAY1S, location.getDay1s());
        values.put(KEY_DAY1E, location.getDay1e());
        values.put(KEY_DAY2S, location.getDay2s());
        values.put(KEY_DAY2E, location.getDay2e());
        values.put(KEY_DAY3S, location.getDay3s());
        values.put(KEY_DAY3E, location.getDay3e());
        values.put(KEY_DAY4S, location.getDay4s());
        values.put(KEY_DAY4E, location.getDay4e());
        values.put(KEY_DAY5S, location.getDay5s());
        values.put(KEY_DAY5E, location.getDay5e());
        values.put(KEY_DAY6S, location.getDay6s());
        values.put(KEY_DAY6E, location.getDay6e());
        values.put(KEY_DAY7S, location.getDay7s());
        values.put(KEY_DAY7E, location.getDay7e());


        // updating row
        return db.update(TABLE_NAME, values, KEY_ID + " = ?",
                new String[]{String.valueOf(location.getId())});
    }

    // Deleting single location
    public void deleteLocation(Location location) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_ID + " = ?",
                new String[]{String.valueOf(location.getId())});
        db.close();
    }


    // Getting locations Count
    public int getLocationsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();

        // return count
        return count;
    }



}
