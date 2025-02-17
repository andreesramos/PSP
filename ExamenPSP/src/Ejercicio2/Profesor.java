package Ejercicio2;

public class Profesor {
    int idprofesor;
    String nombre;
    Asignatura[] asignaturas;
    Especialidad espe;

    public Profesor(int id, String nombre, Asignatura[] asignaturas, Especialidad espe) {
        this.idprofesor=id;
        this.nombre=nombre;
        this.asignaturas=asignaturas;
        this.espe=espe;
    }

    @Override
    public String toString() {
        return "Profesor: id=" + idprofesor + ", nombre=" + nombre + ", asignaturas=" + asignaturas + ", espe=" + espe;
    }
}
