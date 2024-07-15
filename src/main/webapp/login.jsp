<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CLINICA DENTAL KREBS</title>
        <link rel="icon" href="img/icon.ico">
        <link href="https://fonts.googleapis.com/css?family=ZCOOL+XiaoWei" rel="stylesheet">
        <style>
            body {
                display: flex;
                min-height: 100vh;
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
                background-image: url('img/fondo.jpg');
                background-size: cover;
                background-repeat: no-repeat;
                background-position: center;
                height: 100vh;
                justify-content: center;
                align-items: center;
            }

            .container {
                display: flex;
                justify-content: center;
                align-items: center;
                min-height: 100vh;
            }

            .box {
                background-color: rgba(255, 255, 255, 0.9);
                padding: 20px 40px;
                border-radius: 10px;
                box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
                text-align: center;
                max-width: 400px;
                width: 100%;
            }

            .box img.avatar {
                display: block;
                max-width: 100px;
                height: auto;
                margin: 0 auto 20px auto;
                border-radius: 50%;
            }

            .box h1 {
                color: #264653;
                font-family: 'ZCOOL XiaoWei', serif;
                margin-bottom: 20px;
            }

            .box p {
                color: #264653;
                font-weight: bold;
                margin: 10px 0;
            }

            .box input[type="text"],
            .box input[type="password"] {
                width: 100%;
                padding: 10px;
                margin: 10px 0 20px 0;
                border: 1px solid #CCC;
                border-radius: 5px;
                font-size: 16px;
            }

            .box input[type="text"]:focus,
            .box input[type="password"]:focus {
                outline: none;
                border-color: #8EC5FC;
            }

            .box input[type="submit"] {
                background-color: #8EC5FC;
                color: white;
                border: none;
                padding: 10px;
                border-radius: 5px;
                cursor: pointer;
                transition: background-color 0.3s ease;
                width: 100%;
                font-size: 16px;
                margin-top: 10px;
            }

            .box input[type="submit"]:hover {
                background-color: #64B5F6;
            }

            .error-message {
                background-color: white;
                color: red;
                padding: 10px;
                margin-bottom: 20px;
                text-align: center;
                border-radius: 5px;
            }

        </style>

    </head>
    <body>
        <% if (request.getParameter("error") != null) { %>
        <div class="error-message">
            <p>Error: El nombre de usuario o la contraseña son incorrectos.</p>
        </div>
        <% }%>
        <div class="container">
            <div class="box">
                <img class="avatar" src="img/logo2.jpg">
                <h1>CLINICA DENTAL KREBS</h1> <!-- Nuevo título -->
                <form action="Login?accion=verificar" method="post">
                    <p>Nombre de Usuario</p>
                    <input type="text" placeholder="Nombre de Usuario" name="username" required>
                    <p>Contraseña</p>
                    <input type="password" placeholder="Contraseña" name="password" required>
                    <input type="submit" value="Ingresar" <BR>
                    <%
                            String mensaje = (String) request.getAttribute("mensaje");
                            if (mensaje != null) {
                        %>
                            <div class="error-message"><%= mensaje %></div>
                        <%
                            }
                    %>
                </form>
            </div>
        </div>
    </body>
</html>
