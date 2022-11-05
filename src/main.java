import clinica.Clinica;
import individuos.Administrativo;
import individuos.TipoServicio;
import menus.MenuAdministrativo;
import menus.MenuPrincipal;

import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Clinica clinica = Clinica.getInstance();
        Administrativo administrativo = new Administrativo("Fabian","Cerchi","38324991");


        MenuPrincipal.mostrar(clinica,administrativo);
    }
}