package Controlador;

import DAO.*;
import Modelo.Servicios;
import javax.servlet.RequestDispatcher;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControladorServicios extends HttpServlet {
    String listarservicios = "index.jsp";
    String vistaagregar = "VistasServicios/AgregarServicios.jsp";
    String vistaeditar = "VistasServicios/EditarServicios.jsp";
    Servicios s = new Servicios();
    DAOservicio dao = new DAOservicio();
    String idser;

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String ruta = "";
        String accion = request.getParameter("accion");
        if (accion.equalsIgnoreCase("listar")) {
            ruta = listarservicios;
        } else if (accion.equalsIgnoreCase("mostraragregar")) {
            ruta = vistaagregar;
        } else if (accion.equalsIgnoreCase("Agregar")) {
            LeerDatosServicio(request, response);
            dao.Agregar(s);
            ruta = listarservicios;
        } else if (accion.equalsIgnoreCase("editar")) {
            request.setAttribute("idser", request.getParameter("idser"));
            ruta=vistaeditar;
        } else if (accion.equalsIgnoreCase("Actualizar")) {
            LeerDatosServicio(request, response);
            dao.Editar(s);
            ruta = listarservicios;
        } else if (accion.equalsIgnoreCase("eliminar")) {
            idser= request.getParameter("idser");
            dao.Eliminar(idser);
            ruta=listarservicios;
        }
        RequestDispatcher vista = request.getRequestDispatcher(ruta);
        vista.forward(request, response);

    }
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
    private void LeerDatosServicio(HttpServletRequest request, HttpServletResponse response) {
        s.setId_servicio(request.getParameter("txtId"));
        s.setNombre_servicio(request.getParameter("txtNomSer"));
        s.setDescripcion(request.getParameter("txtDesc"));
        s.setPrecio(request.getParameter("txtPrec"));
    }
}
