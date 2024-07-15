/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import DAO.DAOusuario;
import Modelo.Usuario;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class Login extends HttpServlet {
@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            response.setContentType("text/html;charset=UTF-8");
            String accion = request.getParameter("accion");
        try {
            if (accion != null) {
                switch (accion) {
                    case "verificar":
                        VerificarLogin(request, response);
                        break;
                    case "cerrar":
                        CerrarSesion(request, response);
                        break;
                    default:
                        response.sendRedirect("Login.jsp");
                }
            } else {
                response.sendRedirect("Login.jsp");
            }

        } catch (Exception ex) {
            System.out.println("ERROR : " + ex.getMessage());
        }

    }

    private void VerificarLogin(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession sesion;
            DAOusuario dao;
            Usuario user = new Usuario();
            //leyendo los datos del formulario
            user.setUsername(request.getParameter("username"));
            user.setPassword(request.getParameter("password"));
            dao = new DAOusuario();
            Usuario usuario = dao.VerifcarUsuario(user);
            sesion = request.getSession();
            sesion.setAttribute("username", usuario);
            if (usuario != null) {
                request.setAttribute("mensaje", "Bienvenido al panel principal");
                response.sendRedirect("index.jsp");
            } else {
                request.setAttribute("mensaje", "CREDENCIALES INCORRECTAS!!!");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }

        } catch (Exception ex) {
            System.out.println("ERROR no se puede verificar " + ex.getMessage());
        }
    }//fin método

    private void CerrarSesion(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession sesion = request.getSession();
            sesion.setAttribute("username", null);
            sesion.invalidate();
            response.sendRedirect("login.jsp");
        } catch (Exception ex) {
            System.out.println("ERROR no se puede cerrar sesion " + ex.getMessage());
        }
    }
}
