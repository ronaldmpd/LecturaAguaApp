package com.rmpd.lecturaaguaapp.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.rmpd.lecturaaguaapp.Model.LecturaRegistro;
import com.rmpd.lecturaaguaapp.Util.ConvertUtil;

import java.util.ArrayList;

/**
 * Created by RP on 6/26/2015.
 */
public class LecturaRegistroDAO {


    private static final String LOG_TAG = LecturaRegistroDAO.class.getSimpleName();

    protected SQLiteDatabase database;
    private DataBaseHelper dbHelper;
    private Context mContext;


    public LecturaRegistroDAO(Context context) {
        this.mContext = context;
        dbHelper = DataBaseHelper.getHelper(mContext);
    }


    public void addLecturaRegistro(LecturaRegistro lecturaRegistro) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues lecturaRegistroValues = new ContentValues();
        lecturaRegistroValues.put(DataBaseContract.LecturaRegistroEntry.COLUMN_CODIGO_CLIENTE, lecturaRegistro.getCodigoCliente());
        lecturaRegistroValues.put(DataBaseContract.LecturaRegistroEntry.COLUMN_ACCION_ID, lecturaRegistro.getAccionId());
        lecturaRegistroValues.put(DataBaseContract.LecturaRegistroEntry.COLUMN_PERIODO_ID, lecturaRegistro.getPeriodoId());
        lecturaRegistroValues.put(DataBaseContract.LecturaRegistroEntry.COLUMN_PERIODO_NAME, lecturaRegistro.getPeriodoName());
        lecturaRegistroValues.put(DataBaseContract.LecturaRegistroEntry.COLUMN_NUMERO_MEDIDOR, lecturaRegistro.getNumeroMedidor());
        lecturaRegistroValues.put(DataBaseContract.LecturaRegistroEntry.COLUMN_NOMBRE, lecturaRegistro.getNombre());
        lecturaRegistroValues.put(DataBaseContract.LecturaRegistroEntry.COLUMN_DIRECCION, lecturaRegistro.getDireccion());
        lecturaRegistroValues.put(DataBaseContract.LecturaRegistroEntry.COLUMN_LECTURA_ANTERIOR, lecturaRegistro.getLecturaAnterior());
        lecturaRegistroValues.put(DataBaseContract.LecturaRegistroEntry.COLUMN_LECTURA_ACTUAL, lecturaRegistro.getLecturaActual());
        lecturaRegistroValues.put(DataBaseContract.LecturaRegistroEntry.COLUMN_FECHA_LECTURA, lecturaRegistro.getFechaLectura());
        lecturaRegistroValues.put(DataBaseContract.LecturaRegistroEntry.COLUMN_CONSUMO, lecturaRegistro.getConsumo());
        lecturaRegistroValues.put(DataBaseContract.LecturaRegistroEntry.COLUMN_TOTAL_MES, lecturaRegistro.getTotalMes());
        lecturaRegistroValues.put(DataBaseContract.LecturaRegistroEntry.COLUMN_DEUDA_ANTERIOR, lecturaRegistro.getDeudaAnterior());
        lecturaRegistroValues.put(DataBaseContract.LecturaRegistroEntry.COLUMN_NUMERO_MESES_DEUDA, lecturaRegistro.getNumeroMesesDeuda());
        lecturaRegistroValues.put(DataBaseContract.LecturaRegistroEntry.COLUMN_TOTAL, lecturaRegistro.getTotal());
        lecturaRegistroValues.put(DataBaseContract.LecturaRegistroEntry.COLUMN_PRECIO_CUBO, lecturaRegistro.getPrecioCubo());
        lecturaRegistroValues.put(DataBaseContract.LecturaRegistroEntry.COLUMN_ES_TARIFA_DIFERENCIADO, lecturaRegistro.getEsTarifaDiferenciado());
        lecturaRegistroValues.put(DataBaseContract.LecturaRegistroEntry.COLUMN_CONSUMO_DIFERENCIADO, lecturaRegistro.getConsumoDiferenciado());
        lecturaRegistroValues.put(DataBaseContract.LecturaRegistroEntry.COLUMN_PRECIO_DIFERENCIADO, lecturaRegistro.getPrecioDiferenciado());
        lecturaRegistroValues.put(DataBaseContract.LecturaRegistroEntry.COLUMN_CONSUMO_AGUA_ID_LAST, lecturaRegistro.getConsumoAguaIdLast());

