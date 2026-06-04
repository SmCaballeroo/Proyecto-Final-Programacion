package persistencia;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import modelo.Prestamo;

/*
 * =====================================================
 * CLASE ARCHIVO PRESTAMOS
 * =====================================================
 * FUNCIÓN:
 * Guardar y cargar préstamos.
 * =====================================================
 */

public class ArchivoPrestamos {

    private static final String ARCHIVO =
            "prestamos.dat";

    /*
     * =====================================================
     * GUARDAR
     * =====================================================
     */

    public static void guardar(
            ArrayList<Prestamo> prestamos) {

        try {

            ObjectOutputStream salida =
                    new ObjectOutputStream(
                            new FileOutputStream(
                                    ARCHIVO));

            salida.writeObject(prestamos);

            salida.close();

        } catch (Exception e) {

            System.out.println(
                    "Error al guardar préstamos.");
        }
    }

    /*
     * =====================================================
     * CARGAR
     * =====================================================
     */

    @SuppressWarnings("unchecked")
    public static ArrayList<Prestamo> cargar() {

        try {

            ObjectInputStream entrada =
                    new ObjectInputStream(
                            new FileInputStream(
                                    ARCHIVO));

            ArrayList<Prestamo> prestamos =
                    (ArrayList<Prestamo>)
                            entrada.readObject();

            entrada.close();

            return prestamos;

        } catch (Exception e) {

            return new ArrayList<>();
        }
    }
}