public class Alumno {
    String idAlumno;
    String nombre;
    Curso curso;
    int nota;

    Alumno(String idAlumno, String nombre, Curso curso, int nota) {
        this.idAlumno = idAlumno;
        this.nombre = nombre;
        this.curso = curso;
        this.nota = nota;
    }

    public String getIdAlumno() {return idAlumno;}
    public String getNombre() {return nombre;}
    public Curso getCurso() {return curso;}
    public int getNota() {return nota;}

    public void setIdAlumno(String idAlumno) {this.idAlumno = idAlumno;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public void setCurso(Curso curso) {this.curso = curso;}
    public void setNota(int nota) {this.nota = nota;}
}
