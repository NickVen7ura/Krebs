<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Modelo.Servicios"%>
<%@page import="DAO.DAOservicio"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <title>JSP Page</title>
        <style>
            body {
                background: #f5f5f5;
                font-family: Arial, sans-serif;
            }
            .form-container {
                background: white;
                border-radius: 8px;
                box-shadow: 0px 0px 10px 0px rgba(0,0,0,0.1);
                padding: 20px;
                max-width: 600px;
                margin: 40px auto;
            }
            .form-container h1 {
                font-size: 24px;
                font-weight: 600;
                margin-bottom: 20px;
                text-align: center;
                color: #444;
            }
            .form-control {
                border-radius: 0;
                border: 1px solid #ddd;
                box-shadow: none;
                height: 45px;
                font-size: 16px;
                margin-bottom: 10px;
            }
            .form-control:focus {
                border-color: #007bff;
                box-shadow: none;
            }
            .btn-primary {
                background-color: #007bff;
                border: none;
                height: 45px;
                font-size: 16px;
                border-radius: 0;
                padding: 0 20px;
                width: 100%;
                color: white;
            }
        </style>
    </head>
    <body>
        <div class="form-container">
            <div class="col-lg-6">
                <%
                    DAOservicio dao = new DAOservicio();
                    String idser = (String) request.getAttribute("idser");
                    Servicios s = dao.Obtener(idser);
                %>
                
            <h1>Editar Servicio</h1>
            <form action="ControladorServicios">
                <input class="form-control" type="hidden" name="txtId" value="<%=s.getId_servicio()%>" ><br>
                Nombre Servicio <br>
                <input class="form-control" type="text" name="txtNomSer" value="<%=s.getNombre_servicio()%>" required><br>
                Descripcion: <br>
                <input class="form-control" type="text" name="txtDesc" value="<%=s.getDescripcion()%>" required><br>
                Precio: <br>
                <input class="form-control" type="number" name="txtPrec" value="<%=s.getPrecio()%>" required><br>
                <input class="btn btn-primary" type="submit" name="accion" value="Actualizar"> 

            </form>
          </div>
          
        </div>
    </body>
</html>
