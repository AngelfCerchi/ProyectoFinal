package clinica;

import clinica.prestacion.Estudio;
import clinica.prestacion.Prestacion;
import clinica.prestacion.PrestacionTradicional;
import clinica.ubicaciones.Ubicacion;
import individuos.Doctor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class GeneradorDeDatos {

    public static void crearDoctoresEspecialidadesYPrestaciones(ArrayList<Doctor> doctores, ArrayList<Prestacion> prestaciones, ArrayList<Especialidad> especialidades, ArrayList<Ubicacion> ubicaciones) {

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

        ubicaciones.addAll(Arrays.asList(ubicacionA,
                ubicacionB,
                ubicacionC,
                ubicacionD,
                ubicacionE,
                ubicacionF,
                ubicacionG,
                ubicacionH,
                ubicacionI));

        Especialidad radiologia = new Especialidad("Radiologia");
        Especialidad toxicologia = new Especialidad("Toxicologia");
        Especialidad psicologia = new Especialidad("Psicologia");
        Especialidad medicinaClinica = new Especialidad("Medicina Clinica");
        Especialidad dermatologia = new Especialidad("Dermatologia");
        Especialidad cardiologia = new Especialidad("Cardiologia");

        /**/

        Doctor nicolas = new Doctor("Nico", "Coluc", "37123231");
        Doctor nestorK = new Doctor("Nestor", "Krack", "123412612");
        Doctor florK = new Doctor("Florencia", "Kraken", "89754112");
        Doctor mercedes = new Doctor("Mereceds", "Passucci", "25789125");
        Doctor martinIbarrola = new Doctor("Martin", "Ibarrolla", "27898541");
        Doctor lisMont = new Doctor("Lis", "Monti", "37869099");
        Doctor ubaldoLanza = new Doctor("Ubaldo", "Lanza", "4089123");
        Doctor facu = new Doctor("Facu", "Coluc", "40430178");


        doctores.add(nicolas);
        doctores.add(nestorK);
        doctores.add(florK);
        doctores.add(mercedes);
        doctores.add(martinIbarrola);
        doctores.add(lisMont);
        doctores.add(ubaldoLanza);
        doctores.add(facu);

        Prestacion placaToraxica = new Estudio("RX Torax");
        placaToraxica.setEsEstudio(true);
        placaToraxica.setDoctorAsociado(nicolas);
        placaToraxica.setEspecialidad(radiologia);
        placaToraxica.setUbicacion(ubicacionA);

        Prestacion estudioAzufre = new Estudio("Analisis Azufre");
        estudioAzufre.setEsEstudio(true);
        estudioAzufre.setDoctorAsociado(nestorK);
        estudioAzufre.setEspecialidad(toxicologia);
        estudioAzufre.setUbicacion(ubicacionB);

        Prestacion estudioCianuro = new Estudio("Analisis Cianuro");
        estudioCianuro.setEsEstudio(true);
        estudioCianuro.setDoctorAsociado(florK);
        estudioCianuro.setEspecialidad(toxicologia);
        estudioCianuro.setUbicacion(ubicacionC);

        Prestacion terapia = new PrestacionTradicional("Terapia");
        terapia.setEsEstudio(false);
        terapia.setDoctorAsociado(mercedes);
        terapia.setEspecialidad(psicologia);
        terapia.setUbicacion(ubicacionD);

        Prestacion consulta = new PrestacionTradicional("Consulta Gral");
        consulta.setEsEstudio(false);
        consulta.setDoctorAsociado(ubaldoLanza);
        consulta.setEspecialidad(medicinaClinica);
        consulta.setUbicacion(ubicacionE);

        Prestacion consultaPsicologia = new PrestacionTradicional("Consulta Gral Psicologia");
        consulta.setEsEstudio(false);
        consulta.setDoctorAsociado(facu);
        consulta.setEspecialidad(psicologia);
        consulta.setUbicacion(ubicacionE);

        Prestacion biopsiaPiel = new Estudio("Biopsia de Piel");
        biopsiaPiel.setEsEstudio(true);
        biopsiaPiel.setDoctorAsociado(lisMont);
        biopsiaPiel.setEspecialidad(dermatologia);
        biopsiaPiel.setUbicacion(ubicacionF);

        Prestacion electroCardiograma = new Estudio("Electrocardiograma");
        electroCardiograma.setEsEstudio(true);
        electroCardiograma.setDoctorAsociado(martinIbarrola);
        electroCardiograma.setEspecialidad(cardiologia);
        electroCardiograma.setUbicacion(ubicacionH);


        radiologia.getPrestaciones().add(placaToraxica);

        toxicologia.getPrestaciones().add(estudioAzufre);
        toxicologia.getPrestaciones().add(estudioCianuro);

        consultaPsicologia.setActiva(false);
        terapia.setActiva(true);
        psicologia.getPrestaciones().add(terapia);
        psicologia.getPrestaciones().add(consultaPsicologia);

        medicinaClinica.getPrestaciones().add(consulta);

        dermatologia.getPrestaciones().add(biopsiaPiel);

        cardiologia.getPrestaciones().add(electroCardiograma);

        prestaciones.add(placaToraxica);
        prestaciones.add(estudioAzufre);
        prestaciones.add(estudioCianuro);
        prestaciones.add(terapia);
        prestaciones.add(consulta);
        prestaciones.add(biopsiaPiel);
        prestaciones.add(electroCardiograma);
        prestaciones.add(consultaPsicologia);

        /**/

        especialidades.add(radiologia);
        especialidades.add(toxicologia);
        especialidades.add(psicologia);
        especialidades.add(medicinaClinica);
        especialidades.add(dermatologia);
        especialidades.add(cardiologia);

    }

    public static void asignarTurnosAPrestaciones(HashMap<Prestacion, ArrayList<Turno>> turnos, HashMap<Prestacion, ArrayList<Turno>> sobreTurnos, ArrayList<Prestacion> prestaciones) {
        for (Prestacion p : prestaciones) {
            crearTurnosYSobreTurnosParaPrestacion(turnos, sobreTurnos, p);
        }
    }

    public static void crearTurnosYSobreTurnosParaPrestacion(HashMap<Prestacion, ArrayList<Turno>> turnos, HashMap<Prestacion, ArrayList<Turno>> sobreTurnos, Prestacion prestacion) {
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

        ArrayList<Turno> sobreTurnosDisponibles = new ArrayList<>();
        sobreTurnosDisponibles.add(t9);
        sobreTurnosDisponibles.add(t10);
        sobreTurnosDisponibles.add(t11);
        sobreTurnosDisponibles.add(t12);
        sobreTurnosDisponibles.add(t13);
        sobreTurnosDisponibles.add(t14);
        sobreTurnosDisponibles.add(t15);
        sobreTurnosDisponibles.add(t16);

        turnos.put(prestacion, turnosDisponibles);
        sobreTurnos.put(prestacion, sobreTurnosDisponibles);
    }
}
