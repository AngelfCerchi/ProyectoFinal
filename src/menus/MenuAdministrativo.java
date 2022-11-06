package menus;

import clinica.Clinica;
import clinica.Especialidad;
import clinica.prestacion.Prestacion;
import enums.TipoServicio;
import individuos.Administrativo;
import individuos.Paciente;

import java.util.Scanner;

public class MenuAdministrativo {



    public static void mostrarMenu(Administrativo administrativo) {

        //TODO er q esto funcione
        Clinica clinica = Clinica.getInstance();

        Scanner sn = new Scanner(System.in);
        boolean salir = false;
        int op;

        while (!salir) {
            System.out.println("1. Crear Prestacion");
            System.out.println("2. Prestaciones Activas");
            System.out.println("3. Especialidades con Turnos");
            System.out.println("4. Turnos del Paciente");
            System.out.println("5. Menu Paciente");
            System.out.println("6. Dar turno");
            System.out.println("7. Salir");
            op = sn.nextInt();

            switch (op) {
                case 1:
                    String nombre;
                    System.out.println("Nombre de la prestacion: ");
                    nombre = sn.next();
                    administrativo.crearPrestacion(nombre, new Especialidad(null));
                    mostrarMenu(administrativo);
                    break;
                case 2:
                    administrativo.prestacionesActivasPorEspecialidad(new Especialidad(null));
                    mostrarMenu(administrativo);
                    break;
                case 3:
                    administrativo.turnosDisponiblesPorPrestacion(new Prestacion(null));
                    mostrarMenu(administrativo);
                    break;
                case 6:
                    darTurno(clinica, sn);
                    mostrarMenu(administrativo);
                    break;
                case 0:
                    salir = true;
            }

        }
    }

    private static void darTurno(Clinica c,  Scanner sn){
        //Pido el DNI y lo busco en el singletone o lo creo...
        System.out.println("Ingrese el DNI del paciente: ");
        int dni = sn.nextInt();
        Paciente p = c.getPacientePorDni(String.valueOf(dni));

        if (p == null) {
            System.out.println("Paciente no encontrado. Ingrese su Apellido: ");
            String apellido = sn.next();

            //apellido
            System.out.println("Ingrese su nombre: ");
            String nombre = sn.next();
            //DNI

            System.out.println("Ingrese su DNI: ");
            int documento = sn.nextInt();
            //Tipo Servicvio

            System.out.println("Seleccione su tipo de cobertura: ");
            System.out.println(TipoServicio.mostrarTipos());

            int tipoServicio = sn.nextInt();


            Paciente nuevoPaciente = new Paciente(apellido, nombre, String.valueOf(dni), TipoServicio.seleccionarTipoPorIndice(tipoServicio));
            p = nuevoPaciente;

        }

        System.out.println("Seleccione la prestacion deseada:");
        //Imprimir todas las prestaciones
    //TODO imprimir aca las prestaciones de clinica
        int prestacionElegida = sn.nextInt();


    }

}