<%@page import="java.text.SimpleDateFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Modelo.*"%>
<%@page import="DAO.*"%>
<%@page import="java.util.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <title>Editar Citas</title>
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
        <div class="form-container">
            <div class="col-lg-6">

                <%
                    DAOcitas dao = new DAOcitas();
                    String idcit = request.getParameter("idcit");
                    Citas c = dao.Obtener(idcit);
                    DAOpadre daocl = new DAOpadre();
                    DAOhijo daom = new DAOhijo();
                    DAOservicio daos = new DAOservicio();
                    DAOespecialistas daov = new DAOespecialistas();
                    DAOrecepcionista daor = new DAOrecepcionista();
                    
                    List<Padre> listaClientes = daocl.ListarPadres();
                    List<Hijo> listaHijo = daom.ListarHijos();
                    List<Servicios> listaServicios = daos.ListarCargo();
                    List<Especialistas> listaEspecialistas = daov.ListarEspecialistas();
                    List<Usuario> listaUsuario = daor.ListarUsuarios();
                    
                    SimpleDateFormat sdf24 = new SimpleDateFormat("HH:mm");
                    SimpleDateFormat sdf12 = new SimpleDateFormat("hh:mm a");
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
                        // Convertir la hora de la cita a formato de 12 horas para preseleccionarla en el combo-box
                    String horaCita12 = "";
                        if (c.getHora_cita() != null && !c.getHora_cita().isEmpty()) {
                            horaCita12 = sdf12.format(sdf24.parse(c.getHora_cita()));
                    }
                %>

                <h1>Editar Cita</h1>
                    <form action="ControladorCitas">
                        <input class="form-control" type="hidden" name="id_cita" value="<%=c.getId_cita()%>">
                        
                        Fecha de Cita:<br>
                        <input class="form-control" type="date" name="fecha_cita" value="<%= new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd/MM/yyyy").parse(c.getFecha_cita())) %>" min="<%= new SimpleDateFormat("yyyy-MM-dd").format(new Date())%>" required><br>
                       
                        Hora de cita <br>
                        <input class="form-control" type="time" name="hora_cita" value="<%=c.getHora_cita()%>" required><br>
                        
                        Apoderado:<br>
                        <select name="nombre_padre" class="form-control" required>
                            <% for (Padre cl : listaClientes) { %>
                                <option value="<%= cl.getId_padre() %>" <%= (c.getId_padre() != null && c.getId_padre().equals(cl.getId_padre())) ? "selected" : "" %> >
                                    <%= cl.getNombre_padre() %> <%= (c.getId_padre() != null && c.getId_padre().equals(cl.getId_padre())) ? "(Actual)" : "" %>
                                </option>
                            <% } %>
                        </select><br>
                       
                        Paciente: <br>
                        <select name="nombre_hijo" class="form-control" required>
                            <% for (Hijo m : listaHijo) { %>
                                <option value="<%= m.getId_hijo() %>" <%= (c.getId_hijo() != null && c.getId_hijo().equals(m.getId_hijo())) ? "selected" : "" %> >
                                    <%= m.getNombre_hijo() %> <%= (c.getId_hijo() != null && c.getId_hijo().equals(m.getId_hijo())) ? "(Actual)" : "" %>
                                </option>
                            <% } %>
                        </select><br>

                        Servicio:<br>
                        <select name="nombre_servicio" class="form-control" required>
                            <% for (Servicios s : listaServicios) { %>
                                <option value="<%= s.getId_servicio() %>" <%= (c.getId_servicio() != null && c.getId_servicio().equals(s.getId_servicio())) ? "selected" : "" %> >
                                    <%= s.getNombre_servicio() %> <%= (c.getId_servicio() != null && c.getId_servicio().equals(s.getId_servicio())) ? "(Actual)" : "" %>
                                </option>
                            <% } %>
                        </select><br>

                        Especialista:<br>
                        <select name="nombre_especialista" class="form-control" required>
                            <% for (Especialistas e : listaEspecialistas) { %>
                                <option value="<%= e.getId_especialista() %>" <%= (c.getId_especialista() != null && c.getId_especialista().equals(e.getId_especialista())) ? "selected" : "" %> >
                                    <%= e.getNombre_especialista() %> <%= (c.getId_especialista() != null && c.getId_especialista().equals(e.getId_especialista())) ? "(Actual)" : "" %>
                                </option>
                            <% } %>
                        </select><br>

                        Recepcionista:<br>
                        <select name="nombre_usuario" class="form-control" required>
                            <% for (Usuario usuario : listaUsuario) { %>
                                <option value="<%= usuario.getId_usuario() %>" <%= (c.getId_usuario() != null && c.getId_usuario().equals(usuario.getId_usuario())) ? "selected" : "" %> >
                                    <%= usuario.getUsername() %> <%= (c.getId_usuario() != null && c.getId_usuario().equals(usuario.getId_usuario())) ? "(Actual)" : "" %>
                                </option>
                            <% } %>
                        </select><br>
                        
                        Estado:<BR>
                        <select name="situacion" class="form-control" required>
                            <option value="Pendiente" <%= "Pendiente".equals(c.getSituacion()) ? "selected" : "" %>>Pendiente</option>
                            <option value="Cancelado" <%= "En Proceso".equals(c.getSituacion()) ? "selected" : "" %>>Cancelado</option>
                            <option value="Asistio" <%= "Finalizado".equals(c.getSituacion()) ? "selected" : "" %>>Asistio</option>
                            <option value="Reprogramado" <%= "Finalizado".equals(c.getSituacion()) ? "selected" : "" %>>Reprogramado</option>
                        </select><br>
                        <input class="form-control" type="submit" name="accion" value="Actualizar">
                    </form>

            </div>
        </div>
    </body>
</html>

