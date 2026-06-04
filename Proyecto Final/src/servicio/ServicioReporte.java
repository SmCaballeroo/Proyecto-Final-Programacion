package servicio;

import modelo.Figura;
import modelo.Item;
import modelo.Juego;
import modelo.Libro;
import modelo.Prestamo;
import modelo.Socio;

/*
 * =====================================================
 * CLASE SERVICIO REPORTE
 * =====================================================
 * FUNCIÓN:
 * Generar estadísticas y métricas.
 * =====================================================
 */

public class ServicioReporte {

    /*
     * =====================================================
     * ITEM MÁS PRESTADO
     * =====================================================
     */

    public Item itemMasPrestado(
            ServicioItem servicioItem) {

        Item mayor = null;

        for(Item item :
                servicioItem.getItems()) {

            if(mayor == null ||
                    item.getVecesPrestado()
                    > mayor.getVecesPrestado()) {

                mayor = item;
            }
        }

        return mayor;
    }

    /*
     * =====================================================
     * SOCIO CON MÁS PRÉSTAMOS
     * =====================================================
     */

    public Socio socioMasActivo(
            ServicioPrestamo servicioPrestamo) {

        Socio mejorSocio = null;

        int maxPrestamos = 0;

        for(Prestamo p :
                servicioPrestamo.getPrestamos()) {

            Socio socio = p.getSocio();

            int contador = 0;

            for(Prestamo otro :
                    servicioPrestamo.getPrestamos()) {

                if(otro.getSocio()
                        .equals(socio)) {

                    contador++;
                }
            }

            if(contador > maxPrestamos) {

                maxPrestamos = contador;

                mejorSocio = socio;
            }
        }

        return mejorSocio;
    }

    /*
     * =====================================================
     * TOTAL MULTAS
     * =====================================================
     */

    public double totalMultas(
            ServicioPrestamo servicioPrestamo) {

        double total = 0;

        for(Prestamo p :
                servicioPrestamo.getPrestamos()) {

            total += p.calcularMulta();
        }

        return total;
    }

    /*
     * =====================================================
     * PRÉSTAMOS ACTIVOS
     * =====================================================
     */

    public int prestamosActivos(
            ServicioPrestamo servicioPrestamo) {

        int contador = 0;

        for(Prestamo p :
                servicioPrestamo.getPrestamos()) {

            if(!p.isDevuelto()) {

                contador++;
            }
        }

        return contador;
    }

    /*
     * =====================================================
     * CONTAR LIBROS
     * =====================================================
     */

    public int totalLibros(
            ServicioItem servicioItem) {

        int contador = 0;

        for(Item item :
                servicioItem.getItems()) {

            if(item instanceof Libro) {

                contador++;
            }
        }

        return contador;
    }

    /*
     * =====================================================
     * CONTAR JUEGOS
     * =====================================================
     */

    public int totalJuegos(
            ServicioItem servicioItem) {

        int contador = 0;

        for(Item item :
                servicioItem.getItems()) {

            if(item instanceof Juego) {

                contador++;
            }
        }

        return contador;
    }

    /*
     * =====================================================
     * CONTAR FIGURAS
     * =====================================================
     */

    public int totalFiguras(
            ServicioItem servicioItem) {

        int contador = 0;

        for(Item item :
                servicioItem.getItems()) {

            if(item instanceof Figura) {

                contador++;
            }
        }

        return contador;
    }

    /*
     * =====================================================
     * PRÉSTAMOS VENCIDOS
     * =====================================================
     */

    public int prestamosVencidos(
            ServicioPrestamo servicioPrestamo) {

        int contador = 0;

        for(Prestamo p :
                servicioPrestamo.getPrestamos()) {

            if(p.calcularMulta() > 0) {

                contador++;
            }
        }

        return contador;
    }
}