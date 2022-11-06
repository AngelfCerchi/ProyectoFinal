package menus;

import individuos.Administrativo;

import java.util.Scanner;

public class MenuAdministrativo {

    public static void mostrarMenu(Administrativo administrativo) {
        Scanner sn = new Scanner(System.in);
        boolean salir = false;
        int op;

        while (!salir) {
            System.out.println("1. Crear Prestacion");
            System.out.println("2. Prestaciones Activas");
            System.out.println("3. Especialidades con Turnos");
            System.out.println("4. Turnos del Paciente");
            System.out.println("5. Menu Paciente");
            System.out.println("6. Salir");
            op = sn.nextInt();

            switch (op) {
                case 1:
                    String nombre;
                    System.out.println("Nombre de la prestacion: ");
                    nombre = sn.next();
                    administrativo.crearPrestacion(nombre);
                    mostrarMenu(administrativo);
                    break;
                case 2:
                    administrativo.prestacionesActivas();
                    mostrarMenu(administrativo);
                    break;
                case 3:
                    administrativo.especialidadesTurnoDisponibles();
                    mostrarMenu(administrativo);
                    break;

            }

        }
    }


}
//TODO y si nos ponemos a ver si se puede heredar el menu?