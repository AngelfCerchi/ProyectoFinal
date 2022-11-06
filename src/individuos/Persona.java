package individuos;

import clinica.prestacion.Turno;

import java.util.ArrayList;

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

    public String getNombreCompleto(){
        return getNombre() + " " + getApellido();
    }

    /**
     * SETTERS
     **/
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }


}
