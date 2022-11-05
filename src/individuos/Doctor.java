package individuos;

import clinica.prestacion.Turno;

public class Doctor extends Persona{
    /**
     * CONSTRUCTOR
     *
     * @param nombre
     * @param apellido
     * @param dni
     **/
    public Doctor(String nombre, String apellido, String dni) {
        super(nombre, apellido, dni);
    }

    private void registrarAsistencia(Turno turno){
        //TODO: Se debe poder registrar asistencia de c/paciente.
    }
    private void cargarEstudios(){
        //TODO: Se debe poder cargar los estudios y recetas prescriptas durante la visita
    }

    public void brindarPrestacion(String dni){
        //TODO: Brindar prestacion al paciente
    }

}
