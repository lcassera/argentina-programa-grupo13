package data;

import modelo.Casa;
import modelo.Estudiante;

import java.sql.*;
import java.util.ArrayList;

public class RepositorioCasas {

    private Connection conexion;

    public RepositorioCasas(Connection conexion){
        this.conexion = conexion;

    }

    public void agregarCasa(Casa unaCasa) {

        try (Statement sentenciaInsert = conexion.createStatement()) {
            String insert = "insert into Casas (id_casa, nombre) values(";
            insert += unaCasa.getIdCasa() + ", ";
            // Sanitizamos el nombre de la casa por seguridad
            String nombreCasa = unaCasa.getNombre().replace("'", "\'");
            insert += "'" + nombreCasa + "'";
            insert += ")";
            sentenciaInsert.executeUpdate(insert);

        }
        catch (SQLException ex) {
            System.out.println("Error: en sentencia de creación de casa: \n" + ex.getMessage());
            ex.printStackTrace();
        }

    }

    public Casa getCasa(int idCasa){
        Casa result = null;
        try (Statement sentenciaConsulta = conexion.createStatement()) {
            String query = "select nombre from Casas where id_Casa = ";
            query += idCasa;
            ResultSet rs = sentenciaConsulta.executeQuery(query);

            while (rs.next())
            {
                String nombre = rs.getString(1);
                result = new Casa(idCasa, nombre);

            }

        }
        catch (SQLException ex) {
            System.out.println("Error: en sentencia de creación de casa: \n" + ex.getMessage());
            ex.printStackTrace();
        }
        return result;

    }

    public ArrayList<Casa> getAllCasa(){
        ArrayList<Casa> todasLasCasas = new ArrayList<Casa>();
        try (Statement sentenciaConsulta = conexion.createStatement()) {
            String query = "select * from Casas";
            ResultSet rs = sentenciaConsulta.executeQuery(query);

            while (rs.next())
            {
                int id_casa = rs.getInt("id_casa");
                String nombre = rs.getString("nombre");

                Casa casaActual = new Casa(id_casa,nombre);
                todasLasCasas.add(casaActual);


                StringBuilder builder = new StringBuilder();
                builder.append("\n\t").append(id_casa)
                        .append(". Casa: ").append(nombre);
                System.out.println(builder.toString());
            }

        }
        catch (SQLException ex) {
            System.out.println("Error: en sentencia recuperación casa: \n" + ex.getMessage());
            ex.printStackTrace();
        }
        return todasLasCasas;

    }

}
