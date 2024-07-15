package Controlador;

import DAO.*;
import Modelo.*;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControladorRecepcionistas extends HttpServlet {
    String listaRecepcionistas = "index.jsp";
    String vistaAgregar = "VistasRecepcionistas/AgregarUsuarios.jsp";
    String vistaEditar = "VistasRecepcionistas/EditarUsuarios.jsp";
    Usuario u = new Usuario();
    DAOrecepcionista dao = new DAOrecepcionista();
    String idrec;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String ruta = "";
        String accion = request.getParameter("accion");
        if (accion.equalsIgnoreCase("listar")) {
            ruta = listaRecepcionistas;
        } else if (accion.equalsIgnoreCase("mostrarAgregar")) {
            ruta = vistaAgregar;
        } else if (accion.equalsIgnoreCase("Agregar")) {
            leerDatosRecepcionista(request,response);
            dao.Agregar(u);
            ruta = listaRecepcionistas;
        } else if (accion.equalsIgnoreCase("editar")) {
            request.setAttribute("idrec", request.getParameter("idrec"));
            ruta = vistaEditar;
        } else if (accion.equalsIgnoreCase("actualizar")) {
            leerDatosRecepcionista(request,response);
            dao.Editar(u);
            ruta = listaRecepcionistas;
        } else if (accion.equalsIgnoreCase("eliminar")) {
            idrec = request.getParameter("idrec");
            dao.Eliminar(idrec);
            ruta = listaRecepcionistas;
        }
        RequestDispatcher vista = request.getRequestDispatcher(ruta);
        vista.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

    private void leerDatosRecepcionista(HttpServletRequest request, HttpServletResponse response) {
        u.setId_usuario(request.getParameter("txtId_Recepcionista"));
        u.setUsername(request.getParameter("txtNombre_Recepcionista"));
        u.setPassword(request.getParameter("txtContrasena"));
        u.setRol(request.getParameter("txtRol"));
    }
}

