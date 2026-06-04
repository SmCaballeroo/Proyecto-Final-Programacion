package utilidades;

/*
 * =====================================================
 * CLASE VALIDADOR
 * =====================================================
 * FUNCIÓN:
 * Validaciones reutilizables del sistema.
 * =====================================================
 */

public class Validador {

    public static boolean textoVacio(String texto) {

        return texto == null ||
               texto.trim().isEmpty();
    }

    public static boolean valorPositivo(double valor) {

        return valor > 0;
    }
}