<%@page import="java.util.List"%>
<%@page import="Modelo.*"%>
<%@page import="DAO.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editar Hijos</title>
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
        <script>
            function soloLetras(e) {
                var key = e.keyCode || e.which;
                var tecla = String.fromCharCode(key).toLowerCase();
                var letras = "abcdefghijklmnopqrstuvwxyzñáéíóúü";
                var especiales = "8-37-39-46";

                var tecla_especial = false;
                for (var i in especiales) {
                    if (key == especiales[i]) {
                        tecla_especial = true;
                        break;
                    }
                }

                if (letras.indexOf(tecla) == -1 && !tecla_especial) {
                    return false;
                }
            }
        </script>
    </head>
    <body>
        <div class="form-container">
            <div class="col-lg-6">
                <%
                    DAOhijo daoh = new DAOhijo();
                    String idhi = (String) request.getParameter("idhi");
                    Hijo h = daoh.Obtener(idhi);
                    DAOpadre dao = new DAOpadre();
                    List<Padre> listaPadre = dao.ListarPadres();
                %>

                <h1>Editar Hijo</h1>
                <form action="ControladorHijo">
                    <input class="form-control" type="hidden" name="id_hijo" value="<%=h.getId_hijo()%>"><br>
                    Nombre del Hijo:<br>
                    <input class="form-control" type="text" name="nombre_hijo" value="<%=h.getNombre_hijo()%>" required><br>
                    Sexo: <br>
                    <input class="form-control" type="text" name="sexo" value="<%=h.getSexo()%>" onkeypress="return soloLetras(event)" required><br>
                    Fecha Nacimiento: <br>
                    <input class="form-control" type="text" name="fecha_nacimiento" value="<%=h.getFecha_nacimiento()%>"><br>
                    ID Padre:<br>
                    <select name="id_padre" class="form-control">
                       <option value="<%=h.getId_padre()%>"><%=h.getId_padre()%> (Actual)</option>
                       <% for (Padre padre : listaPadre) {
                              if (!padre.getId_padre().equals(h.getId_padre())) { %>
                           <option value="<%= padre.getId_padre()%>"><%= padre.getId_padre()%></option>
                       <%    }
                          } %>
                     </select><br>
                     Nombre del Padre:<br>
                    <input class="form-control" type="text" name="txtNombre_padre" value="<%=h.getNombre_padre()%>" readonly><br><br>
                    <input class="btn btn-primary" type="submit" name="accion" value="Actualizar"> 
                </form>
            </div>
        </div>
    </body>
</html>
