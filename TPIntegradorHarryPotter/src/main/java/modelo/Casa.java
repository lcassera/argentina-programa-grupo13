package modelo;

import java.util.ArrayList;

public class Casa {

    private int idCasa;
    private String nombre;
    private ArrayList<Estudiante> estudiantes;

    public Casa(int idCasa, String nombre) {
        this.idCasa = idCasa;
        this.nombre = nombre;
        this.estudiantes = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public int getIdCasa() {
        return idCasa;
    }

    public int getCantidadEstudiantes(){
        return estudiantes.size();
    }

    public void agregarEstudiante(Estudiante e){
        if (e != null && e.getNombreCasa().equals(nombre)){
            e.setCasa(this);
            estudiantes.add(e);
        }
        else
            throw new RuntimeException("Nombre de casa inválido. [" + e.getNombreCasa() + "]");

    }



    //Metodo que devuelve un listado de estudiantes no humanos
    public ArrayList<Estudiante> estudiantesNoHumanos() {
        ArrayList<Estudiante> noHumanos = new ArrayList<>();
        for (Estudiante e : estudiantes) {
            if (!e.isHuman()) {
                noHumanos.add(e);
            }
        }
        return noHumanos;
    }

    //Metodo que devuelve un listado de estudiantes no humanos
    public ArrayList<Estudiante> estudiantesHumanos() {
        ArrayList<Estudiante> estudiantesHumanos = new ArrayList<>();
        for (Estudiante e : estudiantes) {
            if (e.isHuman()) {
                estudiantesHumanos.add(e);
            }
        }
        return estudiantesHumanos;
    }


}
