package DAO;

import Intefaces.CRUDcitas;
import Modelo.Citas;
import java.sql.*;
import java.util.*;
import javax.swing.JOptionPane;


public class DAOcitas implements CRUDcitas {

    Conexion conexion = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Citas c = new Citas();

 @Override
    public List<Citas> ListarCitas() {
        ArrayList<Citas> Lista = new ArrayList<>();
        String consulta = "SELECT c.id_cita, DATE_FORMAT(c.fecha_cita, '%d/%m/%Y') AS fecha_programada, DATE_FORMAT(c.hora_cita, '%h:%i %p') AS hora_programada, " +
                  "p.nombre_padre, h.nombre_hijo, s.nombre_servicio, e.nombre_especialista, u.nombre_usuario, c.situacion " +
                  "FROM citas c " +
                  "JOIN Padres p ON c.id_padre = p.id_padre " +
                  "JOIN Hijos h ON c.id_hijo = h.id_hijo " +
                  "JOIN servicios s ON c.id_servicio = s.id_servicio " +
                  "JOIN Especialistas e ON c.id_especialista = e.id_especialista " +
                  "JOIN usuarios u ON c.id_usuario = u.id_usuario " +
                  "WHERE c.estado = 'S';";

        try {
            con = conexion.getConnection();
            ps = con.prepareStatement(consulta);
            rs = ps.executeQuery();
            while (rs.next()) {
                Citas c = new Citas();
                c.setId_cita(rs.getString("id_cita"));
                c.setFecha_cita(rs.getString("fecha_programada"));
                c.setHora_cita(rs.getString("hora_programada"));
                c.setNombre_padre(rs.getString("nombre_padre"));
                c.setNombre_hijo(rs.getString("nombre_hijo"));
                c.setNombre_servicio(rs.getString("nombre_servicio"));
                c.setNombre_especialista(rs.getString("nombre_especialista"));
                c.setNombre_usuario(rs.getString("nombre_usuario"));
                c.setSituacion(rs.getString("situacion"));
                Lista.add(c);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "ERROR no se puede extraer los datos de la Cita ..." + ex);
        }
        return Lista;
    }
    
@Override
    public Citas Obtener(String id) {
        String consulta = "SELECT c.id_cita, DATE_FORMAT(c.fecha_cita, '%d/%m/%Y') AS fecha_programada, DATE_FORMAT(c.hora_cita, '%h:%i') AS hora_programada, " +
                          "p.nombre_padre, h.nombre_hijo, s.nombre_servicio, e.nombre_especialista, u.nombre_usuario, c.situacion " +
                          "FROM citas c " +
                          "JOIN Padres p ON c.id_padre = p.id_padre " +
                          "JOIN Hijos h ON c.id_hijo = h.id_hijo " +
                          "JOIN servicios s ON c.id_servicio = s.id_servicio " +
                          "JOIN Especialistas e ON c.id_especialista = e.id_especialista " +
                          "JOIN usuarios u ON c.id_usuario = u.id_usuario " +
                          "WHERE c.id_cita = ? " +
                          "ORDER BY c.fecha_cita, c.hora_cita";
        try {
            con = conexion.getConnection();
            ps = con.prepareStatement(consulta);
            ps.setString(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                Citas c = new Citas();
                c.setId_cita(rs.getString("id_cita"));
                c.setFecha_cita(rs.getString("fecha_programada"));
                c.setHora_cita(rs.getString("hora_programada"));
                c.setNombre_padre(rs.getString("nombre_padre"));
                c.setNombre_hijo(rs.getString("nombre_hijo"));
                c.setNombre_servicio(rs.getString("nombre_servicio"));
                c.setNombre_especialista(rs.getString("nombre_especialista"));
                c.setNombre_usuario(rs.getString("nombre_usuario"));
                c.setSituacion(rs.getString("situacion"));
                return c;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error no se puede obtener el cargo de las citas" + e);
        }
        return null;
    }

@Override
    public boolean Agregar(Citas car) {
        String sqlselect = "SELECT id_cita FROM citas WHERE fecha_cita = ? AND hora_cita = ?";
        String sqlinsert = "INSERT INTO citas (fecha_cita, hora_cita, id_padre, id_hijo, id_servicio, id_especialista, id_usuario, situacion, estado) VALUES (?, ?, ?, ?, ?, ?, ?, ?, 'S')";
        try {
            con = conexion.getConnection();
            ps = con.prepareStatement(sqlselect);
            ps.setString(1, car.getFecha_cita());
            ps.setString(2, car.getHora_cita());
            rs = ps.executeQuery();
            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "La hora seleccionada ya está reservada.");
                return false;
            } else {
                ps = con.prepareStatement(sqlinsert);
                ps.setString(1, car.getFecha_cita());
                ps.setString(2, car.getHora_cita());
                ps.setString(3, car.getId_padre());
                ps.setString(4, car.getId_hijo());
                ps.setString(5, car.getId_servicio());
                ps.setString(6, car.getId_especialista());
                ps.setString(7, car.getId_usuario());
                ps.setString(8, car.getSituacion());
                ps.execute();
                return true;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR no se pudo insertar cita .." + e);
        }
        return false;
    }

@Override
public boolean Editar(Citas car) {
    String sqlupdate = "UPDATE citas SET fecha_cita = ?, hora_cita = ?, id_padre = ?, id_hijo = ?, id_servicio = ?, id_especialista = ?, id_usuario = ?, situacion = ? WHERE id_cita = ?";
    try {
        con = conexion.getConnection();
        ps = con.prepareStatement(sqlupdate);
        ps.setString(1, car.getFecha_cita());
        ps.setString(2, car.getHora_cita());
        ps.setString(3, car.getId_padre());
        ps.setString(4, car.getId_hijo());
        ps.setString(5, car.getId_servicio());
        ps.setString(6, car.getId_especialista());
        ps.setString(7, car.getId_usuario());
        ps.setString(8, car.getSituacion());
        ps.setString(9, car.getId_cita());      
        int filasActualizadas = ps.executeUpdate();
        return filasActualizadas > 0;
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "ERROR al actualizar cita: " + e.getMessage());
        e.printStackTrace();  
    }
    return false;
}


@Override
    public boolean Eliminar(String id) {
        String sqleliminar = "UPDATE citas SET estado = 'N' WHERE id_cita = ?";
        try {
            con = conexion.getConnection();
            ps = con.prepareStatement(sqleliminar);
            ps.setString(1, id);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR al eliminar la cita" + e);
        }
        return false;
    }
}

