package Modelo;

public class Citas {

    private String id_cita;
    private String fecha_cita;
    private String hora_cita;
    private String id_padre;
    private String id_hijo;
    private String id_servicio;
    private String id_especialista;
    private String id_usuario;
    private String situacion;
    private String nombre_padre;
    private String nombre_hijo;
    private String nombre_servicio;
    private String nombre_especialista;
    private String nombre_usuario;

    public Citas() {}

    public String getId_cita() {return id_cita;}
    public void setId_cita(String id_cita) {this.id_cita = id_cita;}

    public String getFecha_cita() {return fecha_cita;}
    public void setFecha_cita(String fecha_cita) {this.fecha_cita = fecha_cita;}

    public String getHora_cita() {return hora_cita;}
    public void setHora_cita(String hora_cita) {this.hora_cita = hora_cita;}

    public String getId_padre() {return id_padre;}
    public void setId_padre(String id_padre) {this.id_padre = id_padre;}

    public String getId_hijo() {return id_hijo;}
    public void setId_hijo(String id_hijo) {this.id_hijo = id_hijo;}

    public String getId_servicio() {return id_servicio;}
    public void setId_servicio(String id_servicio) {this.id_servicio = id_servicio;}

    public String getId_especialista() {return id_especialista;}
    public void setId_especialista(String id_especialista) {this.id_especialista = id_especialista;}

    public String getId_usuario() {return id_usuario;}
    public void setId_usuario(String id_usuario) {this.id_usuario = id_usuario;}
    
    public String getSituacion() {return situacion;}
    public void setSituacion(String situacion) {this.situacion = situacion;}
    
    public String getNombre_padre() {return nombre_padre;}
    public void setNombre_padre(String nombre_padre) {this.nombre_padre = nombre_padre;}

    public String getNombre_hijo() {return nombre_hijo;}
    public void setNombre_hijo(String nombre_hijo) {this.nombre_hijo = nombre_hijo;}

    public String getNombre_servicio() {return nombre_servicio;}
    public void setNombre_servicio(String nombre_servicio) {this.nombre_servicio = nombre_servicio;}

    public String getNombre_especialista() {return nombre_especialista;}
    public void setNombre_especialista(String nombre_especialista) {this.nombre_especialista = nombre_especialista;}

    public String getNombre_usuario() {return nombre_usuario;}
    public void setNombre_usuario(String nombre_usuario) {this.nombre_usuario = nombre_usuario;}   
}

