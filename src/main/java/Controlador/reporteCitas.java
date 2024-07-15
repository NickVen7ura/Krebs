package Controlador;

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
import java.util.Date;
import java.text.SimpleDateFormat;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "reporteCitas", urlPatterns = {"/reporteCitas"})
public class reporteCitas extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("aplication/pdf");
        OutputStream out = response.getOutputStream();
        try{
            
            try{
                Connection con = null;
                Statement st = null;
                ResultSet rs = null;
                
                Class.forName("com.mysql.jdbc.Driver");
                con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3305/krebs", "root", "");
                st = con.createStatement();
                rs = st.executeQuery("SELECT c.id_cita, DATE_FORMAT(c.fecha_cita, '%d/%m/%Y') AS fecha_programada, DATE_FORMAT(c.hora_cita, '%h:%i %p') AS hora_programada, " +
                  "p.nombre_padre, h.nombre_hijo, s.nombre_servicio, e.nombre_especialista, u.nombre_usuario, c.situacion " +
                  "FROM citas c " +
                  "JOIN Padres p ON c.id_padre = p.id_padre " +
                  "JOIN Hijos h ON c.id_hijo = h.id_hijo " +
                  "JOIN servicios s ON c.id_servicio = s.id_servicio " +
                  "JOIN Especialistas e ON c.id_especialista = e.id_especialista " +
                  "JOIN usuarios u ON c.id_usuario = u.id_usuario " +
                  "WHERE c.estado = 'S';");
                
                if(con!=null){
                    try{
                        Document documento = new Document();
                        PdfWriter.getInstance(documento, out);
                        documento.open();
                        
                        Paragraph par1 = new Paragraph();
                        Font ft = new Font(Font.FontFamily.HELVETICA,16,Font.BOLD,BaseColor.BLACK);
                        par1.add(new Phrase("Reporte de Citas - Clinica Dental KREBS 🦷", ft));
                        par1.setAlignment(Element.ALIGN_CENTER);
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        documento.add(par1);
                        
                        try {
                            String imagePath = getServletContext().getRealPath("/img/logo2.jpg");
                            Image img = Image.getInstance(imagePath);
                            img.setAlignment(Element.ALIGN_CENTER);
                            img.scaleToFit(200, 200);
                            documento.add(img);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        
                        Paragraph par2 = new Paragraph();
                        Font fa = new Font(Font.FontFamily.TIMES_ROMAN,9,Font.BOLD,BaseColor.BLACK);
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
                        par2.add(new Phrase(Chunk.NEWLINE));
                        documento.add(par2);                 
                                          
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                        String fechaHora = dateFormat.format(new Date());
                        Paragraph parFecha = new Paragraph();
                        Font ftFecha = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL, BaseColor.BLACK);
                        parFecha.add(new Phrase("Fecha y hora de generación del reporte: " + fechaHora, ftFecha));
                        parFecha.setAlignment(Element.ALIGN_RIGHT);
                        parFecha.add(new Phrase(Chunk.NEWLINE));
                        parFecha.add(new Phrase(Chunk.NEWLINE));
                        parFecha.add(new Phrase(Chunk.NEWLINE));
                        documento.add(parFecha);
                                                
                        PdfPTable tabla = new PdfPTable(8);
                        
                        // Definir los anchos de las columnas
                        float[] columnWidths = {4f, 4f,4f,4f,5f,5f,4f,2f};
                        tabla.setWidths(columnWidths);       
                        
                        // Aprietala tabla
                        tabla.setWidthPercentage(100); // Aquí puedes ajustar el porcentaje según lo necesites
                        
                                                                                 
                        PdfPCell celda1 = new PdfPCell(new Paragraph("Fecha", FontFactory.getFont("Arial",12,Font.BOLD,BaseColor.BLUE)));                                  
                        PdfPCell celda2 = new PdfPCell(new Paragraph("Hora", FontFactory.getFont("Arial",12,Font.BOLD,BaseColor.BLUE)));
                        PdfPCell celda3 = new PdfPCell(new Paragraph("Padre", FontFactory.getFont("Arial",12,Font.BOLD,BaseColor.BLUE)));
                        PdfPCell celda4 = new PdfPCell(new Paragraph("Hijo", FontFactory.getFont("Arial",12,Font.BOLD,BaseColor.BLUE)));
                        PdfPCell celda5 = new PdfPCell(new Paragraph("Servicio", FontFactory.getFont("Arial",12,Font.BOLD,BaseColor.BLUE)));
                        PdfPCell celda6 = new PdfPCell(new Paragraph("Especialista", FontFactory.getFont("Arial",12,Font.BOLD,BaseColor.BLUE)));
                        PdfPCell celda7 = new PdfPCell(new Paragraph("Recepción", FontFactory.getFont("Arial",12,Font.BOLD,BaseColor.BLUE)));                       
                        PdfPCell celda8 = new PdfPCell(new Paragraph("Estado", FontFactory.getFont("Arial",12,Font.BOLD,BaseColor.BLUE)));

                        tabla.addCell(celda1);
                        tabla.addCell(celda2);
                        tabla.addCell(celda3);
                        tabla.addCell(celda4);
                        tabla.addCell(celda5);
                        tabla.addCell(celda6);
                        tabla.addCell(celda7);
                        tabla.addCell(celda8);
                       						
                        while(rs.next()){
                            tabla.addCell(rs.getString((2))); 
                            tabla.addCell(rs.getString((3)));
                            tabla.addCell(rs.getString((4)));
                            tabla.addCell(rs.getString((5)));
                            tabla.addCell(rs.getString((6)));
                            tabla.addCell(rs.getString((7)));
                            tabla.addCell(rs.getString((8)));
                            tabla.addCell(rs.getString((9)));

                        }
                        
                        documento.add(tabla);
                        
                        documento.close();
                    }catch(Exception ex){ex.getMessage();}                    
                    }
            }catch(Exception e){
                e.getMessage();
            }                
        }finally{
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