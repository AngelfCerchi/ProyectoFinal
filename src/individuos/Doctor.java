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

    private void registrarAsistencia(Turno turno){
        //TODO: Se debe poder registrar asistencia de c/paciente.
    }
    private void cargarExamen(){
        //TODO: Se debe poder cargar los estudios y recetas prescriptas durante la visita
    }

}
