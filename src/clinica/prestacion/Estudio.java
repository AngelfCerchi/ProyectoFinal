package clinica.prestacion;

import java.time.LocalDateTime;

public class Estudio extends Prestacion {

    private LocalDateTime fechaYHoraRealizacion;
    private boolean asistio;

    public Estudio(String nombre) {
        super(nombre);
        this.asistio = false;
        this.setEsEstudio(true);
    }
}
