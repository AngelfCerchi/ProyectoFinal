package individuos;

import clinica.prestacion.Prestacion;
import clinica.prestacion.Turno;

public class Administrativo extends Persona{
    /**
     * CONSTRUCTOR
     *
     * @param nombre
     * @param apellido
     * @param dni
     * @param turnos
     * @param servicio
     **/
    public Administrativo(String nombre, String apellido, String dni, Turno[] turnos, TipoServicio servicio) {
        super(nombre, apellido, dni, turnos, servicio);
    }

    public void crearPrestacion(){
        //TODO: Se debe poder armar una prestacion inicial.
    }
    public Prestacion[] prestacionesActivas(){
        //TODO: Se debe poder traer todas las prestaciones activas para luego poder brindar turnos.
        return;
    }

    public Especialidad[] especialidadesDisponibles(){
        //TODO: Se debe poder conocer las actividades con turnos disponibles
    }

    public Turno[] turnosTomados(Persona paciente){
        //TODO: Turnos tomados por el paciente
    }

    public void asignarUbicacion(Turno turno, Medico medico){
        //TODO: Se debe poder asignar ubicaciones (Consultorio/Laboratorio) donde van a brindarse la atencion y un medico
        //Evitar la supersposición de turnos y medicos.
    }

}
