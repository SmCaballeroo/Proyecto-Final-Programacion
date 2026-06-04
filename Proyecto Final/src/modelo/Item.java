package modelo;

import java.io.Serializable;

/*
 * =====================================================
 * CLASE ABSTRACTA ITEM
 * =====================================================
 * FUNCIÓN:
 * Clase padre de todos los elementos de la colección.
 * Contiene los atributos comunes para Libros,
 * Juegos y Figuras.
 * =====================================================
 */

public abstract class Item implements Serializable {

    private static final long serialVersionUID = 1L;

    /*
     * =====================================================
     * ATRIBUTOS GENERALES
     * =====================================================
     */

    protected String codigo;
    protected String titulo;
    protected int anio;
    protected String condicion;
    protected double valorEstimado;
    protected int vecesPrestado;

    /*
     * =====================================================
     * CONSTRUCTOR
     * =====================================================
     */

    public Item(String codigo,
                String titulo,
                int anio,
                String condicion,
                double valorEstimado) {

        this.codigo = codigo;
        this.titulo = titulo;
        this.anio = anio;
        this.condicion = condicion;
        this.valorEstimado = valorEstimado;
        this.vecesPrestado = 0;
    }

    /*
     * =====================================================
     * POLIMORFISMO
     * =====================================================
     */

    public abstract String mostrarDetalle();

    /*
     * =====================================================
     * GETTERS Y SETTERS
     * =====================================================
     */

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public String getCondicion() {
        return condicion;
    }

    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }

    public double getValorEstimado() {
        return valorEstimado;
    }

    public void setValorEstimado(double valorEstimado) {
        this.valorEstimado = valorEstimado;
    }

    public int getVecesPrestado() {
        return vecesPrestado;
    }

    public void setVecesPrestado(int vecesPrestado) {
        this.vecesPrestado = vecesPrestado;
    }

    /*
     * =====================================================
     * TOSTRING
     * =====================================================
     */

    
    public String toString() {

        return codigo + " - " + titulo;
    }
}