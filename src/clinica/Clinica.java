package clinica;

import clinica.prestacion.Estudio;
import clinica.prestacion.Prestacion;
import clinica.prestacion.PrestacionTradicional;
import clinica.ubicaciones.Ubicacion;
import individuos.Director;
import individuos.Doctor;
import individuos.Paciente;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class Clinica {
    private static Clinica instance;
    private Director director;
    private ArrayList<Paciente> pacientes;
    private ArrayList<Especialidad> especialidades;
    private ArrayList<Prestacion> prestaciones;
    private HashMap<Prestacion, ArrayList<Turno>> turnos;
    private HashMap<Prestacion, ArrayList<Turno>> sobreTurnos;

    public Clinica() {
        this.director = new Director("Martin", "Abogado", "36758988");
        this.pacientes = new ArrayList<>();
        this.especialidades = new ArrayList<>();
        this.prestaciones = new ArrayList<>();
        this.turnos = new HashMap<>();
        this.sobreTurnos = new HashMap<>();
        inicializarEspecialidades();
        inicializarTurnosYSobreturnos();
    }

    public static Clinica getInstance() {
        if (instance == null) {
            instance = new Clinica();
        }
        return instance;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public ArrayList<Paciente> getPacientes() {
        return pacientes;
    }

    public void setPacientes(ArrayList<Paciente> pacientes) {
        this.pacientes = pacientes;
    }

    public ArrayList<Especialidad> getEspecialidades() {
        return especialidades;
    }

    public ArrayList<Prestacion> getPrestaciones() {
        return prestaciones;
    }

    public void setPrestaciones(ArrayList<Prestacion> prestaciones) {
        this.prestaciones = prestaciones;
    }

    public void setEspecialidades(ArrayList<Especialidad> especialidades) {
        this.especialidades = especialidades;
    }

    public HashMap<Prestacion, ArrayList<Turno>> getTurnos() {
        return turnos;
    }

    public void setTurnos(HashMap<Prestacion, ArrayList<Turno>> turnos) {
        this.turnos = turnos;
    }

    public HashMap<Prestacion, ArrayList<Turno>> getSobreTurnos() {
        return sobreTurnos;
    }

    public void setSobreTurnos(HashMap<Prestacion, ArrayList<Turno>> sobreTurnos) {
        this.sobreTurnos = sobreTurnos;
    }

    public void agregarPrestacion(Prestacion nuevaPrestacion, Especialidad especialidad) {
        for (Especialidad e : getEspecialidades()) {
            if (e.getNombre().equals(especialidad.getNombre())) {
                System.out.println("Se agregara la prestacion nueva para la especialidad " + especialidad.getNombre());
                e.getPrestaciones().add(nuevaPrestacion);
            }
            System.out.println("No existe la especialidad para la que se desea agregar la especliadad");
        }
    }

    public String reportePrestacionesPorDoctor(Doctor doctor) {
        int estudios = cantidadPrestaciones(doctor, true);
        int prestaciones = cantidadPrestaciones(doctor, false);
        return "El doctor " + doctor.getNombreCompleto() + " realizo " + estudios + " estudios y " + prestaciones + " prestaciones";
    }


    private int cantidadPrestaciones(Doctor doctor, boolean esEstudio) {
        int cantidad = 0;
        for (Prestacion p : this.turnos.keySet()) {
            for (Turno t : this.turnos.get(p)) {
                if (t.getDoctor().getDni().equals(doctor.getDni()) && !t.getAusente() && t.getPrestacionBrindada().getEsEstudio().equals(esEstudio)) {
                    cantidad++;
                }
            }
        }
        return cantidad;
    }

    public List<Turno> getTurnosDisponiblesPorPrestacion(Prestacion prestacion) {
        return obtenerTurnosDisponiblesSegunEspeciliadadYMapa(turnos, prestacion);
    }

    public List<Turno> getSobreTurnosDisponiblesPorPrestacion(Prestacion prestacion) {
        return obtenerTurnosDisponiblesSegunEspeciliadadYMapa(sobreTurnos, prestacion);
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
                for (Turno t : entry.getValue()) {
                    listaTurnos.add(t);
                }
            }
        }
        return listaTurnos;
    }

    public List<Turno> getTurnosAsistidosPorPrestacion(Prestacion prestacion) {
        return obtenerTurnosAsistidosSegunEspeciliadadYMapa(turnos, prestacion);
    }

    public List<Turno> getSobreTurnosAsistidosPorPrestacion(Prestacion prestacion) {
        return obtenerTurnosAsistidosSegunEspeciliadadYMapa(sobreTurnos, prestacion);
    }

    public List<Prestacion> getPrestacionesPorEspecialidad(Especialidad especialidad, boolean activas) {
        return getEspecialidades().stream()
                .filter(x -> x.getNombre().equals(especialidad.getNombre()))
                .findFirst().get().getPrestaciones().stream().filter(x -> x.getActiva().equals(activas)).collect(Collectors.toList());
    }


    public List<Prestacion> getPrestacionesActivas() {
        List<Prestacion> prestaciones = new ArrayList<>();
        getEspecialidades().stream().forEach(x -> prestaciones.addAll(getPrestacionesPorEspecialidad(x, false)));
        return prestaciones;
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
                .filter(x -> x.getPacienteAsociado().getDni().equals(paciente.getDni())).collect(Collectors.toList());
    }

    public void modificarEstadoDeActividadPrestacion(Prestacion prestacion, boolean activa) {
        getPrestaciones().stream().filter(x -> x.getNombre().equals(prestacion.getNombre())).findFirst().get().setActiva(activa);
    }

    private List<Turno> obtenerTurnosAsistidosSegunEspeciliadadYMapa(HashMap<Prestacion, ArrayList<Turno>> turnos, Prestacion prestacion) {
        List<Turno> turnosAsistidos = new ArrayList<>();
        for (Turno t : getTurnosPorPrestacion(turnos, prestacion)) {
            if (!t.getAusente())
                turnosAsistidos.add(t);
        }
        return turnosAsistidos;
    }

    private List<Turno> obtenerTurnosDisponiblesSegunEspeciliadadYMapa(HashMap<Prestacion, ArrayList<Turno>> turnos, Prestacion prestacion) {
        List<Turno> turnosDisponibles = new ArrayList<>();
        for (Turno t : getTurnosPorPrestacion(turnos, prestacion)) {
            if (t.getDisponible())
                turnosDisponibles.add(t);
        }
        return turnosDisponibles;
    }

    public ArrayList<Especialidad> listaDeEspecialidades(boolean especialidadesActivas) {
        ArrayList<Especialidad> listaNueva = new ArrayList<>();
        for (int i = 0; i < getEspecialidades().size(); i++) {
            if (getEspecialidades().get(i).getActiva().equals(especialidadesActivas))
                listaNueva.add(getEspecialidades().get(i));
        }
        return listaNueva;
    }

    public String listarPrestacionesActivas(Especialidad especialidad) {
        StringBuilder str = new StringBuilder();
        List<Prestacion> prestacionesDisponiblesPorEspecialidad = getPrestacionesPorEspecialidad(especialidad, true);
        for (Prestacion pdispo : prestacionesDisponiblesPorEspecialidad) {
            for (int i = 0; i < prestaciones.size(); i++) {
                if (prestaciones.get(i).getNombre().equals(pdispo.getNombre())) {
                    str.append(i).append(" - ").append(prestaciones.get(i).getNombre()).append("\n");
                }
            }
        }
        return str.toString();
    }

    public String listarHorariosDeTodosLosTurnosPorPrestacion(Prestacion prestacion) {
        StringBuilder str = new StringBuilder();
        List<Turno> listaTurnos = getTurnosDisponiblesPorPrestacion(prestacion);
        List<Turno> listaSobre = getSobreTurnosDisponiblesPorPrestacion(prestacion);

        for (int i = 0; i < listaTurnos.size(); i++) {
            Turno turno = listaTurnos.get(i);
            str.append(i + 1).append(" - ").append(turno.getHorario()).append("\n");
        }
        str.append("Sobre turnos:").append("\n");
        for (int i = 0; i < listaSobre.size(); i++) {
            Turno turno = listaSobre.get(i);
            str.append(i + 1 + listaTurnos.size()).append(" - ").append(turno.getHorario()).append("\n");
        }
        return str.toString();
    }

    public Turno seleccionarTurnoPorPrestacionConIndice(Prestacion prestacion, int indice) {
        Turno turno;
        if (indice < getTurnos().size()) {
            turno = getTurnos().get(prestacion).get(indice - 1);
        } else {
            turno = getSobreTurnos().get(prestacion).get(indice - 1 - getTurnos().get(prestacion).size());
        }
        return turno;
    }

    private void inicializarEspecialidades() {

        Ubicacion ubicacionA = new Ubicacion("Consultorio 01") {
        };
        Ubicacion ubicacionB = new Ubicacion("Consultorio 02") {
        };
        Ubicacion ubicacionC = new Ubicacion("Consultorio 03") {
        };
        Ubicacion ubicacionD = new Ubicacion("Consultorio 04") {
        };
        Ubicacion ubicacionE = new Ubicacion("Consultorio 05") {
        };

        Ubicacion ubicacionF = new Ubicacion("Consultorio 06") {
        };

        Ubicacion ubicacionG = new Ubicacion("Consultorio 07") {
        };

        Ubicacion ubicacionH = new Ubicacion("Consultorio 08") {
        };

        Ubicacion ubicacionI = new Ubicacion("Consultorio 09") {
        };


        Especialidad radiologia = new Especialidad("Radiologia");
        Especialidad toxicologia = new Especialidad("Toxicologia");
        Especialidad psicologia = new Especialidad("Psicologia");
        Especialidad medicinaClinica = new Especialidad("Medicina Clinica");
        Especialidad dermatologia = new Especialidad("Dermatologia");
        Especialidad cardiologia = new Especialidad("Cardiologia");

        /**/

        Prestacion placaToraxica = new Estudio("RX Torax");
        placaToraxica.setEsEstudio(true);
        placaToraxica.setDoctorAsociado(new Doctor("Nico", "Coluc", "37123231"));
        placaToraxica.setEspecialidad(radiologia);
        placaToraxica.setUbicacion(ubicacionA);

        Prestacion estudioAzufre = new Estudio("Analisis Azufre");
        estudioAzufre.setEsEstudio(true);
        estudioAzufre.setDoctorAsociado(new Doctor("Mart", "Law", "123412612"));
        estudioAzufre.setEspecialidad(toxicologia);
        estudioAzufre.setUbicacion(ubicacionB);

        Prestacion estudioCianuro = new Estudio("Analisis Cianuro");
        estudioCianuro.setEsEstudio(true);
        estudioCianuro.setDoctorAsociado(new Doctor("Mart", "Law", "123412612"));
        estudioCianuro.setEspecialidad(toxicologia);
        estudioCianuro.setUbicacion(ubicacionC);

        Prestacion terapia = new PrestacionTradicional("Terapia");
        terapia.setEsEstudio(false);
        terapia.setDoctorAsociado(new Doctor("Mercedes", "P", "123231723"));
        terapia.setEspecialidad(psicologia);
        terapia.setUbicacion(ubicacionD);

        Prestacion consulta = new PrestacionTradicional("Consulta Gral");
        consulta.setEsEstudio(false);
        consulta.setDoctorAsociado(new Doctor("Mart", "Law", "123412612"));
        consulta.setEspecialidad(medicinaClinica);
        consulta.setUbicacion(ubicacionE);

        Prestacion biopsiaPiel = new Estudio("Biopsia de Piel");
        biopsiaPiel.setEsEstudio(true);
        biopsiaPiel.setDoctorAsociado(new Doctor("Mart", "Law", "123412612"));
        biopsiaPiel.setEspecialidad(dermatologia);
        biopsiaPiel.setUbicacion(ubicacionF);

        Prestacion limpiezaFacial = new PrestacionTradicional("Limpieza cutis");
        limpiezaFacial.setEsEstudio(false);
        limpiezaFacial.setDoctorAsociado(new Doctor("Mart", "Law", "123412612"));
        limpiezaFacial.setEspecialidad(dermatologia);
        limpiezaFacial.setUbicacion(ubicacionG);

        Prestacion electroCardiograma = new Estudio("Electrocardiograma");
        electroCardiograma.setEsEstudio(true);
        electroCardiograma.setDoctorAsociado(new Doctor("Mart", "Law", "123412612"));
        electroCardiograma.setEspecialidad(cardiologia);
        electroCardiograma.setUbicacion(ubicacionH);

        Prestacion ergometria = new Estudio("Ergometria");
        ergometria.setEsEstudio(true);
        ergometria.setDoctorAsociado(new Doctor("Mart", "Law", "123412612"));
        ergometria.setEspecialidad(cardiologia);
        ergometria.setUbicacion(ubicacionI);


        radiologia.getPrestaciones().add(placaToraxica);

        toxicologia.getPrestaciones().add(estudioAzufre);
        toxicologia.getPrestaciones().add(estudioCianuro);

        psicologia.getPrestaciones().add(terapia);

        medicinaClinica.getPrestaciones().add(consulta);

        dermatologia.getPrestaciones().add(biopsiaPiel);
        dermatologia.getPrestaciones().add(limpiezaFacial);


        cardiologia.getPrestaciones().add(electroCardiograma);
        cardiologia.getPrestaciones().add(ergometria);
        cardiologia.getPrestaciones().add(consulta);

        this.prestaciones.add(placaToraxica);
        this.prestaciones.add(estudioAzufre);
        this.prestaciones.add(estudioCianuro);
        this.prestaciones.add(terapia);
        this.prestaciones.add(consulta);
        this.prestaciones.add(biopsiaPiel);
        this.prestaciones.add(limpiezaFacial);
        this.prestaciones.add(electroCardiograma);
        this.prestaciones.add(ergometria);

        /**/

        this.especialidades.add(radiologia);
        this.especialidades.add(toxicologia);
        this.especialidades.add(psicologia);
        this.especialidades.add(medicinaClinica);
        this.especialidades.add(dermatologia);
        this.especialidades.add(cardiologia);

    }

    private void inicializarTurnosYSobreturnos() {
        ArrayList<Especialidad> esp = new ArrayList<>();

        for (Especialidad e : this.especialidades) {
            esp.add(e);
        }

        //Los turnos son de 1 hora, politica de la Clinica.
        //En horario de almuerzo, de 13 a 14 hrs no se atienden turnos.

        LocalDateTime hoy = LocalDateTime.now();

        Turno t1 = new Turno(LocalDateTime.of(hoy.getYear(), hoy.getMonth(), hoy.getDayOfMonth(), 9, 0), LocalDateTime.of(hoy.getYear(), hoy.getMonth(), hoy.getDayOfMonth(), 10, 0));
        Turno t2 = new Turno(LocalDateTime.of(hoy.getYear(), hoy.getMonth(), hoy.getDayOfMonth(), 10, 0), LocalDateTime.of(hoy.getYear(), hoy.getMonth(), hoy.getDayOfMonth(), 11, 0));
        Turno t3 = new Turno(LocalDateTime.of(hoy.getYear(), hoy.getMonth(), hoy.getDayOfMonth(), 11, 0), LocalDateTime.of(hoy.getYear(), hoy.getMonth(), hoy.getDayOfMonth(), 12, 0));
        Turno t4 = new Turno(LocalDateTime.of(hoy.getYear(), hoy.getMonth(), hoy.getDayOfMonth(), 12, 0), LocalDateTime.of(hoy.getYear(), hoy.getMonth(), hoy.getDayOfMonth(), 13, 0));
        Turno t5 = new Turno(LocalDateTime.of(hoy.getYear(), hoy.getMonth(), hoy.getDayOfMonth(), 14, 0), LocalDateTime.of(hoy.getYear(), hoy.getMonth(), hoy.getDayOfMonth(), 15, 0));
        Turno t6 = new Turno(LocalDateTime.of(hoy.getYear(), hoy.getMonth(), hoy.getDayOfMonth(), 15, 0), LocalDateTime.of(hoy.getYear(), hoy.getMonth(), hoy.getDayOfMonth(), 16, 0));
        Turno t7 = new Turno(LocalDateTime.of(hoy.getYear(), hoy.getMonth(), hoy.getDayOfMonth(), 16, 0), LocalDateTime.of(hoy.getYear(), hoy.getMonth(), hoy.getDayOfMonth(), 17, 0));
        Turno t8 = new Turno(LocalDateTime.of(hoy.getYear(), hoy.getMonth(), hoy.getDayOfMonth(), 17, 0), LocalDateTime.of(hoy.getYear(), hoy.getMonth(), hoy.getDayOfMonth(), 18, 0));

        Turno t9 = new Turno(LocalDateTime.of(hoy.getYear(), hoy.getMonth(), hoy.getDayOfMonth(), 9, 0), LocalDateTime.of(hoy.getYear(), hoy.getMonth(), hoy.getDayOfMonth(), 10, 0));
        Turno t10 = new Turno(LocalDateTime.of(hoy.getYear(), hoy.getMonth(), hoy.getDayOfMonth(), 10, 0), LocalDateTime.of(hoy.getYear(), hoy.getMonth(), hoy.getDayOfMonth(), 11, 0));
        Turno t11 = new Turno(LocalDateTime.of(hoy.getYear(), hoy.getMonth(), hoy.getDayOfMonth(), 11, 0), LocalDateTime.of(hoy.getYear(), hoy.getMonth(), hoy.getDayOfMonth(), 12, 0));
        Turno t12 = new Turno(LocalDateTime.of(hoy.getYear(), hoy.getMonth(), hoy.getDayOfMonth(), 12, 0), LocalDateTime.of(hoy.getYear(), hoy.getMonth(), hoy.getDayOfMonth(), 13, 0));
        Turno t13 = new Turno(LocalDateTime.of(hoy.getYear(), hoy.getMonth(), hoy.getDayOfMonth(), 14, 0), LocalDateTime.of(hoy.getYear(), hoy.getMonth(), hoy.getDayOfMonth(), 15, 0));
        Turno t14 = new Turno(LocalDateTime.of(hoy.getYear(), hoy.getMonth(), hoy.getDayOfMonth(), 15, 0), LocalDateTime.of(hoy.getYear(), hoy.getMonth(), hoy.getDayOfMonth(), 16, 0));
        Turno t15 = new Turno(LocalDateTime.of(hoy.getYear(), hoy.getMonth(), hoy.getDayOfMonth(), 16, 0), LocalDateTime.of(hoy.getYear(), hoy.getMonth(), hoy.getDayOfMonth(), 17, 0));
        Turno t16 = new Turno(LocalDateTime.of(hoy.getYear(), hoy.getMonth(), hoy.getDayOfMonth(), 17, 0), LocalDateTime.of(hoy.getYear(), hoy.getMonth(), hoy.getDayOfMonth(), 18, 0));

        ArrayList<Turno> turnosDisponibles = new ArrayList<>();
        turnosDisponibles.add(t1);
        turnosDisponibles.add(t2);
        turnosDisponibles.add(t3);
        turnosDisponibles.add(t4);
        turnosDisponibles.add(t5);
        turnosDisponibles.add(t6);
        turnosDisponibles.add(t7);
        turnosDisponibles.add(t8);

        ArrayList<Turno> turnosDisponibles2 = new ArrayList<>();
        turnosDisponibles2.add(t9);
        turnosDisponibles2.add(t10);
        turnosDisponibles2.add(t11);
        turnosDisponibles2.add(t12);
        turnosDisponibles2.add(t13);
        turnosDisponibles2.add(t14);
        turnosDisponibles2.add(t15);
        turnosDisponibles2.add(t16);

        for (Prestacion p : prestaciones) {
            turnos.put(p, turnosDisponibles);
            sobreTurnos.put(p, turnosDisponibles2);
        }
    }
}
