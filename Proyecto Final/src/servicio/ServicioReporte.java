package servicio;

import java.util.ArrayList;

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
     * SOCIO MÁS ACTIVO
     * =====================================================
     */

    public Socio socioMasActivo(
            ServicioPrestamo servicioPrestamo) {

        Socio socioActivo = null;

        int maxPrestamos = 0;

        for(Prestamo prestamo :
                servicioPrestamo.getPrestamos()) {

            Socio socio =
                    prestamo.getSocio();

            int contador = 0;

            for(Prestamo p :
                    servicioPrestamo.getPrestamos()) {

                if(p.getSocio()
                        .getDocumento()
                        .equals(
                                socio.getDocumento())) {

                    contador++;
                }
            }

            if(contador > maxPrestamos) {

                maxPrestamos = contador;

                socioActivo = socio;
            }
        }

        return socioActivo;
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
     * PRESTAMOS ACTIVOS
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
     * TOTAL LIBROS
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
     * TOTAL JUEGOS
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
     * TOTAL FIGURAS
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
     * PRESTAMOS VENCIDOS
     * =====================================================
     */

    public int prestamosVencidos(
            ServicioPrestamo servicioPrestamo) {

        int contador = 0;

        for(Prestamo prestamo :
                servicioPrestamo.getPrestamos()) {

            if(!prestamo.isDevuelto()
                    &&
                    prestamo.calcularMulta() > 0) {

                contador++;
            }
        }

        return contador;
    }

    /*
     * =====================================================
     * PROMEDIO PRESTAMOS POR SOCIO
     * =====================================================
     */

    public double promedioPrestamosPorSocio(
            ServicioPrestamo servicioPrestamo) {

        if(servicioPrestamo
                .getPrestamos()
                .isEmpty()) {

            return 0;
        }

        ArrayList<String> socios =
                new ArrayList<>();

        for(Prestamo p :
                servicioPrestamo.getPrestamos()) {

            String documento =
                    p.getSocio()
                    .getDocumento();

            if(!socios.contains(documento)) {

                socios.add(documento);
            }
        }

        return (double)
                servicioPrestamo
                .getPrestamos()
                .size()
                /
                socios.size();
    }

    /*
     * =====================================================
     * ITEMS DISPONIBLES
     * =====================================================
     */

    public int itemsDisponibles(
            ServicioItem servicioItem,
            ServicioPrestamo servicioPrestamo) {

        int disponibles = 0;

        for(Item item :
                servicioItem.getItems()) {

            boolean prestado = false;

            for(Prestamo p :
                    servicioPrestamo.getPrestamos()) {

                if(p.getItem().equals(item)
                        &&
                        !p.isDevuelto()) {

                    prestado = true;

                    break;
                }
            }

            if(!prestado) {

                disponibles++;
            }
        }

        return disponibles;
    }
}