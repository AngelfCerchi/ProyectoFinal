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
}
