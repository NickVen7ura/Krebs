package Controlador;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.OutputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@WebServlet(name = "reporteCitasExcel", urlPatterns = {"/reporteCitasExcel"})
public class reporteCitasExcel extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=citas.xlsx");

        try (OutputStream out = response.getOutputStream()) {
            Connection con = null;
            Statement st = null;
            ResultSet rs = null;

            try {
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3305/krebs", "root", "");
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

                Workbook workbook = new XSSFWorkbook();
                Sheet sheet = workbook.createSheet("Citas");

                // Crear estilos de celda
                CellStyle headerStyle = workbook.createCellStyle();
                Font headerFont = workbook.createFont();
                headerFont.setBold(true);
                headerStyle.setFont(headerFont);
                headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
                headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                headerStyle.setBorderBottom(BorderStyle.THIN);
                headerStyle.setBorderTop(BorderStyle.THIN);
                headerStyle.setBorderLeft(BorderStyle.THIN);
                headerStyle.setBorderRight(BorderStyle.THIN);
                headerStyle.setAlignment(HorizontalAlignment.CENTER);
                headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);

                CellStyle cellStyle = workbook.createCellStyle();
                cellStyle.setBorderBottom(BorderStyle.THIN);
                cellStyle.setBorderTop(BorderStyle.THIN);
                cellStyle.setBorderLeft(BorderStyle.THIN);
                cellStyle.setBorderRight(BorderStyle.THIN);


                // Crear encabezados
                String[] headers = {"ID", "Fecha", "Hora", "Padre", "Hijo", "Servicio", "Especialista", "Recepción","Estado"};
                Row headerRow = sheet.createRow(0);
                for (int i = 0; i < headers.length; i++) {
                    Cell cell = headerRow.createCell(i);
                    cell.setCellValue(headers[i]);
                    cell.setCellStyle(headerStyle);
                }

                int rowNum = 1;
                while (rs.next()) {
                    Row row = sheet.createRow(rowNum++);
                    for (int i = 0; i < headers.length; i++) {
                        Cell cell = row.createCell(i);
                        switch (i) {
                            case 0:
                                cell.setCellValue(rs.getInt("id_cita"));
                                break;
                            case 1:
                                cell.setCellValue(rs.getString("fecha_programada"));
                                break;
                            case 2:
                                cell.setCellValue(rs.getString("hora_programada"));
                                break;
                            case 3:
                                cell.setCellValue(rs.getString("nombre_padre"));
                                break;
                            case 4:
                                cell.setCellValue(rs.getString("nombre_hijo"));
                                break;
                            case 5:
                                cell.setCellValue(rs.getString("nombre_servicio"));
                                break;
                            case 6:
                                cell.setCellValue(rs.getString("nombre_especialista"));
                                break;
                            case 7:
                                cell.setCellValue(rs.getString("nombre_usuario"));
                                break;
                            case 8:
                                cell.setCellValue(rs.getString("situacion"));
                                break;                                
                        }
                        cell.setCellStyle(cellStyle); 
                        //cell.setCellStyle(cellStyle);
                    }
                }
               
                workbook.write(out);
                workbook.close();
                
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (rs != null) try {
                    rs.close();
                } catch (Exception e) {
                }
                if (st != null) try {
                    st.close();
                } catch (Exception e) {
                }
                if (con != null) try {
                    con.close();
                } catch (Exception e) {
                }
            }
        }
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

}
