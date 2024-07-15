package Intefaces;
import Modelo.Usuario;
import java.util.List;

public interface CRUDrecepcionista {
    public List ListarUsuarios();
    public Usuario Obtener(String id);
    public boolean Agregar(Usuario usu);
    public boolean Editar(Usuario usu);
    public boolean Eliminar(String id);
}
