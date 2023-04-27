package data;
import modelo.Casa;
import modelo.Estudiante;
import java.sql.*;

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


}
