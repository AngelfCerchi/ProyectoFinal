package menus;

import clinica.Clinica;
import clinica.Especialidad;
import clinica.Turno;
import clinica.prestacion.Prestacion;
import individuos.Administrativo;
import individuos.Paciente;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MenuAdministrativo {

    private static final Clinica clinica = Clinica.getInstance();

    public static void mostrarMenu(Administrativo administrativo, Scanner sn) {
        boolean salir = false;
        int op = 0;

        while (!salir) {
            MenuHelper.imprimirMenu(Arrays.asList("1. Crear Prestacion",
                    "2. Activar prestacion",
                    "3. Desactivar prestacion",
                    "4. Listar prestaciones activas",
                    "5. Listar prestaciones desactivadas",
                    "6. Turnos disponibles por prestacion",
                    "7. Turnos del paciente",
                    "8. Dar turno",
                    "0. Salir"));
            try {
                op = sn.nextInt();
                switch (op) {
                    case 1:
                        nuevaPrestacion(administrativo, sn);
                        break;
                    case 2:
                        modificarEstadoPrestacion(sn, true);
                        break;
                    case 3:
                        modificarEstadoPrestacion(sn, false);
                        break;
                    case 4:
                        listarPrestacionesActivasPorEspecialidad(sn);
                        break;
                    case 5:
                        listarPrestacionesDesactivasPorEspecialidad(sn);
                        break;
                    case 6:
                        listarTurnosDisponiblesPorPrestacion(sn);
                        break;
                    case 7:
                        listarTurnosDelPaciente(sn);
                        break;
                    case 8:
                        darTurno(administrativo, sn);
                        break;
                    case 0:
                        salir = true;
                }
            } catch (Exception e) {
                System.out.println("Ocurrio un error al procesar su selección. Intente nuevamente!");
                e.printStackTrace();
                mostrarMenu(administrativo, sn);
            }
        }
    }

    private static void listarTurnosDelPaciente(Scanner sn) {
        System.out.println("Turnos del paciente");
        System.out.println("Ingrese el DNI del paciente: ");
        int dni = sn.nextInt();
        Paciente paciente = clinica.getPacientePorDni(String.valueOf(dni));
        if (paciente != null) {
            List<Turno> turnosDelPaciente = clinica.getListaTurnosDePaciente(paciente);
            String mensaje = turnosDelPaciente.isEmpty() ? "El paciente no tiene tiene ningun turno asociado todavia"
                    : "El paciente tiene los siguientes turnos: \n" + MenuHelper.getStringTurnosDelPaciente(turnosDelPaciente);
            System.out.println(mensaje);
        } else {
            System.out.println("El paciente no existe en nuestra base. No tiene turnos asignados");
        }
    }

    private static void modificarEstadoPrestacion(Scanner sn, boolean activar) {
        List<Prestacion> prestaciones = listarPrestacionesPorEspecialidad(sn, !activar);
        String mensaje = activar ? "Seleccione la prestacion que desea activar" : "Seleccione la prestacion que desea desactivar";
        System.out.println(mensaje);
        int prestacionDeseada = sn.nextInt() - 1;
        Prestacion prestacion = prestaciones.get(prestacionDeseada);
        clinica.modificarEstadoDeActividadPrestacion(prestacion, activar);
        mensaje = activar ? "Prestacion: \"" + prestacion.getNombre() + "\" activada correctamente"
                : "Prestacion: \"" + prestacion.getNombre() + "\" desactivada correctamente";
        System.out.println(mensaje);
    }

    private static Prestacion listarTurnosDisponiblesPorPrestacion(Scanner sn) {
        System.out.println("Turnos disponibles por prestacion");
        List<Prestacion> prestaciones = listarPrestacionesPorEspecialidad(sn, true);
        Prestacion prestacion = null;
        if (!prestaciones.isEmpty()) {
            System.out.println("Seleccione la prestacion que desea mostrar sus turnos disponibles");
            int prestacionDeseada = sn.nextInt() - 1;
            prestacion = prestaciones.get(prestacionDeseada);
            System.out.println("Turnos disponibles de la prestacion: " + prestacion.getNombre());
            System.out.println(clinica.listarHorariosDeTodosLosTurnosPorPrestacion(prestacion));
        }
        return prestacion;
    }

    private static void listarPrestacionesActivasPorEspecialidad(Scanner sn) {
        System.out.println("Lista de prestaciones activas");
        listarPrestacionesPorEspecialidad(sn, true);
    }

    private static void listarPrestacionesDesactivasPorEspecialidad(Scanner sn) {
        System.out.println("Lista de prestaciones desactivadas");
        listarPrestacionesPorEspecialidad(sn, false);
    }

    private static List<Prestacion> listarPrestacionesPorEspecialidad(Scanner sn, boolean activas) {
        System.out.println("Seleccione la especialidad deseada para listar sus prestaciones:");
        ArrayList<Especialidad> listaEspecialidades = clinica.listaDeEspecialidades(true);
        System.out.println(MenuHelper.getStringEspecialidadesConIndice(listaEspecialidades));
        int especialidadDeseada = sn.nextInt();
        Especialidad especialidad = listaEspecialidades.get(especialidadDeseada - 1);
        System.out.println("Prestaciones de la especialidad: " + especialidad);
        List<Prestacion> prestaciones = clinica.getPrestacionesPorEspecialidad(especialidad, activas);
        String mensaje = prestaciones.isEmpty() ? "No hay prestaciones para mostrar" : MenuHelper.getStringPrestacionesConIndice(prestaciones);
        System.out.println(mensaje);
        return prestaciones;
    }

    private static void nuevaPrestacion(Administrativo administrativo, Scanner sn) {
        System.out.println("Crear prestacion nueva");
        System.out.println("Seleccione a que Especialidad va a pertenecer la prestacion");
        clinica.listaDeEspecialidades(true);
        int especialidadElegida = sn.nextInt();
        System.out.println("Nombre de la prestacion: ");
        String nombre = sn.next();
        administrativo.crearPrestacion(nombre, clinica.getEspecialidades().get(especialidadElegida));
    }

    private static void darTurno(Administrativo administrativo, Scanner sn) {
        System.out.println("Dar turno a paciente");
        //Pido el DNI y lo busco en el singletone o lo creo...
        System.out.println("Ingrese el DNI del paciente: ");
        int dni = sn.nextInt();
        Paciente paciente = clinica.getPacientePorDni(String.valueOf(dni));

        if (paciente == null) {
            System.out.println("Paciente no encontrado.");
            paciente = MenuPaciente.crearPacienteInexistente(sn, dni);

        }
        Prestacion prestacion = listarTurnosDisponiblesPorPrestacion(sn);
        if (prestacion != null) {
            System.out.println("Seleccione el horario deseado: ");
            int horarioElegido = sn.nextInt();
            Turno turno = clinica.seleccionarTurnoPorPrestacionConIndice(prestacion, horarioElegido);
            turno.setPrestacionBrindada(prestacion);
            turno = administrativo.darTurno(paciente, turno, prestacion, (clinica.getTurnosDisponiblesPorPrestacion(prestacion).size() < horarioElegido));
            String mensaje = turno != null ? "Se creo el siguiente turno: \n" + turno : "No se encontro el turno o el mismo ya no esta disponible. Saque otro turno.";
            System.out.println(mensaje);
        } else {
            System.out.println("No hay turnos disponibles");
        }
    }
}
