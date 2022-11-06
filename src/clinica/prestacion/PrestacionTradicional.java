package clinica.prestacion;

import java.util.ArrayList;

public class PrestacionTradicional extends  Prestacion {

    private ArrayList<Prescripcion> prescripciones;

    public PrestacionTradicional(String nombre) {
        super(nombre);
        this.prescripciones = new ArrayList<>();
        this.setEsEstudio(false);
    }

    public ArrayList<Prescripcion> getPrescripciones() {
        return prescripciones;
    }

    public void setPrescripciones(ArrayList<Prescripcion> prescripciones) {
        this.prescripciones = prescripciones;
    }
}
