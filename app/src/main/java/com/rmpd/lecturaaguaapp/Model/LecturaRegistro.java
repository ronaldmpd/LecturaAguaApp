package com.rmpd.lecturaaguaapp.Model;

import android.util.Log;

/**
 * Created by RP on 6/23/2015.
 */
public class LecturaRegistro {

    private static final String LOG_TAG = LecturaRegistro.class.getSimpleName();

    private Integer id;

    private String codigoCliente;
    private String accionId;
    private Integer numeroMedidor;
    private String periodoId;
    private String periodoName;
    private String nombre;
    private String direccion;
    private Integer lecturaAnterior;
    private Integer lecturaActual;
    private String fechaLectura;
    private Integer consumo;
    private Double totalMes;
    private Double deudaAnterior;
    private Integer numeroMesesDeuda;
    private Double total;
    private Double precioCubo;

    private Boolean esTarifaDiferenciado;
    private Integer consumoDiferenciado;
    private Double precioDiferenciado;

    private Integer consumoAguaIdLast;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(String codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public Integer getNumeroMedidor() {
        return numeroMedidor;
    }

    public void setNumeroMedidor(Integer numeroMedidor) {
        this.numeroMedidor = numeroMedidor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Integer getLecturaAnterior() {
        return lecturaAnterior;
    }

    public void setLecturaAnterior(Integer lecturaAnterior) {
        this.lecturaAnterior = lecturaAnterior;
    }

    public Integer getLecturaActual() {
        return lecturaActual;
    }

    public void setLecturaActual(Integer lecturaActual) {
        this.lecturaActual = lecturaActual;
    }

    public String getFechaLectura() {
        return fechaLectura;
    }

    public void setFechaLectura(String fechaLectura) {
        this.fechaLectura = fechaLectura;
    }

    public Integer getConsumo() {
        return consumo;
    }

    public void setConsumo(Integer consumo) {
        this.consumo = consumo;
    }

    public Double getTotalMes() {
        return totalMes;
    }

    public void setTotalMes(Double totalMes) {
        this.totalMes = totalMes;
    }

    public Double getDeudaAnterior() {
        return deudaAnterior;
    }

    public void setDeudaAnterior(Double deudaAnterior) {
        this.deudaAnterior = deudaAnterior;
    }

    public Integer getNumeroMesesDeuda() {
        return numeroMesesDeuda;
    }

    public void setNumeroMesesDeuda(Integer numeroMesesDeuda) {
        this.numeroMesesDeuda = numeroMesesDeuda;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getPrecioCubo() {
        return precioCubo;
    }

    public void setPrecioCubo(Double precioCubo) {
        this.precioCubo = precioCubo;
    }

    public String getAccionId() {
        return accionId;
    }

    public void setAccionId(String accionId) {
        this.accionId = accionId;
    }

    public String getPeriodoName() {
        return periodoName;
    }

    public void setPeriodoName(String periodoName) {
        this.periodoName = periodoName;
    }

    public String getPeriodoId() {
        return periodoId;
    }

    public void setPeriodoId(String periodoId) {
        this.periodoId = periodoId;
    }

    public Boolean getEsTarifaDiferenciado() {
        return esTarifaDiferenciado;
    }

    public void setEsTarifaDiferenciado(Boolean esTarifaDiferenciado) {
        this.esTarifaDiferenciado = esTarifaDiferenciado;
    }

    public Integer getConsumoDiferenciado() {
        return consumoDiferenciado;
    }

    public void setConsumoDiferenciado(Integer consumoDiferenciado) {
        this.consumoDiferenciado = consumoDiferenciado;
    }

    public Double getPrecioDiferenciado() {
        return precioDiferenciado;
    }

    public void setPrecioDiferenciado(Double precioDiferenciado) {
        this.precioDiferenciado = precioDiferenciado;
    }

    public Integer getConsumoAguaIdLast() {
        return consumoAguaIdLast;
    }

    public void setConsumoAguaIdLast(Integer consumoAguaIdLast) {
        this.consumoAguaIdLast = consumoAguaIdLast;
    }

    public void recalculateTotalMes()
    {
        try{

            if(lecturaActual != null && lecturaActual != 0)
            {
                this.consumo = this.lecturaActual - this.lecturaAnterior;

                //this.precioCubo = 2d;

                if(!this.esTarifaDiferenciado)
                    this.totalMes = this.consumo * this.precioCubo;
                else
                {
                    if(this.consumo > this.consumoDiferenciado)
                        this.totalMes = this.consumo * this.precioDiferenciado;
                }

                this.total = this.totalMes + this.deudaAnterior;
                //this.fechaLectura =
            }
        }catch (Exception ex)
        {
            Log.e(LOG_TAG, ex.getMessage() + " " + ex.toString());
        }

    }


    @Override
    public String toString() {
        return "LecturaRegistro{" +
                "id=" + id +
                ", codigoCliente='" + codigoCliente + '\'' +
                ", accionId='" + accionId + '\'' +
                ", numeroMedidor=" + numeroMedidor +
                ", periodoId='" + periodoId + '\'' +
                ", periodoName='" + periodoName + '\'' +
                ", nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                ", lecturaAnterior=" + lecturaAnterior +
                ", lecturaActual=" + lecturaActual +
                ", fechaLectura='" + fechaLectura + '\'' +
                ", consumo=" + consumo +
                ", totalMes=" + totalMes +
                ", deudaAnterior=" + deudaAnterior +
                ", numeroMesesDeuda=" + numeroMesesDeuda +
                ", total=" + total +
                ", precioCubo=" + precioCubo +
                ", esTarifaDiferenciado=" + esTarifaDiferenciado +
                ", consumoDiferenciado=" + consumoDiferenciado +
                ", precioDiferenciado=" + precioDiferenciado +
                ", consumoAguaIdLast=" + consumoAguaIdLast +
                '}';
    }
}
