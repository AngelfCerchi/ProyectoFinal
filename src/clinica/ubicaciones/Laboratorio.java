package clinica.ubicaciones;

import clinica.prestacion.Estudio;

import java.util.ArrayList;

public class Laboratorio extends Ubicacion {

    private ArrayList<Estudio> estudiosDisponibles = new ArrayList<>();

    public Laboratorio(String nombre) {
        super(nombre);
    }

    public ArrayList<Estudio> getEstudiosDisponibles() {
        return estudiosDisponibles;
    }

    public void setEstudiosDisponibles(ArrayList<Estudio> estudiosDisponibles) {
        this.estudiosDisponibles = estudiosDisponibles;
    }

    public ArrayList<Estudio> estudiosDisponibles(){
        return getEstudiosDisponibles();
    }
}
