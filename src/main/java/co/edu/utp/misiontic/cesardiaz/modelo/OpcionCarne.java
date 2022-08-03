package co.edu.utp.misiontic.cesardiaz.modelo;

public class OpcionCarne {
    private String nombre;
    private Integer id;

    public OpcionCarne(String nombre) {
        this.nombre = nombre;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return getNombre();
    }
    
}
