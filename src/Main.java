import clinica.Clinica;
import menus.MenuPrincipal;

public class Main {

    public static void main(String[] args) {
        Clinica clinica = Clinica.getInstance();
        System.out.println(clinica + "\n");
        MenuPrincipal.mostrarMenu();
    }
}
