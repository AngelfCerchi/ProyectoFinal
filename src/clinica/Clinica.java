package clinica;

public class Clinica {
    private static Clinica instance;


    private Clinica() {}
    public static Clinica getInstance() {
        if (instance == null) {
            instance = new Clinica();
        }
        return instance;
    }
}
