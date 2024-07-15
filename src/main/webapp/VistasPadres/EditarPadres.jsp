<%@page import="Modelo.Padre"%>
<%@page import="DAO.DAOpadre"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editar Padres</title>
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
            function soloLetrasDireccion(e) {
                var key = e.keyCode || e.which;
                var tecla = String.fromCharCode(key).toLowerCase();
                var letras = "abcdefghijklmnopqrstuvwxyzñáéíóúü";
                var numeros = "0123456789";
                var especiales = "8-37-39-46";

                var tecla_especial = false;
                for (var i in especiales) {
                    if (key == especiales[i]) {
                        tecla_especial = true;
                        break;
                    }
                }

                if ((letras.indexOf(tecla) == -1 && numeros.indexOf(tecla) == -1 && tecla !== "." && tecla !== "-") && !tecla_especial) {
                    return false;
                }
            }

        </script>
    </head>
    <body>
        <div class="form-container">
            <div class="col-lg-6">
                <%
                    DAOpadre daop = new DAOpadre();
                    String idpa = (String) request.getParameter("idpa");
                    Padre p = daop.Obtener(idpa);
                %>

                <h1>Editar Padre </h1>
                <form action="ControladorPadre">
                    <input class="form-control" type="hidden" name="txtId_padre" value="<%=p.getId_padre()%>"><br>
                    Nombre del Padre: <br>
                    <input class="form-control" type="text" name="txtNombre_padre" value="<%=p.getNombre_padre()%>" required><br>
                    DNI: <br>
                    <input class="form-control" type="text" name="txtDNI" value="<%=p.getDNI()%>" min="00000000" max="99999999" required><br>
                    Dirección: <br>
                    <input class="form-control" type="text" name="txtDireccion" value="<%=p.getDireccion()%>" required><br>                    
                    Teléfono: <br>
                    <input class="form-control" type="text" name="txtTelefono" value="<%=p.getTelefono()%>"  min="900000000" max="999999999" required><br>
                    Correo Electronico: <br>
                    <input class="form-control" type="text" name="txtCorreo" value="<%=p.getCorreo()%>"><br>
                    <input class="btn btn-primary" type="submit" name="accion" value="Actualizar"> 

                </form>
            </div>

        </div>
    </body>
</html>