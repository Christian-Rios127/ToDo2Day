package edu.miracosta.cs134.todo2day.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import static android.provider.Contacts.SettingsColumns.KEY;
import static android.provider.Telephony.Mms.Part.TEXT;
import static java.sql.Types.INTEGER;
import static java.text.Collator.PRIMARY;

public class DBHelper extends SQLiteOpenHelper {
    //task 1 Make a constants for all database values
    //database name, version,table,field names primary key
    public static final String DATABASE_NAME = "ToDo2Day";
    public static final String TABLE_NAME = "Tasks";
    public static final int VERSION = 1;

    public static final String KEY_FIELD_ID = "_id";
    public static final String FIELD_DESCRIPTION = "description";
    public static final String FIELD_IS_DONE = "is_done";


    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME,null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createSQL = "CREATE TABLE IF NOT EXISTS "
                +TABLE_NAME + "("
                +KEY_FIELD_ID + " INTEGER PRIMARY KEY, "
                + FIELD_DESCRIPTION + " TEXT, "
                +FIELD_IS_DONE + " INTEGER" + ")";
        //get the Database name & create the SQL
        Log.i(DATABASE_NAME,createSQL);
        db.execSQL(createSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //1) Drop the old table and create the new
        if(newVersion > oldVersion){
            String dropSQL = "DROP TABLE IF EXISTS "
                    + TABLE_NAME;
            Log.i(DATABASE_NAME,dropSQL);
            db.execSQL(dropSQL);
            onCreate(db);
        }

    }
    /**
     * Adds new Task to datsbase.
     * @param task the new task to be added
     */
    public void addTask(Task task){
        //Decide whether we're reading or writing to/from the
        // database
        //for adding tasks, we're  writing to the database
        SQLiteDatabase db = getWritableDatabase();
        //when we add to the database, use a data structure
        //called ConventValues (key, value) pair's
        ContentValues values = new ContentValues();

        //set up key,values pairs
        values.put(FIELD_DESCRIPTION,task.getDescription());
        values.put(FIELD_IS_DONE, task.isDone() ? 1 : 0);

        db.insert(TABLE_NAME, null,values);
        //after we're close the connection to the database
        db.close();
    }
}