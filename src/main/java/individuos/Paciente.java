package individuos;

import enums.TipoServicio;

public class Paciente extends Persona {
    private TipoServicio tipoServicio;

    public Paciente(String nombre, String apellido, String dni, TipoServicio tipoServicio) {
        super(nombre, apellido, dni);
        this.tipoServicio = tipoServicio;
    }

    public TipoServicio getTipoServicio() {
        return tipoServicio;
    }

    @Override
    public String toString() {
        return "Nombre: " + getNombreCompleto() + " - DNI: " + getDni() + " - Tipo de servicio: " + getTipoServicio().getTipo();
    }
}
