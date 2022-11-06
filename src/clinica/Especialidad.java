package clinica;

public class Especialidad {
    public static int NRO_ESPECIALIDAD = 0;
    private String nombre;

    public static int getNroEspecialidad() {
        return NRO_ESPECIALIDAD;
    }

    public Especialidad(String nombre) {
        this.nombre = nombre;
        incrementarNro();
    }

    private static void incrementarNro(){
        NRO_ESPECIALIDAD++;
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
                "Nro='" + NRO_ESPECIALIDAD + '\'' +
                "nombre='" + nombre + '\'' +
                '}';
    }
}
