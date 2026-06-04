package servicio;

import java.time.LocalDate;
import java.util.ArrayList;

import excepciones.ItemNoDisponibleException;
import excepciones.LimitePrestamosException;
import modelo.Item;
import modelo.Prestamo;
import modelo.Socio;
import persistencia.ArchivoPrestamos;

/*
 * =====================================================
 * CLASE SERVICIO PRESTAMO
 * =====================================================
 * FUNCIÓN:
 * Gestionar préstamos y devoluciones.
 * =====================================================
 */

public class ServicioPrestamo {

    /*
     * =====================================================
     * CONSTANTE DE NEGOCIO
     * =====================================================
     */

    private static final int LIMITE_PRESTAMOS = 3;

    private ArrayList<Prestamo> prestamos;

    /*
     * =====================================================
     * CONSTRUCTOR
     * =====================================================
     * Carga automáticamente los préstamos guardados.
     * =====================================================
     */

    public ServicioPrestamo() {

        prestamos = ArchivoPrestamos.cargar();
    }

    /*
     * =====================================================
     * REGISTRAR PRÉSTAMO
     * =====================================================
     */

    public void registrarPrestamo(
            Item item,
            Socio socio,
            LocalDate fechaPrestamo,
            LocalDate fechaDevolucion)

            throws ItemNoDisponibleException,
                   LimitePrestamosException {

        if(!itemDisponible(item)) {

            throw new ItemNoDisponibleException(
                    "El item ya está prestado.");
        }

        if(contarPrestamosSocio(socio)
                >= LIMITE_PRESTAMOS) {

            throw new LimitePrestamosException(
                    "El socio alcanzó el límite de préstamos.");
        }

        Prestamo prestamo =
                new Prestamo(
                        item,
                        socio,
                        fechaPrestamo,
                        fechaDevolucion);

        prestamos.add(prestamo);

        item.setVecesPrestado(
                item.getVecesPrestado() + 1);
    }

    /*
     * =====================================================
     * VERIFICAR DISPONIBILIDAD
     * =====================================================
     */

    private boolean itemDisponible(Item item) {

        for(Prestamo p : prestamos) {

            if(p.getItem().equals(item)
                    && !p.isDevuelto()) {

                return false;
            }
        }

        return true;
    }

    /*
     * =====================================================
     * CONTAR PRÉSTAMOS ACTIVOS
     * =====================================================
     */

    private int contarPrestamosSocio(
            Socio socio) {

        int contador = 0;

        for(Prestamo p : prestamos) {

            if(p.getSocio().equals(socio)
                    && !p.isDevuelto()) {

                contador++;
            }
        }

        return contador;
    }

    /*
     * =====================================================
     * DEVOLVER ITEM
     * =====================================================
     */

    public void devolverItem(
            Prestamo prestamo) {

        prestamo.setDevuelto(true);
    }

    /*
     * =====================================================
     * GUARDAR DATOS
     * =====================================================
     */

    public void guardarDatos() {

        ArchivoPrestamos.guardar(prestamos);
    }

    /*
     * =====================================================
     * GETTERS Y SETTERS
     * =====================================================
     */

    public ArrayList<Prestamo> getPrestamos() {
        return prestamos;
    }

    public void setPrestamos(
            ArrayList<Prestamo> prestamos) {

        this.prestamos = prestamos;
    }
}