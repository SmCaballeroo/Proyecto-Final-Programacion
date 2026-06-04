package persistencia;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import modelo.Item;

/*
 * =====================================================
 * CLASE ARCHIVO ITEMS
 * =====================================================
 * FUNCIÓN:
 * Guardar y cargar los items de la colección.
 * =====================================================
 */

public class ArchivoItems {

    /*
     * =====================================================
     * NOMBRE DEL ARCHIVO
     * =====================================================
     */

    private static final String ARCHIVO =
            "items.dat";

    /*
     * =====================================================
     * GUARDAR ITEMS
     * =====================================================
     */

    public static void guardar(
            ArrayList<Item> items) {

        try {

            ObjectOutputStream salida =
                    new ObjectOutputStream(
                            new FileOutputStream(
                                    ARCHIVO));

            salida.writeObject(items);

            salida.close();

        } catch (Exception e) {

            System.out.println(
                    "Error al guardar items.");
        }
    }

    /*
     * =====================================================
     * CARGAR ITEMS
     * =====================================================
     */

    @SuppressWarnings("unchecked")
    public static ArrayList<Item> cargar() {

        try {

            ObjectInputStream entrada =
                    new ObjectInputStream(
                            new FileInputStream(
                                    ARCHIVO));

            ArrayList<Item> items =
                    (ArrayList<Item>)
                            entrada.readObject();

            entrada.close();

            return items;

        } catch (Exception e) {

            return new ArrayList<>();
        }
    }
}