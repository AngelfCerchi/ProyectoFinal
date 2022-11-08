package clinica.ubicaciones;

public abstract class Ubicacion {

    private String nombre;

    public Ubicacion(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
