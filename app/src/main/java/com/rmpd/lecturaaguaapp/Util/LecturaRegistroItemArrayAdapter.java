package com.rmpd.lecturaaguaapp.Util;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.rmpd.lecturaaguaapp.R;

import java.util.LinkedList;

/**
 * Created by RP on 6/30/2015.
 */
public class LecturaRegistroItemArrayAdapter extends ArrayAdapter<LecturaRegistroItem> {

    LayoutInflater inf;
    LinkedList<LecturaRegistroItem> objects;

    public LecturaRegistroItemArrayAdapter(Context context, int resource, int textViewResourceId, LinkedList<LecturaRegistroItem> objects)
    {
        super(context, resource, textViewResourceId,objects);
        this.inf= LayoutInflater.from(context);
        this.objects = objects;
    }

    public View getView (int position, View convertView, ViewGroup parent)
    {
        View row = convertView;
        LecturaRegistroItem currentProductItem = (LecturaRegistroItem)objects.get(position);

        if(row ==  null)
        {
            row = inf.inflate(R.layout.lectura_registro_item_row,null);
        }

        ImageView iv = (ImageView) row.findViewById(R.id.imgIconUserItem);
        iv.setImageResource(currentProductItem.getImgIconUserId());
        //iv.setImageBitmap(currentProductItem.getImg1());
        iv.setScaleType(ImageView.ScaleType.FIT_XY);


        TextView codigoClienteTextView = (TextView)row.findViewById(R.id.codigoClienteItem);
        codigoClienteTextView.setText("Codigo: " + currentProductItem.getCodigoCliente());

        TextView periodoTextView = (TextView)row.findViewById(R.id.periodoItem);
        periodoTextView.setText(currentProductItem.getPeriodoName());

        TextView nameTextView = (TextView)row.findViewById(R.id.nameItem);
        nameTextView.setText(currentProductItem.getName());

        //TextView lecturaMedidorTextView = (TextView)row.findViewById(R.id.lecturaMedidorItem);
        //lecturaMedidorTextView.setText("Lectura: " + currentProductItem.getLecturaMedidor());

        TextView consumoTextView = (TextView)row.findViewById(R.id.consumoItem);

        String lectura = "Lectura: " ;
        if(currentProductItem.getLecturaMedidor() == null || currentProductItem.getLecturaMedidor().isEmpty()) {
            lectura += "          ";

        }
        else {

            lectura += currentProductItem.getLecturaMedidor();
            consumoTextView.setBackgroundColor(Color.GREEN);
        }

        consumoTextView.setText( lectura +  " Consumo(m3): " + currentProductItem.getConsumo());



        return row;
    }
}
