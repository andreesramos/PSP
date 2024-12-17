import java.io.Serializable;

public class Curso implements Serializable {
    String id;
    String descrpcion;

    Curso(String id, String descrpcion) {
        this.id = id;
        this.descrpcion = descrpcion;
    }

    public String getId() {return id;}
    public String getDescrpcion() {return descrpcion;}

    public void setId(String id) {this.id = id;}
    public void setDescrpcion(String descrpcion) {this.descrpcion = descrpcion;}

    @Override
    public String toString(){
        return "Curso{" + "id=" + id + ", descrpcion=" + descrpcion + '}';
    }
}
