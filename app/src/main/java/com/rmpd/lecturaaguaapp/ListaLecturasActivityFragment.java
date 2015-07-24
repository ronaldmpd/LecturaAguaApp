package com.rmpd.lecturaaguaapp;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.rmpd.lecturaaguaapp.Data.LecturaRegistroDAO;
import com.rmpd.lecturaaguaapp.Model.LecturaRegistro;
import com.rmpd.lecturaaguaapp.Util.LecturaRegistroItem;
import com.rmpd.lecturaaguaapp.Util.LecturaRegistroItemArrayAdapter;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * A placeholder fragment containing a simple view.
 */
public class ListaLecturasActivityFragment extends Fragment {

    private static final String LOG_TAG = ListaLecturasActivityFragment.class.getSimpleName();

    ListView scrollView ;// (ListView)rootView.findViewById(R.id.result_list_view);
    LinkedList<LecturaRegistroItem> productItems;// = new LinkedList<ProductItem>();
    View rootViewParent;


    public ListaLecturasActivityFragment() {
    }

    public void onResume() {
        super.onResume();  // Always call the superclass method first

        Log.i(LOG_TAG,"OnResume ListaLecturas");

        loadDataList();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.fragment_lista_lecturas, container, false);

        final View rootView = inflater.inflate(R.layout.fragment_lista_lecturas, container, false);

        rootViewParent =rootView;


        loadDataList();

        //Onclick
        scrollView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Log.d(LOG_TAG, "Click List, position: " + position);
                LecturaRegistroItem lecturaRegistroItem = productItems.get(position);
                Log.d(LOG_TAG, "lecturaRegistroItem: " + lecturaRegistroItem.toString());


                Intent intent = new Intent(getActivity(),RegistrarLecturaActivity.class);
                intent.putExtra("codigoCliente", lecturaRegistroItem.getCodigoCliente());
                startActivity(intent);

            }
        });


        return  rootView;
    }


    public void loadDataList()
    {
        scrollView = (ListView)rootViewParent.findViewById(R.id.products_list_view);
        productItems = new LinkedList<LecturaRegistroItem>();

        LecturaRegistroDAO lecturaRegistroDAO = new LecturaRegistroDAO(rootViewParent.getContext());

        ArrayList<LecturaRegistro> lecturaRegistros = lecturaRegistroDAO.getLecturaRegistroAll();


        for (LecturaRegistro lecturaRegistro : lecturaRegistros)
        {

            LecturaRegistroItem lecturaRegistroItem = new LecturaRegistroItem();
            lecturaRegistroItem.setId(1);
            lecturaRegistroItem.setCodigoCliente(lecturaRegistro.getCodigoCliente());
            lecturaRegistroItem.setPeriodoName(lecturaRegistro.getPeriodoName());
            lecturaRegistroItem.setName(lecturaRegistro.getNombre());

            String lectura = lecturaRegistro.getLecturaActual() != null && lecturaRegistro.getLecturaActual() != -1 ? lecturaRegistro.getLecturaActual().toString() : "";
            lecturaRegistroItem.setLecturaMedidor(lectura);

            String consumo = lecturaRegistro.getConsumo() != null && lecturaRegistro.getConsumo() != -1.0  ? lecturaRegistro.getConsumo().toString() : "";
            lecturaRegistroItem.setConsumo(consumo);

            lecturaRegistroItem.setImgIconUserId(R.drawable.users_wather);

            productItems.add(lecturaRegistroItem);

        }



        scrollView.setAdapter( new LecturaRegistroItemArrayAdapter(rootViewParent.getContext(), R.layout.lectura_registro_item_row,0, productItems));

    }
}
