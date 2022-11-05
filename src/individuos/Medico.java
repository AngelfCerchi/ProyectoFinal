package individuos;

import clinica.prestacion.Turno;

public class Medico extends Persona{
    /**
     * CONSTRUCTOR
     *
     * @param nombre
     * @param apellido
     * @param dni
     * @param turnos
     * @param servicio
     **/
    public Medico(String nombre, String apellido, String dni, Turno[] turnos, TipoServicio servicio) {
        super(nombre, apellido, dni, turnos, servicio);
    }

    public void brindarPrestacion(Prestacion prestacion , Persona paciente){
        //TODO: Brindar prestacion al paciente
    }

}
