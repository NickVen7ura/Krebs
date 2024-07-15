/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import com.itextpdf.text.*;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author lenovo
 */
@WebServlet(name = "boletaCita", urlPatterns = {"/boletaCita"})
public class boletaCita extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("aplication/pdf");
        //response.setHeader("Content-Disposition", "attachment; filename=boleta_cita_" + idCita + ".pdf");
        OutputStream out = response.getOutputStream();
        String idCita = request.getParameter("id_cita");
        
            try{
                Connection con = null;
                Statement st = null;
                ResultSet rs = null;
                
                Class.forName("com.mysql.jdbc.Driver");
                con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3305/krebs", "root", "");
                st = con.createStatement();
                rs = st.executeQuery("SELECT c.id_cita, DATE_FORMAT(c.fecha_cita, '%d/%m/%Y') AS fecha_programada, DATE_FORMAT(c.hora_cita, '%h:%i %p') AS hora_programada, " +
                        "p.nombre_padre, p.DNI, h.nombre_hijo, s.nombre_servicio, e.nombre_especialista, u.nombre_usuario, s.precio " +
                        "FROM citas c " +
                        "JOIN Padres p ON c.id_padre = p.id_padre " +
                        "JOIN Hijos h ON c.id_hijo = h.id_hijo " +
                        "JOIN servicios s ON c.id_servicio = s.id_servicio " +
                        "JOIN Especialistas e ON c.id_especialista = e.id_especialista " +
                        "JOIN usuarios u ON c.id_usuario = u.id_usuario " +
                        "WHERE c.id_cita = " + idCita);
                
                if (rs.next()) {
                Document documento = new Document(PageSize.A4.rotate());
                PdfWriter.getInstance(documento, out);
                documento.open();

                // Aquí generas el contenido del PDF con iTextPDF
                
            // Crear el primer párrafo
            Paragraph par0 = new Paragraph();
            Font fta = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD, BaseColor.BLACK);
            par0.add(new Phrase("Clinica Dental KREBS 🦷", fta));
            par0.setAlignment(Element.ALIGN_CENTER);
            par0.add(new Phrase(Chunk.NEWLINE));
            par0.add(new Phrase(Chunk.NEWLINE));
            
            Paragraph par1 = new Paragraph();
            Font ft = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD, BaseColor.BLACK);
            par1.add(new Phrase("BOLETA", ft));
            par1.setAlignment(Element.ALIGN_CENTER);
            par1.add(new Phrase(Chunk.NEWLINE));
            par1.add(new Phrase(Chunk.NEWLINE));

            // Añadir la imagen
                        try {
                            String imagePath = getServletContext().getRealPath("/img/logo2.jpg");
                            Image img = Image.getInstance(imagePath);
                            img.setAlignment(Element.ALIGN_CENTER);
                            img.scaleToFit(200, 200);
                            documento.add(img);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

            // Crear el segundo párrafo
            Paragraph par2 = new Paragraph();
            Font fa = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLACK);
            par2.add(new Phrase("Clínica Dental Krebs", fa));
            par2.add(new Phrase(Chunk.NEWLINE));
            par2.add(new Phrase("RUC: 34302480914672", fa));
            par2.add(new Phrase(Chunk.NEWLINE));          
            par2.add(new Phrase("Dirección: Mz K5 Lt.22 Urb. San Isidro", fa));
            par2.add(new Phrase(Chunk.NEWLINE));
            par2.add(new Phrase("Correo: clinicakrebs@hotmail.com", fa));
            par2.add(new Phrase(Chunk.NEWLINE));
            par2.add(new Phrase("Teléfono: 5121728", fa));
            par2.add(new Phrase(Chunk.NEWLINE));
            par2.setAlignment(Element.ALIGN_LEFT);

            // Añadir la fecha y hora de generación del reporte
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            String fechaHora = dateFormat.format(new Date());
            Paragraph parFecha = new Paragraph();
            Font ftFecha = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL, BaseColor.BLACK);
            parFecha.add(new Phrase("Fecha y Hora: " + fechaHora, ftFecha));
            parFecha.setAlignment(Element.ALIGN_RIGHT);

            // Crear una tabla para alinear los párrafos en una misma línea
            PdfPTable table = new PdfPTable(3);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10f);

            // Añadir las celdas a la tabla
            PdfPCell cell1 = new PdfPCell(par1);
            cell1.setBorder(Rectangle.NO_BORDER);
            cell1.setHorizontalAlignment(Element.ALIGN_LEFT);

            PdfPCell cell2 = new PdfPCell(par2);
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);

            PdfPCell cell3 = new PdfPCell(parFecha);
            cell3.setBorder(Rectangle.NO_BORDER);
            cell3.setHorizontalAlignment(Element.ALIGN_RIGHT);

            table.addCell(cell1);
            table.addCell(cell2);
            table.addCell(cell3);

            // Añadir la tabla al documento
            documento.add(table);
                documento.add(new Paragraph("DATOS DE LA CITA:"));
                documento.add(new Phrase(Chunk.NEWLINE));
                documento.add(new Paragraph("Fecha programada de la Cita: " + rs.getString("fecha_programada")));
                documento.add(new Paragraph("Hora de la Cita: " + rs.getString("hora_programada")));
                documento.add(new Paragraph("Cita reservada por: " + rs.getString("nombre_padre")));
                documento.add(new Paragraph("DNI del Apoderado: " + rs.getString("DNI")));
                documento.add(new Paragraph("Paciente: " + rs.getString("nombre_hijo")));
                documento.add(new Paragraph("Especialista: " + rs.getString("nombre_especialista")));            
                documento.add(new Paragraph("Recepcionista: " + rs.getString("nombre_usuario")));
                documento.add(new Phrase(Chunk.NEWLINE));
                documento.add(new Paragraph("SERVICIO: " + rs.getString("nombre_servicio")));
                documento.add(new Paragraph("TOTAL A PAGAR (S/.): " + rs.getString("precio")));

            // Crear la tabla de datos
            PdfPTable tablaDatos = new PdfPTable(2);

            // Definir los anchos de las columnas
            float[] columnWidths = {4f, 4f};
            tablaDatos.setWidths(columnWidths);

            // Ajustar el ancho de la tabla
            tablaDatos.setWidthPercentage(100);

            // Añadir las celdas de encabezado
            PdfPCell celda1 = new PdfPCell(new Paragraph("Servicio", FontFactory.getFont("Arial", 12, Font.BOLD, BaseColor.BLUE)));
            PdfPCell celda2 = new PdfPCell(new Paragraph("Total a Pagar", FontFactory.getFont("Arial", 12, Font.BOLD, BaseColor.BLUE)));
            
            tablaDatos.addCell(celda1);
            tablaDatos.addCell(celda2);
                documento.close();
            } else {
                response.sendRedirect("boletaCita.jsp?error=not_found");
            }

            rs.close();
            st.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
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

}