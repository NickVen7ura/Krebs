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

@WebServlet(name = "pdf", urlPatterns = {"/pdf"})
public class pdf extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/pdf");
        OutputStream out = response.getOutputStream();

        try {

            try {
                Connection con = null;
                Statement st = null;
                ResultSet rs = null;

                Class.forName("com.mysql.jdbc.Driver");
                con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3305/dental", "root", "");
                st = con.createStatement();
                rs = st.executeQuery("SELECT DISTINCT id_cita, c.fecha_cita, c.hora_cita, i.id_cliente, i.nombre_cliente,"
                        + "m.id_mascota, m.nombre_mascota, s.id_servicio, s.nombre_servicio, s.precio, c.estado FROM citas c"
                        + " INNER JOIN padres i ON c.id_cliente = i.id_cliente"
                        + " INNER JOIN hijos m ON c.id_mascota = m.id_mascota"
                        + " INNER JOIN servicios s ON c.id_servicio = s.id_servicio ORDER BY c.id_cita");

                if (con != null) {
                    try {
                        Document documento = new Document();
                        PdfWriter.getInstance(documento, out);

                        documento.open();

                        // Título
                        Paragraph titulo = new Paragraph("REGISTRO DE CITAS", FontFactory.getFont(FontFactory.HELVETICA, 20, Font.BOLD, BaseColor.BLUE));
                        titulo.add(new Phrase("Reporte con iTextpdf.jar"));
                        titulo.setAlignment(Element.ALIGN_CENTER);
                        titulo.add(Chunk.NEWLINE);
                        titulo.add(Chunk.NEWLINE);
                        titulo.add(new Phrase(Chunk.NEWLINE));
                        documento.add(titulo);

                        // Imagen
                        String rutaImagen = getServletContext().getRealPath("/img/silueta2.jpg");
                        Image imagen = Image.getInstance(rutaImagen);
                        imagen.scaleToFit(80, 80);

                        Paragraph parrafo = new Paragraph();

                        PdfPTable tabla2 = new PdfPTable(2);
                        tabla2.setWidthPercentage(100);
                        float[] columnWidths = {1f, 1f};
                        tabla2.setWidths(columnWidths);

                        PdfPCell celdaTexto = new PdfPCell();
                        celdaTexto.addElement(new Phrase("Veterinaria 911", FontFactory.getFont(FontFactory.TIMES_ROMAN, 14, Font.NORMAL, BaseColor.BLACK)));
                        celdaTexto.addElement(new Phrase("RUC: 16302480914672", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.BLACK)));
                        celdaTexto.addElement(new Phrase("Dirección: Mz K5 Lt.22 Urb. LosOlivos", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.BLACK)));
                        celdaTexto.addElement(new Phrase("Correo: veterinaria911@hotmail.com", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.BLACK)));
                        celdaTexto.addElement(new Phrase("Teléfono: 5401728", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.BLACK)));
                        celdaTexto.addElement(new Phrase(" ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.BLACK)));
                        celdaTexto.addElement(new Phrase(" ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.BLACK)));
                        celdaTexto.setBorder(PdfPCell.NO_BORDER);
                        celdaTexto.setHorizontalAlignment(Element.ALIGN_LEFT); // Alinación del texto a la izquierda
                        tabla2.addCell(celdaTexto);

                        PdfPCell celdaImagen = new PdfPCell(imagen);
                        celdaImagen.setBorder(PdfPCell.NO_BORDER);
                        celdaImagen.setHorizontalAlignment(Element.ALIGN_RIGHT); // Alinación de la imagen a la derecha
                        tabla2.addCell(celdaImagen);
                        parrafo.add(tabla2);

                        documento.add(parrafo);

                        // Tabla
                        PdfPTable tabla = new PdfPTable(8);
                        tabla.setWidthPercentage(100);
                        PdfPCell celda1 = new PdfPCell(new Paragraph("ID", FontFactory.getFont("Arial", 10, Font.BOLD, BaseColor.BLUE)));
                        celda1.setHorizontalAlignment(Element.ALIGN_CENTER);
                        tabla.addCell(celda1);
                        PdfPCell celda2 = new PdfPCell(new Paragraph("FECHA", FontFactory.getFont("Arial", 10, Font.BOLD, BaseColor.BLUE)));
                        celda2.setHorizontalAlignment(Element.ALIGN_CENTER);
                        tabla.addCell(celda2);
                        PdfPCell celda3 = new PdfPCell(new Paragraph("HORA", FontFactory.getFont("Arial", 10, Font.BOLD, BaseColor.BLUE)));
                        celda3.setHorizontalAlignment(Element.ALIGN_CENTER);
                        tabla.addCell(celda3);
                        PdfPCell celda4 = new PdfPCell(new Paragraph("CLIENTE", FontFactory.getFont("Arial", 10, Font.BOLD, BaseColor.BLUE)));
                        celda4.setHorizontalAlignment(Element.ALIGN_CENTER);
                        tabla.addCell(celda4);
                        PdfPCell celda5 = new PdfPCell(new Paragraph("MASCOTA", FontFactory.getFont("Arial", 10, Font.BOLD, BaseColor.BLUE)));
                        celda5.setHorizontalAlignment(Element.ALIGN_CENTER);
                        tabla.addCell(celda5);
                        PdfPCell celda6 = new PdfPCell(new Paragraph("SERVICIO", FontFactory.getFont("Arial", 10, Font.BOLD, BaseColor.BLUE)));
                        celda6.setHorizontalAlignment(Element.ALIGN_CENTER);
                        tabla.addCell(celda6);
                        PdfPCell celda7 = new PdfPCell(new Paragraph("PRECIO", FontFactory.getFont("Arial", 10, Font.BOLD, BaseColor.BLUE)));
                        celda7.setHorizontalAlignment(Element.ALIGN_CENTER);
                        tabla.addCell(celda7);
                        PdfPCell celda8 = new PdfPCell(new Paragraph("ESTADO", FontFactory.getFont("Arial", 10, Font.BOLD, BaseColor.BLUE)));
                        celda8.setHorizontalAlignment(Element.ALIGN_CENTER);
                        tabla.addCell(celda8);

                        while (rs.next()) {
                            PdfPCell celdaId = new PdfPCell(new Paragraph(rs.getString(1)));
                            celdaId.setHorizontalAlignment(Element.ALIGN_CENTER);
                            celdaId.setVerticalAlignment(Element.ALIGN_MIDDLE);
                            tabla.addCell(celdaId);

                            PdfPCell celdaFecha = new PdfPCell(new Paragraph(rs.getString(2)));
                            celdaFecha.setHorizontalAlignment(Element.ALIGN_CENTER);
                            celdaFecha.setVerticalAlignment(Element.ALIGN_MIDDLE);
                            tabla.addCell(celdaFecha);

                            PdfPCell celdaHora = new PdfPCell(new Paragraph(rs.getString(3)));
                            celdaHora.setHorizontalAlignment(Element.ALIGN_CENTER);
                            celdaHora.setVerticalAlignment(Element.ALIGN_MIDDLE);
                            tabla.addCell(celdaHora);

                            PdfPCell celdaCliente = new PdfPCell(new Paragraph(rs.getString(5)));
                            celdaCliente.setHorizontalAlignment(Element.ALIGN_CENTER);
                            celdaCliente.setVerticalAlignment(Element.ALIGN_MIDDLE);
                            tabla.addCell(celdaCliente);

                            PdfPCell celdaMascota = new PdfPCell(new Paragraph(rs.getString(7)));
                            celdaMascota.setHorizontalAlignment(Element.ALIGN_CENTER);
                            celdaMascota.setVerticalAlignment(Element.ALIGN_MIDDLE);
                            tabla.addCell(celdaMascota);

                            PdfPCell celdaServicio = new PdfPCell(new Paragraph(rs.getString(9)));
                            celdaServicio.setHorizontalAlignment(Element.ALIGN_CENTER);
                            celdaServicio.setVerticalAlignment(Element.ALIGN_MIDDLE);
                            tabla.addCell(celdaServicio);

                            PdfPCell celdaPrecio = new PdfPCell(new Paragraph(rs.getString(10)));
                            celdaPrecio.setHorizontalAlignment(Element.ALIGN_CENTER);
                            celdaPrecio.setVerticalAlignment(Element.ALIGN_MIDDLE);
                            tabla.addCell(celdaPrecio);

                            PdfPCell celdaEstado = new PdfPCell(new Paragraph(rs.getString(11)));
                            celdaEstado.setHorizontalAlignment(Element.ALIGN_CENTER);
                            celdaEstado.setVerticalAlignment(Element.ALIGN_MIDDLE);
                            tabla.addCell(celdaEstado);

                        }

                        documento.add(tabla);

                        documento.close();

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

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
