package individuos;

import clinica.prestacion.Turno;

public class Director extends Persona{
    private static Director instance;
    private Director(String nombre, String apellido, String dni, Turno[] turnos, TipoServicio servicio) {
        super(nombre, apellido, dni, turnos, servicio);
        
    }
    public static Director setInstance(String nombre, String apellido, String dni, Turno[] turnos, TipoServicio servicio){
        if (instance == null ){
            instance = new Director(nombre, apellido, dni, turnos, servicio);
        }
        return instance;
    }
}
