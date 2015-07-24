package com.rmpd.lecturaaguaapp.Util;

import android.util.Log;

import com.rmpd.lecturaaguaapp.Model.LecturaRegistro;
import com.rmpd.lecturaaguaapp.Model.Periodo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by RP on 6/23/2015.
 */
public class JsonParseUtil {
    private static final String LOG_TAG = JsonParseUtil.class.getSimpleName() ;

    public static ArrayList<Periodo> parseStringPeriodos(String jsonString)
    {


        ArrayList<Periodo> periodos = new ArrayList<Periodo>();
        try{

            Log.d(LOG_TAG, "products jsonString: " + jsonString);


            Log.d(LOG_TAG, "jsonString: " + jsonString);
            JSONObject jsonObject = new JSONObject(jsonString);


            String success = jsonObject.getString(ConstantsApp.JSON_SUCCESS);

            if(success.equals("1")) {

                JSONArray produtsArray = jsonObject.getJSONArray(ConstantsApp.JSON_RESULTS); //new JSONArray(jsonString);
                Log.d(LOG_TAG, "produtsArray.count: " + produtsArray.length());

                for (int i = 0; i < produtsArray.length(); i++){

                    JSONObject prodObject = produtsArray.getJSONObject(i);
                    Periodo p = new Periodo();
                    p.setIdServer(prodObject.getString(ConstantsApp.JSON_ID));
                    p.setName(prodObject.getString(ConstantsApp.JSON_NOMBRE));

                    periodos.add(p);

                    Log.d(LOG_TAG,"add p:"+ p.toString());

                }

            }
            else
            {
                //user.setErrorMessage(jsonObject.getString(ConstantsApp.JSON_ERROR_MESSAGE));

                Periodo p = new Periodo();
                p.setId(-1);
                p.setName("Error");
                periodos.add(p);

            }


        }
        catch(JSONException e){
            Log.e(LOG_TAG, "Error parsing data " + e.toString());
            e.printStackTrace();
            return null;
        }


        return periodos;

    }



    public static ArrayList<LecturaRegistro> parseStringLecturaRegistros(String jsonString)
    {


        ArrayList<LecturaRegistro> lecturaRegistros = new ArrayList<LecturaRegistro>();
        try{

            Log.d(LOG_TAG, "lecturaRegistros jsonString: " + jsonString);
            JSONObject jsonObject = new JSONObject(jsonString);

            String success = jsonObject.getString(ConstantsApp.JSON_SUCCESS);

            if(success.equals("1")) {

                JSONArray produtsArray = jsonObject.getJSONArray(ConstantsApp.JSON_RESULTS); //new JSONArray(jsonString);
                Log.d(LOG_TAG, "lecturaRegistrosArray.count: " + produtsArray.length());

                for (int i = 0; i < produtsArray.length(); i++){

                    JSONObject prodObject = produtsArray.getJSONObject(i);
                    LecturaRegistro lecturaRegistro = new LecturaRegistro();

                    lecturaRegistro.setCodigoCliente(prodObject.getString(ConstantsApp.JSON_CODIGO_CLIENTE));
                    lecturaRegistro.setNumeroMedidor(ConvertUtil.stringToInteger(prodObject.getString(ConstantsApp.JSON_NUMERO_MEDIDOR)));
                    lecturaRegistro.setNombre(prodObject.getString(ConstantsApp.JSON_NOMBRE));
                    lecturaRegistro.setAccionId(prodObject.getString(ConstantsApp.JSON_ACCION_ID));
                    lecturaRegistro.setPrecioCubo(ConvertUtil.stringToDouble(prodObject.getString(ConstantsApp.JSON_PRECIO_CUBO)));
                    lecturaRegistro.setDireccion(prodObject.getString(ConstantsApp.JSON_DIRECCION));
                    lecturaRegistro.setEsTarifaDiferenciado(ConvertUtil.stringToBoolean(prodObject.getString(ConstantsApp.JSON_ES_TARIFA_DIFERENCIADO)));
                    lecturaRegistro.setConsumoDiferenciado(ConvertUtil.stringToInteger(prodObject.getString(ConstantsApp.JSON_CONSUMO_DIFERENCIADO)));
                    lecturaRegistro.setPrecioDiferenciado(ConvertUtil.stringToDouble(prodObject.getString(ConstantsApp.JSON_PRECIO_DIFERENCIADO)));
                    lecturaRegistro.setConsumoAguaIdLast(ConvertUtil.stringToInteger(prodObject.getString(ConstantsApp.JSON_CONSUMO_AGUA_ID_LAST)));
                    lecturaRegistro.setLecturaAnterior(ConvertUtil.stringToInteger(prodObject.getString(ConstantsApp.JSON_LECTURA_ANTERIOR)));
                    lecturaRegistro.setNumeroMesesDeuda(ConvertUtil.stringToInteger(prodObject.getString(ConstantsApp.JSON_NUMERO_MESES_DEUDA)));
                    lecturaRegistro.setDeudaAnterior(ConvertUtil.stringToDouble(prodObject.getString(ConstantsApp.JSON_DEUDA_ANTERIOR)));
                    lecturaRegistro.setPeriodoId(prodObject.getString(ConstantsApp.JSON_PERIODO_ID));
                    lecturaRegistro.setPeriodoName(prodObject.getString(ConstantsApp.JSON_PERIODO_NAME));

                    //Defaul
                    lecturaRegistro.setLecturaActual(-1);
                    lecturaRegistro.setConsumo(-1);
                    lecturaRegistro.setTotal(-1.0);
                    lecturaRegistro.setTotalMes(-1.0);
                    lecturaRegistro.setFechaLectura("");

                    lecturaRegistros.add(lecturaRegistro);
                    Log.d(LOG_TAG, "add lecturaRegistro:" + lecturaRegistro.toString());
                }
            }


        }
        catch(JSONException e){
            Log.e(LOG_TAG, "Error parsing data " + e.toString() );
            e.printStackTrace();
            return null;
        }

        return lecturaRegistros;
    }

}
