/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

@WebServlet(name = "CitasData", urlPatterns = {"/CitasData"})
public class CitasData extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        try {
            Connection con = null;
            Statement st = null;
            ResultSet rs = null;

            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3305/krebs", "root", "");
            st = con.createStatement();
            rs = st.executeQuery("SELECT MONTH(fecha_cita) AS mes, COUNT(*) AS total FROM citas GROUP BY MONTH(fecha_cita)");

            JSONArray jsonArray = new JSONArray();
            JSONArray labels = new JSONArray();
            JSONArray values = new JSONArray();

            while (rs.next()) {
                labels.put(rs.getString("mes"));
                values.put(rs.getInt("total"));
            }

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("labels", labels);
            jsonObject.put("values", values);

            out.print(jsonObject);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}