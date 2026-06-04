package principal;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import vista.VentanaPrincipal;

/*
 * =====================================================
 * CLASE MAIN
 * =====================================================
 * FUNCIÓN:
 * Punto de entrada principal del sistema.
 *
 * Responsabilidades:
 * 1. Configurar el aspecto visual (Look & Feel).
 * 2. Crear la ventana principal.
 * 3. Mostrar la aplicación al usuario.
 * =====================================================
 */

public class Main {

    /*
     * =====================================================
     * MÉTODO MAIN
     * =====================================================
     * FUNCIÓN:
     * Iniciar la ejecución del programa.
     * =====================================================
     */

    public static void main(String[] args) {

        try {

            /*
             * =============================================
             * LOOK AND FEEL DEL SISTEMA OPERATIVO
             * =============================================
             * Hace que la interfaz tenga el aspecto
             * nativo de Windows.
             * =============================================
             */

            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());

        } catch (ClassNotFoundException e) {

            System.out.println(
                    "Error al cargar apariencia.");

        } catch (InstantiationException e) {

            System.out.println(
                    "Error de instanciación.");

        } catch (IllegalAccessException e) {

            System.out.println(
                    "Acceso denegado.");

        } catch (UnsupportedLookAndFeelException e) {

            System.out.println(
                    "Look And Feel no soportado.");
        }

        /*
         * =============================================
         * CREACIÓN DE LA VENTANA PRINCIPAL
         * =============================================
         */

        VentanaPrincipal ventana =
                new VentanaPrincipal();

        /*
         * =============================================
         * CENTRAR VENTANA
         * =============================================
         */

        ventana.setLocationRelativeTo(null);

        /*
         * =============================================
         * MOSTRAR VENTANA
         * =============================================
         */

        ventana.setVisible(true);
    }
}