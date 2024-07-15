package Modelo;

public class Servicios {
    private String id_servicio;
    private String nombre_servicio;
    private String descripcion;
    private String precio;
    
    public Servicios() {}

    public String getId_servicio() {return id_servicio;}
    public void setId_servicio(String id_servicio) {this.id_servicio = id_servicio;}

    public String getNombre_servicio() {return nombre_servicio;}
    public void setNombre_servicio(String nombre_servicio) {this.nombre_servicio = nombre_servicio;}

    public String getDescripcion() {return descripcion;}
    public void setDescripcion(String descripcion) {this.descripcion = descripcion;}

    public String getPrecio() {return precio;}
    public void setPrecio(String precio) {this.precio = precio;}
}

