package Modelo;

public class Padre {
    private String id_padre;
    private String nombre_padre;
    private String DNI;
    private String direccion;
    private String telefono;
    private String correo;
    public Padre(){}
    
    public String getId_padre() {return id_padre;}
    public void setId_padre(String id_padre) {this.id_padre = id_padre;}

    public String getNombre_padre() {return nombre_padre;}
    public void setNombre_padre(String nombre_padre) {this.nombre_padre = nombre_padre;}
    
    public String getDNI() {return DNI;}
    public void setDNI(String DNI) {this.DNI = DNI;}
    
    public String getDireccion() {return direccion;}
    public void setDireccion(String direccion) {this.direccion = direccion;}
    
    public String getTelefono() {return telefono;}
    public void setTelefono(String telefono) {this.telefono = telefono;}

    public String getCorreo() {return correo;}
    public void setCorreo(String correo) {this.correo = correo;}
}
