<%@page import="java.text.SimpleDateFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="DAO.*" %>
<%@page import="Modelo.*" %>
<%@page import="Controlador.*"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.util.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <title>Agregar Citas</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #F0F0F0;
                margin: 0;
                padding: 0;
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
            }

            .form-container {
                background-color: #FFFFFF;
                border-radius: 10px;
                box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
                padding: 20px;
                width: 400px;
                max-width: 100%;
            }

            h1 {
                color: #264653;
                text-align: center;
                margin-bottom: 20px;
            }

            form {
                display: flex;
                flex-direction: column;
            }

            label {
                margin-bottom: 5px;
                font-weight: bold;
                color: #264653;
            }

            input[type="date"], select, input[type="submit"] {
                margin-bottom: 15px;
                padding: 10px;
                border: 1px solid #CCC;
                border-radius: 5px;
                font-size: 16px;
            }

            input[type="date"]:focus, select:focus, input[type="submit"]:focus {
                outline: none;
                border-color: #8EC5FC;
            }

            input[type="submit"] {
                background-color: #8EC5FC;
                color: white;
                cursor: pointer;
                transition: background-color 0.3s ease;
            }

            input[type="submit"]:hover {
                background-color: #64B5F6;
            }

        </style>
        <script>
            function validarHoraCita(horaSeleccionada) {
                var horaActual = new Date().getHours();
                var minutoActual = new Date().getMinutes();

                var partesHora = horaSeleccionada.split(":");
                var hora = parseInt(partesHora[0]);
                var minuto = parseInt(partesHora[1]);

                if (hora < horaActual || (hora === horaActual && minuto < minutoActual)) {
                    alert("No puedes seleccionar una hora anterior a la actual.");
                    document.getElementById("hora_cita").value = ""; // Limpiar el campo de hora
                }
            }
        </script>
    </head>
    <body>
        <div>
            <div class="form-container">
                <%
                    DAOcitas dao = new DAOcitas();
                    DAOpadre daocl = new DAOpadre();
                    DAOservicio daos = new DAOservicio();
                    DAOespecialistas daov = new DAOespecialistas();
                    DAOhijo daom = new DAOhijo();
                    DAOrecepcionista daor = new DAOrecepcionista();
                    List<Citas> lista = dao.ListarCitas();
                    List<Padre> listaPadre = daocl.ListarPadres();
                    List<Servicios> listaServicios = daos.ListarCargo();
                    List<Especialistas> listaEspecialistas = daov.ListarEspecialistas();
                    List<Hijo> listaHijo = daom.ListarHijos();
                    List<Usuario> listaUsuario = daor.ListarUsuarios();

                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                    Date horaInicial = sdf.parse("08:00");
                    Date horaFinal = sdf.parse("20:00");
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(horaInicial);

                    List<String> horariosDisponibles = new ArrayList<String>();

                    while (calendar.getTime().before(horaFinal)) {
                        horariosDisponibles.add(sdf.format(calendar.getTime()));
                        calendar.add(Calendar.MINUTE, 30);
                    }
                %>
                <BR><BR><BR><h1>Datos de la Nueva Cita</h1>
                <form action="ControladorCitas" method="post">
                    <input type="hidden" name="id_cita" value="0"><br>
                    Fecha de Cita: <br>
                    <input type="date" name="fecha_cita" value="<%= new SimpleDateFormat("yyyy-MM-dd").format(new Date())%>" required><br>
                    Hora de Cita: <br>
                    <select name="hora_cita" required onchange="validarHoraCita(this.value);">
                        <% for (String horario : horariosDisponibles) {%>
                        <option value="<%= horario%>"><%= horario%></option>
                        <% } %>
                    </select><br>
                    Apoderado: <br>
                    <select name="nombre_padre" required>
                        <option value="">Seleccionar Padre</option>
                        <% for (Padre padre : listaPadre) {%>
                        <option value="<%= padre.getId_padre()%>"><%= padre.getNombre_padre()%></option>
                        <% } %>
                    </select><br>
                    Paciente: <br>
                    <select name="nombre_hijo">
                        <option value="">Seleccionar Hijo</option>
                        <% for (Hijo hijo : listaHijo) {%>
                        <option value="<%= hijo.getId_hijo()%>"><%= hijo.getNombre_hijo()%></option>
                        <% } %>
                    </select><br>
                    Servicio: <br>
                    <select name="nombre_servicio" required>
                        <option value="">Seleccionar servicio</option>
                        <% for (Servicios servicio : listaServicios) {%>
                        <option value="<%= servicio.getId_servicio()%>"><%= servicio.getNombre_servicio()%></option>
                        <% } %>
                    </select><br>
                    Especialista: <br>
                    <select name="nombre_especialista" required>
                        <option value="">Seleccionar especialista</option>
                        <% for (Especialistas especialista : listaEspecialistas) {%>
                        <option value="<%= especialista.getId_especialista()%>"><%= especialista.getNombre_especialista()%></option>
                        <% }%>
                    </select><br>
                    Recepcionista: <br>
                    <select name="nombre_usuario" required>
                        <option value="">Seleccionar recepcionista</option>
                        <% for (Usuario usuario : listaUsuario) {%>
                        <option value="<%= usuario.getId_usuario()%>"><%= usuario.getUsername()%></option>
                        <% }%>
                    </select><br> 
                    Estado: <br>
                    <select name="situacion" readonly>
                        <option value="Pendiente">Pendiente</option>
                    </select><br>                   
                    <input type="submit" name="accion" value="Agregar">
                </form>

            </div>
        </div>
    </body>
</html>
