package Controlador;

import DAO.*;
import Modelo.Especialistas;
import javax.servlet.RequestDispatcher;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControladorEspecialistas extends HttpServlet {
    String listarEspecialistas = "index.jsp";
    String vistaAgregarEspecialista = "VistasEspecialistas/AgregarEspecialistas.jsp";
    String a = "VistasEspecialistas/EditarEspecialistas.jsp";
    Especialistas e = new Especialistas();
    DAOespecialistas daoEspecialista = new DAOespecialistas();
    String idesp;

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String ruta = "";
        String accion = request.getParameter("accion");
        if (accion.equalsIgnoreCase("listar")) {
            ruta = listarEspecialistas;
        } else if (accion.equalsIgnoreCase("mostraragregar")) {
            ruta = vistaAgregarEspecialista;
        } else if (accion.equalsIgnoreCase("agregar")) {
            leerEspecialista(request, response);
            daoEspecialista.Agregar(e);
            ruta = listarEspecialistas;
        } else if (accion.equalsIgnoreCase("editar")) {
            request.setAttribute("idesp", request.getParameter("idesp"));
            ruta = a;
        } else if (accion.equalsIgnoreCase("actualizar")) {
            leerEspecialista(request, response);
            daoEspecialista.Editar(e);
            ruta = listarEspecialistas;
        } else if (accion.equalsIgnoreCase("eliminar")) {
            idesp = request.getParameter("idesp");
            daoEspecialista.Eliminar(idesp);
            ruta = listarEspecialistas;
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

    private void leerEspecialista(HttpServletRequest request, HttpServletResponse response) {
        e.setId_especialista(request.getParameter("txtId"));
        e.setNombre_especialista(request.getParameter("txtNombre"));
        e.setEspecialidad(request.getParameter("txtEspecialidad"));
        e.setCorreo_electronico(request.getParameter("txtCorreo"));
    }
}

