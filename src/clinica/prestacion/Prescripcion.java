package clinica.prestacion;

import java.util.ArrayList;

public class Prescripcion {
    private ArrayList<Medicamento> medicamentos;
    private ArrayList<Estudio> estudios;

    public Prescripcion(){}

    public Prescripcion(ArrayList<Medicamento> medicamentos, ArrayList<Estudio> estudios) {
        this.medicamentos = medicamentos;
        this.estudios = estudios;
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
}
