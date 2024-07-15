package Controlador;

import Modelo.*;
import DAO.*;
import javax.servlet.RequestDispatcher;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControladorCitas extends HttpServlet {

    String listarcitas = "index.jsp";
    String bodylinks = "bodylinks.jsp";
    String vistaagregar = "VistasCitas/AgregarCita.jsp";
    String vistaeditar = "VistasCitas/EditarCitas.jsp";
    Citas c = new Citas();
    DAOcitas dao = new DAOcitas();
    String id;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String ruta = "";
        String accion = request.getParameter("accion");
        if (accion.equalsIgnoreCase("listar")) {
            ruta = listarcitas;
        } else if (accion.equalsIgnoreCase("mostraragregar")) {
            ruta = vistaagregar;
        } else if (accion.equalsIgnoreCase("Agregar")) {
            LeerDatosCitas(request, response);
            dao.Agregar(c);
            ruta = listarcitas;
        } else if (accion.equalsIgnoreCase("Editar")) {
            request.setAttribute("idcit", request.getParameter("idcit"));
            ruta = vistaeditar;
        } else if (accion.equalsIgnoreCase("Actualizar")) {
            LeerDatosCitas(request, response);
            dao.Editar(c);
            ruta = listarcitas;
        } else if (accion.equalsIgnoreCase("eliminar")) {
            id = request.getParameter("idcit");
            dao.Eliminar(id);
            ruta = listarcitas;
        }
        
        RequestDispatcher vista = request.getRequestDispatcher(ruta);
        vista.forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet request
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

    private void LeerDatosCitas(HttpServletRequest request, HttpServletResponse response) {
        c.setId_cita(request.getParameter("id_cita"));
        c.setFecha_cita(request.getParameter("fecha_cita"));
        c.setHora_cita(request.getParameter("hora_cita"));
        c.setId_padre(request.getParameter("nombre_padre"));
        c.setId_hijo(request.getParameter("nombre_hijo"));
        c.setId_servicio(request.getParameter("nombre_servicio"));
        c.setId_especialista(request.getParameter("nombre_especialista"));
        c.setId_usuario(request.getParameter("nombre_usuario"));
        c.setSituacion(request.getParameter("situacion"));
    }
}
