package clinica.prestacion;

import java.util.ArrayList;

public class Prescripcion {
    private ArrayList<Medicamento> medicamentos;
    private ArrayList<Estudio> estudios;

    public Prescripcion() {
        this.medicamentos = new ArrayList<>();
        this.estudios = new ArrayList<>();
    }

    public ArrayList<Medicamento> getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(ArrayList<Medicamento> medicamentos) {
        this.medicamentos = medicamentos;
    }

    public ArrayList<Estudio> getEstudios() {
        return estudios;
    }

    public void setEstudios(ArrayList<Estudio> estudios) {
        this.estudios = estudios;
    }

    public void agregarEstudio(Estudio estudio) {
        getEstudios().add(estudio);
    }

    public void agregarMedicamento(Medicamento medicamento) {
        getMedicamentos().add(medicamento);
    }

    @Override
    public String toString() {
        return "Prescripcion{" +
                "medicamentos=" + medicamentos +
                ", estudios=" + estudios +
                '}';
    }
}
