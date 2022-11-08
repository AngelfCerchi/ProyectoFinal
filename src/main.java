import clinica.Clinica;
import individuos.Administrativo;
import menus.MenuPrincipal;

public class main {
    public static void main(String[] args) {
        Clinica clinica = Clinica.getInstance();
        System.out.println(clinica+"\n");
        MenuPrincipal.mostrarMenu(new Administrativo("Fabian", "Cerchi", "37566897"));
    }
}