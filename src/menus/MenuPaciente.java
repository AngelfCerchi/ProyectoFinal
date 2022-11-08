package menus;

import clinica.Clinica;
import enums.TipoServicio;
import individuos.Paciente;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MenuPaciente {

    private static final Clinica clinica = Clinica.getInstance();

    public static void mostrarMenu(Scanner sn) {
        boolean salir = false;
        int op = 0;

        while (!salir) {
            MenuHelper.imprimirMenu(Arrays.asList("1. Agregar Paciente",
                    "2. Listar pacientes",
                    "0. Salir"));
            try {
                op = sn.nextInt();
                switch (op) {
                    case 1:
                        crearPacienteMenu(sn);
                        break;
                    case 2:
                        listarPacientes();
                        break;
                    case 0:
                        salir = true;
                }
            } catch (Exception e) {
                System.out.println("Ocurrio un error al procesar su selección. Intente nuevamente!");
                e.printStackTrace();
                mostrarMenu(sn);
            }
        }
    }

    private static void listarPacientes() {
        System.out.println("Lista de pacientes:");
        ArrayList<Paciente> pacientes = clinica.getPacientes();
        if (pacientes.isEmpty())
            System.out.println("No hay pacientes registrados en la clinica");
        else
            pacientes.forEach(x -> System.out.println(x.toString()));
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

    protected static Paciente crearPacienteInexistente(Scanner sn, int dniPaciente) {
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

}
