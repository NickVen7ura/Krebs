package DAO;
import java.sql.*;
public class Conexion implements Parametros{
    public Connection con;
    public PreparedStatement ps;
    public Statement smt;
    public ResultSet rs;
    public Conexion(){
        try {
            Class.forName(Driver);
            con = DriverManager.getConnection(url, USER, CLAVE);
            smt = con.createStatement();
        } catch (Exception ex) {
            System.out.println("Error al conectar la base de datos");
        }
    }
     public Connection getConnection(){
        return con;
    }
}
