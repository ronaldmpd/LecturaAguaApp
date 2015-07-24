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
import android.widget.TextView;

import com.rmpd.lecturaaguaapp.Data.ConfigurationDAO;
import com.rmpd.lecturaaguaapp.Model.Configuration;
import com.rmpd.lecturaaguaapp.Util.ConstantsApp;

/**
 * A placeholder fragment containing a simple view.
 */
public class LoginFragment extends Fragment {

    private static final String LOG_TAG = LoginFragment.class.getSimpleName();

    EditText editTextUserName;
    EditText editTextPassword;
    Button buttontnEntrar;
    Button buttonCancelar;
    LoginFragment parent;

    TextView texViewtitleSystem;

    ConfigurationDAO configurationDAO = null;
    Configuration configuration = null;


    public LoginFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        parent = this;
        Log.i(LOG_TAG, "Start Login");

        configurationDAO = new ConfigurationDAO(rootView.getContext());
        configuration = configurationDAO.getUserByUsername("admin");

        if (configuration == null){
            configuration = new Configuration();
            configuration.setUsername("admin");
            configuration.setPassword("1234");
            configuration.setHost(ConstantsApp.URL_BASE_API);
            configurationDAO.addConfiguration(configuration);
        }


        editTextUserName =(EditText)rootView.findViewById(R.id.txtUserName);
        editTextPassword = (EditText)rootView.findViewById(R.id.txtPassword);

        buttontnEntrar = (Button)rootView.findViewById(R.id.btnEntrar);

        texViewtitleSystem = (TextView)rootView.findViewById(R.id.titleSystem);

        buttontnEntrar.setOnClickListener(new View.OnClickListener(){

                                              public void onClick(View view){

                                                  String msg="";
                                                  if( editTextUserName.getText().toString().isEmpty())
                                                      msg += "Ingrese: Usuario. \n";
                                                  if( editTextPassword.getText().toString().isEmpty())
                                                      msg += "Ingrese: Contrasena. \n";
                                                  else
                                                  {
                                                      if(editTextUserName.getText().toString().equals(configuration.getUsername()) && editTextPassword.getText().toString().equals(configuration.getPassword()))
                                                      {
                                                          Log.i(LOG_TAG, "click login, username: " + editTextUserName.getText().toString());
                                                          Intent myIntent = new Intent(rootView.getContext(), MenuActivity.class);
                                                          startActivity(myIntent);
                                                      }
                                                      else{
                                                          msg += "El usuario y contresena son incorrectos";
                                                      }
                                                  }
                                                  if(!msg.isEmpty())
                                                  {
                                                      AlertDialog alertDialog;
                                                      alertDialog = new AlertDialog.Builder(rootView.getContext()).create();
                                                      alertDialog.setTitle("Validar ingreso");
                                                      alertDialog.setMessage(msg);
                                                      alertDialog.show();
                                                  }

                                              }


                                          }

        );



        return rootView;
    }
}
