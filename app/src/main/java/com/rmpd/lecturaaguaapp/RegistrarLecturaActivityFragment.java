package com.rmpd.lecturaaguaapp;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.rmpd.lecturaaguaapp.Data.LecturaRegistroDAO;
import com.rmpd.lecturaaguaapp.Model.LecturaRegistro;
import com.rmpd.lecturaaguaapp.Util.ConvertUtil;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * A placeholder fragment containing a simple view.
 */
public class RegistrarLecturaActivityFragment extends Fragment {

    private static final String LOG_TAG = RegistrarLecturaActivityFragment.class.getSimpleName();

    LecturaRegistro lecturaRegistroProc = null;
    View rootViewParent;
    LecturaRegistroDAO lecturaRegistroDAO = null;
    String codigoClienteParametro = null;

    public RegistrarLecturaActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_registrar_lectura, container, false);
        rootViewParent = rootView;

        lecturaRegistroDAO = new LecturaRegistroDAO(rootView.getContext());

        //hide datos lectura
        showDatosMedidor(false);




        //Parametro codigo cliente
        final String codigoClienteParametro = getActivity().getIntent().getStringExtra("codigoCliente");
        if(codigoClienteParametro != null && !codigoClienteParametro.isEmpty())
        {
            Log.i(LOG_TAG, ">>> Codigo Cliente, parametro: " + codigoClienteParametro);
            EditText editTextCodigoCliente = (EditText)rootViewParent.findViewById(R.id.editTextCodigoCliente);
            editTextCodigoCliente.setText(codigoClienteParametro);

            LecturaRegistro lecturaRegSearch = lecturaRegistroDAO.getLecturaRegistroByCodigoCliente(codigoClienteParametro);


            lecturaRegistroProc = lecturaRegSearch;

            String msg = "";
            if(lecturaRegSearch == null)
            {	msg = "El Codigo Cliente, no existe: " + codigoClienteParametro;
                showDatosMedidor(false);
            }
            else
            {

                Log.i(LOG_TAG, ">>> Registro lectura: "+ lecturaRegistroProc);

                loadDatosOfLecturaMedidor(lecturaRegSearch);

                showDatosMedidor(true);

                //msg = "El numero del medidor existe: " + lecturaRegSearch.getNombre();
                msg = null;
                EditText editTextLecturaActual = (EditText)rootViewParent.findViewById(R.id.editTextLecturaActual);
                editTextLecturaActual.setFocusable(true);
                editTextLecturaActual.setFocusableInTouchMode(true);
                editTextLecturaActual.requestFocus();
                // editTextLecturaActual.setOnFocusChangeListener(this);
                // Log.i("registrar","setFocusActualLectura");
            }

            if(msg != null && !msg.isEmpty())
            {
                AlertDialog alertDialog;
                alertDialog = new AlertDialog.Builder(rootViewParent.getContext()).create();
                alertDialog.setTitle("Registrar Lectura");
                alertDialog.setMessage(msg);
                alertDialog.show();
            }
        }


        Button buttonBuscar = (Button) rootViewParent.findViewById(R.id.buttonBuscar);
        buttonBuscar.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){
                Log.i(LOG_TAG, "Buscar usuario por codigo cliente");

                try {

                    Log.i("agua","start - buscar cliente");
                    EditText editTextCodigoCliente = (EditText)rootViewParent.findViewById(R.id.editTextCodigoCliente);

                    String msg = "";
                    if(editTextCodigoCliente != null && editTextCodigoCliente.getText().length()>0)
                    {

                        String codigoCliente = null;
                        codigoCliente = editTextCodigoCliente.getText().toString();

                        LecturaRegistro lecturaRegSearch = lecturaRegistroDAO.getLecturaRegistroByCodigoCliente(codigoCliente);

                        lecturaRegistroProc = lecturaRegSearch;
                        if(lecturaRegSearch == null)
                        {	msg = "El Codigo Cliente, no existe: " + codigoCliente;
                            showDatosMedidor(false);
                        }
                        else
                        {

                            Log.i(LOG_TAG, ">>> Registro lectura: "+ lecturaRegistroProc);

                            loadDatosOfLecturaMedidor(lecturaRegSearch);

                            showDatosMedidor(true);

                            //msg = "El numero del medidor existe: " + lecturaRegSearch.getNombre();
                            msg = null;
                            EditText editTextLecturaActual = (EditText)rootViewParent.findViewById(R.id.editTextLecturaActual);
                            editTextLecturaActual.setFocusable(true);
                            editTextLecturaActual.setFocusableInTouchMode(true);
                            editTextLecturaActual.requestFocus();
                            // editTextLecturaActual.setOnFocusChangeListener(this);
                            // Log.i("registrar","setFocusActualLectura");
                        }
                    }
                    else
                    {
                        msg = "Ingrese el Codigo Cliente";
                        showDatosMedidor(false);
                    }

                    //if(msg != null)
                    //    Toast.makeText(owner,msg,Toast.LENGTH_SHORT).show();
                    if(msg != null && !msg.isEmpty())
                    {
                        AlertDialog alertDialog;
                        alertDialog = new AlertDialog.Builder(rootViewParent.getContext()).create();
                        alertDialog.setTitle("Registrar Lectura");
                        alertDialog.setMessage(msg);
                        alertDialog.show();
                    }


                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                Log.i("agua","end - buscar cliente");

            }
        });



        EditText editTextLecturaActual = (EditText)rootViewParent.findViewById(R.id.editTextLecturaActual);
        editTextLecturaActual.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            public void afterTextChanged(Editable s) {
                // set oid value now
                //oid.setText(eid.getText().toString());
                EditText editTextLecturaActual = (EditText)rootViewParent.findViewById(R.id.editTextLecturaActual);
                TextView textViewConsumo =(TextView)rootViewParent.findViewById(R.id.textViewConsumo);
                TextView textViewTotalMes =(TextView)rootViewParent.findViewById(R.id.textViewTotalMes);
                TextView textViewTotalGeneral =(TextView)rootViewParent.findViewById(R.id.textViewTotalGeneral);
                String msg = null;

                if(editTextLecturaActual.getText().length()>0)
                {
                    Integer lecturaActual = null;
                    lecturaActual = ConvertUtil.stringToInteger(editTextLecturaActual.getText().toString());

                    if(lecturaRegistroProc != null && lecturaActual != null &&  lecturaRegistroProc.getLecturaAnterior() != null && lecturaRegistroProc.getLecturaAnterior() > lecturaActual)
                    {
                        msg = "La Lectura actual: " + lecturaActual.toString() + " no puede ser menor que la anterior: " + lecturaRegistroProc.getLecturaAnterior();
                    }
                    else
                    {
                        lecturaRegistroProc.setLecturaActual(lecturaActual);
                        lecturaRegistroProc.recalculateTotalMes();

                        textViewConsumo.setText(lecturaRegistroProc.getConsumo().toString());
                        textViewTotalMes.setText(lecturaRegistroProc.getTotalMes().toString());
                        textViewTotalGeneral.setText(lecturaRegistroProc.getTotal().toString());

                    }
                }

                //if(msg != null)
                //    Toast.makeText(owner,msg,Toast.LENGTH_SHORT).show();
                Log.i("agua","change value: " + editTextLecturaActual.getText().toString());
            }
        });



        Button buttonGuardar = (Button) rootViewParent.findViewById(R.id.buttonGuardar);
        buttonGuardar.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){
                Log.i(LOG_TAG, "Guardar Registro Lectura - Start");

                try {

                    EditText editTextLecturaActual = (EditText)rootViewParent.findViewById(R.id.editTextLecturaActual);

                    String msg = "";
                    if(editTextLecturaActual.getText().length()>0)
                    {
                        Integer lecturaActual = null;
                        lecturaActual = ConvertUtil.stringToInteger(editTextLecturaActual.getText().toString());

                        if(lecturaRegistroProc != null && lecturaActual != null &&  lecturaRegistroProc.getLecturaAnterior()> lecturaActual)
                        {
                            msg = "La Lectura actual: " + lecturaActual.toString() + " no puede ser menor que la anterior: " + lecturaRegistroProc.getLecturaAnterior();
                        }
                        else
                        {
                            DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
                            DateTime dateTime = new DateTime();
                            Log.i(LOG_TAG, ">>> now datetime: " + dtf.print(dateTime));

                            lecturaRegistroProc.setLecturaActual(lecturaActual);
                            lecturaRegistroProc.recalculateTotalMes();
                            lecturaRegistroProc.setFechaLectura(dtf.print(dateTime));
                            //lecturaCSVHT.put(lecturaRegistroProc.getCodigoCliente().toString(), lecturaRegistroProc);
                            //saveLecturasInCSV();




                            lecturaRegistroDAO.updateLecturaRegistro(lecturaRegistroProc);
                            Log.i(LOG_TAG,"Save Lectura Registro in DB");

                            resetLectura();
                            msg = "Se registro la lectura, correctamente";

                            if(codigoClienteParametro != null) {
                                //return list
                                getActivity().finish();
                            }

                        }
                    }
                    else
                    {
                        msg = "Ingrese la lectura actual";
                    }

                    //Toast.makeText(owner,msg,Toast.LENGTH_SHORT).show();

                    if(msg != null && !msg.isEmpty())
                    {
                        AlertDialog alertDialog;
                        alertDialog = new AlertDialog.Builder(rootViewParent.getContext()).create();
                        alertDialog.setTitle("Registrar Lectura");
                        alertDialog.setMessage(msg);
                        alertDialog.show();
                    }




                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                Log.i(LOG_TAG, "Guardar Registro Lectura - End");

            }
        });



        Button buttonMenuSalir =(Button)rootViewParent.findViewById(R.id.returnMenuR);
        buttonMenuSalir.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){


                Log.i("login","Menu Salir de la aplicacion");
                /*
                if(codigoClienteParametro == null) {
                    //rootView.getContext().cl owner.finish();
                    getActivity().finish();
                }else{
                    Intent myIntent = new Intent(rootView.getContext(), MenuActivity.class);
                    startActivity(myIntent);
                }
                */

                getActivity().finish();
            }
        });






        return rootView;
    }

    public void onBackPressed(){
        // do something here and don't write super.onBackPressed()

        Log.i(LOG_TAG,"Back Press");
        /*
        if(codigoClienteParametro == null) {
            //rootView.getContext().cl owner.finish();
            getActivity().finish();
        }else{
            Intent myIntent = new Intent(rootViewParent.getContext(), MenuActivity.class);
            startActivity(myIntent);
        }*/

        getActivity().finish();
    }


    public void loadDatosOfLecturaMedidor(LecturaRegistro lecturaRegistro)
    {
        if(lecturaRegistro== null)
            return;

        TextView textViewNombre = (TextView)rootViewParent.findViewById(R.id.textViewNombre);
        TextView textViewDireccion = (TextView)rootViewParent.findViewById(R.id.textViewDireccion);
        TextView textViewPeriodo = (TextView)rootViewParent.findViewById(R.id.textViewPeriodo);
        TextView textViewLecturaAnterior = (TextView)rootViewParent.findViewById(R.id.textViewLecturaAnterior);
        //TextView textViewLecturaActual = (TextView)findViewById(R.id.textViewLecturaActual);
        EditText editTextLecturaActual =(EditText)rootViewParent.findViewById(R.id.editTextLecturaActual);
        TextView textViewConsumo =(TextView)rootViewParent.findViewById(R.id.textViewConsumo);
        TextView textViewTotalMes =(TextView)rootViewParent.findViewById(R.id.textViewTotalMes);
        TextView textViewDeudaAnterior =(TextView)rootViewParent.findViewById(R.id.textViewDeudasAnterior);
        TextView textViewNumeroMeses =(TextView)rootViewParent.findViewById(R.id.textViewNumeroMeses);
        TextView textViewTotalGeneral =(TextView)rootViewParent.findViewById(R.id.textViewTotalGeneral);

        textViewNombre.setText(lecturaRegistro.getNombre());
        textViewDireccion.setText(lecturaRegistro.getDireccion());
        textViewPeriodo.setText(lecturaRegistro.getPeriodoName());
        textViewLecturaAnterior.setText(lecturaRegistro.getLecturaAnterior().toString());
        if(lecturaRegistro.getLecturaActual() != null && lecturaRegistro.getLecturaActual()!= -1)
            editTextLecturaActual.setText(lecturaRegistro.getLecturaActual().toString());

        if(lecturaRegistro.getConsumo() != null && lecturaRegistro.getConsumo()!= -1)
            textViewConsumo.setText(lecturaRegistro.getConsumo().toString());

        if(lecturaRegistro.getTotalMes() != null && lecturaRegistro.getTotalMes()!= -1)
            textViewTotalMes.setText(lecturaRegistro.getTotalMes().toString());

        textViewDeudaAnterior.setText(lecturaRegistro.getDeudaAnterior().toString());
        textViewNumeroMeses.setText(lecturaRegistro.getNumeroMesesDeuda().toString());

        if(lecturaRegistro.getTotal() != null && lecturaRegistro.getTotal()!= -1)
            textViewTotalGeneral.setText(lecturaRegistro.getTotal().toString());

        editTextLecturaActual.setFocusable(true);
        //showDatosMedidor(true);
    }

    public void showDatosMedidor(Boolean isShows)
    {
        Log.i(LOG_TAG, "Metodo showDatosMedidor - start");

        TextView textViewNombreLabel = (TextView)rootViewParent.findViewById(R.id.textViewNombreLabel);
        TextView textViewNombre = (TextView)rootViewParent.findViewById(R.id.textViewNombre);

        TextView textViewDireccionLabel = (TextView)rootViewParent.findViewById(R.id.textViewDireccionLabel);
        TextView textViewDireccion = (TextView)rootViewParent.findViewById(R.id.textViewDireccion);

        TextView textViewPeriodoLabel = (TextView)rootViewParent.findViewById(R.id.textViewPeriodoLabel);
        TextView textViewPeriodo = (TextView)rootViewParent.findViewById(R.id.textViewPeriodo);

        TextView textViewLecturaAnteriorLabel = (TextView)rootViewParent.findViewById(R.id.textViewLecturaAnteriorLabel);
        TextView textViewLecturaAnterior = (TextView)rootViewParent.findViewById(R.id.textViewLecturaAnterior);

        TextView textViewLecturaActual = (TextView)rootViewParent.findViewById(R.id.textViewLecturaActual);
        EditText editTextLecturaActual =(EditText)rootViewParent.findViewById(R.id.editTextLecturaActual);

        TextView textViewConsumoLabel =(TextView)rootViewParent.findViewById(R.id.textViewConsumoLabel);
        TextView textViewConsumo =(TextView)rootViewParent.findViewById(R.id.textViewConsumo);

        TextView textViewTotalMesLabel =(TextView)rootViewParent.findViewById(R.id.textViewTotalMesLabel);
        TextView textViewTotalMes =(TextView)rootViewParent.findViewById(R.id.textViewTotalMes);

        TextView textViewDeudaAnteriorLabel =(TextView)rootViewParent.findViewById(R.id.textViewDeudasAnteriorLabel);
        TextView textViewDeudaAnterior =(TextView)rootViewParent.findViewById(R.id.textViewDeudasAnterior);

        TextView textViewNumeroMesesLabel =(TextView)rootViewParent.findViewById(R.id.textViewNumeroMesesLabel);
        TextView textViewNumeroMeses =(TextView)rootViewParent.findViewById(R.id.textViewNumeroMeses);

        TextView textViewTotalGeneralLabel =(TextView)rootViewParent.findViewById(R.id.textViewTotalGeneralLabel);
        TextView textViewTotalGeneral =(TextView)rootViewParent.findViewById(R.id.textViewTotalGeneral);

        Button buttonGuardar = (Button)rootViewParent.findViewById(R.id.buttonGuardar);
        //Button buttonCancelar = (Button)rootViewParent.findViewById(R.id.buttonCancelar);

        if(isShows)
        {
            Log.i(LOG_TAG,"Show visible datos lectura");

            textViewNombreLabel.setVisibility(View.VISIBLE);
            textViewNombre.setVisibility(View.VISIBLE);

            textViewDireccionLabel.setVisibility(View.VISIBLE);
            textViewDireccion.setVisibility(View.VISIBLE);

            textViewPeriodoLabel.setVisibility(View.VISIBLE);
            textViewPeriodo.setVisibility(View.VISIBLE);

            textViewLecturaAnteriorLabel.setVisibility(View.VISIBLE);
            textViewLecturaAnterior.setVisibility(View.VISIBLE);

            textViewLecturaActual.setVisibility(View.VISIBLE);
            editTextLecturaActual.setVisibility(View.VISIBLE);

            textViewConsumoLabel.setVisibility(View.VISIBLE);
            textViewConsumo.setVisibility(View.VISIBLE);
            textViewTotalMesLabel.setVisibility(View.VISIBLE);
            textViewTotalMes.setVisibility(View.VISIBLE);
            textViewDeudaAnteriorLabel.setVisibility(View.VISIBLE);
            textViewDeudaAnterior.setVisibility(View.VISIBLE);
            textViewNumeroMesesLabel.setVisibility(View.VISIBLE);
            textViewNumeroMeses.setVisibility(View.VISIBLE);
            textViewTotalGeneralLabel.setVisibility(View.VISIBLE);
            textViewTotalGeneral.setVisibility(View.VISIBLE);
            buttonGuardar.setVisibility(View.VISIBLE);
            //buttonCancelar.setVisibility(View.VISIBLE);
        }
        else
        {
            Log.i(LOG_TAG,"Show Invisible datos lectura");

            textViewNombreLabel.setVisibility(View.INVISIBLE);
            textViewNombre.setVisibility(View.INVISIBLE);
            textViewDireccionLabel.setVisibility(View.INVISIBLE);
            textViewDireccion.setVisibility(View.INVISIBLE);
            textViewPeriodoLabel.setVisibility(View.INVISIBLE);
            textViewPeriodo.setVisibility(View.INVISIBLE);
            textViewLecturaAnteriorLabel.setVisibility(View.INVISIBLE);
            textViewLecturaAnterior.setVisibility(View.INVISIBLE);
            textViewLecturaActual.setVisibility(View.INVISIBLE);
            editTextLecturaActual.setVisibility(View.INVISIBLE);
            textViewConsumoLabel.setVisibility(View.INVISIBLE);
            textViewConsumo.setVisibility(View.INVISIBLE);
            textViewTotalMesLabel.setVisibility(View.INVISIBLE);
            textViewTotalMes.setVisibility(View.INVISIBLE);
            textViewDeudaAnteriorLabel.setVisibility(View.INVISIBLE);
            textViewDeudaAnterior.setVisibility(View.INVISIBLE);
            textViewNumeroMesesLabel.setVisibility(View.INVISIBLE);
            textViewNumeroMeses.setVisibility(View.INVISIBLE);
            textViewTotalGeneralLabel.setVisibility(View.INVISIBLE);
            textViewTotalGeneral.setVisibility(View.INVISIBLE);
            buttonGuardar.setVisibility(View.INVISIBLE);
           // buttonCancelar.setVisibility(View.INVISIBLE);
        }
    }

    public void resetLectura()
    {
        showDatosMedidor(false);
        lecturaRegistroProc = null;

        EditText editTextNumeroMedidor = (EditText)rootViewParent.findViewById(R.id.editTextCodigoCliente);

        editTextNumeroMedidor.setText("");
        editTextNumeroMedidor.setFocusable(true);

        EditText editTextLecturaActual = (EditText)rootViewParent.findViewById(R.id.editTextLecturaActual);
        editTextLecturaActual.setText("");

    }
}
