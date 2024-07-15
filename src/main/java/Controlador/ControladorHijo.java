package Controlador;

import DAO.*;
import Modelo.*;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControladorHijo extends HttpServlet {

    String listarhijo = "index.jsp";
    String vistaagregar = "VistasHijos/AgregarHijo.jsp";
    String vistaeditar = "VistasHijos/EditarHijo.jsp";
    Hijo h = new Hijo();
    DAOhijo daoh = new DAOhijo();
    String idhi;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String ruta = "";
        String accion = request.getParameter("accion");
        if (accion.equalsIgnoreCase("listar")) {
            ruta = listarhijo;
        } else if (accion.equalsIgnoreCase("mostraragregar")) {
            ruta = vistaagregar;
        } else if (accion.equalsIgnoreCase("Agregar")) {
            LeerDatosHijos(request, response);
            daoh.Agregar(h);
            ruta = listarhijo;
        } else if (accion.equalsIgnoreCase("editar")) {
            request.setAttribute("idhi", request.getParameter("idhi"));
            ruta = vistaeditar;
        } else if (accion.equalsIgnoreCase("Actualizar")) {
            LeerDatosHijos(request, response);
            daoh.Editar(h);
            ruta = listarhijo;
        } else if (accion.equalsIgnoreCase("eliminar")) {
            idhi = request.getParameter("idhi");
            daoh.Eliminar(idhi);
            ruta = listarhijo;
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

    private void LeerDatosHijos(HttpServletRequest request, HttpServletResponse response) {
        h.setId_hijo(request.getParameter("id_hijo"));
        h.setNombre_hijo(request.getParameter("nombre_hijo"));
        h.setSexo(request.getParameter("sexo"));
        h.setFecha_nacimiento(request.getParameter("fecha_nacimiento"));
        h.setId_padre(request.getParameter("id_padre"));
    }
}
