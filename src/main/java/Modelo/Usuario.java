
package Modelo;

public class Usuario {
    private String id_usuario;
    private String username;
    private String password;
    private String rol;
    
    public Usuario(){}
    
    public String getId_usuario() {return id_usuario;}
    public void setId_usuario(String id_usuario) {this.id_usuario = id_usuario;}
    
    public String getUsername(){return username;}
    public void setUsername(String username) {this.username = username;}
    
    public String getPassword(){return password;}
    public void setPassword(String password) {this.password = password;}

    public String getRol() {return rol;}
    public void setRol(String rol) {this.rol = rol;}
    
}
