package co.edu.utp.misiontic.cesardiaz.modelo;

public class OpcionSopa {
    private String nombre;

    public OpcionSopa(String nombre) {
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
