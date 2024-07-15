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

@WebServlet(name = "HijosData", urlPatterns = {"/HijosData"})
public class HijosData extends HttpServlet {

     protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        try {
            // Conexión a la base de datos 
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3305/krebs", "root", "");
            Statement st = con.createStatement();

            // Consulta SQL para obtener la cantidad de personas por sexo
            String sqlQuery = "SELECT sexo, COUNT(*) AS cantidad FROM hijos GROUP BY sexo";
            ResultSet rs = st.executeQuery(sqlQuery);

            // Arrays JSON para almacenar etiquetas (sexo) y valores (cantidad)
            JSONArray labels = new JSONArray();
            JSONArray data = new JSONArray();

            // Iterar sobre los resultados de la consulta
            while (rs.next()) {
                labels.put(rs.getString("sexo"));
                data.put(rs.getInt("cantidad"));
            }

            // Crear objeto JSON con las etiquetas y datos
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("labels", labels);
            jsonObject.put("data", data);

            // Enviar la respuesta JSON al cliente
            out.print(jsonObject);
            out.flush();

            // Cerrar conexiones y recursos
            rs.close();
            st.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

