package co.edu.udea.compumovil.gr4.lab2activities.co.edu.udea.compumovil.gr4.lab2activities.entities;

import com.orm.SugarRecord;

/**
 * Created by Steven on 06/09/2016.
 */
public class Place extends SugarRecord{

    private String nombre;
    private String descripcion;
    private int puntuación;


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPuntuación() {
        return puntuación;
    }

    public void setPuntuación(int puntuación) {
        this.puntuación = puntuación;
    }

    public Place(String nombre, String descripcion, int puntuación) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.puntuación = puntuación;
    }

    public Place() {
    }
}
