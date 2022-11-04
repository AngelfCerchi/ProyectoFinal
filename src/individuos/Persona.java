package individuos;

import clinica.prestacion.Turno;
import com.sun.xml.internal.bind.v2.TODO;

public class Persona {
    private String nombre, apellido, dni;
    private Turno[] turnos;
    private TipoServicio servicio;

    /** CONSTRUCTOR **/
    public Persona(String nombre, String apellido, String dni, Turno[] turnos, TipoServicio servicio) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.turnos = turnos;
        this.servicio = servicio;
    }
    /** GETTERS **/
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getDni() { return dni; }
    public Turno[] getTurnos() { return turnos; }
    public TipoServicio getServicio() { return servicio; }

    /** SETTERS **/
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public void setDni(String dni) { this.dni = dni; }
    public void setTurnos(Turno[] turnos) { this.turnos = turnos; }
    public void setServicio(TipoServicio servicio) { this.servicio = servicio; }

    private void abonar() {
        //TODO Se debe poder abonar la prestación
    }
}
