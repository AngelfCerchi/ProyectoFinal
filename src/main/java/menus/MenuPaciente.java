package menus;

import clinica.Clinica;
import clinica.Turno;
import enums.TipoMediosDePago;
import enums.TipoServicio;
import individuos.Administrativo;
import individuos.Paciente;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MenuPaciente {

    private static final Clinica clinica = Clinica.getInstance();

    public static void mostrarMenu(Administrativo administrativo, Scanner sn) {
        boolean salir = false;
        int op = 0;

        while (!salir) {
            MenuHelper.imprimirMenu(Arrays.asList("1. Agregar Paciente",
                    "2. Listar pacientes",
                    "3. Pagar turno",
                    "0. Salir"));
            try {
                op = MenuHelper.controlDeOpcionElegidaEntero(sn, 0, 3);
                switch (op) {
                    case 1:
                        crearPacienteMenu(administrativo, sn);
                        break;
                    case 2:
                        listarPacientes();
                        break;
                    case 3:
                        pagarTurno(administrativo, sn);
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

    private static void pagarTurno(Administrativo administrativo, Scanner sn) throws InterruptedException {
        System.out.println("Pagar turno del paciente.");
        List<Turno> turnos = MenuHelper.listarTurnosDelPaciente(sn);
        Turno turnoAtendido;
        if (turnos == null || turnos.size() < 1) {
            System.out.println("No se encontraron turnos");
        } else {
            System.out.println("Seleccione el turno que desea pagar el paciente");
            int turnoSeleccionado = MenuHelper.controlDeOpcionElegidaEntero(sn, 1, turnos.size());
            turnoAtendido = turnos.get(turnoSeleccionado - 1);
            if (!turnoAtendido.getTurnoPagado()) {
                System.out.println("Como desea pagar el turno: ");
                System.out.println(TipoMediosDePago.mostrarTipos());
                int opcion = MenuHelper.controlDeOpcionElegidaEntero(sn, 1, TipoMediosDePago.values().length);
                administrativo.pagarTurno(turnoAtendido, TipoMediosDePago.seleccionarTipoPorIndice(opcion - 1).getTipo());
                if (opcion == 2) {
                    System.out.println("Aguarde unos segundos.\nEl pago con tarjeta tiene una pequeña demora. No más de 5 segundos...");
                    Thread.sleep(4000);
                }
                System.out.println("Listo! Pago realizado correctamente");
            } else {
                System.out.println("El turno elegido ya esta pago. Puede pagar otro turno si quiere");
            }
        }
    }

    private static void listarPacientes() {
        System.out.println("Lista de pacientes:");
        ArrayList<Paciente> pacientes = clinica.getPacientes();
        if (pacientes.isEmpty()) {
            System.out.println("No hay pacientes registrados en la clinica");
        } else {
            pacientes.forEach(x -> System.out.println(x.toString()));
        }
    }

    private static void crearPacienteMenu(Administrativo administrativo, Scanner sn) {
        System.out.println("Crear paciente");
        System.out.print("Ingrese el DNI del nuevo paciente: ");
        int dni = MenuHelper.controlDeOpcionElegidaEntero(sn, 1, 99999999);
        sn.nextLine();
        Paciente paciente = clinica.getPacientePorDni(String.valueOf(dni));
        if (paciente == null) {
            paciente = crearPacienteInexistente(administrativo, sn, dni);
            System.out.println("Paciente creado exitosamente");
        } else {
            System.out.println("El paciente ya existe en nuestra base");
        }
        System.out.println(paciente);
    }

    protected static Paciente crearPacienteInexistente(Administrativo administrativo, Scanner sn, int dniPaciente) {
        System.out.print("Ingrese su apellido: ");
        String apellido = sn.nextLine();

        //apellido
        System.out.print("Ingrese su nombre: ");
        String nombre = sn.nextLine();

        //Tipo Servicvio
        System.out.println("Seleccione su tipo de cobertura: ");
        System.out.println(TipoServicio.mostrarTipos());

        int tipoServicio = MenuHelper.controlDeOpcionElegidaEntero(sn, 1, TipoServicio.values().length);
        Paciente paciente = administrativo.crearPaciente(nombre, apellido, String.valueOf(dniPaciente), TipoServicio.seleccionarTipoPorIndice(tipoServicio));
        return paciente;
    }

}
