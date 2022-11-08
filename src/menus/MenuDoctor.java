package menus;

import clinica.Clinica;
import clinica.Turno;
import clinica.prestacion.*;
import individuos.Doctor;
import individuos.Paciente;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MenuDoctor {

    private final static Clinica clinica = Clinica.getInstance();

    public static void mostrarMenu(Scanner sn) {
        boolean salir = false;
        int op = 0;
        Doctor doctor = null;
        try {

            System.out.println("Seleccione el doctor: ");
            System.out.println(MenuHelper.getStringDoctores(clinica.getDoctores()));

            int doctornum = sn.nextInt();

            doctor = clinica.getDoctores().get(doctornum - 1);

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
                        registrarAsistencia(sn, doctor);
                        break;
                    case 2:
                        atenderPaciente(sn, doctor);
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

    private static void atenderPaciente(Scanner sn, Doctor doctor) {
        System.out.println("Atendiendo paciente. Se completara la prestacion del turno");
        List<Turno> turnos = listarTurnosDelPaciente(sn);
        Turno turnoAtendido;
        if (turnos == null || turnos.size() < 1) {
            System.out.println("No se encontraron turnos");
        } else {
            System.out.println("Seleccione el turno al que esta asistiendo el paciente");
            int turnoSeleccionado = sn.nextInt();
            turnoAtendido = turnos.get(turnoSeleccionado - 1);
            if (turnoAtendido.getAsistio()) {
                Prestacion prestacionDelTurno = turnoAtendido.getPrestacionBrindada();
                //Si es un estudio o no, solo completar si asistio y la fecha
                if (prestacionDelTurno.getEsEstudio()) {
                    System.out.println("La prestacion es un estudio. Unicamente registramos la asistencia y el horario");
                    Estudio estudio = (Estudio) prestacionDelTurno;
                    doctor.registrarAtencionDeEstudio(turnoAtendido, estudio);
                } else {
                    //Si no es estudio hay que hacer una prestacion tradicional
                    // y agregar prescripciones que puede tener medicamentos y estudios
                    System.out.println("La prestacion NO es un estudio unicamente. Por lo tanto es necesario agregar prescipciones");
                    int opcion;
                    do {
                        System.out.println("Se creara una nueva prescripcion");
                        Prescripcion prescripcion = new Prescripcion();
                        opcion = agregarPrescripciones(sn, doctor, turnoAtendido, prestacionDelTurno, prescripcion);
                    } while (opcion == 1);
                    System.out.println("Se asignaron todas las prescripciones correctamente");
                }
            } else {
                System.out.println("No se puede atender al paciente y registrar sus precripciones por que el paciente no registra la asistencia en el turno");
            }
        }
    }

    private static int agregarPrescripciones(Scanner sn, Doctor doctor, Turno turnoAtendido, Prestacion prestacionDelTurno, Prescripcion prescripcion) {
        PrestacionTradicional prestacionTradicional = new PrestacionTradicional(prestacionDelTurno.getNombre(), doctor, prestacionDelTurno.getEspecialidad(), prestacionDelTurno.getUbicacion());
        System.out.println("¿Desea agregar un estudio o un medicamento?");
        System.out.println("1- Estudio");
        System.out.println("2- Medicamento");
        int opcion = sn.nextInt();
        if (opcion == 1) {
            System.out.println("Ingrese el nombre del estudio");
            String nombreEstudio = sn.next();
            Estudio estudio = new Estudio(nombreEstudio);
            estudio.setAsistio(true);
            prescripcion.agregarEstudio(estudio);
            System.out.println("Estudio asignado correctamente: " + estudio.getNombre());
        } else if (opcion == 2) {
            System.out.println("Ingrese el nombre del medicamento");
            String nombre = sn.next();
            System.out.println("Ingrese los gramos del medicamento en enteros por favor");
            int gramos = sn.nextInt();
            Medicamento medicamento = new Medicamento(nombre, gramos);
            prescripcion.agregarMedicamento(medicamento);
            System.out.println("Medicamento asignado correctamente: " + medicamento.getNombre() + " por " + medicamento.getGramos() + "grs.");
        }
        System.out.println("¿Desea agregar más estudios o medicamentos a la misma prescripcion?");
        System.out.println("1- Si");
        System.out.println("2- No");
        int salir = 2;
        if (sn.nextInt() == 1) {
            agregarPrescripciones(sn, doctor, turnoAtendido, prestacionTradicional, prescripcion);
        } else {
            System.out.println("Se creo la prescripcion correctamente\n");
            System.out.println("¿Desea agregar UNA NUEVA prescripcion más?");
            System.out.println("1- Si");
            System.out.println("2- No");
            salir = sn.nextInt();
        }
        doctor.agregarPrescripcionAPrestacion(turnoAtendido, prestacionTradicional, prescripcion);
        turnoAtendido.setPrestacionBrindada(prestacionTradicional);
        return salir;
    }

    private static void registrarAsistencia(Scanner sn, Doctor doctor) {
        List<Turno> turnos = listarTurnosDelPaciente(sn);
        if (turnos == null || turnos.size() < 1) {
            System.out.println("No se encontraron turnos");
        } else {
            System.out.println("Seleccione el turno al que esta asistiendo el paciente");
            int turnoSeleccionado = sn.nextInt();
            doctor.registrarAsistenciaPaciente(turnos, turnoSeleccionado);
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
