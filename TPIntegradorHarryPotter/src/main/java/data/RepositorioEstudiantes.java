package data;
import modelo.Casa;
import modelo.Estudiante;
import java.sql.*;
import java.util.ArrayList;

public class RepositorioEstudiantes {

    private Connection conexion;

    public RepositorioEstudiantes(Connection conexion){
        this.conexion = conexion;

    }
    public void agregarEstudiante(Estudiante unEstudiante) {

        try (Statement sentenciaInsert = conexion.createStatement()) {
            String insert = "insert into Estudiantes (numero, nombre, genero, especie, blod_status, id_casa) values(";
            insert += unEstudiante.getNumero() + ", ";
            insert += "'" + unEstudiante.getNombre() + "',";
            insert += "'" + unEstudiante.getGenero() + "',";
            insert += "'" + unEstudiante.getEspecie() + "',";
            insert += "'" + unEstudiante.getBlodStatus() + "',";
            insert +=  + (unEstudiante.getCasa()).getIdCasa();
            insert += ")";
            //System.out.println(insert);
            sentenciaInsert.executeUpdate(insert);

        }
        catch (SQLException ex) {
            System.out.println("Error: en sentencia de creación de Estudiante: \n" + ex.getMessage());
            ex.printStackTrace();
        }

    }
    public boolean estudiantesEnTabla(int idEstudiante){

        boolean estudianteExiste = false;
        try (Statement sentenciaConsulta = conexion.createStatement()) {
            String query = "select nombre from Estudiantes where numero = ";
            query += idEstudiante;
            ResultSet rs = sentenciaConsulta.executeQuery(query);

            while (rs.next())
            {

                estudianteExiste = true;
            }

        }
        catch (SQLException ex) {
            System.out.println("Error: en sentencia de creación del Estudiante: \n" + ex.getMessage());
            ex.printStackTrace();
        }
        return estudianteExiste;

    }



    public ArrayList<Estudiante> getAllEstudiantes (){
        ArrayList<Estudiante> todosLosEstudiantes = new ArrayList<Estudiante>();
        try (Statement sentenciaConsulta = conexion.createStatement()) {
            String query = "select * from Estudiantes";
            ResultSet rs = sentenciaConsulta.executeQuery(query);


            while (rs.next())
            {
                int numero = rs.getInt("numero");
                String nombre = rs.getString("nombre");
                String genero = rs.getString("genero");
                String especie= rs.getString("especie");
                String blod_status = rs.getString("blod_status");
                String id_casa = rs.getString("id_casa");


                Estudiante estudianteActual = new Estudiante(numero,nombre,genero,especie,blod_status);
                todosLosEstudiantes.add(estudianteActual);



                StringBuilder builder = new StringBuilder("Datos Estudiante\n");
                builder.append("\tNúmero: ").append(numero)
                        .append("\tNombre y Apellido: ").append(nombre)
                        .append("\n\tGenero: ").append(genero)
                        .append("\n\tEspecie: ").append(especie)
                        .append("\n\tBloodStatus: ").append(blod_status)
                        .append("\n");
                System.out.println(builder.toString());

            }
            rs.close();
            sentenciaConsulta.close();
        }
        catch (SQLException ex) {
            System.out.println("Error: en sentencia de creación de estudiante: \n" + ex.getMessage());
            ex.printStackTrace();
        }
        return todosLosEstudiantes;
    }






}
