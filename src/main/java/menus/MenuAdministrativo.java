package menus;

import clinica.Clinica;
import clinica.Especialidad;
import clinica.Turno;
import clinica.prestacion.Prestacion;
import clinica.ubicaciones.Ubicacion;
import individuos.Administrativo;
import individuos.Doctor;
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
                op = MenuHelper.controlDeOpcionElegidaEntero(sn, 0, 8);
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
                        MenuHelper.listarTurnosDelPaciente(sn);
                        break;
                    case 8:
                        darTurno(administrativo, sn);
                        break;
                    case 0:
                        salir = true;
                        break;
                    default:
                        mostrarMenu(administrativo, sn);
                }
            } catch (Exception e) {
                System.out.println("Ocurrio un error al procesar su selección. Intente nuevamente!");
                e.printStackTrace();
                mostrarMenu(administrativo, sn);
            }
        }
    }

    private static void modificarEstadoPrestacion(Scanner sn, boolean activar) {
        List<Prestacion> prestaciones = listarPrestacionesPorEspecialidad(sn, !activar);
        if (!prestaciones.isEmpty()) {
            String mensaje = activar ? "Seleccione la prestacion que desea activar" : "Seleccione la prestacion que desea desactivar";
            System.out.println(mensaje);
            int prestacionDeseada = MenuHelper.controlDeOpcionElegidaEntero(sn, 1, prestaciones.size());
            Prestacion prestacion = prestaciones.get(prestacionDeseada - 1);
            clinica.modificarEstadoDeActividadPrestacion(prestacion, activar);
            mensaje = activar ? "Prestacion: \"" + prestacion.getNombre() + "\" activada correctamente"
                    : "Prestacion: \"" + prestacion.getNombre() + "\" desactivada correctamente";
            System.out.println(mensaje);
        } else {
            System.out.println("No hay prestaciones disponibles para cambiar de estado");
        }
    }

    private static Prestacion listarTurnosDisponiblesPorPrestacion(Scanner sn) {
        System.out.println("Turnos disponibles por prestacion");
        List<Prestacion> prestaciones = listarPrestacionesPorEspecialidad(sn, true);
        Prestacion prestacion = null;
        if (!prestaciones.isEmpty()) {
            System.out.println("Seleccione la prestacion que desea mostrar sus turnos disponibles");
            int prestacionDeseada = MenuHelper.controlDeOpcionElegidaEntero(sn, 1, prestaciones.size());
            prestacion = prestaciones.get(prestacionDeseada - 1);
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
        int especialidadDeseada = MenuHelper.controlDeOpcionElegidaEntero(sn, 1, listaEspecialidades.size());
        Especialidad especialidad = listaEspecialidades.get(especialidadDeseada - 1);
        System.out.println("Prestaciones de la especialidad: " + especialidad);
        List<Prestacion> prestaciones = clinica.getPrestacionesPorEspecialidad(especialidad, activas);
        String mensaje = prestaciones.isEmpty() ? "No hay prestaciones para mostrar" : MenuHelper.getStringPrestacionesConIndice(prestaciones);
        System.out.println(mensaje);
        return prestaciones;
    }

    private static void nuevaPrestacion(Administrativo administrativo, Scanner sn) {
        System.out.println("Crear prestacion nueva");
        System.out.println("Seleccione a que Especialidad va a pertenecer la nueva prestacion");
        ArrayList<Especialidad> especialidades = clinica.getEspecialidades();
        System.out.println(MenuHelper.getStringEspecialidadesConIndice(especialidades));
        int especialidadElegida = MenuHelper.controlDeOpcionElegidaEntero(sn, 1, especialidades.size());
        sn.nextLine();
        Especialidad especialidad = especialidades.get(especialidadElegida - 1);
        System.out.print("Nombre de la prestacion: ");
        String nombrePrestacion = sn.nextLine();
        System.out.println("¿La prestacion a crear va a ser un estudio?\n" + "1- Si\n" + "2- No\n");
        boolean esEstudio = sn.nextInt() == 1;
        System.out.println("Seleccione la ubicacion donde se va a realizar la prestacion");
        System.out.println(MenuHelper.getStringUbicaciones(clinica.getUbicaciones()));
        int ubicacionElegida = MenuHelper.controlDeOpcionElegidaEntero(sn, 1, clinica.getUbicaciones().size());
        Ubicacion ubicacion = clinica.getUbicaciones().get(ubicacionElegida - 1);

        System.out.println("Todos los doctores estan ocupados. Registre un nuevo doctor.");
        System.out.print("Ingrese el DNI del nuevo doctor: ");
        int dni = MenuHelper.controlDeOpcionElegidaEntero(sn, 1, 99999999);
        sn.nextLine();
        System.out.print("Ingrese su apellido: ");
        String apellidoDoctor = sn.nextLine();
        //apellido
        System.out.print("Ingrese su nombre: ");
        String nombreDoctor = sn.nextLine();
        Doctor doctor = new Doctor(nombreDoctor, apellidoDoctor, String.valueOf(dni));
        clinica.getDoctores().add(doctor);
        Prestacion prestacionNueva = administrativo.crearPrestacion(nombrePrestacion, esEstudio, especialidad, ubicacion, doctor);
        System.out.println("Se creo la prestacion: " + prestacionNueva.getNombre());
    }

    private static void darTurno(Administrativo administrativo, Scanner sn) {
        System.out.println("Dar turno a paciente");
        //Pido el DNI y lo busco en el singletone o lo creo...
        System.out.println("Ingrese el DNI del paciente: ");
        int dni = MenuHelper.controlDeOpcionElegidaEntero(sn, 1, 99999999);
        sn.nextLine();
        Paciente paciente = clinica.getPacientePorDni(String.valueOf(dni));
        if (paciente == null) {
            System.out.print("Paciente no encontrado.");
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
