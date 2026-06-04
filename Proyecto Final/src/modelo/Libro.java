package modelo;

/*
 * =====================================================
 * CLASE LIBRO
 * =====================================================
 * FUNCIÓN:
 * Representa un libro dentro de la colección.
 * =====================================================
 */

public class Libro extends Item {

    private static final long serialVersionUID = 1L;

    private String autor;

    public Libro(String codigo,
                 String titulo,
                 int anio,
                 String condicion,
                 double valorEstimado,
                 String autor) {

        super(codigo,
              titulo,
              anio,
              condicion,
              valorEstimado);

        this.autor = autor;
    }

    @Override
    public String mostrarDetalle() {

        return "LIBRO | " +
                titulo +
                " | Autor: " +
                autor;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
}