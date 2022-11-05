import clinica.Clinica;
import individuos.Administrativo;
import menus.MenuPrincipal;

public class main {
    public static void main(String[] args) {
        Clinica clinica = Clinica.getInstance();
        Administrativo administrativo = new Administrativo("Fabian","Cerchi","38324991");

        MenuPrincipal.mostrarMenu(clinica,administrativo);
    }
}