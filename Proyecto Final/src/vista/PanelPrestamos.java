package vista;

import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import modelo.Item;
import modelo.Prestamo;
import modelo.Socio;
import servicio.ServicioItem;
import servicio.ServicioPrestamo;
import servicio.ServicioSocio;

public class PanelPrestamos extends JPanel {

    private static final long serialVersionUID = 1L;

    /*
     * =====================================================
     * SERVICIOS
     * =====================================================
     */

    private ServicioItem servicioItem;
    private ServicioSocio servicioSocio;
    private ServicioPrestamo servicioPrestamo;

    /*
     * =====================================================
     * COMPONENTES
     * =====================================================
     */

    private JTable tabla;

    private DefaultTableModel modeloTabla;

    private JComboBox<Item> comboItems;

    private JComboBox<Socio> comboSocios;

    private JComboBox<String> comboFiltro;

    private JButton btnPrestar;
    private JButton btnDevolver;
    private JButton btnRenovar;

    /*
     * =====================================================
     * CONSTRUCTOR
     * =====================================================
     */

    public PanelPrestamos(
            ServicioItem servicioItem,
            ServicioSocio servicioSocio,
            ServicioPrestamo servicioPrestamo) {

        this.servicioItem = servicioItem;
        this.servicioSocio = servicioSocio;
        this.servicioPrestamo = servicioPrestamo;

        setLayout(null);

        /*
         * =====================================================
         * ITEM
         * =====================================================
         */

        JLabel lblItem =
                new JLabel("Item:");

        lblItem.setBounds(
                20,
                20,
                100,
                25);

        add(lblItem);

        comboItems =
                new JComboBox<>();

        comboItems.setBounds(
                100,
                20,
                180,
                25);

        add(comboItems);

        /*
         * =====================================================
         * SOCIO
         * =====================================================
         */

        JLabel lblSocio =
                new JLabel("Socio:");

        lblSocio.setBounds(
                20,
                60,
                100,
                25);

        add(lblSocio);

        comboSocios =
                new JComboBox<>();

        comboSocios.setBounds(
                100,
                60,
                180,
                25);

        add(comboSocios);

        /*
         * =====================================================
         * BOTONES
         * =====================================================
         */

        btnPrestar =
                new JButton("Prestar");

        btnPrestar.setBounds(
                20,
                120,
                120,
                30);

        add(btnPrestar);

        btnDevolver =
                new JButton("Devolver");

        btnDevolver.setBounds(
                160,
                120,
                120,
                30);

        add(btnDevolver);

        btnRenovar =
                new JButton("Renovar");

        btnRenovar.setBounds(
                90,
                170,
                120,
                30);

        add(btnRenovar);

        /*
         * =====================================================
         * FILTRO
         * =====================================================
         */

        JLabel lblFiltro =
                new JLabel("Filtro:");

        lblFiltro.setBounds(
                20,
                220,
                100,
                25);

        add(lblFiltro);

        comboFiltro =
                new JComboBox<>();

        comboFiltro.addItem("Todos");
        comboFiltro.addItem("Activos");
        comboFiltro.addItem("Vencidos");
        comboFiltro.addItem("Devueltos");

        comboFiltro.setBounds(
                100,
                220,
                180,
                25);

        add(comboFiltro);

        comboFiltro.addActionListener(e ->
                actualizarTabla());

        /*
         * =====================================================
         * TABLA
         * =====================================================
         */

        modeloTabla =
                new DefaultTableModel();

        modeloTabla.addColumn("Item");
        modeloTabla.addColumn("Socio");
        modeloTabla.addColumn("Prestamo");
        modeloTabla.addColumn("Devolucion");
        modeloTabla.addColumn("Estado");
        modeloTabla.addColumn("Multa");

        tabla =
                new JTable(modeloTabla);

        JScrollPane scroll =
                new JScrollPane(tabla);

        scroll.setBounds(
                320,
                20,
                620,
                550);

        add(scroll);

        /*
         * =====================================================
         * EVENTO PRESTAR
         * =====================================================
         */

        btnPrestar.addActionListener(e -> {

            try {

                Item item =
                        (Item) comboItems.getSelectedItem();

                Socio socio =
                        (Socio) comboSocios.getSelectedItem();

                if(item == null || socio == null) {

                    JOptionPane.showMessageDialog(
                            null,
                            "Debe seleccionar un item y un socio");

                    return;
                }

                servicioPrestamo.registrarPrestamo(
                        item,
                        socio,
                        LocalDate.now(),
                        LocalDate.now().plusDays(7));

                servicioPrestamo.guardarDatos();

                actualizarTabla();

                JOptionPane.showMessageDialog(
                        null,
                        "Préstamo registrado correctamente");

            } catch(Exception ex) {

                JOptionPane.showMessageDialog(
                        null,
                        ex.getMessage());
            }
        });

        /*
         * =====================================================
         * EVENTO DEVOLVER
         * =====================================================
         */

        btnDevolver.addActionListener(e -> {

            int fila =
                    tabla.getSelectedRow();

            if(fila < 0) {

                JOptionPane.showMessageDialog(
                        null,
                        "Seleccione un préstamo");

                return;
            }

            Prestamo prestamo =
                    servicioPrestamo
                    .getPrestamos()
                    .get(fila);

            if(prestamo.isDevuelto()) {

                JOptionPane.showMessageDialog(
                        null,
                        "El préstamo ya fue devuelto");

                return;
            }

            int respuesta =
                    JOptionPane.showConfirmDialog(
                            null,
                            "¿Desea registrar la devolución?",
                            "Confirmación",
                            JOptionPane.YES_NO_OPTION);

            if(respuesta ==
                    JOptionPane.YES_OPTION) {

                servicioPrestamo
                        .devolverItem(prestamo);

                servicioPrestamo
                        .guardarDatos();

                actualizarTabla();

                JOptionPane.showMessageDialog(
                        null,
                        "Devolución registrada correctamente");
            }
        });

        /*
         * =====================================================
         * EVENTO RENOVAR
         * =====================================================
         */

        btnRenovar.addActionListener(e -> {

            int fila =
                    tabla.getSelectedRow();

            if(fila < 0) {

                JOptionPane.showMessageDialog(
                        null,
                        "Seleccione un préstamo");

                return;
            }

            Prestamo prestamo =
                    servicioPrestamo
                    .getPrestamos()
                    .get(fila);

            if(prestamo.isDevuelto()) {

                JOptionPane.showMessageDialog(
                        null,
                        "No puede renovarse un préstamo devuelto");

                return;
            }

            if(prestamo.calcularMulta() > 0) {

                JOptionPane.showMessageDialog(
                        null,
                        "No puede renovarse un préstamo vencido");

                return;
            }

            prestamo.renovarPrestamo(7);

            servicioPrestamo.guardarDatos();

            actualizarTabla();

            JOptionPane.showMessageDialog(
                    null,
                    "Préstamo renovado por 7 días");
        });

        /*
         * =====================================================
         * CARGAR DATOS
         * =====================================================
         */

        cargarCombos();

        actualizarTabla();
    }

