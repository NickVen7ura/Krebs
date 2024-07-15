package DAO;

import Modelo.*;
import java.sql.*;
import java.util.*;
import Intefaces.*;
import javax.swing.JOptionPane;

public class DAOespecialistas implements CRUDespecialistas {
    
    Conexion conexion = new Conexion();
    Connection con = conexion.getConnection();
    PreparedStatement ps;
    ResultSet rs;
    Especialistas e = new Especialistas();
    
    @Override
    public List<Especialistas> ListarEspecialistas() {
        ArrayList<Especialistas> lista = new ArrayList<>();
        String consulta = "SELECT * FROM especialistas where estado_especialista='S';";
        try {
            con = conexion.getConnection();
            ps = con.prepareStatement(consulta);
            rs = ps.executeQuery();
            while (rs.next()) {
                Especialistas especialista = new Especialistas();
                especialista.setId_especialista(rs.getString("id_especialista"));
                especialista.setNombre_especialista(rs.getString("nombre_especialista"));
                especialista.setEspecialidad(rs.getString("especialidad"));
                especialista.setCorreo_electronico(rs.getString("correo_electronico"));
                lista.add(especialista);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "ERROR al extraer los datos: " + ex);
        }
        return lista;
    }

    @Override
    public Especialistas Obtener(String id) {
        String consulta = "select * from especialistas WHERE id_especialista=?";
        try {
            con = conexion.getConnection();
            ps = con.prepareStatement(consulta);
            ps.setString(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                e.setId_especialista(rs.getString("id_especialista"));
                e.setNombre_especialista(rs.getString("nombre_especialista"));
                e.setEspecialidad(rs.getString("especialidad"));
                e.setCorreo_electronico(rs.getString("correo_electronico"));
            }
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al obtener el especialista: " + e);           
        }      
        return e;
    }
    
    @Override
    public boolean Agregar(Especialistas esp) {
        String sqlinsert = "insert into Especialistas (nombre_especialista,especialidad,correo_electronico,estado_especialista) values (?, ?, ?, 'S')";
        try {
            con = conexion.getConnection();
            ps = con.prepareStatement(sqlinsert);
            ps.setString(1, esp.getNombre_especialista());
            ps.setString(2, esp.getEspecialidad());
            ps.setString(3, esp.getCorreo_electronico());
            ps.executeUpdate();
            return true;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al insertar el especialista: " + ex);
            return false;
        }
    }

    @Override
    public boolean Editar(Especialistas esp) {
        String sqlupdate = "UPDATE especialistas SET nombre_especialista=?, especialidad=?, correo_electronico=? WHERE id_especialista=?";
        try {
            con = conexion.getConnection();
            ps = con.prepareStatement(sqlupdate);
            ps.setString(1, esp.getNombre_especialista());
            ps.setString(2, esp.getEspecialidad());
            ps.setString(3, esp.getCorreo_electronico());
            ps.setString(4, esp.getId_especialista());
            ps.executeUpdate();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al actualizar el especialista: " + ex);          
        }
        return false;
    }

    @Override
    public boolean Eliminar(String id) {
        String sqleliminar = "UPDATE Especialistas SET estado_especialista='N' WHERE id_especialista=?";
        try {
            con = conexion.getConnection();
            ps = con.prepareStatement(sqleliminar);
            ps.setString(1, id);
            ps.executeUpdate();
            return true;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar el especialista: " + ex);
            return false;
        }
    }   
}


