package individuos;

public enum TipoServicio {

    OBRASOCIAL("Obra Social"),
    PARTICULAR("Particular"),
    PREPAGA("Prepaga");

    private String tipo;

    private TipoServicio(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }
}
