package clinica.prestacion;

import clinica.Especialidad;
import clinica.ubicaciones.Ubicacion;
import individuos.Doctor;

import java.util.ArrayList;

public class PrestacionTradicional extends Prestacion {

    private ArrayList<Prescripcion> prescripciones;

    public PrestacionTradicional(String nombre) {
        super(nombre);
        this.prescripciones = new ArrayList<>();
        this.setEsEstudio(false);
    }

    public PrestacionTradicional(String nombre, Doctor doctor, Especialidad especialidad, Ubicacion ubicacion) {
        super(nombre, false, especialidad, ubicacion);
        this.prescripciones = new ArrayList<>();

        this.setEsEstudio(false);
        this.setDoctorAsociado(doctor);
    }

    public ArrayList<Prescripcion> getPrescripciones() {
        return prescripciones;
    }

    public void agregarPrescripcion(Prescripcion prescripcion) {
        getPrescripciones().add(prescripcion);
    }

    @Override
    public String toString() {
        return "PrestacionTradicional{" +
                "prescripciones=" + prescripciones +
                '}';
    }
}
