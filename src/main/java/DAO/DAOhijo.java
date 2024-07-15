package DAO;

import Intefaces.CRUDhijo;
import Modelo.Hijo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class DAOhijo implements CRUDhijo {

    Conexion conexion = new Conexion();
    Connection con = conexion.getConnection();
    PreparedStatement ps;
    ResultSet rs;
    Hijo h = new Hijo();

@Override
public List<Hijo> ListarHijos() {
    ArrayList<Hijo> listaHijo = new ArrayList<>();
    String consulta = "SELECT H.id_hijo, H.nombre_hijo, H.sexo, H.fecha_nacimiento, H.id_padre, P.nombre_padre " +
                      "FROM hijos H JOIN padres P ON H.id_padre = P.id_padre WHERE H.estado_hijo = 'S';";
    try {
        con = conexion.getConnection();
        ps = con.prepareStatement(consulta);
        rs = ps.executeQuery();
        while (rs.next()) {
            Hijo h = new Hijo();
            h.setId_hijo(rs.getString("id_hijo"));
            h.setNombre_hijo(rs.getString("nombre_hijo"));
            h.setSexo(rs.getString("sexo"));
            h.setFecha_nacimiento(rs.getString("fecha_nacimiento"));
            h.setId_padre(rs.getString("id_padre"));
            h.setNombre_padre(rs.getString("nombre_padre"));
            listaHijo.add(h);
        }
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, "ERROR al obtener datos de los hijos: " + ex);
    } finally {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    return listaHijo;
}

    
@Override
    public Hijo Obtener(String id) {
        Hijo h = new Hijo();
        String consulta = "SELECT H.id_hijo, H.nombre_hijo, H.sexo, H.fecha_nacimiento, H.id_padre, P.nombre_padre " +
                          "FROM hijos H JOIN Padres P ON H.id_padre = P.id_padre " +
                          "WHERE H.id_hijo = ?";
        try {
            con = conexion.getConnection();
            ps = con.prepareStatement(consulta);
            ps.setString(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                h.setId_hijo(rs.getString("id_hijo"));
                h.setNombre_hijo(rs.getString("nombre_hijo"));
                h.setSexo(rs.getString("sexo"));
                h.setFecha_nacimiento(rs.getString("fecha_nacimiento"));
                h.setId_padre(rs.getString("id_padre"));
                h.setNombre_padre(rs.getString("nombre_padre"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al obtener el hijo: " + e);
        }
        return h;
    }

@Override
public boolean Editar(Hijo hi) {
    String sqlupdate = "UPDATE hijos SET nombre_hijo=?, sexo=?, fecha_nacimiento=?, id_padre=? WHERE id_hijo=?";
    try {
        con = conexion.getConnection();
        ps = con.prepareStatement(sqlupdate);
        ps.setString(1, hi.getNombre_hijo());
        ps.setString(2, hi.getSexo());
        ps.setString(3, hi.getFecha_nacimiento());
        ps.setString(4, hi.getId_padre());
        ps.setString(5, hi.getId_hijo());
        int filasActualizadas = ps.executeUpdate();
        return filasActualizadas > 0;
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "ERROR al actualizar el hijo: " + e);
        return false;
    }
}


   
@Override
    public boolean Agregar(Hijo hi) {
        String sql = "INSERT INTO hijos (nombre_hijo, sexo, fecha_nacimiento, id_padre,estado_hijo) VALUES (?, ?, ?, ?, 'S')";
        try {
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, hi.getNombre_hijo());
            ps.setString(2, hi.getSexo());
            ps.setString(3, hi.getFecha_nacimiento());
            ps.setString(4, hi.getId_padre());
            int filasInsertadas = ps.executeUpdate();
            return filasInsertadas > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR al agregar el hijo: " + e);
            return false;
        }
}
  
@Override
    public boolean Eliminar(String id) {
        String sqleliminar = "UPDATE Hijos SET estado_hijo='N' WHERE id_hijo=?";
        try {
            con = conexion.getConnection();
            ps = con.prepareStatement(sqleliminar);
            ps.setString(1, id);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR al eliminar el hijo: " + e);
            return false;
        }
    }
}

    