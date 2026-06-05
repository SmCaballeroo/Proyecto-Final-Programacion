package vista;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import modelo.Socio;
import servicio.ServicioSocio;

/*
 * =====================================================
 * PANEL SOCIOS
 * =====================================================
 * FUNCIÓN:
 * Administrar los socios registrados.
 * =====================================================
 */

public class PanelSocios extends JPanel {

    private ServicioSocio servicioSocio;
    private PanelPrestamos panelPrestamos;

    /*
     * =====================================================
     * COMPONENTES
     * =====================================================
     */

    private JTable tabla;

    private DefaultTableModel modeloTabla;

    private JTextField txtDocumento;
    private JTextField txtNombre;
    private JTextField txtTelefono;
    private JTextField txtCorreo;

    private JButton btnRegistrar;
    private JButton btnBuscar;
    private JButton btnEditar;
    private JButton btnEliminar;

    /*
     * =====================================================
     * CONSTRUCTOR
     * =====================================================
     */

    public PanelSocios(
            ServicioSocio servicioSocio,
            PanelPrestamos panelPrestamos) {

        this.servicioSocio = servicioSocio;
        this.panelPrestamos = panelPrestamos;

        setLayout(null);

        /*
         * =====================================================
         * DOCUMENTO
         * =====================================================
         */

        JLabel lblDocumento =
                new JLabel("Documento:");

        lblDocumento.setBounds(
                20,
                20,
                100,
                25);

        add(lblDocumento);

        txtDocumento =
                new JTextField();

        txtDocumento.setBounds(
                120,
                20,
                150,
                25);

        add(txtDocumento);

        /*
         * =====================================================
         * NOMBRE
         * =====================================================
         */

        JLabel lblNombre =
                new JLabel("Nombre:");

        lblNombre.setBounds(
                20,
                60,
                100,
                25);

        add(lblNombre);

        txtNombre =
                new JTextField();

        txtNombre.setBounds(
                120,
                60,
                150,
                25);

        add(txtNombre);

        /*
         * =====================================================
         * TELEFONO
         * =====================================================
         */

        JLabel lblTelefono =
                new JLabel("Telefono:");

        lblTelefono.setBounds(
                20,
                100,
                100,
                25);

        add(lblTelefono);

        txtTelefono =
                new JTextField();

        txtTelefono.setBounds(
                120,
                100,
                150,
                25);

        add(txtTelefono);

        /*
         * =====================================================
         * CORREO
         * =====================================================
         */

        JLabel lblCorreo =
                new JLabel("Correo:");

        lblCorreo.setBounds(
                20,
                140,
                100,
                25);

        add(lblCorreo);

        txtCorreo =
                new JTextField();

        txtCorreo.setBounds(
                120,
                140,
                150,
                25);

        add(txtCorreo);

        /*
         * =====================================================
         * BOTON REGISTRAR
         * =====================================================
         */

        btnRegistrar =
                new JButton("Registrar");

        btnRegistrar.setBounds(
                20,
                190,
                120,
                30);

        add(btnRegistrar);

        /*
         * =====================================================
         * BOTON BUSCAR
         * =====================================================
         */

        btnBuscar =
                new JButton("Buscar");

        btnBuscar.setBounds(
                150,
                190,
                120,
                30);

        add(btnBuscar);

        /*
         * =====================================================
         * BOTON EDITAR
         * =====================================================
         */

        btnEditar =
                new JButton("Editar");

        btnEditar.setBounds(
                20,
                240,
                120,
                30);

        add(btnEditar);

        /*
         * =====================================================
         * BOTON ELIMINAR
         * =====================================================
         */

        btnEliminar =
                new JButton("Eliminar");

        btnEliminar.setBounds(
                150,
                240,
                120,
                30);

        add(btnEliminar);

        /*
         * =====================================================
         * TABLA
         * =====================================================
         */

        modeloTabla =
                new DefaultTableModel();

        modeloTabla.addColumn("Documento");
        modeloTabla.addColumn("Nombre");

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
         * EVENTO REGISTRAR
         * =====================================================
         */

        btnRegistrar.addActionListener(e -> {

            String documento =
                    txtDocumento.getText().trim();

            String nombre =
                    txtNombre.getText().trim();

            String telefono =
                    txtTelefono.getText().trim();

            String correo =
                    txtCorreo.getText().trim();

            if(documento.isEmpty()) {

                JOptionPane.showMessageDialog(
                        null,
                        "Ingrese el documento");

                return;
            }

            if(!documento.matches("\\d+")) {

                JOptionPane.showMessageDialog(
                        null,
                        "El documento solo debe contener números positivos");

                return;
            }

            if(nombre.isEmpty()) {

                JOptionPane.showMessageDialog(
                        null,
                        "Ingrese el nombre");

                return;
            }

            if(!nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {

                JOptionPane.showMessageDialog(
                        null,
                        "El nombre no puede contener números");

                return;
            }

            if(telefono.isEmpty()) {

                JOptionPane.showMessageDialog(
                        null,
                        "Ingrese el teléfono");

                return;
            }

            if(correo.isEmpty()) {

                JOptionPane.showMessageDialog(
                        null,
                        "Ingrese el correo");

                return;
            }

            Socio socio =
                    new Socio(
                            documento,
                            nombre,
                            telefono,
                            correo);

            boolean agregado =
                    servicioSocio.agregarSocio(socio);

            if(!agregado) {

                JOptionPane.showMessageDialog(
                        null,
                        "Ya existe un socio con ese documento");

                return;
            }

            servicioSocio.guardarDatos();

            actualizarTabla();

            panelPrestamos.recargarCombos();

            limpiarCampos();
            JOptionPane.showMessageDialog(
                    null,
                    "Socio registrado correctamente");
        });
        /*
         * =====================================================
         * EVENTO BUSCAR
         * =====================================================
         */

        btnBuscar.addActionListener(e -> {

            String documento =
                    JOptionPane.showInputDialog(
                            "Ingrese el documento");

            if(documento == null) {

                return;
            }

            Socio socio =
                    servicioSocio
                    .buscarPorDocumento(documento);

            if(socio == null) {

                JOptionPane.showMessageDialog(
                        null,
                        "Socio no encontrado");

                return;
            }

            txtDocumento.setText(
                    socio.getDocumento());

            txtNombre.setText(
                    socio.getNombre());

            txtTelefono.setText(
                    socio.getTelefono());

            txtCorreo.setText(
                    socio.getCorreo());
        });

        /*
         * =====================================================
         * EVENTO EDITAR
         * =====================================================
         */

        btnEditar.addActionListener(e -> {

            if(txtDocumento.getText()
                    .trim()
                    .isEmpty()) {

                JOptionPane.showMessageDialog(
                        null,
                        "Primero busque un socio");

                return;
            }

            boolean actualizado =
                    servicioSocio
                    .actualizarSocio(
                            txtDocumento.getText(),
                            txtNombre.getText(),
                            txtTelefono.getText(),
                            txtCorreo.getText());

            if(actualizado) {

                servicioSocio.guardarDatos();

                actualizarTabla();

                JOptionPane.showMessageDialog(
                        null,
                        "Socio actualizado correctamente");
            }
        });

        /*
         * =====================================================
         * EVENTO ELIMINAR
         * =====================================================
         */

        btnEliminar.addActionListener(e -> {

            String documento =
                    txtDocumento.getText();

            if(documento.isEmpty()) {

                JOptionPane.showMessageDialog(
                        null,
                        "Seleccione o busque un socio");

                return;
            }

            int respuesta =
                    JOptionPane.showConfirmDialog(
                            null,
                            "¿Desea eliminar el socio?",
                            "Confirmación",
                            JOptionPane.YES_NO_OPTION);

            if(respuesta ==
                    JOptionPane.YES_OPTION) {

                servicioSocio.eliminarSocio(documento);

                servicioSocio.guardarDatos();

                actualizarTabla();

                panelPrestamos.recargarCombos();

                limpiarCampos();

                JOptionPane.showMessageDialog(
                        null,
                        "Socio eliminado correctamente");
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

                    String documento =
                            modeloTabla
                            .getValueAt(fila, 0)
                            .toString();

                    Socio socio =
                            servicioSocio
                            .buscarPorDocumento(documento);

                    if(socio != null) {

                        txtDocumento.setText(
                                socio.getDocumento());

                        txtNombre.setText(
                                socio.getNombre());

                        txtTelefono.setText(
                                socio.getTelefono());

                        txtCorreo.setText(
                                socio.getCorreo());
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

        for(Socio socio :
                servicioSocio.getSocios()) {

            modeloTabla.addRow(
                    new Object[] {
                            socio.getDocumento(),
                            socio.getNombre()
                    });
        }
    }

    /*
     * =====================================================
     * LIMPIAR CAMPOS
     * =====================================================
     */

    private void limpiarCampos() {

        txtDocumento.setText("");
        txtNombre.setText("");
        txtTelefono.setText("");
        txtCorreo.setText("");
    }
}