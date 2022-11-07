package menus;

import clinica.Especialidad;
import clinica.Turno;
import clinica.prestacion.Prestacion;

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
            str.append(i + 1).append(" - ").append(prestaciones.get(i)).append("\n");
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
}
