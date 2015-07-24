package com.rmpd.lecturaaguaapp.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.rmpd.lecturaaguaapp.Util.ConstantsApp;
import com.rmpd.lecturaaguaapp.Data.DataBaseContract.LecturaRegistroEntry;
import com.rmpd.lecturaaguaapp.Data.DataBaseContract.PeriodoEntry;
import com.rmpd.lecturaaguaapp.Data.DataBaseContract.ConfigurationEntry;

/**
 * Created by RP on 6/24/2015.
 */
public class DataBaseHelper extends SQLiteOpenHelper {
    private static final String LOG_TAG = ConfigurationDAO.class.getSimpleName();


    private static DataBaseHelper instance;

    public static synchronized DataBaseHelper getHelper(Context context) {
        if (instance == null)
            instance = new DataBaseHelper(context);
        return instance;
    }

    public DataBaseHelper(Context context) {
        super(context, ConstantsApp.DATABASE_NAME, null, ConstantsApp.DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        final String SQL_CREATE_LECTURA_REGISTRO_TABLE = "CREATE TABLE " + DataBaseContract.LecturaRegistroEntry.TABLE_NAME + " (" +
                LecturaRegistroEntry._ID + " INTEGER PRIMARY KEY," +
                LecturaRegistroEntry.COLUMN_CODIGO_CLIENTE + " TEXT NOT NULL," +
                LecturaRegistroEntry.COLUMN_ACCION_ID + " TEXT NOT NULL," +
                LecturaRegistroEntry.COLUMN_PERIODO_ID + " TEXT NOT NULL," +
                LecturaRegistroEntry.COLUMN_PERIODO_NAME + " TEXT NOT NULL," +
                LecturaRegistroEntry.COLUMN_NUMERO_MEDIDOR + " INTEGER NOT NULL," +
                LecturaRegistroEntry.COLUMN_NOMBRE + " TEXT NOT NULL," +
                LecturaRegistroEntry.COLUMN_DIRECCION + " TEXT NOT NULL," +
                LecturaRegistroEntry.COLUMN_LECTURA_ANTERIOR + " INTEGER NOT NULL," +
                LecturaRegistroEntry.COLUMN_LECTURA_ACTUAL + " INTEGER," +
                LecturaRegistroEntry.COLUMN_FECHA_LECTURA + " TEXT," +
                LecturaRegistroEntry.COLUMN_CONSUMO + " INTEGER," +
                LecturaRegistroEntry.COLUMN_TOTAL_MES + " DECIMAL(10, 2)," +
                LecturaRegistroEntry.COLUMN_DEUDA_ANTERIOR + " DECIMAL(10, 2)," +
                LecturaRegistroEntry.COLUMN_NUMERO_MESES_DEUDA + " INTEGER," +
                LecturaRegistroEntry.COLUMN_TOTAL + " DECIMAL(10, 2)," +
                LecturaRegistroEntry.COLUMN_PRECIO_CUBO + " DECIMAL(10, 2)," +
                LecturaRegistroEntry.COLUMN_ES_TARIFA_DIFERENCIADO + " INTEGER," +
                LecturaRegistroEntry.COLUMN_CONSUMO_DIFERENCIADO + " INTEGER," +
                LecturaRegistroEntry.COLUMN_PRECIO_DIFERENCIADO + " DECIMAL(10, 2)," +
                LecturaRegistroEntry.COLUMN_CONSUMO_AGUA_ID_LAST + " INTEGER" +
                " );";
        db.execSQL(SQL_CREATE_LECTURA_REGISTRO_TABLE);


        final String SQL_CREATE_PERIODO_TABLE = "CREATE TABLE " + PeriodoEntry.TABLE_NAME + " (" +
                PeriodoEntry._ID + " INTEGER PRIMARY KEY," +
                PeriodoEntry.COLUMN_ID_SERVER + " TEXT NOT NULL," +
                PeriodoEntry.COLUMN_NAME + " TEXT NOT NULL," +
                PeriodoEntry.COLUMN_IS_SAVE_SERVER + " INTEGER NOT NULL" +
                " );";
        db.execSQL(SQL_CREATE_PERIODO_TABLE);

        final String SQL_CREATE_CONFIGURATION_TABLE = "CREATE TABLE " + ConfigurationEntry.TABLE_NAME + " (" +
                ConfigurationEntry._ID + " INTEGER PRIMARY KEY," +
                ConfigurationEntry.COLUMN_HOST + " TEXT NOT NULL," +
                ConfigurationEntry.COLUMN_USERNAME + " TEXT NOT NULL," +
                ConfigurationEntry.COLUMN_PASSWORD + " TEXT NOT NULL" +
                " );";
        db.execSQL(SQL_CREATE_CONFIGURATION_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DataBaseContract.LecturaRegistroEntry.TABLE_NAME);
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS " + PeriodoEntry.TABLE_NAME);
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS " + ConfigurationEntry.TABLE_NAME);
        onCreate(db);
    }




}
