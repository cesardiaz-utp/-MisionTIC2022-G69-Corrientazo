package co.edu.utp.misiontic.cesardiaz.modelo;

public class OpcionPrincipio {
    private String nombre;

    public OpcionPrincipio(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return getNombre();
    }

}