        // Inserting Row
        db.insert(DataBaseContract.LecturaRegistroEntry.TABLE_NAME, null, lecturaRegistroValues);
        db.close(); // Closing database connection
    }

    public ArrayList<LecturaRegistro> getLecturaRegistroAll() {
        ArrayList<LecturaRegistro> lecturaRegistros = new ArrayList<LecturaRegistro>();

        try {

            SQLiteDatabase db = dbHelper.getReadableDatabase();
            Cursor cursor = db.query(DataBaseContract.LecturaRegistroEntry.TABLE_NAME, null, null,
                    null, null, null, null, null);


            Log.d(LOG_TAG, "cursor.getCount() " + cursor.getCount());

            if(cursor.getCount() == 0) {
                Log.d(LOG_TAG, "Return null");
                return lecturaRegistros;
            }

            if(cursor != null && cursor.moveToFirst()) {


                while (cursor.isAfterLast() == false) {
                    //cursor.moveToFirst();

                    LecturaRegistro lecturaRegistro = new LecturaRegistro();
                    lecturaRegistro.setId(ConvertUtil.stringToInteger(cursor.getString(cursor.getColumnIndex(DataBaseContract.LecturaRegistroEntry._ID))));
                    lecturaRegistro.setCodigoCliente(cursor.getString(cursor.getColumnIndex(DataBaseContract.LecturaRegistroEntry.COLUMN_CODIGO_CLIENTE)));
                    lecturaRegistro.setAccionId(cursor.getString(cursor.getColumnIndex(DataBaseContract.LecturaRegistroEntry.COLUMN_ACCION_ID)));
                    lecturaRegistro.setPeriodoId(cursor.getString(cursor.getColumnIndex(DataBaseContract.LecturaRegistroEntry.COLUMN_PERIODO_ID)));
                    lecturaRegistro.setPeriodoName(cursor.getString(cursor.getColumnIndex(DataBaseContract.LecturaRegistroEntry.COLUMN_PERIODO_NAME)));
                    lecturaRegistro.setNumeroMedidor(ConvertUtil.stringToInteger(cursor.getString(cursor.getColumnIndex(DataBaseContract.LecturaRegistroEntry.COLUMN_NUMERO_MEDIDOR))));
                    lecturaRegistro.setNombre(cursor.getString(cursor.getColumnIndex(DataBaseContract.LecturaRegistroEntry.COLUMN_NOMBRE)));
                    lecturaRegistro.setDireccion(cursor.getString(cursor.getColumnIndex(DataBaseContract.LecturaRegistroEntry.COLUMN_DIRECCION)));
                    lecturaRegistro.setLecturaAnterior(ConvertUtil.stringToInteger(cursor.getString(cursor.getColumnIndex(DataBaseContract.LecturaRegistroEntry.COLUMN_LECTURA_ANTERIOR))));
                    lecturaRegistro.setLecturaActual(ConvertUtil.stringToInteger(cursor.getString(cursor.getColumnIndex(DataBaseContract.LecturaRegistroEntry.COLUMN_LECTURA_ACTUAL))));
                    lecturaRegistro.setFechaLectura(cursor.getString(cursor.getColumnIndex(DataBaseContract.LecturaRegistroEntry.COLUMN_FECHA_LECTURA)));
                    lecturaRegistro.setConsumo(ConvertUtil.stringToInteger(cursor.getString(cursor.getColumnIndex(DataBaseContract.LecturaRegistroEntry.COLUMN_CONSUMO))));
                    lecturaRegistro.setTotalMes(ConvertUtil.stringToDouble(cursor.getString(cursor.getColumnIndex(DataBaseContract.LecturaRegistroEntry.COLUMN_TOTAL_MES))));
                    lecturaRegistro.setDeudaAnterior(ConvertUtil.stringToDouble(cursor.getString(cursor.getColumnIndex(DataBaseContract.LecturaRegistroEntry.COLUMN_DEUDA_ANTERIOR))));
                    lecturaRegistro.setNumeroMesesDeuda(ConvertUtil.stringToInteger(cursor.getString(cursor.getColumnIndex(DataBaseContract.LecturaRegistroEntry.COLUMN_NUMERO_MESES_DEUDA))));
                    lecturaRegistro.setTotal(ConvertUtil.stringToDouble(cursor.getString(cursor.getColumnIndex(DataBaseContract.LecturaRegistroEntry.COLUMN_TOTAL))));
                    lecturaRegistro.setPrecioCubo(ConvertUtil.stringToDouble(cursor.getString(cursor.getColumnIndex(DataBaseContract.LecturaRegistroEntry.COLUMN_PRECIO_CUBO))));
                    lecturaRegistro.setEsTarifaDiferenciado(ConvertUtil.stringToBoolean(cursor.getString(cursor.getColumnIndex(DataBaseContract.LecturaRegistroEntry.COLUMN_ES_TARIFA_DIFERENCIADO))));
                    lecturaRegistro.setConsumoDiferenciado(ConvertUtil.stringToInteger(cursor.getString(cursor.getColumnIndex(DataBaseContract.LecturaRegistroEntry.COLUMN_CONSUMO_DIFERENCIADO))));
                    lecturaRegistro.setPrecioDiferenciado(ConvertUtil.stringToDouble(cursor.getString(cursor.getColumnIndex(DataBaseContract.LecturaRegistroEntry.COLUMN_PRECIO_DIFERENCIADO))));
                    lecturaRegistro.setConsumoAguaIdLast(ConvertUtil.stringToInteger(cursor.getString(cursor.getColumnIndex(DataBaseContract.LecturaRegistroEntry.COLUMN_CONSUMO_AGUA_ID_LAST))));

                    lecturaRegistros.add(lecturaRegistro);

                    cursor.moveToNext();
                }
            }
        }
        catch (Exception ex)
        {
            Log.e(LOG_TAG,ex.getMessage());
            ex.printStackTrace();
        }
        return lecturaRegistros;
    }


    public LecturaRegistro getLecturaRegistroByCodigoCliente(String codigoCliente) {
        LecturaRegistro lecturaRegistro = null;
        try {

            SQLiteDatabase db = dbHelper.getReadableDatabase();
            Cursor cursor = db.query(DataBaseContract.LecturaRegistroEntry.TABLE_NAME, null, DataBaseContract.LecturaRegistroEntry.COLUMN_CODIGO_CLIENTE + " = ?",
                    new String[]{String.valueOf(codigoCliente)}, null, null, null, null);

            if(cursor != null && cursor.getCount() == 0) {
                Log.d(LOG_TAG, "Return null");
                return lecturaRegistro;
            }

            Log.d(LOG_TAG, "cursor.getCount() " + cursor.getCount());

            if (cursor != null && cursor.moveToFirst()) {
                //cursor.moveToFirst();

                lecturaRegistro = new LecturaRegistro();
                lecturaRegistro.setId(ConvertUtil.stringToInteger(cursor.getString(cursor.getColumnIndex(DataBaseContract.LecturaRegistroEntry._ID))));
                lecturaRegistro.setCodigoCliente(cursor.getString(cursor.getColumnIndex(DataBaseContract.LecturaRegistroEntry.COLUMN_CODIGO_CLIENTE)));
                lecturaRegistro.setAccionId(cursor.getString(cursor.getColumnIndex(DataBaseContract.LecturaRegistroEntry.COLUMN_ACCION_ID)));
                lecturaRegistro.setPeriodoId(cursor.getString(cursor.getColumnIndex(DataBaseContract.LecturaRegistroEntry.COLUMN_PERIODO_ID)));
                lecturaRegistro.setPeriodoName(cursor.getString(cursor.getColumnIndex(DataBaseContract.LecturaRegistroEntry.COLUMN_PERIODO_NAME)));
                lecturaRegistro.setNumeroMedidor(ConvertUtil.stringToInteger(cursor.getString(cursor.getColumnIndex(DataBaseContract.LecturaRegistroEntry.COLUMN_NUMERO_MEDIDOR))));
                lecturaRegistro.setNombre(cursor.getString(cursor.getColumnIndex(DataBaseContract.LecturaRegistroEntry.COLUMN_NOMBRE)));
                lecturaRegistro.setDireccion(cursor.getString(cursor.getColumnIndex(DataBaseContract.LecturaRegistroEntry.COLUMN_DIRECCION)));
                lecturaRegistro.setLecturaAnterior(ConvertUtil.stringToInteger(cursor.getString(cursor.getColumnIndex(DataBaseContract.LecturaRegistroEntry.COLUMN_LECTURA_ANTERIOR))));
                lecturaRegistro.setLecturaActual(ConvertUtil.stringToInteger(cursor.getString(cursor.getColumnIndex(DataBaseContract.LecturaRegistroEntry.COLUMN_LECTURA_ACTUAL))));
                lecturaRegistro.setFechaLectura(cursor.getString(cursor.getColumnIndex(DataBaseContract.LecturaRegistroEntry.COLUMN_FECHA_LECTURA)));
                lecturaRegistro.setConsumo(ConvertUtil.stringToInteger(cursor.getString(cursor.getColumnIndex(DataBaseContract.LecturaRegistroEntry.COLUMN_CONSUMO))));
                lecturaRegistro.setTotalMes(ConvertUtil.stringToDouble(cursor.getString(cursor.getColumnIndex(DataBaseContract.LecturaRegistroEntry.COLUMN_TOTAL_MES))));
                lecturaRegistro.setDeudaAnterior(ConvertUtil.stringToDouble(cursor.getString(cursor.getColumnIndex(DataBaseContract.LecturaRegistroEntry.COLUMN_DEUDA_ANTERIOR))));
                lecturaRegistro.setNumeroMesesDeuda(ConvertUtil.stringToInteger(cursor.getString(cursor.getColumnIndex(DataBaseContract.LecturaRegistroEntry.COLUMN_NUMERO_MESES_DEUDA))));
                lecturaRegistro.setTotal(ConvertUtil.stringToDouble(cursor.getString(cursor.getColumnIndex(DataBaseContract.LecturaRegistroEntry.COLUMN_TOTAL))));
                lecturaRegistro.setPrecioCubo(ConvertUtil.stringToDouble(cursor.getString(cursor.getColumnIndex(DataBaseContract.LecturaRegistroEntry.COLUMN_PRECIO_CUBO))));
                lecturaRegistro.setEsTarifaDiferenciado(ConvertUtil.stringToBoolean(cursor.getString(cursor.getColumnIndex(DataBaseContract.LecturaRegistroEntry.COLUMN_ES_TARIFA_DIFERENCIADO))));
                lecturaRegistro.setConsumoDiferenciado(ConvertUtil.stringToInteger(cursor.getString(cursor.getColumnIndex(DataBaseContract.LecturaRegistroEntry.COLUMN_CONSUMO_DIFERENCIADO))));
                lecturaRegistro.setPrecioDiferenciado(ConvertUtil.stringToDouble(cursor.getString(cursor.getColumnIndex(DataBaseContract.LecturaRegistroEntry.COLUMN_PRECIO_DIFERENCIADO))));
                lecturaRegistro.setConsumoAguaIdLast(ConvertUtil.stringToInteger(cursor.getString(cursor.getColumnIndex(DataBaseContract.LecturaRegistroEntry.COLUMN_CONSUMO_AGUA_ID_LAST))));

            }
        }
        catch (Exception ex)
        {
            Log.e(LOG_TAG,ex.getMessage());
            ex.printStackTrace();
        }
        return lecturaRegistro;
    }


    public int updateLecturaRegistro(LecturaRegistro lecturaRegistro) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();


        ContentValues lecturaRegistroValues = new ContentValues();
        lecturaRegistroValues.put(DataBaseContract.LecturaRegistroEntry.COLUMN_CODIGO_CLIENTE, lecturaRegistro.getCodigoCliente());
        lecturaRegistroValues.put(DataBaseContract.LecturaRegistroEntry.COLUMN_ACCION_ID, lecturaRegistro.getAccionId());
        lecturaRegistroValues.put(DataBaseContract.LecturaRegistroEntry.COLUMN_PERIODO_ID, lecturaRegistro.getPeriodoId());
        lecturaRegistroValues.put(DataBaseContract.LecturaRegistroEntry.COLUMN_PERIODO_NAME, lecturaRegistro.getPeriodoName());
        lecturaRegistroValues.put(DataBaseContract.LecturaRegistroEntry.COLUMN_NUMERO_MEDIDOR, lecturaRegistro.getNumeroMedidor());
        lecturaRegistroValues.put(DataBaseContract.LecturaRegistroEntry.COLUMN_NOMBRE, lecturaRegistro.getNombre());
        lecturaRegistroValues.put(DataBaseContract.LecturaRegistroEntry.COLUMN_DIRECCION, lecturaRegistro.getDireccion());
        lecturaRegistroValues.put(DataBaseContract.LecturaRegistroEntry.COLUMN_LECTURA_ANTERIOR, lecturaRegistro.getLecturaAnterior());
        lecturaRegistroValues.put(DataBaseContract.LecturaRegistroEntry.COLUMN_LECTURA_ACTUAL, lecturaRegistro.getLecturaActual());
        lecturaRegistroValues.put(DataBaseContract.LecturaRegistroEntry.COLUMN_FECHA_LECTURA, lecturaRegistro.getFechaLectura());
        lecturaRegistroValues.put(DataBaseContract.LecturaRegistroEntry.COLUMN_CONSUMO, lecturaRegistro.getConsumo());
        lecturaRegistroValues.put(DataBaseContract.LecturaRegistroEntry.COLUMN_TOTAL_MES, lecturaRegistro.getTotalMes());
        lecturaRegistroValues.put(DataBaseContract.LecturaRegistroEntry.COLUMN_DEUDA_ANTERIOR, lecturaRegistro.getDeudaAnterior());
        lecturaRegistroValues.put(DataBaseContract.LecturaRegistroEntry.COLUMN_NUMERO_MESES_DEUDA, lecturaRegistro.getNumeroMesesDeuda());
        lecturaRegistroValues.put(DataBaseContract.LecturaRegistroEntry.COLUMN_TOTAL, lecturaRegistro.getTotal());
        lecturaRegistroValues.put(DataBaseContract.LecturaRegistroEntry.COLUMN_PRECIO_CUBO, lecturaRegistro.getPrecioCubo());
        lecturaRegistroValues.put(DataBaseContract.LecturaRegistroEntry.COLUMN_ES_TARIFA_DIFERENCIADO, lecturaRegistro.getEsTarifaDiferenciado());
        lecturaRegistroValues.put(DataBaseContract.LecturaRegistroEntry.COLUMN_CONSUMO_DIFERENCIADO, lecturaRegistro.getConsumoDiferenciado());
        lecturaRegistroValues.put(DataBaseContract.LecturaRegistroEntry.COLUMN_PRECIO_DIFERENCIADO, lecturaRegistro.getPrecioDiferenciado());
        lecturaRegistroValues.put(DataBaseContract.LecturaRegistroEntry.COLUMN_CONSUMO_AGUA_ID_LAST, lecturaRegistro.getConsumoAguaIdLast());

        // updating row
        return db.update(DataBaseContract.LecturaRegistroEntry.TABLE_NAME, lecturaRegistroValues, DataBaseContract.LecturaRegistroEntry._ID + " = ?",
                new String[] { String.valueOf(lecturaRegistro.getId().toString()) });
    }

    public void deleteLecturaRegistro(LecturaRegistro lecturaRegistro) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(DataBaseContract.LecturaRegistroEntry.TABLE_NAME, DataBaseContract.LecturaRegistroEntry._ID + " = ?",
                new String[] { String.valueOf(lecturaRegistro.getId()) });
        db.close();
    }


    public void deleteLecturaRegistroALL() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(DataBaseContract.LecturaRegistroEntry.TABLE_NAME, null,
                null);
        db.close();
    }
}
