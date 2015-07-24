package com.rmpd.lecturaaguaapp.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.rmpd.lecturaaguaapp.Model.Configuration;
import com.rmpd.lecturaaguaapp.Model.Periodo;
import com.rmpd.lecturaaguaapp.Util.ConstantsApp;
import com.rmpd.lecturaaguaapp.Data.DataBaseContract.ConfigurationEntry;
import com.rmpd.lecturaaguaapp.Util.ConvertUtil;

/**
 * Created by RP on 6/19/2015.
 */
public class ConfigurationDAO  {

    private static final String LOG_TAG = ConfigurationDAO.class.getSimpleName();

    protected SQLiteDatabase database;
    private DataBaseHelper dbHelper;
    private Context mContext;


    public ConfigurationDAO(Context context) {
        this.mContext = context;
        dbHelper = DataBaseHelper.getHelper(mContext);

    }


    public void addConfiguration(Configuration configuration) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues periodoValues = new ContentValues();
        periodoValues.put(ConfigurationEntry.COLUMN_USERNAME, configuration.getUsername());
        periodoValues.put(ConfigurationEntry.COLUMN_PASSWORD, configuration.getPassword());
        periodoValues.put(ConfigurationEntry.COLUMN_HOST, configuration.getHost());


        // Inserting Row
        db.insert(ConfigurationEntry.TABLE_NAME, null, periodoValues);
        db.close(); // Closing database connection
    }



    public Configuration getUserByUsername(String username) {
        Configuration user = null;
        try {

            SQLiteDatabase db = dbHelper.getReadableDatabase();
            Cursor cursor = db.query(ConfigurationEntry.TABLE_NAME, null, ConfigurationEntry.COLUMN_USERNAME + " = ?",
                    new String[]{String.valueOf(username)}, null, null, null, null);


            Log.d(LOG_TAG, "cursor.getCount() " + cursor.getCount());

            if(cursor.getCount() == 0) {
                Log.d(LOG_TAG, "Return null");
                return user;
            }

            if (cursor != null && cursor.moveToFirst()) {
                //cursor.moveToFirst();

                user = new Configuration();
                user.setId(ConvertUtil.stringToInteger(cursor.getString(cursor.getColumnIndex(ConfigurationEntry._ID))));
                user.setHost(cursor.getString(cursor.getColumnIndex(ConfigurationEntry.COLUMN_HOST)));
                user.setUsername(cursor.getString(cursor.getColumnIndex(ConfigurationEntry.COLUMN_USERNAME)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(ConfigurationEntry.COLUMN_PASSWORD)));


            }
        }
        catch (Exception ex)
        {
            Log.e(LOG_TAG,ex.getMessage());
            ex.printStackTrace();
        }
        return user;
    }

    public int updateConfiguration(Configuration user) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();


        ContentValues userValues = new ContentValues();
        userValues.put(ConfigurationEntry.COLUMN_HOST, user.getHost());
        userValues.put(ConfigurationEntry.COLUMN_USERNAME, user.getUsername());
        userValues.put(ConfigurationEntry.COLUMN_PASSWORD, user.getPassword());


        // updating row
        return db.update(ConfigurationEntry.TABLE_NAME, userValues, ConfigurationEntry._ID + " = ?",
                new String[] { String.valueOf(user.getId().toString()) });
    }



}
