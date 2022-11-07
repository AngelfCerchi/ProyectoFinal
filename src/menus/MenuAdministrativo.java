package menus;

import clinica.Clinica;
import clinica.Especialidad;
import clinica.Turno;
import clinica.prestacion.Prestacion;
import enums.TipoServicio;
import individuos.Administrativo;
import individuos.Paciente;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuAdministrativo {

    private static final Clinica clinica = Clinica.getInstance();

    public static void mostrarMenu(Administrativo administrativo, Scanner sn) {
        boolean salir = false;
        int op = 0;

        while (!salir) {
            System.out.println("1. Agregar Paciente");
            System.out.println("2. Crear Prestacion");
            System.out.println("3. Activar prestacion");
            System.out.println("4. Desactivar prestacion");
            System.out.println("5. Listar prestaciones activas");
            System.out.println("6. Listar prestaciones desactivadas");
            System.out.println("7. Turnos por prestacion");
            System.out.println("8. Turnos del Paciente");
            System.out.println("9. Dar turno");
            System.out.println("0. Salir");
            try {
                op = sn.nextInt();
                switch (op) {
                    case 1:
                        crearPacienteMenu(sn);
                        break;
                    case 2:
                        nuevaPrestacion(administrativo, sn);
                        break;
                    case 3:
                        modificarEstadoPrestacion(sn, true);
                        break;
                    case 4:
                        modificarEstadoPrestacion(sn, false);
                        break;
                    case 5:
                        listarPrestacionesActivasPorEspecialidad(sn);
                        break;
                    case 6:
                        listarPrestacionesDesactivasPorEspecialidad(sn);
                        break;
                    case 7:
                        listarTurnosDisponiblesPorPrestacion(sn);
                        break;
                    case 8:
                        listarTurnosDelPaciente(sn);
                        break;
                    case 9:
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
                    : "El paciente tiene los siguientes turnos: \n" + getStringTurnosDelPaciente(turnosDelPaciente);
            System.out.println(mensaje);
        } else {
            System.out.println("El paciente no existe en nuestra base. No tiene turnos asignados");
        }
    }

    private static void modificarEstadoPrestacion(Scanner sn, boolean activar) {
        List<Prestacion> prestaciones = listarPrestacionesPorEspecialidad(sn, !activar);
        String mensaje = activar ? "Seleccione la prestacion que desea activar" : "Seleccione la prestacion que desea desactivar";
        System.out.println(mensaje);
        int prestacionDeseada = sn.nextInt();
        Prestacion prestacion = prestaciones.get(prestacionDeseada);
        clinica.modificarEstadoDeActividadPrestacion(prestacion, activar);
        mensaje = activar ? "Prestacion: \"" + prestacion.getNombre() + "\" activada correctamente"
                : "Prestacion: \"" + prestacion.getNombre() + "\" desactivada correctamente";
        System.out.println(mensaje);
    }

    private static Prestacion listarTurnosDisponiblesPorPrestacion(Scanner sn) {
        System.out.println("Turnos disponibles por prestacion");
        List<Prestacion> prestaciones = listarPrestacionesPorEspecialidad(sn, true);
        System.out.println("Seleccione la prestacion que desea mostrar sus turnos disponibles");
        int prestacionDeseada = sn.nextInt();
        Prestacion prestacion = prestaciones.get(prestacionDeseada);
        System.out.println("Turnos disponibles de la prestacion: " + prestacion.getNombre());
        System.out.println(clinica.listarHorariosDeTodosLosTurnosPorPrestacion(prestacion));
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
        System.out.println(getStringEspecialidadesConIndice(listaEspecialidades));
        int especialidadDeseada = sn.nextInt();
        System.out.println("Prestaciones de la especialidad: " + listaEspecialidades.get(especialidadDeseada));
        List<Prestacion> prestaciones = clinica.getPrestacionesPorEspecialidad(listaEspecialidades.get(especialidadDeseada), activas);
        getStringPrestacionesConIndice(prestaciones);
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

    private static void crearPacienteMenu(Scanner sn) {
        System.out.println("Crear paciente");
        System.out.println("Ingrese el DNI del nuevo paciente: ");
        int dni = sn.nextInt();
        Paciente paciente = clinica.getPacientePorDni(String.valueOf(dni));
        if (paciente == null) {
            paciente = crearPacienteInexistente(sn, dni);
            System.out.println("Paciente creado exitosamente");
        } else {
            System.out.println("El paciente ya existe en nuestra base");
        }
        System.out.println(paciente);
    }

    private static void darTurno(Administrativo administrativo, Scanner sn) {
        System.out.println("Dar turno a paciente");
        //Pido el DNI y lo busco en el singletone o lo creo...
        System.out.println("Ingrese el DNI del paciente: ");
        int dni = sn.nextInt();
        Paciente paciente = clinica.getPacientePorDni(String.valueOf(dni));

        if (paciente == null) {
            System.out.println("Paciente no encontrado.");
            paciente = crearPacienteInexistente(sn, dni);

        }
        Prestacion prestacion = listarTurnosDisponiblesPorPrestacion(sn);
        System.out.println("Seleccione el horario deseado: ");
        System.out.println(clinica.listarHorariosDeTodosLosTurnosPorPrestacion(prestacion));
        int horarioElegido = sn.nextInt();
        Turno turno = clinica.seleccionarTurnoPorPrestacionConIndice(prestacion, horarioElegido);
        turno.setPrestacionBrindada(prestacion);
        turno = administrativo.darTurno(paciente, turno, (clinica.getTurnosDisponiblesPorPrestacion(prestacion).size() < horarioElegido));
        String mensaje = turno != null ? "Se creo el siguiente turno: \n" + turno : "No se encontro el turno o el mismo ya no esta disponible. Saque otro turno.";
        System.out.println(mensaje);
    }

    private static Paciente crearPacienteInexistente(Scanner sn, int dniPaciente) {
        System.out.println("Ingrese su Apellido: ");
        String apellido = sn.next();

        //apellido
        System.out.println("Ingrese su nombre: ");
        String nombre = sn.next();

        //Tipo Servicvio
        System.out.println("Seleccione su tipo de cobertura: ");
        System.out.println(TipoServicio.mostrarTipos());

        int tipoServicio = sn.nextInt();
        Paciente paciente = new Paciente(apellido, nombre, String.valueOf(dniPaciente), TipoServicio.seleccionarTipoPorIndice(tipoServicio));
        clinica.getPacientes().add(paciente);
        return paciente;
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

}
