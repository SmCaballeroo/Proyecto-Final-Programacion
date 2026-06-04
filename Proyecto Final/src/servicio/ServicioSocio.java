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
     * Carga automáticamente los socios guardados.
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

    public void agregarSocio(Socio socio) {

        socios.add(socio);
    }

    /*
     * =====================================================
     * BUSCAR SOCIO
     * =====================================================
     */

    public Socio buscarSocio(String documento) {

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

    public boolean eliminarSocio(String documento) {

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
     * GUARDAR DATOS
     * =====================================================
     */

    public void guardarDatos() {

        ArchivoSocios.guardar(socios);
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
     * GETTERS Y SETTERS
     * =====================================================
     */

    public ArrayList<Socio> getSocios() {
        return socios;
    }

    public void setSocios(ArrayList<Socio> socios) {
        this.socios = socios;
    }
}