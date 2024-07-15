package DAO;
import Modelo.*;

public class DAOusuario extends Conexion{
    public DAOusuario(){}
    public Usuario VerifcarUsuario(Usuario usu){
       Usuario usuario = null;
       String consulta="select * from usuarios where "
                        + "nombre_usuario=? and contrasena=?";
        try {
            ps= con.prepareStatement(consulta);
            ps.setString(1, usu.getUsername());
            ps.setString(2, usu.getPassword());
            rs= ps.executeQuery();
            if (rs.next()) {
                usuario = new Usuario();
                usuario.setId_usuario(rs.getString("id_usuario"));
                usuario.setUsername(rs.getString("nombre_usuario"));
                usuario.setPassword(rs.getString("contrasena"));
                usuario.setRol(rs.getString("rol"));
            }
        } catch (Exception ex) {
            System.out.println("ERROR: "+ex.getMessage());
        }
        return usuario;
    }
}//fin de la clase
