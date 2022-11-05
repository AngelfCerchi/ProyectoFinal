package individuos;

public class Paciente extends Persona {
    private static int nroAfiliado = 0;
    private TipoServicio tipoServicio;
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
}
