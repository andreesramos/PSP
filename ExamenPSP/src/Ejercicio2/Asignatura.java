package Ejercicio2;

public class Asignatura {
    private int id;
    private String nombreasig;

    public Asignatura(int id, String nombreasig) {
        this.id = id;
        this.nombreasig = nombreasig;
    }

    @Override
    public String toString() {
        return "Asignatura: id=" + id + ", nombreasig=" + nombreasig;
    }
}
