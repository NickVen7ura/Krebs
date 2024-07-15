package Intefaces;

import Modelo.Especialistas;
import java.util.List;

public interface CRUDespecialistas {
    public List ListarEspecialistas();
    public Especialistas Obtener(String id);
    public boolean Agregar(Especialistas esp);
    public boolean Editar(Especialistas esp);
    public boolean Eliminar(String id);
}
