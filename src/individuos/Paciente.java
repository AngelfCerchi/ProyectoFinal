package individuos;

import clinica.Turno;
import enums.TipoServicio;

import java.util.ArrayList;

public class Paciente extends Persona {
    private static int nroAfiliado = 0;
    private TipoServicio tipoServicio;
    private ArrayList<Turno> turnosAsignados = new ArrayList<>();

    public Paciente(String nombre, String apellido, String dni, TipoServicio tipoServicio) {
        super(nombre, apellido, dni);
        this.tipoServicio = tipoServicio;
        nroAfiliado++;
    }

    public static int getNroAfiliado() {
        return nroAfiliado;
    }

    public TipoServicio getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(TipoServicio tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public ArrayList<Turno> getTurnosAsignados() {
        return turnosAsignados;
    }

    public void setTurnosAsignados(ArrayList<Turno> turnosAsignados) {
        this.turnosAsignados = turnosAsignados;
    }

    public void agregarTurno(Turno turno) {
        getTurnosAsignados().add(turno);
    }
}
