package menus;

import clinica.Clinica;
import clinica.Turno;
import individuos.Administrativo;
import individuos.Doctor;
import individuos.Paciente;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MenuDoctor {

    private final static Clinica clinica = Clinica.getInstance();

    public static void mostrarMenu(Scanner sn) {
        boolean salir = false;
        int op = 0;

        try {

            System.out.println("Seleccione el doctor: ");
            System.out.println(MenuHelper.getStringDoctores(clinica.getDoctores()));

            int doctornum = sn.nextInt();

            Doctor doctor = clinica.getDoctores().get(doctornum - 1);

        } catch (Exception e) {
            System.out.println("Opcion invalida.");
            mostrarMenu(sn);
        }

        while (!salir) {
            MenuHelper.imprimirMenu(Arrays.asList("1. Registrar asistencia",
                    "2. Atender turno",
                    "0. Salir"));
            try {
                op = sn.nextInt();
                switch (op) {
                    case 1:
                        registrarAsistencia(sn);
                        break;
                    case 2:
                        //modificarEstadoPrestacion(sn, true);
                        break;
                    case 0:
                        salir = true;
                }
            } catch (Exception e) {
                System.out.println("Ocurrio un error al procesar su selección. Intente nuevamente!");
                mostrarMenu(sn);
            }
        }
    }

    private static void registrarAsistencia(Scanner sn) {
        List<Turno> turnos = listarTurnosDelPaciente(sn);
        if (turnos == null || turnos.size() < 1) {
            System.out.println("No se encontraron turnos");
        } else {
            System.out.println("Seleccione el turno a atender");
            int turnoSeleccionado = sn.nextInt();
            Turno t = turnos.get(turnoSeleccionado - 1);
            t.setAusente(false);
            System.out.println("Se registro la asistencia");
        }
    }

    private static List<Turno> listarTurnosDelPaciente(Scanner sn) {
        System.out.println("Turnos del paciente");
        System.out.println("Ingrese el DNI del paciente: ");
        int dni = sn.nextInt();
        Paciente paciente = clinica.getPacientePorDni(String.valueOf(dni));
        if (paciente != null) {
            List<Turno> turnosDelPaciente = clinica.getListaTurnosDePaciente(paciente);
            String mensaje = turnosDelPaciente.isEmpty() ? "El paciente no tiene tiene ningun turno asociado todavia"
                    : "El paciente tiene los siguientes turnos: \n" + MenuHelper.getStringTurnosDelPaciente(turnosDelPaciente);
            System.out.println(mensaje);
            return turnosDelPaciente;
        } else {
            System.out.println("El paciente no existe en nuestra base. No tiene turnos asignados");
            return null;
        }
    }

}
