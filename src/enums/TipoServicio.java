package enums;

public enum TipoServicio {

    OBRASOCIAL("Obra Social"),
    PARTICULAR("Particular"),
    PREPAGA("Prepaga");

    private final String tipo;

    TipoServicio(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public static String mostrarTipos() {
        int indice = 1;
        StringBuilder str = new StringBuilder();
        for (TipoServicio servicio : TipoServicio.values()) {
            str.append(indice).append(" - ").append(servicio.getTipo()).append("\n");
            indice++;
        }
        return str.toString();
    }

    public static TipoServicio seleccionarTipoPorIndice(int indice) {
        return TipoServicio.values()[indice - 1];
    }

}
