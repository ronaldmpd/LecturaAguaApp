package com.rmpd.lecturaaguaapp.Util;

/**
 * Created by RP on 6/19/2015.
 */
public class ConstantsApp {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "lectura_agua.db3";

    public static final String URL_BASE_API = "http://190.129.110.214/sap/api/services/api.php";
    //public static final String URL_BASE_API = "http://192.168.0.109:8080/sap/api/services/api.php";

    public static final String JSON_SUCCESS = "success";
    public static final String JSON_MESSAGE = "message";
    public static final String JSON_ERROR_MESSAGE = "error_message";
    public static final String JSON_USER_ACCESS_KEY = "user_access_key";
    public static final String JSON_RESULTS = "results";

    //periodo
    public static final String JSON_ID = "id";
    public static final String JSON_NOMBRE = "nombre";

    //LecturaRegistro
    public static final String JSON_CODIGO_CLIENTE = "codigo_cliente";
    public static final String JSON_ACCION_ID = "accion_id";
    public static final String JSON_PERIODO_ID = "periodo_id";
    public static final String JSON_PERIODO_NAME = "periodo_name";
    public static final String JSON_NUMERO_MEDIDOR = "numero_medidor";
    //public static final String JSON_NOMBRE = "nombre";
    public static final String JSON_DIRECCION = "direccion";
    public static final String JSON_LECTURA_ANTERIOR = "lectura_anterior";
    public static final String JSON_LECTURA_ACTUAL = "lectura_actual";
    public static final String JSON_FECHA_LECTURA = "fecha_lectura";
    public static final String JSON_CONSUMO = "consumo";
    public static final String JSON_TOTAL_MES = "total_mes";
    public static final String JSON_DEUDA_ANTERIOR = "deuda_anterior";
    public static final String JSON_NUMERO_MESES_DEUDA = "numero_meses_deuda";
    public static final String JSON_TOTAL = "total";
    public static final String JSON_PRECIO_CUBO = "precio_cubo";
    public static final String JSON_ES_TARIFA_DIFERENCIADO = "es_tarifa_diferenciado";
    public static final String JSON_CONSUMO_DIFERENCIADO = "consumo_diferenciado";
    public static final String JSON_PRECIO_DIFERENCIADO = "precio_diferenciado";
    public static final String JSON_CONSUMO_AGUA_ID_LAST = "consumo_agua_id_last";


}
