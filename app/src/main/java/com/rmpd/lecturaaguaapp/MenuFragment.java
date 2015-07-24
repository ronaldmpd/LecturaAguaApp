package com.rmpd.lecturaaguaapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A placeholder fragment containing a simple view.
 */
public class MenuFragment extends Fragment {

    private static final String LOG_TAG = LoginFragment.class.getSimpleName();

    Button buttonMenuRegistrar;
    Button buttonMenuListaPendientes;
    Button buttonMenuListaRegistrados;
    Button buttonMenuSync;
    Button buttonMenuConfiguration;

    MenuFragment parentMenu;

    public MenuFragment() {
    }

    public void onResume() {
        super.onResume();  // Always call the superclass method first

        Log.i(LOG_TAG,"OnResume Menu");
    }

    public void onPause() {
        super.onPause();  // Always call the superclass method first

        Log.i(LOG_TAG,"onPause Menu");
    }

    public void onStart() {
        super.onStart();  // Always call the superclass method first

        Log.i(LOG_TAG,"onStart Menu");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_menu, container, false);



        parentMenu = this;

        buttonMenuRegistrar =(Button) rootView.findViewById(R.id.menuRegistrar);
        buttonMenuListaPendientes =(Button)rootView.findViewById(R.id.menuListaPendientes);
        //buttonMenuListaRegistrados =(Button)rootView.findViewById(R.id.menuListaRegistrados);
        buttonMenuSync =(Button)rootView.findViewById(R.id.menuSync);
        buttonMenuConfiguration =(Button)rootView.findViewById(R.id.menuConfiguration);


        buttonMenuRegistrar.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){

                Log.i(LOG_TAG, "registrar lectura");

                Intent myIntent = new Intent(rootView.getContext(), RegistrarLecturaActivity.class);
                startActivity(myIntent);

            }
        });


        buttonMenuListaPendientes.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){

                Log.i(LOG_TAG,"Menu Lista de pendientes");

                Intent myIntent = new Intent(rootView.getContext(), ListaLecturasActivity.class);
                startActivity(myIntent);
            }
        });

        /*
        buttonMenuListaRegistrados.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){

                Log.i(LOG_TAG,"Menu Lista de registros");

                //Intent myIntent = new Intent(parentMenu, ListaRegistrados2Activity.class);
                //startActivity(myIntent);
            }
        });
        */

        buttonMenuSync.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){

                Log.i(LOG_TAG,"Menu Sync");
                //parentMenu.finish();
                Intent myIntent = new Intent(rootView.getContext(), SynchonizeActivity.class);
                startActivity(myIntent);
            }
        });


        buttonMenuConfiguration.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){

                Log.i(LOG_TAG,"Menu Config");
                //parentMenu.finish();
                Intent myIntent = new Intent(rootView.getContext(), ConfigurationActivity.class);
                startActivity(myIntent);
            }
        });


        return rootView;
    }
}
