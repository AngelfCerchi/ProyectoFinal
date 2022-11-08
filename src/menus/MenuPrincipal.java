package menus;

import clinica.Clinica;
import individuos.Administrativo;

import java.util.Scanner;

public class MenuPrincipal {

    public static void mostrarMenu(Clinica clinica, Administrativo administrativo) {
        Scanner sn = new Scanner(System.in);
        boolean salir = false;
        int op;

        while (!salir) {
            System.out.println("1. Menu Gerencial");
            System.out.println("2. Menu Administrativo");
            System.out.println("3. Menu Doctor");
            System.out.println("4. Menu Paciente");
            System.out.println("0. Salir");
            try {
                op = sn.nextInt();
                switch (op) {
                    case 1:
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
                }
            } catch (Exception e) {
                System.out.println("Ocurrio un error al procesar su solicitud. Seleccione la opcion:");
            }
        }
    }
}
