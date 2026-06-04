package excepciones;

public class ItemDuplicadoException extends Exception {

    private static final long serialVersionUID = 1L;

    public ItemDuplicadoException(String mensaje) {
        super(mensaje);
    }
}