package menus;

import individuos.Administrativo;
import individuos.Doctor;

import java.util.Arrays;
import java.util.Scanner;

public class MenuDoctor {

    public static void mostrarMenu(Scanner sn) {
        boolean salir = false;
        int op = 0;

        while (!salir) {
            MenuHelper.imprimirMenu(Arrays.asList("1. Registrar asistencia",
                    "2. Atender turno",
                    "0. Salir"));
            try {
                op = sn.nextInt();
                switch (op) {
                    case 1:
                        doctor.registrarAsistencia();
                        break;
                    case 2:
                        modificarEstadoPrestacion(sn, true);
                        break;
                    case 0:
                        salir = true;
                }
            } catch (Exception e) {
                System.out.println("Ocurrio un error al procesar su selección. Intente nuevamente!");
                e.printStackTrace();
                mostrarMenu(doctor, sn);
            }
        }
    }

}
