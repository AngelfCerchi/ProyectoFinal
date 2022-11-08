package individuos;

public abstract class Persona {
    private String nombre;
    private String apellido;
    private String dni;

    /**
     * CONSTRUCTOR
     **/
    public Persona(String nombre, String apellido, String dni) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
    }

    /**
     * GETTERS
     **/
    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getDni() {
        return dni;
    }

    public String getNombreCompleto() {
        return getApellido() + " " + getNombre();
    }

    /**
     * SETTERS
     **/
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
