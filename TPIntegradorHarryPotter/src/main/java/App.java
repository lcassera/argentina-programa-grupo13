import modelo.Estudiante;
import proceso.Hogwarts;
import proceso.ProcesadorArchivoCsv;

import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args){
        Scanner miEscanner = new Scanner(System.in);

        System.out.println("Cargador de Datos de Hogwards");
        System.out.println("=============================");
        System.out.println();

        System.out.print("Ingrese el nombre del archivo de datos: ");
        String nombreArchivo = miEscanner.nextLine();

        System.out.println("\nProcesando archivo...");

        ProcesadorArchivoCsv procArchivo = new ProcesadorArchivoCsv(nombreArchivo);

        ArrayList<Estudiante> lista = procArchivo.procesarArchivoConValidacion();

       System.out.println("Proceso finalizado, " + lista.size() + " estudiantes leídos.");

       System.out.println("\nLista de Estudiantes:");
        System.out.println("---------------------------------");
        for(Estudiante e : lista)
           System.out.println(e);

        Hogwarts hogwarts = new Hogwarts();
        for(Estudiante e : lista){
            hogwarts.agregarEstudiante(e);
        }

        System.out.println("---------------------------------");
        System.out.println("Listado de estudiantes no humanos");
        System.out.println("---------------------------------");
        for (String casa : new String[] {"Gryffindor", "Slytherin", "Hufflepuff", "Ravenclaw"}) {
            ArrayList<Estudiante> estudiantesNohumanos = hogwarts.getCasa(casa).estudiantesNoHumanos();
            for (Estudiante e : estudiantesNohumanos) {
                System.out.println(e);
            }
        }
        System.out.println("---------------------------------");
        System.out.println("Cantidad de Estudiantes por casa: ");
        System.out.println("---------------------------------");
        for (String casa : new String[] {"Gryffindor", "Slytherin", "Hufflepuff", "Ravenclaw"}){
            System.out.println("Casa: " + casa + " ==> " + hogwarts.getCasa(casa).getCantidadEstudiantes() + " estudiantes");
        }
        System.out.println();
        System.out.println("---------------------------------");

    }
}