package menus;

import clinica.Clinica;
import clinica.Especialidad;
import clinica.Turno;
import clinica.prestacion.Prestacion;
import clinica.ubicaciones.Ubicacion;
import individuos.Doctor;
import individuos.Paciente;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuHelper {

    private static final Clinica clinica = Clinica.getInstance();

    private static String MENSAJE_INTRODUZCA_VALOR = "Introduzca un valor por favor: ";

    private static String MENSAJE_DATO_INCORRECTO = "X - Dato ingresado incorrecto - X";

    protected static void imprimirMenu(List<String> menu) {
        menu.forEach(System.out::println);
    }

    protected static String getStringEspecialidadesConIndice(ArrayList<Especialidad> especialidades) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < especialidades.size(); i++) {
            str.append(i + 1).append(" - ").append(especialidades.get(i).getNombre()).append("\n");
        }
        return str.toString();
    }

    protected static String getStringPrestacionesConIndice(List<Prestacion> prestaciones) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < prestaciones.size(); i++) {
            str.append(i + 1).append(" - ").append(prestaciones.get(i).getNombre()).append("\n");
        }
        return str.toString();
    }

    protected static String getStringTurnosDelPaciente(List<Turno> turnosDelPaciente) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < turnosDelPaciente.size(); i++) {
            Turno turno = turnosDelPaciente.get(i);
            str.append(i + 1).append(" - ").append(turno).append("\n");
        }
        return str.toString();
    }

    protected static String getStringUbicaciones(List<Ubicacion> ubicaciones) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < ubicaciones.size(); i++) {
            Ubicacion ubicacion = ubicaciones.get(i);
            str.append(i + 1).append(" - ").append(ubicacion.getNombre()).append("\n");
        }
        return str.toString();
    }

    protected static String getStringDoctores(List<Doctor> doctores) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < doctores.size(); i++) {
            Doctor doctor = doctores.get(i);
            str.append(i + 1).append(" - ").append(doctor.getDni()).append(" - ").append(doctor.getNombre()).append("\n");
        }
        return str.toString();
    }

    /*
     * Pre: recibe el scanner para la interaccion con el usuario, un int minimo y int maximo
     * Post: controla que el usuario no pueda introducir ningun valor que no corresponda
     * y que no este entre el minimo y el maximo. Luego retorna el valor que puso el usuario si es correcto
     * @return: String
     */
    protected static Integer controlDeOpcionElegidaEntero(Scanner scanner, int minimo, int maximo) {
        String opcion = scanner.next();
        if (opcion.equals("")) {
            System.out.println(MENSAJE_DATO_INCORRECTO);
            System.out.println("Por favor a continuacion ingrese un numero entre " + minimo + " y " + maximo);
            return controlDeOpcionElegidaEntero(scanner, minimo, maximo);
        }
        if (opcion.matches("[0-9]*")) {
            Integer opcionInteger = Integer.parseInt(opcion);
            if (opcionInteger <= maximo && opcionInteger >= minimo) {
                return Integer.parseInt(opcion);
            } else {
                System.out.println(MENSAJE_DATO_INCORRECTO);
                System.out.println("Por favor a continuacion ingrese un numero entre " + minimo + " y " + maximo);
                return controlDeOpcionElegidaEntero(scanner, minimo, maximo);
            }
        } else {
            System.out.println(MENSAJE_DATO_INCORRECTO);
            System.out.println("Por favor a continuacion ingrese un numero entre " + minimo + " y " + maximo);
            return controlDeOpcionElegidaEntero(scanner, minimo, maximo);
        }
    }

    protected static List<Turno> listarTurnosDelPaciente(Scanner sn, Doctor... doctors) {
        System.out.println("Turnos del paciente");
        System.out.println("Ingrese el DNI del paciente: ");
        int dni = MenuHelper.controlDeOpcionElegidaEntero(sn, 1, 99999999);
        Paciente paciente = clinica.getPacientePorDni(String.valueOf(dni));
        List<Turno> turnosDelPaciente = null;
        if (paciente != null) {
            turnosDelPaciente = clinica.getListaTurnosDePaciente(paciente);
            List<Turno> turnosConElDoctor = new ArrayList<>();
            if (doctors.length != 0) {
                for (int i = 0; i < turnosDelPaciente.size(); i++) {
                    if (turnosDelPaciente.get(i).getDoctor().getNombreCompleto().equals(doctors[0].getNombreCompleto())) {
                        turnosConElDoctor.add(turnosDelPaciente.get(i));
                    }
                }
                String mensaje = turnosConElDoctor.isEmpty() ? "El paciente no tiene ningun turno con el doctor: " + doctors[0].getNombreCompleto()
                        : "El paciente tiene los siguientes turnos: \n" + MenuHelper.getStringTurnosDelPaciente(turnosDelPaciente);
                System.out.println(mensaje);
                turnosDelPaciente = turnosConElDoctor;
            } else {
                String mensaje = turnosDelPaciente.isEmpty() ? "El paciente no tiene tiene ningun turno asociado todavia"
                        : "El paciente tiene los siguientes turnos: \n" + MenuHelper.getStringTurnosDelPaciente(turnosDelPaciente);
                System.out.println(mensaje);
            }
        } else {
            System.out.println("El paciente no existe en nuestra base. No tiene turnos asignados");
        }
        return turnosDelPaciente;
    }
}
