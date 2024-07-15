package Intefaces;

import Modelo.Servicios;
import java.util.List;

public interface CRUDservicios {
    public List ListarCargo();
    public Servicios Obtener(String id);
    public boolean Agregar(Servicios ser);
    public boolean Editar(Servicios ser);
    public boolean Eliminar(String id);
}
