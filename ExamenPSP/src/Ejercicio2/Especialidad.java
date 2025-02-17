package Ejercicio2;

public class Especialidad {
    private int id;
    private String nombreespe;

    public Especialidad(int id, String nombreespe) {
        this.id = id;
        this.nombreespe = nombreespe;
    }

    @Override
    public String toString() {
        return "Especialidad: id=" + id + ", nombreespe=" + nombreespe;
    }
}
