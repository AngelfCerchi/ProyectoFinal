package individuos;

import enums.TipoServicio;

public class Paciente extends Persona {
    private static int nroAfiliado = 0;
    private TipoServicio tipoServicio;

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
}
