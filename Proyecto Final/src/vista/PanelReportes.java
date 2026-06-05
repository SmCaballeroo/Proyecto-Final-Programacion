package vista;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import modelo.Item;
import modelo.Socio;
import servicio.ServicioItem;
import servicio.ServicioPrestamo;
import servicio.ServicioReporte;

/*
 * =====================================================
 * PANEL REPORTES
 * =====================================================
 * FUNCIÓN:
 * Mostrar estadísticas y reportes del sistema.
 * =====================================================
 */

public class PanelReportes extends JPanel {

    private static final long serialVersionUID = 1L;

    /*
     * =====================================================
     * SERVICIOS
     * =====================================================
     */

    private ServicioItem servicioItem;

    private ServicioPrestamo servicioPrestamo;

    /*
     * =====================================================
     * COMPONENTES
     * =====================================================
     */

    private JTextArea areaReporte;

    private JButton btnGenerar;
    private JButton btnExportar;

    /*
     * =====================================================
     * CONSTRUCTOR
     * =====================================================
     */

    public PanelReportes(
            ServicioItem servicioItem,
            ServicioPrestamo servicioPrestamo) {

        this.servicioItem = servicioItem;
        this.servicioPrestamo = servicioPrestamo;

        setLayout(null);

        /*
         * =====================================================
         * BOTÓN GENERAR
         * =====================================================
         */

        btnGenerar =
                new JButton(
                        "Generar Reporte");

        btnGenerar.setBounds(
                20,
                20,
                180,
                30);

        add(btnGenerar);

        /*
         * =====================================================
         * BOTÓN EXPORTAR
         * =====================================================
         */

        btnExportar =
                new JButton(
                        "Exportar TXT");

        btnExportar.setBounds(
                220,
                20,
                180,
                30);

        add(btnExportar);

        /*
         * =====================================================
         * ÁREA DE TEXTO
         * =====================================================
         */

        areaReporte =
                new JTextArea();

        areaReporte.setEditable(false);

        JScrollPane scroll =
                new JScrollPane(
                        areaReporte);

        scroll.setBounds(
                20,
                70,
                900,
                500);

        add(scroll);

        /*
         * =====================================================
         * EVENTO GENERAR REPORTE
         * =====================================================
         */

        btnGenerar.addActionListener(e -> {

            generarReporte();
        });

        /*
         * =====================================================
         * EVENTO EXPORTAR TXT
         * =====================================================
         */

        btnExportar.addActionListener(e -> {

            exportarTXT();
        });
    }

    /*
     * =====================================================
     * GENERAR REPORTE
     * =====================================================
     */

    private void generarReporte() {

        ServicioReporte reporte =
                new ServicioReporte();

        String texto = "";

        texto +=
                "========== REPORTE GENERAL ==========\n\n";

        texto +=
                "Valor Total Inventario: $"
                +
                servicioItem
                .calcularValorInventario();

        texto += "\n\n";

        texto +=
                "Prestamos Activos: "
                +
                reporte.prestamosActivos(
                        servicioPrestamo);

        texto += "\n\n";

        texto +=
                "Cantidad de Items: "
                +
                servicioItem
                .getItems()
                .size();

        texto += "\n\n";

        texto +=
                "Cantidad de Prestamos Registrados: "
                +
                servicioPrestamo
                .getPrestamos()
                .size();

        texto += "\n\n";

        texto +=
                "Promedio Prestamos por Socio: "
                +
                reporte.promedioPrestamosPorSocio(
                        servicioPrestamo);

        texto += "\n\n";

        texto +=
                "Items Disponibles: "
                +
                reporte.itemsDisponibles(
                        servicioItem,
                        servicioPrestamo);

        texto += "\n\n";

        texto +=
                "Cantidad de Libros: "
                +
                reporte.totalLibros(
                        servicioItem);

        texto += "\n\n";

        texto +=
                "Cantidad de Juegos: "
                +
                reporte.totalJuegos(
                        servicioItem);

        texto += "\n\n";

        texto +=
                "Cantidad de Figuras: "
                +
                reporte.totalFiguras(
                        servicioItem);

        texto += "\n\n";

        texto +=
                "Prestamos Vencidos: "
                +
                reporte.prestamosVencidos(
                        servicioPrestamo);

        texto += "\n\n";

        texto +=
                "Total Multas: $"
                +
                reporte.totalMultas(
                        servicioPrestamo);

        texto += "\n\n";

        Item masPrestado =
                reporte.itemMasPrestado(
                        servicioItem);

        if(masPrestado != null) {

            texto +=
                    "Item Mas Prestado: "
                    +
                    masPrestado.getTitulo();
        }
        else {

            texto +=
                    "Item Mas Prestado: No disponible";
        }

        texto += "\n\n";

        Socio socioActivo =
                reporte.socioMasActivo(
                        servicioPrestamo);

        if(socioActivo != null) {

            texto +=
                    "Socio Mas Activo: "
                    +
                    socioActivo.getNombre();
        }
        else {

            texto +=
                    "Socio Mas Activo: No disponible";
        }

        areaReporte.setText(texto);
    }

    /*
     * =====================================================
     * EXPORTAR REPORTE TXT
     * =====================================================
     */

    private void exportarTXT() {

        try {

            BufferedWriter writer =
                    new BufferedWriter(
                            new FileWriter(
                                    "reporte.txt"));

            writer.write(
                    areaReporte.getText());

            writer.close();

            JOptionPane.showMessageDialog(
                    null,
                    "Reporte exportado correctamente");

        } catch(IOException e) {

            JOptionPane.showMessageDialog(
                    null,
                    "Error al exportar reporte");
        }
    }
}