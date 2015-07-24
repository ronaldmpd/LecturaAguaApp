package com.rmpd.lecturaaguaapp.Model;

/**
 * Created by RP on 6/23/2015.
 */
public class Periodo {

    private Integer id;
    private String idServer;
    private String name;
    private Integer isSaveServer;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdServer() {
        return idServer;
    }

    public void setIdServer(String idServer) {
        this.idServer = idServer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIsSaveServer() {
        return isSaveServer;
    }

    public void setIsSaveServer(Integer isSaveServer) {
        this.isSaveServer = isSaveServer;
    }

    @Override
    public String toString() {

        /*
        return "Periodo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
                */

        return name;
    }
}
