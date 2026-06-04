package vista;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import servicio.ServicioItem;
import servicio.ServicioPrestamo;
import servicio.ServicioReporte;
import servicio.ServicioSocio;

/*
 * =====================================================
 * VENTANA PRINCIPAL
 * =====================================================
 * FUNCIÓN:
 * Contener todos los paneles del sistema.
 * =====================================================
 */

public class VentanaPrincipal extends JFrame {

    private static final long serialVersionUID = 1L;

    private JTabbedPane pestañas;

    /*
     * =====================================================
     * SERVICIOS COMPARTIDOS
     * =====================================================
     */

    private ServicioItem servicioItem;
    private ServicioSocio servicioSocio;
    private ServicioPrestamo servicioPrestamo;
    private ServicioReporte servicioReporte;

    public VentanaPrincipal() {

        /*
         * =====================================================
         * CONFIGURACIÓN DE VENTANA
         * =====================================================
         */

        setTitle("Gestor de Colecciones y Prestamos");

        setSize(1000, 700);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /*
         * =====================================================
         * INICIALIZAR SERVICIOS
         * =====================================================
         */

        servicioItem = new ServicioItem();

        servicioSocio = new ServicioSocio();

        servicioPrestamo = new ServicioPrestamo();

        servicioReporte = new ServicioReporte();

        /*
         * =====================================================
         * CREAR TABBEDPANE
         * =====================================================
         */

        pestañas = new JTabbedPane();

        /*
         * =====================================================
         * AGREGAR PANELES
         * =====================================================
         */

        pestañas.addTab(
                "Inventario",
                new PanelInventario(servicioItem));

        pestañas.addTab(
                "Socios",
                new PanelSocios(servicioSocio));

        pestañas.addTab(
                "Prestamos",
                new PanelPrestamos(
                        servicioItem,
                        servicioSocio,
                        servicioPrestamo));

        pestañas.addTab(
                "Reportes",
                new PanelReportes(
                        servicioItem,
                        servicioPrestamo));

        add(pestañas);
    }
}