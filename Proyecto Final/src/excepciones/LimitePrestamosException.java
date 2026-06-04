package excepciones;

public class LimitePrestamosException extends Exception {

    private static final long serialVersionUID = 1L;

    public LimitePrestamosException(String mensaje) {
        super(mensaje);
    }
}