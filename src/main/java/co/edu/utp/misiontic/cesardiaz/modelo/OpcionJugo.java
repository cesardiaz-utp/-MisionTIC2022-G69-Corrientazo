package co.edu.utp.misiontic.cesardiaz.modelo;

public class OpcionJugo {
    private String nombre;

    public OpcionJugo(String nombre) {
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
