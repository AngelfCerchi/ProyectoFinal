package individuos;

import clinica.prestacion.Turno;

public class Doctor extends Persona{
    /**
     * CONSTRUCTOR
     *
     * @param nombre
     * @param apellido
     * @param dni
     * @param turnos
     * @param servicio
     **/
    public Doctor(String nombre, String apellido, String dni, Turno[] turnos, TipoServicio servicio) {
        super(nombre, apellido, dni, turnos, servicio);
    }

    private void registrarAsistencia(){};

}
