package menus;

import clinica.Clinica;
import clinica.Especialidad;
import clinica.Turno;
import clinica.prestacion.Prestacion;
import enums.TipoServicio;
import individuos.Administrativo;
import individuos.Paciente;

import java.util.Scanner;

public class MenuAdministrativo {

    //TODO er q esto funcione
    private static final Clinica clinica = Clinica.getInstance();

    public static void mostrarMenu(Administrativo administrativo, Scanner sn) {


        boolean salir = false;
        int op = 0;

        while (!salir) {
            System.out.println("1. Crear Prestacion");
            System.out.println("2. Prestaciones Activas");
            System.out.println("3. Especialidades con Turnos");
            System.out.println("4. Turnos del Paciente");
            System.out.println("5. Menu Paciente");
            System.out.println("6. Dar turno");
            System.out.println("7. Salir");

            try {

                op = sn.nextInt();

                switch (op) {
                    case 1:
                        String nombre;
                        System.out.println("Nombre de la prestacion: ");
                        nombre = sn.next();
                        administrativo.crearPrestacion(nombre, new Especialidad(null));
                        break;
                    case 2:
                        administrativo.prestacionesActivasPorEspecialidad(new Especialidad(null));
                        break;
                    case 3:
                        administrativo.turnosDisponiblesPorPrestacion(new Prestacion(null));
                        break;
                    case 4:
                        System.out.println("Ingreso a la opcion turnos de paciente");
                        break;
                    case 5:
                        System.out.println("Ingreso a la opcion de paciente");
                        break;
                    case 6:
                        darTurno(administrativo, sn);
                        break;
                    case 0:
                        salir = true;
                }
                mostrarMenu(administrativo, sn);
            } catch (Exception e) {
                System.out.println("Ocurrio un error al procesar su selección. Intente nuevamente!");
                e.printStackTrace();
                mostrarMenu(administrativo, sn);
            }
        }
    }

    private static void darTurno(Administrativo administrativo, Scanner sn) {
        //Pido el DNI y lo busco en el singletone o lo creo...
        System.out.println("Ingrese el DNI del paciente: ");
        int dni = sn.nextInt();
        Paciente paciente = clinica.getPacientePorDni(String.valueOf(dni));

        if (paciente == null) {
            System.out.println("Paciente no encontrado.");
            System.out.println("Ingrese su Apellido: ");
            String apellido = sn.next();

            //apellido
            System.out.println("Ingrese su nombre: ");
            String nombre = sn.next();

            //Tipo Servicvio
            System.out.println("Seleccione su tipo de cobertura: ");
            System.out.println(TipoServicio.mostrarTipos());

            int tipoServicio = sn.nextInt();
            paciente = new Paciente(apellido, nombre, String.valueOf(dni), TipoServicio.seleccionarTipoPorIndice(tipoServicio));
            clinica.getPacientes().add(paciente);
        }

        System.out.println("Seleccione la especialidad deseada:");
        System.out.println(clinica.listarEspecialidadesActivas());
        int especialidad = sn.nextInt();

        System.out.println("Seleccione la prestacion deseada:");
        System.out.println(clinica.listarPrestacionesActivas(clinica.getEspecialidades().get(especialidad - 1)));
        int prestacionElegida = sn.nextInt();
        Prestacion prestacion = clinica.getPrestacionesActivas().get(prestacionElegida);

        System.out.println("Seleccione el horario deseado: ");
        System.out.println(clinica.listarHorariosDeTodosLosTurnosPorPrestacion(prestacion));
        int horarioElegido = sn.nextInt();
        Turno turno = clinica.seleccionarTurnoPorPrestacionConIndice(prestacion, horarioElegido);
        turno.setPrestacionBrindada(prestacion);
        turno = administrativo.darTurno(paciente, turno, (clinica.getTurnos().get(prestacion).size() < horarioElegido));
        String mensaje = turno != null ? "Se creo el siguiente turno: \n" + turno : "No se encontro el turno o el mismo ya no esta disponible. Saque otro turno.";
        System.out.println(mensaje);

    }

}