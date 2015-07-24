package com.rmpd.lecturaaguaapp.Util;

import android.graphics.Bitmap;

/**
 * Created by RP on 6/30/2015.
 */
public class LecturaRegistroItem {
    private int id;
    private String codigoCliente;
    private String name;
    private String periodoName;
    private String lecturaMedidor;
    private String consumo;
    private int imgIconUserId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(String codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPeriodoName() {
        return periodoName;
    }

    public void setPeriodoName(String periodoName) {
        this.periodoName = periodoName;
    }

    public String getLecturaMedidor() {
        return lecturaMedidor;
    }

    public void setLecturaMedidor(String lecturaMedidor) {
        this.lecturaMedidor = lecturaMedidor;
    }

    public String getConsumo() {
        return consumo;
    }

    public void setConsumo(String consumo) {
        this.consumo = consumo;
    }

    public int getImgIconUserId() {
        return imgIconUserId;
    }

    public void setImgIconUserId(int imgIconUserId) {
        this.imgIconUserId = imgIconUserId;
    }

    @Override
    public String toString() {
        return "LecturaRegistroItem{" +
                "id=" + id +
                ", codigoCliente='" + codigoCliente + '\'' +
                ", name='" + name + '\'' +
                ", periodoName='" + periodoName + '\'' +
                ", lecturaMedidor='" + lecturaMedidor + '\'' +
                ", consumo='" + consumo + '\'' +
                ", imgIconUserId=" + imgIconUserId +
                '}';
    }
}
