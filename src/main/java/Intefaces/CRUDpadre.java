package Intefaces;

import Modelo.*;
import java.util.List;

public interface CRUDpadre {
    public List ListarPadres();
    public Padre Obtener(String id);
    public boolean Agregar(Padre pa);
    public boolean Editar(Padre pa);
    public boolean Eliminar(String id);
}