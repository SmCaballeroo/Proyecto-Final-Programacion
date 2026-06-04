package modelo;

/*
 * =====================================================
 * CLASE JUEGO
 * =====================================================
 * FUNCIÓN:
 * Representa juegos dentro de la colección.
 * =====================================================
 */

public class Juego extends Item {

    private static final long serialVersionUID = 1L;

    private String plataforma;

    public Juego(String codigo,
                 String titulo,
                 int anio,
                 String condicion,
                 double valorEstimado,
                 String plataforma) {

        super(codigo,
              titulo,
              anio,
              condicion,
              valorEstimado);

        this.plataforma = plataforma;
    }

    
    public String mostrarDetalle() {

        return "JUEGO | " +
                titulo +
                " | Plataforma: " +
                plataforma;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }
}