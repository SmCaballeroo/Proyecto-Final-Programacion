package vista;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
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

    private JButton btnSalir;

    /*
     * =====================================================
     * PANEL COMPARTIDO
     * =====================================================
     */

    private PanelPrestamos panelPrestamos;

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

        setLayout(null);

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
         * CREAR PANEL PRESTAMOS
         * =====================================================
         */

        panelPrestamos =
                new PanelPrestamos(
                        servicioItem,
                        servicioSocio,
                        servicioPrestamo);

        /*
         * =====================================================
         * CREAR TABBEDPANE
         * =====================================================
         */

        pestañas = new JTabbedPane();

        pestañas.setBounds(
                0,
                0,
                980,
                600);

        /*
         * =====================================================
         * AGREGAR PANELES
         * =====================================================
         */

        pestañas.addTab(
                "Inventario",
                new PanelInventario(
                        servicioItem,
                        panelPrestamos));

        pestañas.addTab(
                "Socios",
                new PanelSocios(
                        servicioSocio,
                        panelPrestamos));

        pestañas.addTab(
                "Prestamos",
                panelPrestamos);

        pestañas.addTab(
                "Reportes",
                new PanelReportes(
                        servicioItem,
                        servicioPrestamo));

        add(pestañas);

        /*
         * =====================================================
         * BOTÓN SALIR
         * =====================================================
         */

        btnSalir =
                new JButton(
                        "Guardar y Salir");

        btnSalir.setBounds(
                800,
                610,
                150,
                30);

        add(btnSalir);

        /*
         * =====================================================
         * EVENTO SALIR
         * =====================================================
         */

        btnSalir.addActionListener(e -> {

            int respuesta =
                    JOptionPane.showConfirmDialog(

                            null,

                            "¿Desea guardar los datos y cerrar el sistema?",

                            "Salir",

                            JOptionPane.YES_NO_OPTION);

            if(respuesta ==
                    JOptionPane.YES_OPTION) {

                servicioItem.guardarDatos();

                servicioSocio.guardarDatos();

                servicioPrestamo.guardarDatos();

                System.exit(0);
            }
        });
    }
}