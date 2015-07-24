package com.rmpd.lecturaaguaapp.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.rmpd.lecturaaguaapp.Model.LecturaRegistro;
import com.rmpd.lecturaaguaapp.Model.Periodo;
import com.rmpd.lecturaaguaapp.Util.ConstantsApp;
import com.rmpd.lecturaaguaapp.Data.DataBaseContract.PeriodoEntry;
import com.rmpd.lecturaaguaapp.Util.ConvertUtil;

import java.util.ArrayList;

/**
 * Created by RP on 6/25/2015.
 */
public class PeriodoDAO {

    private static final String LOG_TAG = PeriodoDAO.class.getSimpleName();

    protected SQLiteDatabase database;
    private DataBaseHelper dbHelper;
    private Context mContext;


    public PeriodoDAO(Context context) {
        this.mContext = context;
        dbHelper = DataBaseHelper.getHelper(mContext);

    }



    public void addPeriodo(Periodo periodo) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues periodoValues = new ContentValues();
        periodoValues.put(PeriodoEntry.COLUMN_ID_SERVER, periodo.getIdServer());
        periodoValues.put(PeriodoEntry.COLUMN_NAME, periodo.getName());
        periodoValues.put(PeriodoEntry.COLUMN_IS_SAVE_SERVER, periodo.getIsSaveServer());


        // Inserting Row
        db.insert(PeriodoEntry.TABLE_NAME, null, periodoValues);
        db.close(); // Closing database connection
    }


    public ArrayList<Periodo> getPeriodosAll() {
        ArrayList<Periodo> periodos = new ArrayList<Periodo>();

        try {

            SQLiteDatabase db = dbHelper.getReadableDatabase();
            Cursor cursor = db.query(PeriodoEntry.TABLE_NAME, null, null,
                    null, null, null, null, null);


            Log.d(LOG_TAG, "cursor.getCount() " + cursor.getCount());

            if(cursor.getCount() == 0) {
                Log.d(LOG_TAG, "Return null");
                return periodos;
            }

            if(cursor != null && cursor.moveToFirst()) {


                while (cursor.isAfterLast() == false) {
                    //cursor.moveToFirst();

                    Periodo periodo = new Periodo();
                    periodo.setId(ConvertUtil.stringToInteger(cursor.getString(cursor.getColumnIndex(PeriodoEntry._ID))));
                    periodo.setIdServer(cursor.getString(cursor.getColumnIndex(PeriodoEntry.COLUMN_ID_SERVER)));
                    periodo.setName(cursor.getString(cursor.getColumnIndex(PeriodoEntry.COLUMN_NAME)));
                    periodo.setIsSaveServer(ConvertUtil.stringToInteger(cursor.getString(cursor.getColumnIndex(PeriodoEntry.COLUMN_IS_SAVE_SERVER))));

                    periodos.add(periodo);

                    cursor.moveToNext();
                }
            }
        }
        catch (Exception ex)
        {
            Log.e(LOG_TAG,ex.getMessage());
            ex.printStackTrace();
        }
        return periodos;
    }



    public void deletePeriodo(Periodo periodo) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(PeriodoEntry.TABLE_NAME, PeriodoEntry._ID + " = ?",
                new String[] { String.valueOf(periodo.getId()) });
        db.close();
    }


    public void deletePeriodosALL() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(PeriodoEntry.TABLE_NAME, null,
                null);
        db.close();
    }




}
