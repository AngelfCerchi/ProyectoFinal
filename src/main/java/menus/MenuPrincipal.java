package menus;

import individuos.Administrativo;

import java.util.Arrays;
import java.util.Scanner;

public class MenuPrincipal {

    private final static Administrativo administrativo = new Administrativo("Fabian", "Cerchi", "37566897");

    public static void mostrarMenu() {
        Scanner sn = new Scanner(System.in);

        boolean salir = false;
        int op;

        while (!salir) {
            MenuHelper.imprimirMenu(Arrays.asList("1. Menu Director",
                    "2. Menu Administrativo",
                    "3. Menu Doctor",
                    "4. Menu Paciente",
                    "0. Salir"));
            try {
                op = MenuHelper.controlDeOpcionElegidaEntero(sn, 0, 4);
                switch (op) {
                    case 1:
                        MenuDirector.mostrarMenu(sn);
                        break;
                    case 2:
                        MenuAdministrativo.mostrarMenu(administrativo, sn);
                        break;
                    case 3:
                        MenuDoctor.mostrarMenu(sn);
                        break;
                    case 4:
                        MenuPaciente.mostrarMenu(sn);
                        break;
                    case 0:
                        System.out.println("Has seleccionado la opcion 0");
                        System.out.println("Saliendo del sistema...");
                        salir = true;
                        break;
                    default:
                        System.out.println("Solo números entre 1 y 4. Opcion 0 para salir.");
                        mostrarMenu();
                }
            } catch (Exception e) {
                System.out.println("Ocurrio un error al procesar su solicitud. Seleccione la opcion: ");
            }
        }
    }
}
