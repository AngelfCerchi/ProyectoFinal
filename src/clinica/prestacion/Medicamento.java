package clinica.prestacion;

public class Medicamento {

    private String nombre;
    private int gramos;

    public Medicamento(String nombre, int gramos) {
        this.nombre = nombre;
        this.gramos = gramos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getGramos() {
        return gramos;
    }

    public void setGramos(int gramos) {
        this.gramos = gramos;
    }

    @Override
    public String toString() {
        return "Medicamento{" +
                "nombre='" + nombre + '\'' +
                ", gramos=" + gramos +
                '}';
    }
}
