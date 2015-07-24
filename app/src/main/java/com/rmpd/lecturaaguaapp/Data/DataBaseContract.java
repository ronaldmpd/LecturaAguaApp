package com.rmpd.lecturaaguaapp.Data;

import android.provider.BaseColumns;
import android.text.format.Time;

/**
 * Created by RP on 6/24/2015.
 */
public class DataBaseContract {

    public static long normalizeDate(long startDate) {
        Time time = new Time();
        time.set(startDate);
        int julianDay = Time.getJulianDay(startDate, time.gmtoff);

        return time.setJulianDay(julianDay);
    }

    public static final class LecturaRegistroEntry implements BaseColumns {

        public static final String TABLE_NAME = "lectura_registro";


        public static final String COLUMN_CODIGO_CLIENTE = "codigo_cliente";
        public static final String COLUMN_ACCION_ID = "accion_id";
        public static final String COLUMN_PERIODO_ID = "periodo_id";
        public static final String COLUMN_PERIODO_NAME = "periodo_name";
        public static final String COLUMN_NUMERO_MEDIDOR = "numero_medidor";
        public static final String COLUMN_NOMBRE = "nombre";
        public static final String COLUMN_DIRECCION = "direccion";
        public static final String COLUMN_LECTURA_ANTERIOR = "lectura_anterior";
        public static final String COLUMN_LECTURA_ACTUAL = "lectura_actual";
        public static final String COLUMN_FECHA_LECTURA = "fecha_lectura";
        public static final String COLUMN_CONSUMO = "consumo";
        public static final String COLUMN_TOTAL_MES = "total_mes";
        public static final String COLUMN_DEUDA_ANTERIOR = "deuda_anterior";
        public static final String COLUMN_NUMERO_MESES_DEUDA = "numero_meses_deuda";
        public static final String COLUMN_TOTAL = "total";
        public static final String COLUMN_PRECIO_CUBO = "precio_cubo";
        public static final String COLUMN_ES_TARIFA_DIFERENCIADO = "es_tarifa_diferenciado";
        public static final String COLUMN_CONSUMO_DIFERENCIADO = "consumo_diferenciado";
        public static final String COLUMN_PRECIO_DIFERENCIADO = "precio_diferenciado";
        public static final String COLUMN_CONSUMO_AGUA_ID_LAST = "consumo_agua_id_last";

    }

    public static final class PeriodoEntry implements BaseColumns {

        public static final String TABLE_NAME = "periodo";

        public static final String COLUMN_ID_SERVER = "id_server";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_IS_SAVE_SERVER = "is_save_server";

    }

    public static final class ConfigurationEntry implements BaseColumns {

        public static final String TABLE_NAME = "users";

        public static final String COLUMN_HOST = "host";
        public static final String COLUMN_USERNAME = "username";
        public static final String COLUMN_PASSWORD = "password";

    }

}
