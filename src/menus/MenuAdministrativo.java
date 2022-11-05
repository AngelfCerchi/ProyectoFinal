package menus;

import clinica.prestacion.Prestacion;
import individuos.Administrativo;

import java.util.Scanner;

public class MenuAdministrativo {

    public void mostrar(Administrativo admin){
        Scanner sn = new Scanner(System.in);
        boolean salir = false;
        int op;

        while (!salir){
            System.out.println("1. Crear Prestacion");
            System.out.println("2. Prestaciones Activas");
            System.out.println("3. Especialidades con Turnos");
            System.out.println("4. Turnos del Paciente");
            System.out.println("5. Menu Paciente");
            System.out.println("6. Salir");
            op = sn.nextInt();

            switch (op){
                case 1:
                    String nombre;
                    System.out.println("Nombre de la prestacion: ");
                    nombre = sn.next();
                    admin.crearPrestacion(nombre);
                    admin.verMenu();
                    break;
                case 2:
                    admin.prestacionesActivas();
                    admin.verMenu();
                    break;
                case 3:
                    admin.especialidadesTurnoDisponibles();
                    admin.verMenu();
                    break;

            }

        }
    }



}
