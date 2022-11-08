package menus;

import clinica.Especialidad;
import clinica.Turno;
import clinica.prestacion.Prestacion;
import clinica.ubicaciones.Ubicacion;
import individuos.Doctor;

import java.util.ArrayList;
import java.util.List;

public class MenuHelper {

    public static void imprimirMenu(List<String> menu) {
        menu.forEach(System.out::println);
    }

    public static String getStringEspecialidadesConIndice(ArrayList<Especialidad> especialidades) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < especialidades.size(); i++) {
            str.append(i + 1).append(" - ").append(especialidades.get(i).getNombre()).append("\n");
        }
        return str.toString();
    }

    public static String getStringPrestacionesConIndice(List<Prestacion> prestaciones) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < prestaciones.size(); i++) {
            str.append(i + 1).append(" - ").append(prestaciones.get(i).getNombre()).append("\n");
        }
        return str.toString();
    }

    public static String getStringTurnosDelPaciente(List<Turno> turnosDelPaciente) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < turnosDelPaciente.size(); i++) {
            Turno turno = turnosDelPaciente.get(i);
            str.append(i + 1).append(" - ").append(turno).append("\n");
        }
        return str.toString();
    }

    public static String getStringUbicaciones(List<Ubicacion> ubicaciones) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < ubicaciones.size(); i++) {
            Ubicacion ubicacion = ubicaciones.get(i);
            str.append(i + 1).append(" - ").append(ubicacion.getNombre()).append("\n");
        }
        return str.toString();
    }

    public static String getStringDoctores(List<Doctor> doctores) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < doctores.size(); i++) {
            Doctor doctor = doctores.get(i);
            str.append(i + 1).append(" - ").append(doctor.getDni()).append(" - ").append(doctor.getNombre()).append("\n");
        }
        return str.toString();
    }
}
