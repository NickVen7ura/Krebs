package DAO;

import Modelo.*;
import java.sql.*;
import java.util.*;
import Intefaces.*;
import javax.swing.JOptionPane;

public class DAOpadre implements CRUDpadre {

    Conexion conexion = new Conexion();
    Connection con = conexion.getConnection();
    PreparedStatement ps;
    ResultSet rs;
    Padre p = new Padre();

    @Override
    public List ListarPadres() {
        ArrayList<Padre> Lista = new ArrayList();
        String consulta = "SELECT * FROM Padres where estado_padre='S';";
        try {
            con = conexion.getConnection();
            ps = con.prepareStatement(consulta);
            rs = ps.executeQuery();
            while (rs.next()) {
                Padre p = new Padre(); // Crear una nueva instancia de Clientes en cada iteración
                p.setId_padre(rs.getString("id_padre"));
                p.setNombre_padre(rs.getString("nombre_padre"));
                p.setDNI(rs.getString("DNI"));
                p.setDireccion(rs.getString("direccion"));
                p.setTelefono(rs.getString("telefono"));
                p.setCorreo(rs.getString("correo_electronico"));
                Lista.add(p);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "ERROR no se puede extraer los datos del padre.." + ex);
        }
        return Lista;
    }

    @Override
    public Padre Obtener(String id) {
        String consulta = "select * from Padres where id_padre=?";
        try {
            con = conexion.getConnection();
            ps = con.prepareStatement(consulta);
            ps.setString(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                p.setId_padre(rs.getString("id_padre"));
                p.setNombre_padre(rs.getString("nombre_padre"));
                p.setDNI(rs.getString("DNI"));
                p.setDireccion(rs.getString("direccion"));
                p.setTelefono(rs.getString("telefono"));
                p.setCorreo(rs.getString("correo_electronico"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error no se puede obtener el padre" + e);
        }
        return p;
    }

    @Override
    public boolean Agregar(Padre pa) {
        String sqlinsert = "insert into Padres (nombre_padre, DNI, direccion, telefono, correo_electronico, estado_padre) values (?,?,?,?,?,'S')";
        try {
            con = conexion.getConnection();
            ps = con.prepareStatement(sqlinsert);
            ps.setString(1, pa.getNombre_padre());
            ps.setString(2, pa.getDNI());
            ps.setString(3, pa.getDireccion());
            ps.setString(4, pa.getTelefono());
            ps.setString(5, pa.getCorreo());
            ps.execute();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR no se puedo insertar padres .." + e);
        }
        return false;
    }

    @Override
    public boolean Editar(Padre pa) {
        String sqlupdate = "update Padres set nombre_padre = ?, DNI=?, direccion=?, telefono=?, correo_electronico=? where id_padre=?";
        try {
            con = conexion.getConnection();
            ps = con.prepareStatement(sqlupdate);
            ps.setString(1, pa.getNombre_padre());
            ps.setString(2, pa.getDNI());
            ps.setString(3, pa.getDireccion());
            ps.setString(4, pa.getTelefono());
            ps.setString(5, pa.getCorreo());
            ps.setString(6, pa.getId_padre());
            ps.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR no se puedo actualizar padres .." + e);
        }
        return false;
    }

    @Override
    public boolean Eliminar(String id) {
        String sqleliminar = "update Padres set estado_padre='N' where id_padre=?";
        try {
            con = conexion.getConnection();
            ps = con.prepareStatement(sqleliminar);
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR al eliminar" + e);
        }
        return false;
    }

}
