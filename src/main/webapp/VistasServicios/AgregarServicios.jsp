<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <title>Agregar Servicios</title>
    </head>
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
            function soloLetrasDescripcion(e) {
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
    <body>
        <div class="form-container">
            <div class="col-lg-6">
                <h1>Datos del Nuevo Servicio</h1>
                <form action="ControladorServicios">
                    
                    <input class="form-control" type="hidden" name="txtId" value="0"><br>
                    Nombre del Servicio: <br>
                    <input class="form-control" type="text" name="txtNomSer" required><br>
                    Descripción: <br>
                    <input class="form-control" type="text" name="txtDesc" required><br>
                    Precio: <br>
                    <input class="form-control" type="number" name="txtPrec" step="0.01" min="0.00" max="9999.99" required>
                    <input class="btn btn-primary" type="submit" name="accion" value="Agregar">               
                </form>
            </div>

        </div>
    </body>
</html>