    /*
     * =====================================================
     * CARGAR COMBOS
     * =====================================================
     */

    private void cargarCombos() {

        comboItems.removeAllItems();

        for(Item item :
                servicioItem.getItems()) {

            comboItems.addItem(item);
        }

        comboSocios.removeAllItems();

        for(Socio socio :
                servicioSocio.getSocios()) {

            comboSocios.addItem(socio);
        }
    }
    public void refrescarCombos() {

        cargarCombos();
    }

    /*
     * =====================================================
     * ACTUALIZAR TABLA
     * =====================================================
     */

    private void actualizarTabla() {

        modeloTabla.setRowCount(0);

        String filtro =
                comboFiltro.getSelectedItem()
                .toString();

        for(Prestamo prestamo :
                servicioPrestamo.getPrestamos()) {

            String estado;

            if(prestamo.isDevuelto()) {

                estado = "Devuelto";
            }

            else if(prestamo.calcularMulta() > 0) {

                estado = "Vencido";
            }

            else {

                estado = "Activo";
            }

            if(filtro.equals("Activos")
                    && !estado.equals("Activo")) {

                continue;
            }

            if(filtro.equals("Vencidos")
                    && !estado.equals("Vencido")) {

                continue;
            }

            if(filtro.equals("Devueltos")
                    && !estado.equals("Devuelto")) {

                continue;
            }

            modeloTabla.addRow(

                    new Object[] {

                            prestamo.getItem()
                                    .getTitulo(),

                            prestamo.getSocio()
                                    .getNombre(),

                            prestamo.getFechaPrestamo(),

                            prestamo.getFechaDevolucion(),

                            estado,

                            "$" +
                            prestamo.calcularMulta()
                    });
         
            
        }
    }
    /*
     * =====================================================
     * RECARGAR COMBOS
     * =====================================================
     */

    public void recargarCombos() {

        cargarCombos();
    }
}