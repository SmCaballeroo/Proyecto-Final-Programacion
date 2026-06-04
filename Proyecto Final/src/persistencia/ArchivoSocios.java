package persistencia;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import modelo.Socio;

/*
 * =====================================================
 * CLASE ARCHIVO SOCIOS
 * =====================================================
 * FUNCIÓN:
 * Guardar y cargar socios.
 * =====================================================
 */

public class ArchivoSocios {

    private static final String ARCHIVO =
            "socios.dat";

    /*
     * =====================================================
     * GUARDAR
     * =====================================================
     */

    public static void guardar(
            ArrayList<Socio> socios) {

        try {

            ObjectOutputStream salida =
                    new ObjectOutputStream(
                            new FileOutputStream(
                                    ARCHIVO));

            salida.writeObject(socios);

            salida.close();

        } catch (Exception e) {

            System.out.println(
                    "Error al guardar socios.");
        }
    }

    /*
     * =====================================================
     * CARGAR
     * =====================================================
     */

    @SuppressWarnings("unchecked")
    public static ArrayList<Socio> cargar() {

        try {

            ObjectInputStream entrada =
                    new ObjectInputStream(
                            new FileInputStream(
                                    ARCHIVO));

            ArrayList<Socio> socios =
                    (ArrayList<Socio>)
                            entrada.readObject();

            entrada.close();

            return socios;

        } catch (Exception e) {

            return new ArrayList<>();
        }
    }
}