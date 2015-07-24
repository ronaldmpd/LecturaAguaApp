package com.rmpd.lecturaaguaapp;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.rmpd.lecturaaguaapp.Data.ConfigurationDAO;
import com.rmpd.lecturaaguaapp.Model.Configuration;

/**
 * A placeholder fragment containing a simple view.
 */
public class ConfigurationFragment extends Fragment {

    private static final String LOG_TAG = ConfigurationFragment.class.getSimpleName();

    ConfigurationDAO configurationDAO = null;
    Configuration configuration = null;

    EditText editTextPasswordOld;
    EditText editTextPasswordNew;
    EditText editTextPasswordNewRepetid;

    Button buttonGuardar;

    public ConfigurationFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_configuration, container, false);


        configurationDAO = new ConfigurationDAO(rootView.getContext());
        configuration = configurationDAO.getUserByUsername("admin");

        final EditText editTextLecturaActual = (EditText)rootView.findViewById(R.id.txtHost);

        editTextPasswordOld = (EditText)rootView.findViewById(R.id.txtPasswordOld);
        editTextPasswordNew = (EditText)rootView.findViewById(R.id.txtPasswordNew);
        editTextPasswordNewRepetid = (EditText)rootView.findViewById(R.id.txtPasswordNewRepit);

        if(configuration != null) {
            editTextLecturaActual.setText(configuration.getHost());
            Log.i(LOG_TAG, "configurationActual: " + configuration.toString());
        }

        buttonGuardar =(Button) rootView.findViewById(R.id.buttonGuardarCnf);
        buttonGuardar.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                Log.i(LOG_TAG, "Guardar configuracion");

                configuration.setHost(editTextLecturaActual.getText().toString());


                //Validate change password

                String passwordOld = editTextPasswordOld.getText().toString();
                String passwordNew = editTextPasswordNew.getText().toString();
                String passwordNewRepited = editTextPasswordNewRepetid.getText().toString();

                String msg = "";
                if (!passwordOld.isEmpty() || !passwordNew.isEmpty() || !passwordNewRepited.isEmpty()) {


                    Log.i(LOG_TAG,"passwordOld: " + passwordOld + " passwordNew: " + passwordNew  + " passwordNewRepited: " + passwordNewRepited);

                    if (passwordOld.isEmpty())
                        msg += "Ingrese: Contrasena Anterior. \n";
                    if (!configuration.getPassword().equals(passwordOld.toString()))
                        msg += "Ingrese: Contrasena Anterior, no es correcto. \n";
                    else {
                        if (passwordNew.isEmpty())
                            msg += "Ingrese: Contrasena Nueva. \n";
                        if (passwordNewRepited.isEmpty())
                            msg += "Ingrese: Contrasena Nueva Repetir. \n";
                        if (!passwordNewRepited.isEmpty() && !passwordNewRepited.equals(passwordNew))
                            msg += "Ingrese: Contrasena Nueva Repetir, no es igual a la nueva contrasena. \n";

                        if(msg.isEmpty())
                            configuration.setPassword(passwordNew);

                    }
                }

                if (!msg.isEmpty()) {
                    AlertDialog alertDialog;
                    alertDialog = new AlertDialog.Builder(rootView.getContext()).create();
                    alertDialog.setTitle("Validar Cambio contrasena");
                    alertDialog.setMessage(msg);
                    alertDialog.show();
                }

                if(msg.isEmpty()) {
                    configurationDAO.updateConfiguration(configuration);
                    Log.i(LOG_TAG, "save configurationActual: " + configuration.toString());
                    getActivity().finish();
                }

            }
        });






        return rootView;
    }
}
