package Controlador;

import DAO.*;
import Modelo.*;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControladorPadre extends HttpServlet {

    String listarpadre = "index.jsp";
    String vistaagregar = "VistasPadres/AgregarPadres.jsp";
    String vistaeditar = "VistasPadres/EditarPadres.jsp";
    Padre p = new Padre();
    DAOpadre dao = new DAOpadre();
    String idpa;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String ruta = "";
        String accion = request.getParameter("accion");
        if (accion.equalsIgnoreCase("listar")) {
            ruta = listarpadre;
        } else if (accion.equalsIgnoreCase("mostraragregar")) {
            ruta = vistaagregar;
        } else if (accion.equalsIgnoreCase("Agregar")) {
            LeerDatosPadres(request, response);
            dao.Agregar(p);
            ruta = listarpadre;
        } else if (accion.equalsIgnoreCase("editar")) {
            request.setAttribute("idpa", request.getParameter("idpa"));
            ruta = vistaeditar;
        } else if (accion.equalsIgnoreCase("Actualizar")) {
            LeerDatosPadres(request, response);
            dao.Editar(p);
            ruta = listarpadre;
        } else if (accion.equalsIgnoreCase("eliminar")) {
            idpa = request.getParameter("idpa");
            dao.Eliminar(idpa);
            ruta = listarpadre;
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

    private void LeerDatosPadres(HttpServletRequest request, HttpServletResponse response) {
        p.setId_padre(request.getParameter("txtId_padre"));
        p.setNombre_padre(request.getParameter("txtNombre_padre"));
        p.setDNI(request.getParameter("txtDNI"));
        p.setDireccion(request.getParameter("txtDireccion"));
        p.setTelefono(request.getParameter("txtTelefono"));
        p.setCorreo(request.getParameter("txtCorreo"));
    }
}
