package individuos;

import clinica.prestacion.Prestacion;
import clinica.prestacion.Turno;

public class Doctor extends Persona {

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

    private void registrarAsistencia(Turno turno) {
        //TODO: Se debe poder registrar asistencia de c/paciente.
        turno.registrarAsistenciaPaciente();
    }

    private void cargarPrestacion() {

    }

    public void brindarPrestacion(Prestacion prestacion, Persona paciente) {
        prestacion.brindarAPaciente(paciente);
    }

}
