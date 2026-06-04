package excepciones;

public class ItemNoDisponibleException extends Exception {

    private static final long serialVersionUID = 1L;

    public ItemNoDisponibleException(String mensaje) {
        super(mensaje);
    }
}