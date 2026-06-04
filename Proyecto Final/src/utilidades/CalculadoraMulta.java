package utilidades;

/*
 * =====================================================
 * CLASE CALCULADORA MULTA
 * =====================================================
 * FUNCIÓN:
 * Centralizar el cálculo de multas.
 * =====================================================
 */

public class CalculadoraMulta {

    private static final double MULTA_DIA = 2000;

    public static double calcular(long diasRetraso) {

        if(diasRetraso <= 0) {
            return 0;
        }

        return diasRetraso * MULTA_DIA;
    }
}