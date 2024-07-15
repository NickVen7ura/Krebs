<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Modelo.Especialistas"%>
<%@page import="DAO.DAOespecialistas"%>





<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <title>Editar Especialistas</title>
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
                    DAOespecialistas daoEspecialista = new DAOespecialistas();
                    String idesp = (String) request.getAttribute("idesp");
                    Especialistas e = daoEspecialista.Obtener(idesp);
                %>
                
            <h1>Editar Especialista</h1>
            <form action="ControladorEspecialistas">
                <input class="form-control" type="hidden" name="txtId" value="<%=e.getId_especialista()%>"><br>
                Nombre del Especialista: <br>
                <input class="form-control" type="text" name="txtNombre" value="<%=e.getNombre_especialista()%>" onkeypress="return soloLetras(event)" required><br>
                Especialidad: <br>
                <input class="form-control" type="text" name="txtEspecialidad" value="<%=e.getEspecialidad()%>" required><br>
                Correo Electronico: <br>
                <input class="form-control" type="email" name="txtCorreo" value="<%=e.getCorreo_electronico()%>" required><br>
                <input class="btn btn-primary" type="submit" name="accion" value="Actualizar"> 

            </form>
          </div>         
        </div>
    </body>
</html>
