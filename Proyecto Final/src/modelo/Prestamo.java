package modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/*
 * =====================================================
 * CLASE PRESTAMO
 * =====================================================
 * FUNCIÓN:
 * Registrar préstamos y calcular multas.
 * =====================================================
 */

public class Prestamo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Item item;
    private Socio socio;

    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;

    private boolean devuelto;

    public Prestamo(Item item,
                     Socio socio,
                     LocalDate fechaPrestamo,
                     LocalDate fechaDevolucion) {

        this.item = item;
        this.socio = socio;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.devuelto = false;
    }

    /*
     * =====================================================
     * CALCULAR MULTA
     * =====================================================
     */

    public double calcularMulta() {

        if(devuelto)
            return 0;

        long diasRetraso =
                ChronoUnit.DAYS.between(
                        fechaDevolucion,
                        LocalDate.now());

        if(diasRetraso <= 0)
            return 0;

        return diasRetraso * 2000;
    }

    public Item getItem() {
        return item;
    }

    public Socio getSocio() {
        return socio;
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    public boolean isDevuelto() {
        return devuelto;
    }

    public void setDevuelto(boolean devuelto) {
        this.devuelto = devuelto;
    }
}