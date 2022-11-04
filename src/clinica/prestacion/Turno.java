package clinica.prestacion;

import java.time.LocalDateTime;

public class Turno {
    private String nombre;
    private LocalDateTime inicio;
    private LocalDateTime fin;
    // private Ubicacion ubicacion; Consultorio Laboratorio
    private Boolean ausente = true;
}
