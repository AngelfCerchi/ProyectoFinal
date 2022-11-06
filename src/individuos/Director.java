package individuos;

public class Director extends Persona{
    private static Director instance;
    private Director(String nombre, String apellido, String dni) {
        super(nombre, apellido, dni);
    }

    public static Director getInstance(){
        return instance;
    }

    public static Director setInstance(String nombre, String apellido, String dni){
        if (instance == null ){
            instance = new Director(nombre, apellido, dni);
        }
        return instance;
    }
}
