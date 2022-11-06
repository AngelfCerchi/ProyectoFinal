package individuos;

import clinica.Especialidad;
import clinica.prestacion.Turno;

import java.util.ArrayList;

public class Doctor extends Persona {

    private ArrayList<Especialidad> especialidades = new ArrayList<>();
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

    public ArrayList<Especialidad> getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(ArrayList<Especialidad> especialidades) {
        this.especialidades = especialidades;
    }

}
