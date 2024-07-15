package Modelo;

public class Hijo{

    private String id_hijo;
    private String id_padre;
    private String nombre_hijo; 
    private String sexo;
    private String fecha_nacimiento;
    private String nombre_padre;    
    public Hijo(){}
    
    public String getId_hijo() {return id_hijo;}
    public void setId_hijo(String id_hijo) {this.id_hijo = id_hijo;}
    
    public String getId_padre() {return id_padre;}
    public void setId_padre(String id_padre) {this.id_padre = id_padre;}

    public String getNombre_hijo() {return nombre_hijo;}
    public void setNombre_hijo(String nombre_hijo) {this.nombre_hijo = nombre_hijo;}

    public String getSexo() {return sexo;}
    public void setSexo(String sexo) {this.sexo = sexo;}

    public String getFecha_nacimiento() {return fecha_nacimiento;}
    public void setFecha_nacimiento(String fecha_nacimiento) {this.fecha_nacimiento = fecha_nacimiento;}

    public String getNombre_padre() {return nombre_padre;}
    public void setNombre_padre(String nombre_padre) {this.nombre_padre = nombre_padre;}

}
   
