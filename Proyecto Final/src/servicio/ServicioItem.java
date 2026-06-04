package servicio;

import java.util.ArrayList;

import excepciones.ItemDuplicadoException;
import modelo.Item;
import persistencia.ArchivoItems;

/*
 * =====================================================
 * CLASE SERVICIO ITEM
 * =====================================================
 * FUNCIÓN:
 * Gestionar todos los elementos de la colección.
 * =====================================================
 */

public class ServicioItem {

    /*
     * =====================================================
     * LISTA PRINCIPAL DE ITEMS
     * =====================================================
     */

    private ArrayList<Item> items;

    /*
     * =====================================================
     * CONSTRUCTOR
     * =====================================================
     * Carga automáticamente los items guardados.
     * =====================================================
     */

    public ServicioItem() {

        items = ArchivoItems.cargar();
    }

    /*
     * =====================================================
     * AGREGAR ITEM
     * =====================================================
     */

    public void agregarItem(Item item)
            throws ItemDuplicadoException {

        if(buscarPorCodigo(item.getCodigo()) != null) {

            throw new ItemDuplicadoException(
                    "Ya existe un item con ese código.");
        }

        items.add(item);
    }

    /*
     * =====================================================
     * BUSCAR ITEM
     * =====================================================
     */

    public Item buscarPorCodigo(String codigo) {

        for(Item item : items) {

            if(item.getCodigo()
                    .equalsIgnoreCase(codigo)) {

                return item;
            }
        }

        return null;
    }

    /*
     * =====================================================
     * ELIMINAR ITEM
     * =====================================================
     */

    public boolean eliminarItem(String codigo) {

        Item item = buscarPorCodigo(codigo);

        if(item != null) {

            items.remove(item);

            return true;
        }

        return false;
    }

    /*
     * =====================================================
     * CALCULAR VALOR INVENTARIO
     * =====================================================
     */

    public double calcularValorInventario() {

        double total = 0;

        for(Item item : items) {

            total += item.getValorEstimado();
        }

        return total;
    }

    /*
     * =====================================================
     * GUARDAR DATOS
     * =====================================================
     */

    public void guardarDatos() {

        ArchivoItems.guardar(items);
    }
    /*
     * =====================================================
     * ACTUALIZAR ITEM
     * =====================================================
     */

    public boolean actualizarItem(
            String codigo,
            String nuevoTitulo) {

        Item item =
                buscarPorCodigo(codigo);

        if(item != null) {

            item.setTitulo(nuevoTitulo);

            return true;
        }

        return false;
    }

    /*
     * =====================================================
     * GETTERS Y SETTERS
     * =====================================================
     */

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }
}