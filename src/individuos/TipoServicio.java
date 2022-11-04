package individuos;

public enum TipoServicio {
    OBRASOCIAL, PREPAGA,PARTICULAR;

    public String getName() {
        switch(this) {
            case OBRASOCIAL:
                return "Obra Social";
            case PARTICULAR:
                return "Particular";
            case PREPAGA:
                return "Prepaga";
            default:
                return null;
        }
    }
}
