package com.rmpd.lecturaaguaapp;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.rmpd.lecturaaguaapp.Data.ConfigurationDAO;
import com.rmpd.lecturaaguaapp.Data.DataBaseHelper;
import com.rmpd.lecturaaguaapp.Data.LecturaRegistroDAO;
import com.rmpd.lecturaaguaapp.Data.PeriodoDAO;
import com.rmpd.lecturaaguaapp.Model.Configuration;
import com.rmpd.lecturaaguaapp.Model.LecturaRegistro;
import com.rmpd.lecturaaguaapp.Model.Periodo;
import com.rmpd.lecturaaguaapp.Util.ConstantsApp;
import com.rmpd.lecturaaguaapp.Util.HttpPosUtil;
import com.rmpd.lecturaaguaapp.Util.JsonParseUtil;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class SynchonizeFragment extends Fragment {

    private static final String LOG_TAG = LoginFragment.class.getSimpleName();
    private ProgressDialog pDialog;
    Context parentContext;
    HttpPosUtil httpPost;
    Configuration configuration;
    View rootViewParent;

    ArrayList<Periodo> periodos;
    Periodo periodoSeleccionado;

    Button buttonActualizarPeriodo;
    Button buttonObtenerPlanillaDeLectura;
    Button buttonGuardarLecturaServer;

    LecturaRegistroDAO lecturaRegistroDAO = null;

    public SynchonizeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.fragment_synchonize, container, false);
        final View rootView = inflater.inflate(R.layout.fragment_synchonize, container, false);
        rootViewParent = rootView;

        ConfigurationDAO configurationDAO = new ConfigurationDAO(rootView.getContext());
        configuration = configurationDAO.getUserByUsername("admin");

        lecturaRegistroDAO = new LecturaRegistroDAO(rootView.getContext());

        if(configuration == null) {

            configuration = new Configuration();
            configuration.setHost(ConstantsApp.URL_BASE_API);
            configuration.setUsername("admin");
            configuration.setPassword("1234");
            configurationDAO.addConfiguration(configuration);
        }


        buttonActualizarPeriodo =(Button) rootView.findViewById(R.id.btnActualizarPeriodo);
        buttonObtenerPlanillaDeLectura =(Button) rootView.findViewById(R.id.btnObtenerPlantillaDeLecturas);
        buttonGuardarLecturaServer = (Button) rootView.findViewById(R.id.btnGuardarPlantillaDeLecturas);

        buttonActualizarPeriodo.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){

                Log.i(LOG_TAG, "actualizar periodo");
                loadPeriodosOfWebServices();

            }
        });


        buttonObtenerPlanillaDeLectura.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){

                Log.i(LOG_TAG, "actualizar periodo");
                //Intent myIntent = new Intent(parentMenu, RegistrarActivity.class);
                //startActivity(myIntent);

                Spinner spinnerPeriodos = null;

                try {



                    //loadPeriodosOfWebServices();



                    spinnerPeriodos = (Spinner) rootViewParent.findViewById(R.id.spinner_periodo);
                    //Periodo periodo = (Periodo) ( (Spinner) rootViewParent.findViewById(R.id.spinner_periodo)).getSelectedItem();

                    periodoSeleccionado = (Periodo) spinnerPeriodos.getSelectedItem();

                    if(spinnerPeriodos != null && spinnerPeriodos.getAdapter() != null && spinnerPeriodos.getAdapter().getCount() > 0) {
                        Log.i(LOG_TAG, "periodo seleccionado: idServer:" + periodoSeleccionado.getIdServer() + " name: " + periodoSeleccionado.getName());

                        getLecturaRegistrosOfWebServices(periodoSeleccionado.getIdServer().toString());

                    }
                    else {
                        AlertDialog alertDialog;
                        alertDialog = new AlertDialog.Builder(rootView.getContext()).create();
                        alertDialog.setTitle("Validar periodo ");
                        alertDialog.setMessage("El periodo no es correcto");
                        alertDialog.show();
                    }

                }catch (Exception ex)
                {
                    Log.e(LOG_TAG, "Error: " + ex.toString());
                    ex.printStackTrace();

                    //Toast toast1 = Toast.makeText(parentContext.getApplicationContext(), "El periodo no es correcto.", Toast.LENGTH_SHORT);
                    //toast1.show();

                }




            }
        });


        buttonGuardarLecturaServer.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){

                Log.i(LOG_TAG, "buttonGuardarLecturaServer");


                Spinner spinnerPeriodos = null;

                spinnerPeriodos = (Spinner) rootViewParent.findViewById(R.id.spinner_periodo);
                //Periodo periodo = (Periodo) ( (Spinner) rootViewParent.findViewById(R.id.spinner_periodo)).getSelectedItem();

                periodoSeleccionado = (Periodo) spinnerPeriodos.getSelectedItem();

                if(spinnerPeriodos != null && spinnerPeriodos.getAdapter() != null && spinnerPeriodos.getAdapter().getCount() > 0) {
                    Log.i(LOG_TAG, "periodo seleccionado: idServer:" + periodoSeleccionado.getIdServer() + " name: " + periodoSeleccionado.getName());

                    //getLecturaRegistrosOfWebServices(periodoSeleccionado.getIdServer().toString());

                    SendLecturaRegistroTask sendLecturaRegistroTask = new SendLecturaRegistroTask();
                    sendLecturaRegistroTask.execute(periodoSeleccionado.getIdServer().toString());

                }
                else {
                    AlertDialog alertDialog;
                    alertDialog = new AlertDialog.Builder(rootView.getContext()).create();
                    alertDialog.setTitle("Validar periodo ");
                    alertDialog.setMessage("El periodo no es correcto");
                    alertDialog.show();
                }


            }
        });

        return rootView;
    }

    public void loadPeriodosOfWebServices()
    {
        GetPeriodostTask task = new GetPeriodostTask();
        task.execute("1", "asdf", "1");
    }

    public void getLecturaRegistrosOfWebServices(String periodoId)
    {
        GetLecturaRegistroTask task = new GetLecturaRegistroTask();
        task.execute(periodoId);
    }

    public void err_periodo(){
        Vibrator vibrator =(Vibrator) parentContext.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(200);
        Toast toast1 = Toast.makeText(parentContext.getApplicationContext(), "Error: Obtener periodos.", Toast.LENGTH_SHORT);
        toast1.show();
    }


    class GetPeriodostTask extends AsyncTask<String, Void, ArrayList<Periodo> > {

        @Override
        protected ArrayList<Periodo> doInBackground(String... params) {

            ArrayList<Periodo> products = new ArrayList<Periodo>();

            Log.v(LOG_TAG, "stat periodos doInBrackGround");

            ArrayList<NameValuePair> postparameters2send= new ArrayList<NameValuePair>();

            String admins_id = params[0];
            String user_access_key = params[1];
            String category_id = params[2];

            postparameters2send.add(new BasicNameValuePair("v","1"));
            postparameters2send.add(new BasicNameValuePair("module","1"));
            postparameters2send.add(new BasicNameValuePair("action","1"));
            postparameters2send.add(new BasicNameValuePair("app_key","asdf"));


            try {

                HttpPosUtil httpPosUtil = new HttpPosUtil();
                String resultString =  httpPosUtil.getserverdata(postparameters2send, configuration.getHost());
                Log.v(LOG_TAG,"server resultString: " + resultString);


                return JsonParseUtil.parseStringPeriodos(resultString);

            } catch (Exception e) {
                Log.e(LOG_TAG, "Error parsing" + e.getMessage(), e);
                e.printStackTrace();

                Periodo p = new Periodo();
                p.setId(0);
                p.setName("NO DATA");

                products.add(p);

            }

            return products;
        }

        @Override
        protected void onPostExecute(ArrayList<Periodo> periodos) {

            Log.d(LOG_TAG, "periodos.size: " + periodos.size());
            ArrayAdapter locationAdapter = new ArrayAdapter(rootViewParent.getContext(), R.layout.spinner_periodo, periodos);

            Spinner locationSpinner = (Spinner) rootViewParent.findViewById(R.id.spinner_periodo);
            locationSpinner.setAdapter(locationAdapter);


        }
    }




    class GetLecturaRegistroTask extends AsyncTask<String, Void, ArrayList<LecturaRegistro> > {

        @Override
        protected ArrayList<LecturaRegistro> doInBackground(String... params) {

            ArrayList<LecturaRegistro> lecturaRegistros = new ArrayList<LecturaRegistro>();

            Log.v(LOG_TAG, "stat periodos doInBrackGround");

            ArrayList<NameValuePair> postparameters2send= new ArrayList<NameValuePair>();

            String periodo_id = params[0];


            postparameters2send.add(new BasicNameValuePair("v","1"));
            postparameters2send.add(new BasicNameValuePair("module","1"));
            postparameters2send.add(new BasicNameValuePair("action","2"));
            postparameters2send.add(new BasicNameValuePair("app_key","asdf"));
            postparameters2send.add(new BasicNameValuePair("periodo_id",periodo_id));


            try {

                HttpPosUtil httpPosUtil = new HttpPosUtil();
                String resultString =  httpPosUtil.getserverdata(postparameters2send, configuration.getHost());
                Log.v(LOG_TAG,"server resultString: " + resultString);


                return JsonParseUtil.parseStringLecturaRegistros(resultString);

            } catch (Exception e) {
                Log.e(LOG_TAG, "Error parsing" + e.getMessage(), e);
                e.printStackTrace();

            }

            return lecturaRegistros;
        }

        @Override
        protected void onPostExecute(ArrayList<LecturaRegistro> lecturaRegistros) {

            Log.d(LOG_TAG, ">>> lecturaRegistros.size: " + lecturaRegistros.size());

            //DataBaseHelper lecturaRegistroDbHelper = new DataBaseHelper(rootViewParent.getContext());
            PeriodoDAO periodoDbHelper = new PeriodoDAO(rootViewParent.getContext());
            LecturaRegistroDAO lecturaRegistroDAO = new LecturaRegistroDAO(rootViewParent.getContext());


            lecturaRegistroDAO.deleteLecturaRegistroALL();

            ArrayList<Periodo> periodos = periodoDbHelper.getPeriodosAll();
            if(periodos.size()> 0)
                periodoDbHelper.deletePeriodosALL();

            //Save data Base
            for (LecturaRegistro lecturaRegistro : lecturaRegistros)
            {
                lecturaRegistroDAO.addLecturaRegistro(lecturaRegistro);
                Log.d(LOG_TAG, "Save in database lecturaRegistro: " + lecturaRegistro);
            }


            periodoSeleccionado.setIsSaveServer(0);
            periodoDbHelper.addPeriodo(periodoSeleccionado);
            Log.d(LOG_TAG, "Save in database periodo: " + periodoSeleccionado);


            AlertDialog alertDialog;
            alertDialog = new AlertDialog.Builder(rootViewParent.getContext()).create();
            alertDialog.setTitle("Sincronizar Servidor");
            alertDialog.setMessage("Se sincronizo correctamente. Periodo: " + periodoSeleccionado.getName() + " Numero de Regitros: " + lecturaRegistros.size());
            alertDialog.show();

        }
    }

    class SendLecturaRegistroTask extends AsyncTask<String, Void, String > {

        @Override
        protected String doInBackground(String... params) {

            Log.v(LOG_TAG, "stat SendLecturaRegistroTask doInBrackGround");

            String responseWS ="";

            ArrayList<LecturaRegistro> lecturaRegistros = new ArrayList<LecturaRegistro>();
            String periodo_id = params[0];

            String lecturasJsonString = "";

            ArrayList<LecturaRegistro> lecturaRegistrosSend = lecturaRegistroDAO.getLecturaRegistroAll();
            if(lecturaRegistrosSend.size() > 0)
            {
                Gson gson = new Gson();
                lecturasJsonString = gson.toJson(lecturaRegistrosSend);
            }

            Log.i(LOG_TAG, "lecturasJsonString: " + lecturasJsonString);
            Log.i(LOG_TAG, "periodo_id: " + periodo_id);

            ArrayList<NameValuePair> postparameters2send= new ArrayList<NameValuePair>();
            postparameters2send.add(new BasicNameValuePair("v","1"));
            postparameters2send.add(new BasicNameValuePair("module","1"));
            postparameters2send.add(new BasicNameValuePair("action","3"));
            postparameters2send.add(new BasicNameValuePair("app_key","asdf"));
            postparameters2send.add(new BasicNameValuePair("periodo_id",periodo_id));
            postparameters2send.add(new BasicNameValuePair("lecturas",lecturasJsonString));


            try {

                HttpPosUtil httpPosUtil = new HttpPosUtil();
                String resultString =  httpPosUtil.getserverdata(postparameters2send, configuration.getHost());
                Log.v(LOG_TAG,"server resultString: " + resultString);

                responseWS = resultString;

            } catch (Exception e) {
                Log.e(LOG_TAG, "Error parsing" + e.getMessage(), e);
                e.printStackTrace();

            }

            return responseWS;
        }

        @Override
        protected void onPostExecute(String responseWS) {

            Log.d(LOG_TAG, ">>> responseWS:" + responseWS);

            AlertDialog alertDialog;
            alertDialog = new AlertDialog.Builder(rootViewParent.getContext()).create();
            alertDialog.setTitle("Sincronizar Servidor");
            alertDialog.setMessage("Response WebServices:" + responseWS);
            alertDialog.show();
        }
    }
}
