package modelo;

import java.io.Serializable;
import java.time.LocalDate;

/*
 * =====================================================
 * CLASE MANTENIMIENTO
 * =====================================================
 * FUNCIÓN:
 * Registrar mantenimientos y reparaciones.
 * =====================================================
 */

public class Mantenimiento implements Serializable {

    private static final long serialVersionUID = 1L;

    private Item item;
    private String descripcion;
    private LocalDate fecha;
    private boolean realizado;

    public Mantenimiento(Item item,
                         String descripcion,
                         LocalDate fecha,
                         boolean realizado) {

        this.item = item;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.realizado = realizado;
    }

    public Item getItem() {
        return item;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public boolean isRealizado() {
        return realizado;
    }

    public void setRealizado(boolean realizado) {
        this.realizado = realizado;
    }
}