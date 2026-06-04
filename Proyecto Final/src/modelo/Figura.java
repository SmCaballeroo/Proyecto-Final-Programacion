package modelo;

/*
 * =====================================================
 * CLASE FIGURA
 * =====================================================
 * FUNCIÓN:
 * Representa figuras coleccionables.
 * =====================================================
 */

public class Figura extends Item {

    private static final long serialVersionUID = 1L;

    private String franquicia;

    public Figura(String codigo,
                  String titulo,
                  int anio,
                  String condicion,
                  double valorEstimado,
                  String franquicia) {

        super(codigo,
              titulo,
              anio,
              condicion,
              valorEstimado);

        this.franquicia = franquicia;
    }

    
    public String mostrarDetalle() {

        return "FIGURA | " +
                titulo +
                " | Franquicia: " +
                franquicia;
    }

    public String getFranquicia() {
        return franquicia;
    }

    public void setFranquicia(String franquicia) {
        this.franquicia = franquicia;
    }
}