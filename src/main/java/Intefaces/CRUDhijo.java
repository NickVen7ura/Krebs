package Intefaces;

import Modelo.*;
import java.util.List;

public interface CRUDhijo {
    public List ListarHijos();
    public Hijo Obtener(String id);
    public boolean Agregar(Hijo hi);
    public boolean Editar(Hijo hi);
    public boolean Eliminar(String id);
}
