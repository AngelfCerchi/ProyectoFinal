package clinica;

import clinica.prestacion.Prestacion;
import clinica.ubicaciones.Ubicacion;
import individuos.Director;
import individuos.Doctor;
import individuos.Paciente;


import java.util.*;
import java.util.stream.Collectors;

public class Clinica {
    private static Clinica instance;
    private String nombre;
    private Director director;
    private ArrayList<Paciente> pacientes;
    private ArrayList<Especialidad> especialidades;
    private ArrayList<Prestacion> prestaciones;
    private ArrayList<Doctor> doctores;
    private HashMap<Prestacion, ArrayList<Turno>> turnos;
    private HashMap<Prestacion, ArrayList<Turno>> sobreTurnos;
    private ArrayList<Ubicacion> ubicaciones;

    public Clinica() {
        this.nombre = "Clinica San Andres Juarez";
        this.director = new Director("Pedro", "Bolanios", "36758988");
        this.pacientes = new ArrayList<>();
        this.especialidades = new ArrayList<>();
        this.prestaciones = new ArrayList<>();
        this.turnos = new HashMap<>();
        this.sobreTurnos = new HashMap<>();
        this.doctores = new ArrayList<>();
        this.ubicaciones = new ArrayList<>();
        GeneradorDeDatos.crearDoctoresEspecialidadesYPrestaciones(this.doctores, this.prestaciones, this.especialidades, this.ubicaciones);
        GeneradorDeDatos.asignarTurnosAPrestaciones(this.turnos, this.sobreTurnos, this.prestaciones);
    }

