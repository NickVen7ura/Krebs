package Intefaces;
//libreria
import Modelo.Citas;
import java.util.List;
public interface CRUDcitas {
    public List ListarCitas();
    public Citas Obtener(String id);
    public boolean Agregar(Citas car);
    public boolean Editar(Citas car);
    public boolean Eliminar(String id);
}