package modelo;

import java.io.Serializable;

/*
 * =====================================================
 * CLASE SOCIO
 * =====================================================
 * FUNCIÓN:
 * Representa las personas que solicitan préstamos.
 * =====================================================
 */

public class Socio implements Serializable {

    private static final long serialVersionUID = 1L;

    private String documento;
    private String nombre;
    private String telefono;
    private String correo;

    public Socio(String documento,
                 String nombre,
                 String telefono,
                 String correo) {

        this.documento = documento;
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Override
    public String toString() {
        return nombre;
    }
}