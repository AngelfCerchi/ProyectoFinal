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

    protected static List<Turno> listarTurnosDelPaciente(Scanner sn) {
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
