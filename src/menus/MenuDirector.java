package menus;

import clinica.Clinica;
import individuos.Doctor;

import java.util.Arrays;
import java.util.Scanner;

public class MenuDirector {

    private final static Clinica clinica = Clinica.getInstance();

    public static void mostrarMenu(Scanner sn) {
        boolean salir = false;
        int op;

        while (!salir) {
            MenuHelper.imprimirMenu(Arrays.asList("1. Obtener reporte prestaciones brindadas",
                    "2. Obtener reporte estudios realizados",
                    "3. Obtener reporte de estudios y prestaciones",
                    "0. Salir"));
            try {
                op = sn.nextInt();
                switch (op) {
                    case 1:
                        obtenerReportePrestaciones(sn);
                        break;
                    case 2:
                        obtenerReporteEstudios(sn);
                        break;
                    case 3:
                        obtenerReporteDeEstudiosYPrestaciones(sn);
                        break;
                    case 0:
                        salir = true;
                        break;
                    default:
                        System.out.println("Opcion incorrecta... Vuelva a intentarlo");
                        mostrarMenu(sn);
                }
            } catch (Exception e) {
                System.out.println(Arrays.toString(e.getStackTrace()));
                System.out.println("Ocurrio un error al procesar su solicitud. Seleccione la opcion: ");
            }
        }
    }

    private static void obtenerReporteDeEstudiosYPrestaciones(Scanner sn) {
        Doctor doctor = elegirDoctorParaReporte(sn);
        System.out.println("Lista de prestaciones y estudios brindados por el doctor: " + doctor.getNombreCompleto());
        String reportePrestaciones = clinica.reportePrestacionesPorDoctor(doctor, false);
        String reporteEstudios = clinica.reportePrestacionesPorDoctor(doctor, true);
        System.out.println(reportePrestaciones);
        System.out.println(reporteEstudios);
    }

    private static void obtenerReporteEstudios(Scanner sn) {
        Doctor doctor = elegirDoctorParaReporte(sn);
        System.out.println("Lista de estudios brindados por el doctor: " + doctor.getNombreCompleto());
        System.out.println(clinica.reportePrestacionesPorDoctor(doctor, true));
    }

    private static void obtenerReportePrestaciones(Scanner sn) {
        Doctor doctor = elegirDoctorParaReporte(sn);
        System.out.println("Lista de prestaciones brindados por el doctor: " + doctor.getNombreCompleto());
        System.out.println(clinica.reportePrestacionesPorDoctor(doctor, false));
    }

    private static Doctor elegirDoctorParaReporte(Scanner sn) {
        System.out.println("Seleccione el doctor para el reporte: ");
        System.out.println(MenuHelper.getStringDoctores(clinica.getDoctores()));
        int doctornum = sn.nextInt();
        return clinica.getDoctores().get(doctornum - 1);
    }


}
