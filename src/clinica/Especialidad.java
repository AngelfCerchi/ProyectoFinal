package clinica;

public class Especialidad {
    public static int nroEspecialidad = 0;
    private String nombre;

    public static int getNroEspecialidad() {
        return nroEspecialidad;
    }

    public Especialidad(String nombre) {
        this.nombre = nombre;
        incrementarNro();
    }

    private static void incrementarNro(){
        nroEspecialidad++;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Especialidad{" +
                "Nro=='" + nroEspecialidad + '\'' +
                "nombre='" + nombre + '\'' +
                '}';
    }
}
