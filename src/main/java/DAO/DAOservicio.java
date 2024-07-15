package DAO;

import Modelo.*;
import java.sql.*;
import java.util.*;
import Intefaces.*;
import javax.swing.JOptionPane;


public class DAOservicio implements CRUDservicios{
    
    Conexion conexion = new Conexion();
    Connection con = conexion.getConnection();
    PreparedStatement ps;
    ResultSet rs;
    Servicios s = new Servicios();
    
@Override
    public List ListarCargo() {
        ArrayList<Servicios> Lista = new ArrayList();
        String consulta = "SELECT * FROM servicios where estado_servicio='S'";
        try {
            con = conexion.getConnection();
            ps = con.prepareStatement(consulta);
            rs = ps.executeQuery();
            while (rs.next()) {
                Servicios s = new Servicios();
                s.setId_servicio(rs.getString("id_servicio"));
                s.setNombre_servicio(rs.getString("nombre_servicio"));
                s.setDescripcion(rs.getString("descripcion"));
                s.setPrecio(rs.getString("precio"));
                Lista.add(s);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "ERROR no se puede extraer los datos.." + ex);
        }
        return Lista;
    }

@Override
    public Servicios Obtener(String id) {
        String consulta = "select * from servicios where id_servicio=?";
        try {
            con = conexion.getConnection();
            ps = con.prepareStatement(consulta);
            ps.setString(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                s.setId_servicio(rs.getString("id_servicio"));
                s.setNombre_servicio(rs.getString("nombre_servicio"));
                s.setDescripcion(rs.getString("descripcion"));
                s.setPrecio(rs.getString("precio"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error no se puede obtener el servicio" + e);
        }
        return s;
    }

@Override
    public boolean Agregar(Servicios ser) {
        String sqlinsert = "insert into servicios (nombre_servicio, descripcion, precio, estado_servicio) values (?,?,?,'S')";
        try {
            con = conexion.getConnection();
            ps = con.prepareStatement(sqlinsert);
            ps.setString(1, ser.getNombre_servicio());
            ps.setString(2, ser.getDescripcion());
            ps.setString(3, ser.getPrecio());
            ps.execute();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR no se puedo insertar servicios .. " + e);
        }
        return false;
    }

    @Override
    public boolean Editar(Servicios ser) {
        String sqlupdate = "update servicios set nombre_servicio = ?, descripcion=?, precio=? where id_servicio=?";
        try {
            con = conexion.getConnection();
            ps = con.prepareStatement(sqlupdate);
            ps.setString(1, ser.getNombre_servicio());
            ps.setString(2, ser.getDescripcion());
            ps.setString(3, ser.getPrecio());
            ps.setString(4, ser.getId_servicio());
            ps.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR no se puedo actualizar servicios .." + e);
        }
        return false;
    }

    @Override
    public boolean Eliminar(String id) {
        String sqleliminar = "update servicios set estado_servicio='N' where id_servicio=?";
            try {
            con = conexion.getConnection();
            ps = con.prepareStatement(sqleliminar);
            ps.setString(1, id);
            ps.executeUpdate();
         } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "ERROR al eliminar"+e);
        }
        return false;
    } 
    
        
}
