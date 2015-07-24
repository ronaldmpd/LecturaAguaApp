package com.rmpd.lecturaaguaapp.Util;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.rmpd.lecturaaguaapp.Model.Configuration;

import java.util.Calendar;

/**
 * Created by RP on 6/22/2015.
 */
public class ConvertUtil {

    private static final String LOG_TAG = ConvertUtil.class.getSimpleName() ;

    public static Integer stringToInteger(String num)
    {
        Integer r = null;
        if(num != null && !num.isEmpty())
        {
            r = Integer.parseInt(num);
        }
        return r;
    }

    public static Double stringToDouble(String num) {
        Double r = null;

        try {
            if (num != null && !num.isEmpty()) {
                num = num.replace(',', '.');
                r = Double.parseDouble(num);
            }
        }catch (Exception ex)
        {
            Log.e(LOG_TAG, "Error (stringToDouble): " + ex.getMessage());
            ex.printStackTrace();
        }

        return r;
    }

    public static Boolean stringToBoolean(String value)
    {
        Boolean r = null;
        if(value != null && !value.isEmpty())
        {
            if(value.equals("1"))
                return  true;

            if(value.equals("0"))
                return  false;

            if(value.equals("Y"))
                return  true;

            if(value.equals("N"))
                return  false;

            r = Boolean.parseBoolean(value);
        }
        return r;
    }


    public static String getDateNowTo_dd_mm_aaaa()
    {
        String dateS = null;

        Calendar c = Calendar.getInstance();
        String dia = Integer.toString(c.get(Calendar.DATE));
        String mes = Integer.toString(c.get(Calendar.MONTH));
        String annio = Integer.toString(c.get(Calendar.YEAR));
        String h = Integer.toString(c.get(Calendar.HOUR));
        String m = Integer.toString(c.get(Calendar.MINUTE));
        String s = Integer.toString(c.get(Calendar.SECOND));
        String am_pm = "";
        if(c.get(Calendar.AM_PM)==0)
            am_pm = "AM";
        else
            am_pm = "PM";

        dateS =  dia+"-"+mes+"-"+annio+" " + h +":"+m+":"+s + " " + am_pm;

        return dateS;
    }


}
