package enums;

public enum TipoMediosDePago {

    EFECTIVO("Efectivo"),
    TARJETA("Tarjeta de credito");

    private final String tipo;

    TipoMediosDePago(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public static String mostrarTipos() {
        int indice = 1;
        StringBuilder str = new StringBuilder();
        for (TipoMediosDePago servicio : TipoMediosDePago.values()) {
            str.append(indice).append(" - ").append(servicio.getTipo()).append("\n");
            indice++;
        }
        return str.toString();
    }

    public static TipoMediosDePago seleccionarTipoPorIndice(int indice) {
        return TipoMediosDePago.values()[indice - 1];
    }

}
