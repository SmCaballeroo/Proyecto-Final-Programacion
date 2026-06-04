package vista;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import excepciones.ItemDuplicadoException;
import modelo.Figura;
import modelo.Item;
import modelo.Juego;
import modelo.Libro;
import servicio.ServicioItem;

/*
 * =====================================================
 * PANEL INVENTARIO
 * =====================================================
 * FUNCIÓN:
 * Administrar los elementos de la colección.
 * =====================================================
 */

public class PanelInventario extends JPanel {

    private ServicioItem servicioItem;

    private JTable tabla;

    private DefaultTableModel modeloTabla;

    private JTextField txtCodigo;
    private JTextField txtTitulo;

    private JComboBox<String> comboTipo;

    private JButton btnAgregar;
    private JButton btnEliminar;
    private JButton btnBuscar;
    private JButton btnEditar;

    /*
     * =====================================================
     * CONSTRUCTOR
     * =====================================================
     */

    public PanelInventario(
            ServicioItem servicioItem) {

        this.servicioItem = servicioItem;

        setLayout(null);

        /*
         * =====================================================
         * CÓDIGO
         * =====================================================
         */

        JLabel lblCodigo =
                new JLabel("Codigo:");

        lblCodigo.setBounds(
                20,
                20,
                80,
                25);

        add(lblCodigo);

        txtCodigo =
                new JTextField();

        txtCodigo.setBounds(
                100,
                20,
                150,
                25);

        add(txtCodigo);

        /*
         * =====================================================
         * TÍTULO
         * =====================================================
         */

        JLabel lblTitulo =
                new JLabel("Titulo:");

        lblTitulo.setBounds(
                20,
                60,
                80,
                25);

        add(lblTitulo);

        txtTitulo =
                new JTextField();

        txtTitulo.setBounds(
                100,
                60,
                150,
                25);

        add(txtTitulo);

        /*
         * =====================================================
         * TIPO
         * =====================================================
         */

        JLabel lblTipo =
                new JLabel("Tipo:");

        lblTipo.setBounds(
                20,
                100,
                80,
                25);

        add(lblTipo);

        comboTipo =
                new JComboBox<>();

        comboTipo.addItem("Libro");
        comboTipo.addItem("Juego");
        comboTipo.addItem("Figura");

        comboTipo.setBounds(
                100,
                100,
                150,
                25);

        add(comboTipo);

        /*
         * =====================================================
         * BOTÓN AGREGAR
         * =====================================================
         */

        btnAgregar =
                new JButton("Agregar");

        btnAgregar.setBounds(
                20,
                150,
                120,
                30);

        add(btnAgregar);

        /*
         * =====================================================
         * BOTÓN ELIMINAR
         * =====================================================
         */

        btnEliminar =
                new JButton("Eliminar");

        btnEliminar.setBounds(
                150,
                150,
                120,
                30);

        add(btnEliminar);

        /*
         * =====================================================
         * BOTÓN BUSCAR
         * =====================================================
         */

        btnBuscar =
                new JButton("Buscar");

        btnBuscar.setBounds(
                20,
                200,
                120,
                30);

        add(btnBuscar);

        /*
         * =====================================================
         * BOTÓN EDITAR
         * =====================================================
         */

        btnEditar =
                new JButton("Editar");

        btnEditar.setBounds(
                150,
                200,
                120,
                30);

        add(btnEditar);

        /*
         * =====================================================
         * TABLA
         * =====================================================
         */

        modeloTabla =
                new DefaultTableModel();

        modeloTabla.addColumn("Codigo");
        modeloTabla.addColumn("Titulo");
        modeloTabla.addColumn("Tipo");

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
         * EVENTO AGREGAR
         * =====================================================
         */

        btnAgregar.addActionListener(e -> {

            try {

                if(txtCodigo.getText().trim().isEmpty()) {

                    JOptionPane.showMessageDialog(
                            null,
                            "Ingrese el código");

                    return;
                }

                if(txtTitulo.getText().trim().isEmpty()) {

                    JOptionPane.showMessageDialog(
                            null,
                            "Ingrese el título");

                    return;
                }

                Item item = null;

                String tipo =
                        comboTipo
                        .getSelectedItem()
                        .toString();

                if(tipo.equals("Libro")) {

                    item =
                            new Libro(
                                    txtCodigo.getText(),
                                    txtTitulo.getText(),
                                    2024,
                                    "Excelente",
                                    50000,
                                    "Autor");
                }

                else if(tipo.equals("Juego")) {

                    item =
                            new Juego(
                                    txtCodigo.getText(),
                                    txtTitulo.getText(),
                                    2024,
                                    "Excelente",
                                    50000,
                                    "PC");
                }

                else {

                    item =
                            new Figura(
                                    txtCodigo.getText(),
                                    txtTitulo.getText(),
                                    2024,
                                    "Excelente",
                                    50000,
                                    "Marvel");
                }

                servicioItem.agregarItem(item);

                servicioItem.guardarDatos();

                actualizarTabla();

                limpiarCampos();

                JOptionPane.showMessageDialog(
                        null,
                        "Item agregado correctamente");

            } catch(ItemDuplicadoException ex) {

                JOptionPane.showMessageDialog(
                        null,
                        ex.getMessage());
            }
        });

        /*
         * =====================================================
         * EVENTO ELIMINAR
         * =====================================================
         */

        btnEliminar.addActionListener(e -> {

            int fila =
                    tabla.getSelectedRow();

            if(fila < 0) {

                JOptionPane.showMessageDialog(
                        null,
                        "Seleccione un item");

                return;
            }

            int respuesta =
                    JOptionPane.showConfirmDialog(
                            null,
                            "¿Desea eliminar el item?",
                            "Confirmación",
                            JOptionPane.YES_NO_OPTION);

            if(respuesta ==
                    JOptionPane.YES_OPTION) {

                String codigo =
                        modeloTabla
                        .getValueAt(fila, 0)
                        .toString();

                servicioItem.eliminarItem(codigo);

                servicioItem.guardarDatos();

                actualizarTabla();

                limpiarCampos();

                JOptionPane.showMessageDialog(
                        null,
                        "Item eliminado correctamente");
            }
        });

        /*
         * =====================================================
         * EVENTO BUSCAR
         * =====================================================
         */

        btnBuscar.addActionListener(e -> {

            String codigo =
                    JOptionPane.showInputDialog(
                            "Ingrese el código");

            if(codigo == null) {

                return;
            }

            Item item =
                    servicioItem
                    .buscarPorCodigo(codigo);

            if(item == null) {

                JOptionPane.showMessageDialog(
                        null,
                        "Item no encontrado");

                return;
            }

            txtCodigo.setText(
                    item.getCodigo());

            txtTitulo.setText(
                    item.getTitulo());

            if(item instanceof Libro) {

                comboTipo.setSelectedItem(
                        "Libro");
            }

            else if(item instanceof Juego) {

                comboTipo.setSelectedItem(
                        "Juego");
            }

            else {

                comboTipo.setSelectedItem(
                        "Figura");
            }
        });

        /*
         * =====================================================
         * EVENTO EDITAR
         * =====================================================
         */

        btnEditar.addActionListener(e -> {

            if(txtCodigo.getText()
                    .trim()
                    .isEmpty()) {

                JOptionPane.showMessageDialog(
                        null,
                        "Busque primero un item");

                return;
            }

            if(txtTitulo.getText()
                    .trim()
                    .isEmpty()) {

                JOptionPane.showMessageDialog(
                        null,
                        "Ingrese un título");

                return;
            }

            boolean actualizado =
                    servicioItem
                    .actualizarItem(
                            txtCodigo.getText(),
                            txtTitulo.getText());

            if(actualizado) {

                servicioItem.guardarDatos();

                actualizarTabla();

                JOptionPane.showMessageDialog(
                        null,
                        "Item actualizado correctamente");
            }
        });

        /*
         * =====================================================
         * EVENTO SELECCIONAR FILA
         * =====================================================
         */

        tabla.getSelectionModel()
                .addListSelectionListener(e -> {

            if(!e.getValueIsAdjusting()) {

                int fila =
                        tabla.getSelectedRow();

                if(fila >= 0) {

                    String codigo =
                            modeloTabla
                            .getValueAt(fila, 0)
                            .toString();

                    Item item =
                            servicioItem
                            .buscarPorCodigo(codigo);

                    if(item != null) {

                        txtCodigo.setText(
                                item.getCodigo());

                        txtTitulo.setText(
                                item.getTitulo());

                        if(item instanceof Libro) {

                            comboTipo.setSelectedItem(
                                    "Libro");
                        }

                        else if(item instanceof Juego) {

                            comboTipo.setSelectedItem(
                                    "Juego");
                        }

                        else {

                            comboTipo.setSelectedItem(
                                    "Figura");
                        }
                    }
                }
            }
        });

        actualizarTabla();
    }

    /*
     * =====================================================
     * ACTUALIZAR TABLA
     * =====================================================
     */

    private void actualizarTabla() {

        modeloTabla.setRowCount(0);

        for(Item item :
                servicioItem.getItems()) {

            modeloTabla.addRow(

                    new Object[] {

                            item.getCodigo(),
                            item.getTitulo(),
                            item.getClass()
                            .getSimpleName()
                    });
        }
    }

    /*
     * =====================================================
     * LIMPIAR CAMPOS
     * =====================================================
     */

    private void limpiarCampos() {

        txtCodigo.setText("");
        txtTitulo.setText("");
        comboTipo.setSelectedIndex(0);
    }
}