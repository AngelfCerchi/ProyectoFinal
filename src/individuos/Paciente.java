package individuos;

import clinica.prestacion.Turno;

import java.util.ArrayList;

public class Paciente extends Persona {
    private static int nroAfiliado = 0;
    private TipoServicio tipoServicio;
    private ArrayList<Turno> turnosAsignados = new ArrayList<>();

    public Paciente(String nombre, String apellido, String dni, TipoServicio tipoServicio) {
        super(nombre, apellido, dni);
        this.tipoServicio = tipoServicio;
        incrementarNro();
    }

    private static void incrementarNro(){
        nroAfiliado++;
    }

    public static int getNroAfiliado() {
        return nroAfiliado;
    }

    public static void setNroAfiliado(int nroAfiliado) {
        Paciente.nroAfiliado = nroAfiliado;
    }



    public TipoServicio getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(TipoServicio tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    private void abonar() {
        //TODO Se debe poder abonar la prestación
    }

    public ArrayList<Turno> getTurnosAsignados() {
        return turnosAsignados;
    }

    public void setTurnosAsignados(ArrayList<Turno> turnosAsignados) {
        this.turnosAsignados = turnosAsignados;
    }
}
