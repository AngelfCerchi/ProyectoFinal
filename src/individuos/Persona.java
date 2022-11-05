package individuos;

import clinica.prestacion.Turno;

import java.util.ArrayList;

public class Persona {
    private String nombre, apellido, dni;

    private ArrayList<Turno> turnos;



    /** CONSTRUCTOR **/
    public Persona(String nombre, String apellido, String dni) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
    }
    /** GETTERS **/
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getDni() { return dni; }



    /** SETTERS **/
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public void setDni(String dni) { this.dni = dni; }



    public ArrayList<Turno> getTurnos() {
        return turnos;
    }

    public void setTurnos(ArrayList<Turno> turnos) {
        this.turnos = turnos;
    }

    private void abonar() {
        //TODO Se debe poder abonar la prestación
    }


}