    public static Clinica getInstance() {
        if (instance == null) {
            instance = new Clinica();
        }
        return instance;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Paciente> getPacientes() {
        return pacientes;
    }

    public ArrayList<Especialidad> getEspecialidades() {
        return especialidades;
    }

    public ArrayList<Prestacion> getPrestaciones() {
        return prestaciones;
    }

    public ArrayList<Doctor> getDoctores() {
        return doctores;
    }

    public HashMap<Prestacion, ArrayList<Turno>> getTurnos() {
        return turnos;
    }

    public HashMap<Prestacion, ArrayList<Turno>> getSobreTurnos() {
        return sobreTurnos;
    }

    public ArrayList<Ubicacion> getUbicaciones() {
        return ubicaciones;
    }

    public void setUbicaciones(ArrayList<Ubicacion> ubicaciones) {
        this.ubicaciones = ubicaciones;
    }

    public void agregarPrestacion(Prestacion nuevaPrestacion) {
        Especialidad especialidad = nuevaPrestacion.getEspecialidad();
        GeneradorDeDatos.crearTurnosYSobreTurnosParaPrestacion(turnos, sobreTurnos, nuevaPrestacion);
        getPrestaciones().add(nuevaPrestacion);
        for (Especialidad e : getEspecialidades()) {
            if (e.getNombre().equals(especialidad.getNombre())) {
                System.out.println("Se agregara la prestacion nueva para la especialidad " + especialidad.getNombre());
                e.getPrestaciones().add(nuevaPrestacion);
            }
        }
    }

    public String reportePrestacionesPorDoctor(Doctor doctor, boolean esEstudio) {
        int cantidad = 0;
        boolean encontreTurno = false;
        StringBuilder str = new StringBuilder();
        String realizadoPorDoctor;
        String mensajeSinResultados;
        if (esEstudio) {
            realizadoPorDoctor = "estudio/estudioss";
            str.append("Estudios brindados:\n");
            mensajeSinResultados = "El doctor seleccionado no tiene " + realizadoPorDoctor + " brindados\n";
        } else {
            realizadoPorDoctor = "prestacion/prestaciones";
            str.append("Prestaciones brindadas:\n");
            mensajeSinResultados = "El doctor seleccionado no tiene " + realizadoPorDoctor + " brindadas\n";
        }
        for (Prestacion p : this.turnos.keySet()) {
            for (Turno t : this.turnos.get(p)) {
                if (t.getDoctor() != null
                        && t.getDoctor().getDni().equals(doctor.getDni())
                        && t.getAsistio()
                        && t.getPrestacionBrindada().getEsEstudio().equals(esEstudio)) {
                    encontreTurno = true;
                    str.append("- ").append(t.getPrestacionBrindada().getNombre()).append(" - Horario: ").append(t.getHorario())
                            .append(" - Ubicacion: ").append(t.getUbicacionTurno().getNombre());
                    str.append("\n");
                    cantidad++;
                }
            }
        }
        str.append("---------------------------------------------------------------------\n");
        String mensaje = "El doctor " + doctor.getNombreCompleto() + " brindo en total " + cantidad + " " + realizadoPorDoctor + "\n";
        if (encontreTurno) {
            str.append(mensaje);
        } else {
            str.append(mensajeSinResultados);
        }
        str.append("---------------------------------------------------------------------\n");
        return str.toString();
    }

    public List<Turno> getTurnosDisponiblesPorPrestacion(Prestacion prestacion) {
        return obtenerTurnosDisponiblesSegunPrestacionYMapa(turnos, prestacion);
    }

    public List<Turno> getSobreTurnosDisponiblesPorPrestacion(Prestacion prestacion) {
        return obtenerTurnosDisponiblesSegunPrestacionYMapa(sobreTurnos, prestacion);
    }

    private List<Turno> getListaTurnosTotales() {
        List<Turno> listaTodosLosTurnos = new ArrayList<>();
        for (Prestacion p : getPrestaciones()) {
            listaTodosLosTurnos.addAll(getTurnosPorPrestacion(getTurnos(), p));
            listaTodosLosTurnos.addAll(getTurnosPorPrestacion(getSobreTurnos(), p));
        }
        return listaTodosLosTurnos;
    }

    private List<Turno> getTurnosPorPrestacion(HashMap<Prestacion, ArrayList<Turno>> turnos, Prestacion prestacion) {
        List<Turno> listaTurnos = new ArrayList<>();
        Set<Map.Entry<Prestacion, ArrayList<Turno>>> mapaTurnos = turnos.entrySet();
        for (Map.Entry<Prestacion, ArrayList<Turno>> entry : mapaTurnos) {
            if (entry.getKey().getNombre().equals(prestacion.getNombre())) {
                listaTurnos.addAll(entry.getValue());
            }
        }
        return listaTurnos;
    }

    public List<Prestacion> getPrestacionesPorEspecialidad(Especialidad especialidad, boolean activas) {
        return getEspecialidades().stream()
                .filter(x -> x.getNombre().equals(especialidad.getNombre()))
                .findFirst().get().getPrestaciones().stream().filter(x -> x.getActiva().equals(activas)).collect(Collectors.toList());
    }

    public Paciente getPacientePorDni(String dni) {
        for (Paciente p : getPacientes()) {
            if (p.getDni().equals(dni)) {
                return p;
            }
        }
        return null;
    }

    public List<Turno> getListaTurnosDePaciente(Paciente paciente) {
        return getListaTurnosTotales().stream()
                .filter(x -> x.getPacienteAsociado() != null)
                .filter(x -> x.getPacienteAsociado().getDni().equals(paciente.getDni()))
                .collect(Collectors.toList());
    }

    public void modificarEstadoDeActividadPrestacion(Prestacion prestacion, boolean activa) {
        getPrestaciones().stream().filter(x -> x.getNombre().equals(prestacion.getNombre())).findFirst().get().setActiva(activa);
    }

    private List<Turno> obtenerTurnosDisponiblesSegunPrestacionYMapa(HashMap<Prestacion, ArrayList<Turno>> turnos, Prestacion prestacion) {
        List<Turno> turnosDisponibles = new ArrayList<>();
        for (Turno t : getTurnosPorPrestacion(turnos, prestacion)) {
            if (t.getDisponible()) {
                turnosDisponibles.add(t);
            }
        }
        return turnosDisponibles;
    }

    public ArrayList<Especialidad> listaDeEspecialidades(boolean especialidadesActivas) {
        ArrayList<Especialidad> listaNueva = new ArrayList<>();
        for (int i = 0; i < getEspecialidades().size(); i++) {
            if (getEspecialidades().get(i).getActiva().equals(especialidadesActivas)) {
                listaNueva.add(getEspecialidades().get(i));
            }
        }
        return listaNueva;
    }

    public String listarHorariosDeTodosLosTurnosPorPrestacion(Prestacion prestacion) {
        StringBuilder str = new StringBuilder();
        List<Turno> listaTurnos = getTurnosDisponiblesPorPrestacion(prestacion);
        List<Turno> listaSobre = getSobreTurnosDisponiblesPorPrestacion(prestacion);

        if (!listaTurnos.isEmpty()) {
            for (int i = 0; i < listaTurnos.size(); i++) {
                Turno turno = listaTurnos.get(i);
                str.append(i + 1).append(" - ").append(turno.getHorario()).append("\n");
            }
        } else {
            str.append("No hay turnos disponibles\n");
        }
        if (!listaSobre.isEmpty()) {
            str.append("Sobre turnos:").append("\n");
            for (int i = 0; i < listaSobre.size(); i++) {
                Turno turno = listaSobre.get(i);
                str.append(i + 1 + listaTurnos.size()).append(" - ").append(turno.getHorario()).append("\n");
            }
        } else {
            str.append("No hay sobre turnos disponibles");
        }
        return str.toString();
    }

    public Turno seleccionarTurnoPorPrestacionConIndice(Prestacion prestacion, int indice) {
        Turno turno;
        List<Turno> listaTurnos = getTurnosDisponiblesPorPrestacion(prestacion);
        List<Turno> listaSobreTurnos = getSobreTurnosDisponiblesPorPrestacion(prestacion);
        if (indice <= listaTurnos.size()) {
            turno = listaTurnos.get(indice - 1);
        } else {
            turno = listaSobreTurnos.get(indice - 1 - listaTurnos.size());
        }
        return turno;
    }

    @Override
    public String toString() {
        return nombre + " Director: " + director.getNombreCompleto();
    }

    public Director getDirector() {
        return director;
    }
}
