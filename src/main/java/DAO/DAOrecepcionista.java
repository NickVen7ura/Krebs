package DAO;

import Modelo.*;
import java.sql.*;
import java.util.*;
import javax.swing.JOptionPane;
import Intefaces.*;

public class DAOrecepcionista implements CRUDrecepcionista {
    Conexion conexion = new Conexion();
    Connection con = conexion.getConnection();
    PreparedStatement ps;
    ResultSet rs;
    Usuario u = new Usuario();

    @Override
    public List<Usuario> ListarUsuarios() {
        ArrayList<Usuario> Lista = new ArrayList<>();
        String consulta = "SELECT * FROM usuarios where estado='S'";
        try {
            con = conexion.getConnection();
            ps = con.prepareStatement(consulta);
            rs = ps.executeQuery();
            while (rs.next()) {
                Usuario u = new Usuario(); // Crear una nueva instancia en cada iteración
                u.setId_usuario(rs.getString("id_usuario"));
                u.setUsername(rs.getString("nombre_usuario"));
                u.setPassword(rs.getString("contrasena"));
                u.setRol(rs.getString("rol"));
                Lista.add(u);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "ERROR no se puede extraer los datos.." + ex);
        }
        return Lista;
    }
   
    @Override
    public Usuario Obtener(String id) {
                String consulta = "SELECT * FROM usuarios WHERE id_usuario = ?";
        try {
            con = conexion.getConnection();
            ps = con.prepareStatement(consulta);
            ps.setString(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                u.setId_usuario(rs.getString("id_usuario"));
                u.setUsername(rs.getString("nombre_usuario"));
                u.setPassword(rs.getString("contrasena"));
                u.setRol(rs.getString("rol"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error no se puede obtener el usuario" + e);
        }
        return u;
    }

@Override
    public boolean Agregar(Usuario usu) {
        String sqlinsert = "INSERT INTO usuarios (nombre_usuario, contrasena, rol, estado) VALUES (?,?,?,'S')";
        try {
            con = conexion.getConnection();
            ps = con.prepareStatement(sqlinsert);
            ps.setString(1, usu.getUsername());
            ps.setString(2, usu.getPassword());
            ps.setString(3, usu.getRol());
            ps.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR: no se puedo insertar Usuarios .. " + e);
        }
        return false;
    }
  
@Override
    public boolean Editar(Usuario usu) {
        String sqlupdate = "UPDATE usuarios SET nombre_usuario = ?, contrasena = ?, rol = ? WHERE id_usuario = ?";
        try {
            con = conexion.getConnection();
            ps = con.prepareStatement(sqlupdate);
            ps.setString(1, usu.getUsername());
            ps.setString(2, usu.getPassword());
            ps.setString(3, usu.getRol());
            ps.setString(4, usu.getId_usuario());
            ps.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR: no se puedo actualizar el Usuario .." + e);
        }
        return false;
    }
    @Override
    public boolean Eliminar(String id) {
        String sqldelete = "update usuarios set estado='N' where id_usuario=?";
        try {
            con = conexion.getConnection();
            ps = con.prepareStatement(sqldelete);
            ps.setString(1, id);
            ps.executeUpdate();
         } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "ERROR al eliminar"+e);
        }
        return false;
    } 
}

