package servicio;

import java.util.ArrayList;

import modelo.Socio;
import persistencia.ArchivoSocios;

/*
 * =====================================================
 * CLASE SERVICIO SOCIO
 * =====================================================
 * FUNCIÓN:
 * Gestionar los socios del sistema.
 * =====================================================
 */

public class ServicioSocio {

    private ArrayList<Socio> socios;

    /*
     * =====================================================
     * CONSTRUCTOR
     * =====================================================
     */

    public ServicioSocio() {

        socios = ArchivoSocios.cargar();
    }

    /*
     * =====================================================
     * AGREGAR SOCIO
     * =====================================================
     */

    public boolean agregarSocio(Socio socio) {

        if(existeDocumento(
                socio.getDocumento())) {

            return false;
        }

        socios.add(socio);

        return true;
    }

    /*
     * =====================================================
     * VERIFICAR DOCUMENTO EXISTENTE
     * =====================================================
     */

    public boolean existeDocumento(
            String documento) {

        return buscarPorDocumento(
                documento) != null;
    }

    /*
     * =====================================================
     * VERIFICAR SOCIO DUPLICADO
     * =====================================================
     */

    public boolean existeSocioDuplicado(
            String nombre,
            String telefono,
            String correo) {

        for(Socio socio : socios) {

            if(socio.getNombre()
                    .equalsIgnoreCase(nombre)

                    &&

                    socio.getTelefono()
                    .equalsIgnoreCase(telefono)

                    &&

                    socio.getCorreo()
                    .equalsIgnoreCase(correo)) {

                return true;
            }
        }

        return false;
    }

    /*
     * =====================================================
     * BUSCAR SOCIO
     * =====================================================
     */

    public Socio buscarSocio(
            String documento) {

        for(Socio socio : socios) {

            if(socio.getDocumento()
                    .equalsIgnoreCase(documento)) {

                return socio;
            }
        }

        return null;
    }

    /*
     * =====================================================
     * BUSCAR POR DOCUMENTO
     * =====================================================
     */

    public Socio buscarPorDocumento(
            String documento) {

        return buscarSocio(documento);
    }

    /*
     * =====================================================
     * ELIMINAR SOCIO
     * =====================================================
     */

    public boolean eliminarSocio(
            String documento) {

        Socio socio =
                buscarSocio(documento);

        if(socio != null) {

            socios.remove(socio);

            return true;
        }

        return false;
    }

    /*
     * =====================================================
     * ACTUALIZAR SOCIO
     * =====================================================
     */

    public boolean actualizarSocio(

            String documento,

            String nombre,

            String telefono,

            String correo) {

        Socio socio =
                buscarSocio(documento);

        if(socio != null) {

            socio.setNombre(nombre);

            socio.setTelefono(telefono);

            socio.setCorreo(correo);

            return true;
        }

        return false;
    }

    /*
     * =====================================================
     * GUARDAR DATOS
     * =====================================================
     */

    public void guardarDatos() {

        ArchivoSocios.guardar(socios);
    }

    /*
     * =====================================================
     * GETTERS Y SETTERS
     * =====================================================
     */

    public ArrayList<Socio> getSocios() {
        return socios;
    }

    public void setSocios(
            ArrayList<Socio> socios) {

        this.socios = socios;
    }
}