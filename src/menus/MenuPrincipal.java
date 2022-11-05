package menus;

import clinica.Clinica;
import individuos.Administrativo;

import java.util.Scanner;

public class MenuPrincipal {

    public static void mostrarMenu(Clinica clinica, Administrativo administrativo) {
        Scanner sn = new Scanner(System.in);
        boolean salir = false;
        int op;

        while (!salir){
            System.out.println("1. Menu Gerencial");
            System.out.println("2. Menu Administrativo");
            System.out.println("3. Menu Doctor");
            System.out.println("4. Menu Paciente");
            System.out.println("5. Salir");

            op = sn.nextInt();
            switch (op){
                case 1:
                    //clinica.verMenu();
                    break;
                case 2:
                    MenuAdministrativo.mostrarMenu(administrativo);
                    break;
                case 3:
                    System.out.println("Has seleccionado la opcion 3");
                    break;
                case 4:
                    salir=true;
                    break;
                default:
                    System.out.println("Solo números entre 1 y 4");
            }
        }
    }
}
