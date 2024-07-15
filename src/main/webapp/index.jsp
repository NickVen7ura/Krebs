

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.util.*" %>
<%@include file="bodylinks.jsp" %>
<%@page import="DAO.*" %>
<%@page import="Modelo.*" %>
<%@page import="Controlador.*"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>CLINICA DENTAL KREBS</title>
        <link rel="icon" href="img/icon.ico">
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
        <!-- código del chatboot -->
        <script src="//code.tidio.co/sla4l4rdancpgo30xrzgj4zxbpwfapcy.js" async></script>
        <!-- cierre código del chatboot -->
        <link href='https://cdn.jsdelivr.net/npm/boxicons@2.0.9/css/boxicons.min.css' rel='stylesheet'>
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
        <style>
            body {
                display: flex;
                min-height: 100vh;
                font-family: Arial, sans-serif;
            }

            #sidebar {
                min-width: 200px; /* Ajuste del ancho de la barra lateral */
                max-width: 200px;
                height: 100vh;
                position: fixed;
                top: 0;
                left: 0;
                background-color: #a0d6e8; /* Color celeste pastel */
                color: #fff;
                transition: all 0.3s;
                padding-top: 60px; /* Para dejar espacio para el botón de Toggle */
                text-align: center; /* Centrar contenido */
            }

            #sidebar img {
                display: block;
                max-width: 80px; /* Ajusta esta anchura según sea necesario */
                max-height: 80px; /* Ajusta esta altura según sea necesario */
                height: auto;
                margin: 20px auto; /* Centrar la imagen */
            }

            #sidebar .logo-text {
                margin-top: 10px;
                font-size: 18px;
                font-weight: bold;
                font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                color: #fff;
                text-shadow: 1px 1px 2px rgba(0, 0, 0, 0.2);
            }

            #sidebar.collapsed {
                margin-left: -200px; /* Ajuste para la barra lateral colapsada */
            }

            #sidebar .nav-link {
                color: #fff;
                font-size: 20px; /* Tamaño de fuente aumentado */
                padding: 15px 20px; /* Espaciado interior aumentado */
                transition: background 0.3s;
                text-decoration: none;
                width: 100%;
                display: block;
                margin-bottom: 10px; /* Separación entre botones aumentada */
            }

            #sidebar .nav-link:hover {
                background-color: #495057;
                text-decoration: none;
            }

            #sidebar ul {
                padding-left: 0;
            }

            #sidebar li {
                list-style: none;
            }

            #content {
                margin-left: 200px; /* Ajuste del margen del contenido */
                padding: 20px;
                width: 100%;
                transition: margin-left 0.3s;
            }

            #content.collapsed {
                margin-left: 0;
            }

            #sidebarCollapse {
                position: fixed;
                top: 10px;
                left: 10px;
                z-index: 1000;
            }

            .content-section {
                display: none;
            }
        </style>
        <style>
            body {
                margin: 0;
                padding: 0;
                background-image: url('img/fondo.jpg');
                background-size: cover; /* Hace que la imagen cubra toda la página */
                background-repeat: no-repeat; /* Evita que la imagen se repita */
                background-position: center; /* Centra la imagen */
                height: 100vh; /* Asegura que la imagen cubra toda la altura de la vista */
            }
        </style>
    </head>
    <body>
        <button id="sidebarCollapse" class="btn btn-light">
            <i class="fas fa-bars"></i> <!-- Ícono de barras -->
        </button>
        <div id="sidebar" class="bg-dark">
            <img src="img/logo2.jpg" alt="Logo">
            <div class="logo-text">CLINICA DENTAL KREBS</div>
            <nav class="nav flex-column mt-3">
                <ul class="list-unstyled">
                    <li><a class="nav-link" href="#" data-content="content1">Cita</a></li>
                    <li><a class="nav-link" href="#" data-content="content2">Especialista</a></li>
                    <li><a class="nav-link" href="#" data-content="content3">Hijos</a></li>
                    <li><a class="nav-link" href="#" data-content="content4">Padres</a></li>
                    <li><a class="nav-link" href="#" data-content="content5">Servicios</a></li>
                    <li><a class="nav-link" href="#" data-content="content6">Recepcionista</a></li>
                    <li><a class="nav-link" href="#" data-content="content7">Dashboard</a></li>
                    <li><a class="nav-link" href="login.jsp" >Cerrar Sesión ⭕ </a></li>

                </ul>
            </nav>
        </div>
        <div id="content">
            <div id="content1" class="content-section">
                <h2>Lista Citas</h2>
                <table class="table table-bordered" id="tablaDatos">
                    <thead>
                        <tr>
                            <th class="text-center" width="100">Fecha de Cita</th>
                            <th class="text-center" width="100">Hora de Cita</th>
                            <th class="text-center" width="200">Nombre del Cliente</th>
                            <th class="text-center" width="200">Nombre del hijo</th>
                            <th class="text-center" width="200">Nombre del Servicio</th>
                            <th class="text-center" width="200">Nombre del Especialista</th>
                            <th class="text-center" width="150">Recepcionista</th>
                            <th class="text-center" width="100">Estado</th>
                            <th class="text-center" width="200">Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                         <%
                            DAOcitas daoci = new DAOcitas();
                            List<Citas> lista = daoci.ListarCitas();                            
                            Iterator<Citas> iter = lista.iterator();
                            String idcit = (String) request.getAttribute("idcit");
                            Citas c = daoci.Obtener(idcit);
                            while (iter.hasNext()) {
                                c = iter.next();
                        %> 
                        <tr>
                            <td class="text-center"><%=c.getFecha_cita()%></td>
                            <td class="text-center"><%=c.getHora_cita()%></td>
                            <td class="text-center"><%=c.getNombre_padre()%></td>
                            <td class="text-center"><%=c.getNombre_hijo()%></td>
                            <td class="text-center"><%=c.getNombre_servicio()%></td>
                            <td class="text-center"><%=c.getNombre_especialista()%></td>
                            <td class="text-center"><%=c.getNombre_usuario()%></td>
                            <td class="text-center"><%=c.getSituacion()%></td>
                            <td class="text-center">
                                <a class="btn btn-primary"
                                   href="ControladorCitas?accion=editar&idcit=<%=c.getId_cita()%>">Editar</a>
                                <a class="btn btn-danger"
                                   href="ControladorCitas?accion=eliminar&idcit=<%=c.getId_cita()%>">Eliminar</a> 
                                <form action="boletaCita" method="POST">
                                    <input type="hidden" name="id_cita" value="<%=c.getId_cita()%>"> <br>
                                    <button type="submit" class="btn btn-success">Generar Boleta</button>
                                </form>   
                            </td>
                        </tr>
                        <%} %>
                    </tbody>
                </table> 
                <div class="col-4 align-self-center">
                    <a class="btn btn-primary" href="ControladorCitas?accion=mostraragregar">Agendar Cita</a>
                </div><BR><BR><BR>
                <div style="display: flex; justify-content: left;">
                    <!-- botón pdf -->
                    <form name="ClinicaDental" action="/ClinicaDental/reporteCitas">
                        <button type="submit" class="btn btn-primary" style="background-color: transparent; border: none;">
                            <i class='bx bxs-file-pdf' style='color: red; font-size: 72px;'></i> 
                            
                        </button>
                    </form>

                    <!-- botón excel -->
                    <form name="ClinicaDental" action="/ClinicaDental/reporteCitasExcel">
                        <button type="submit" class="btn btn-primary" style="background-color: transparent; border: none;">
                                 <i class='bx bx-table' style='color: green; font-size: 72px;'></i>
                            
                        </button>
                    </form>
                </div>
            </div>

            <div id="content2" class="content-section">
                <h2>Lista de Especialistas</h2>
                <table class="table table-bordered" id="tablaDatos">
                    <thead>
                        <tr>
                            <th class="text-center">Id</th>
                            <th class="text-center">Nombre</th>
                            <th class="text-center">Especialidad</th>
                            <th class="text-center">Correo Electrónico</th>
                            <th class="text-center">Acciones</th>
                        </tr>
                    </thead>

                    <tbody>
                        <%
                            DAOespecialistas daoEspecialista = new DAOespecialistas();
                            List<Especialistas> ListarEspecialistas = daoEspecialista.ListarEspecialistas();
                            Iterator<Especialistas> iteresp = ListarEspecialistas.iterator();
                            Especialistas e = null;
                            while (iteresp.hasNext()) {
                                e = iteresp.next();
                        %>

                        <tr>
                            <td class="text-center"><%=e.getId_especialista()%></td>
                            <td class="text-center"><%=e.getNombre_especialista()%></td>
                            <td class="text-center"><%=e.getEspecialidad()%></td>
                            <td class="text-center"><%=e.getCorreo_electronico()%></td>

                            <td class="text-center">
                                <a class="btn btn-primary" 
                                   href="ControladorEspecialistas?accion=editar&idesp=<%=e.getId_especialista()%>">Editar</a>

                                <a class="btn btn-danger" 
                                   href="ControladorEspecialistas?accion=eliminar&idesp=<%=e.getId_especialista()%>">Eliminar</a>
                            </td>
                        </tr>
                        <%}%>
                    </tbody>
                </table>
                <div class="col-4 align-self-center">
                    <a class="btn btn-primary"  href="ControladorEspecialistas?accion=mostraragregar">Agregar Especialista</a>
                </div>
            </div>
            <div id="content3" class="content-section">
                <h2>Lista de Hijos</h2>
                <table class="table table-bordered" id="tablaDatos">
                    <thead>
                        <tr>
                            <th class="text-center" width="200">ID Hijo</th>
                            <th class="text-center" width="200">Nombre</th>
                            <th class="text-center" width="200">Sexo</th>
                            <th class="text-center" width="200">Fecha de Nacimiento</th>
                            <th class="text-center" width="200">ID del Padre</th>
                            <th class="text-center" width="200">Nombre del Padre</th>
                            <th class="text-center" width="200">Acciones</th>
                        </tr>
                    </thead>

                    <tbody id="tbodys">
                        <%
                            DAOhijo daohijo = new DAOhijo();
                            List<Hijo> listaHijos = daohijo.ListarHijos();
                            Iterator<Hijo> iteradorHijos = listaHijos.iterator();
                            Hijo hijo = null;
                            while (iteradorHijos.hasNext()) {
                                hijo = iteradorHijos.next();
                        %>
                        <tr>
                            <td class="text-center"><%= hijo.getId_hijo()%></td>
                            <td class="text-center"><%= hijo.getNombre_hijo()%></td>
                            <td class="text-center"><%= hijo.getSexo()%></td>
                            <td class="text-center"><%= hijo.getFecha_nacimiento()%></td>
                            <td class="text-center"><%= hijo.getId_padre()%></td>
                            <td class="text-center"><%= hijo.getNombre_padre()%></td>
                            <td class="text-center">
                                <a class="btn btn-primary"
                                   href="ControladorHijo?accion=editar&idhi=<%= hijo.getId_hijo()%>">Editar</a>
                                <a class="btn btn-danger"
                                   href="ControladorHijo?accion=eliminar&idhi=<%= hijo.getId_hijo()%>">Eliminar</a> 
                            </td>
                        </tr>
                        <% } %>
                    </tbody>
                </table>
                <div class="col-4 align-self-center">
                    <a class="btn btn-primary" href="ControladorHijo?accion=mostraragregar"> Agregar Hijo</a> 
                </div>

            </div>
            <div id="content4" class="content-section">
                <h2>Lista de Padres</h2>
                <table class="table table-bordered" id="tablaDatos">
                    <thead>
                        <tr>
                            <th class="text-center" width="200">ID Padre</th>
                            <th class="text-center" width="200">Nombres</th>
                            <th class="text-center" width="200">DNI</th>                            
                            <th class="text-center" width="200">Direcciones</th>
                            <th class="text-center" width="200">Teléfono</th>
                            <th class="text-center" width="220">Correo Electrónico</th>
                            <th class="text-center" width="330">Acciones</th>
                        </tr>
                    </thead>
                    <tbody id="tbodys">
                        <%
                            DAOpadre daopadre = new DAOpadre();
                            List<Padre> listaPadres = daopadre.ListarPadres();
                            Iterator<Padre> iteradorPadres = listaPadres.iterator();
                            Padre padre = null;
                            while (iteradorPadres.hasNext()) {
                                padre = iteradorPadres.next();
                        %>
                        <tr>
                            <td class="text-center"><%= padre.getId_padre()%></td>
                            <td class="text-center"><%= padre.getNombre_padre()%></td>
                            <td class="text-center"><%= padre.getDNI()%></td>                            
                            <td class="text-center"><%= padre.getDireccion()%></td>
                            <td class="text-center"><%= padre.getTelefono()%></td>
                            <td class="text-center"><%= padre.getCorreo()%></td>
                            <td class="text-center">
                                <a class="btn btn-primary"
                                   href="ControladorPadre?accion=editar&idpa=<%= padre.getId_padre()%>">Editar</a>
                                <a class="btn btn-danger"
                                   href="ControladorPadre?accion=eliminar&idpa=<%= padre.getId_padre()%>">Eliminar</a>
                            </td>
                        </tr>
                        <% } %>
                    </tbody>
                </table>
                <div class="col-4 align-self-center">
                    <a class="btn btn-primary" href="ControladorPadre?accion=mostraragregar">Agregar Padre</a>
                </div> <BR><BR><BR>
                <div style="display: flex; justify-content: left;">
                    <!-- botón pdf -->
                    <form name="ClinicaDental" action="/ClinicaDental/reportePadre">
                        <button type="submit" class="btn btn-primary" style="background-color: transparent; border: none;">
                            <i class='bx bxs-file-pdf' style='color: red; font-size: 72px;'></i> 
                        </button>
                    </form>
                    <!-- botón excel -->
                    <form name="ClinicaDental" action="/ClinicaDental/reportePadreExcel">
                        <button type="submit" class="btn btn-primary" style="background-color: transparent; border: none;">
                            <i class='bx bx-table' style='color: green; font-size: 72px;'></i>
                        </button>
                    </form>
                </div>
            </div>
            <div id="content5" class="content-section">
                <h2>Lista de Servicios</h2>
                <table class="table table-bordered" id="tablaDatos">
                    <thead>
                        <tr>
                            <th class="text-center" width="250">Servicio</th>
                            <th class="text-center" width="500">Descripción</th>
                            <th class="text-center" width="250">Precio (S/.)</th>
                            <th class="text-center" width="200">Acciones</th>
                        </tr>
                    </thead>

                    <tbody>
                        <%
                            DAOservicio daos = new DAOservicio();
                            List<Servicios> listas = daos.ListarCargo();
                            Iterator<Servicios> iters = listas.iterator();
                            Servicios s = null;
                            while (iters.hasNext()) {
                                s = iters.next();
                        %>

                        <tr>
                            <td class="text-center"><%=s.getNombre_servicio()%></td>
                            <td class="text-center"><%=s.getDescripcion()%></td>
                            <td class="text-center"><%=s.getPrecio()%></td>
                            <td class="text-center">
                                <a class="btn btn-primary" 
                                   href="ControladorServicios?accion=editar&idser=<%=s.getId_servicio()%>">Editar</a>
                                <a class="btn btn-danger" 
                                   href="ControladorServicios?accion=eliminar&idser=<%=s.getId_servicio()%>">Eliminar</a>
                            </td>
                        </tr>
                        <%}%>
                    </tbody>
                </table>
                <div class="col-4 align-self-center">
                    <a class="btn btn-primary"  href="ControladorServicios?accion=mostraragregar">Agregar Servicio</a>
                </div><BR><BR>
                <div style="display: flex; justify-content: left;">
                    <!-- botón pdf -->
                    <form name="ClinicaDental" action="/ClinicaDental/reporteServicios">
                        <button type="submit" class="btn btn-primary" style="background-color: transparent; border: none;">
                            <i class='bx bxs-file-pdf' style='color: red; font-size: 72px;'></i> 
                        </button>
                    </form>
                    <!-- botón excel -->
                    <form name="ClinicaDental" action="/ClinicaDental/reporteServiciosExcel">
                        <button type="submit" class="btn btn-primary" style="background-color: transparent; border: none;">
                              <i class='bx bx-table' style='color: green; font-size: 72px;'></i>
                        </button>
                    </form>
                </div>
            </div>
            <div id="content6" class="content-section">
                <h2>Lista de Recepcionistas </h2>
                <table class="table table-bordered" id="tablaDatos">
                    <thead>
                        <tr>
                            <th class="text-center" width="200">Nombre Usuario</th>
                            <th class="text-center" width="500">Contraseña</th>
                            <th class="text-center" width="200">Rol</th>
                            <th class="text-center" width="380">Acciones</th>
                        </tr>
                    </thead>
                    <tbody id="tbodys">
                        <%
                            DAOrecepcionista d = new DAOrecepcionista();
                            List<Usuario> ListarUsuarios = d.ListarUsuarios();
                            Iterator<Usuario> iterr = ListarUsuarios.iterator();
                            Usuario u = null;
                            while (iterr.hasNext()) {
                                u = iterr.next();
                        %>
                        <tr>
                            <td class="text-center"><%=u.getUsername()%></td>
                            <td class="text-center"><%=u.getPassword()%></td>
                            <td class="text-center"><%=u.getRol()%></td>
                            <td class="text-center">
                                <a class="btn btn-primary"
                                   href="ControladorRecepcionistas?accion=editar&idrec=<%=u.getId_usuario()%>">Editar</a>
                                <a class="btn btn-danger"
                                   href="ControladorRecepcionistas?accion=eliminar&idrec=<%=u.getId_usuario()%>">Eliminar</a>
                            </td>
                        </tr>
                        <% }%>
                    </tbody>

                </table>
                <div class="col-4 align-self-center">
                    <a class="btn btn-primary" href="ControladorRecepcionistas?accion=mostraragregar">Agregar Recepcionista</a>
                </div>
            </div>

            <div id="content7" class="content-section">
                <h2>Dashboard</h2>
                <div class="row">
                    <div class="col-md-6">
                        <div class="card">
                            <div class="card-header">
                                Gráfico de Citas identificas por Meses:
                            </div>
                            <div class="card-body">
                                <canvas id="ventasMensualesChart"></canvas>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="card">
                            <div class="card-header">
                                Gráfico de Pacientes según su Sexo:
                            </div>
                            <div class="card-body">
                                <canvas id="pieChart"></canvas>
                            </div>
                        </div>
                    </div>
                </div>
            </div>              

    <!-- Estamos usando chart.js (Lib. de Javasript) -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.7.0/chart.min.js"></script>
    <script>
        $(document).ready(function () {
            $.ajax({
                url: "CitasData",
                method: "GET",
                dataType: "json",
                success: function (data) {
                    var labels = data.labels;
                    var values = data.values;

                    // Mapeo de los números de meses a nombres de meses
                    var monthNames = ["Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"];
                    var monthLabels = labels.map(function (label) {
                        return monthNames[parseInt(label) - 1];
                    });

                    var ventasMensualesData = {
                        labels: monthLabels,
                        datasets: [{
                            label: 'Citas',
                            data: values,
                            backgroundColor: 'rgba(75, 192, 192, 0.2)',
                            borderColor: 'rgba(75, 192, 192, 1)',
                            borderWidth: 1
                        }]
                    };

                    var ventasMensualesOptions = {
                        scales: {
                            y: {
                                beginAtZero: true
                            }
                        }
                    };

                    var ctx = document.getElementById('ventasMensualesChart').getContext('2d');
                    var myChart = new Chart(ctx, {
                        type: 'bar',
                        data: ventasMensualesData,
                        options: ventasMensualesOptions
                    });
                },
                error: function (error) {
                    console.log(error);
                }
            });
        });
    </script>


<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.7.0/chart.min.js"></script>

<script>
        $(document).ready(function () {
            $.ajax({
                url: 'HijosData', // Asegúrate de que esto coincide con el urlPattern del servlet
                type: 'GET',
                dataType: 'json',
                success: function (data) {
                    // Crear el gráfico con los datos obtenidos
                    var ctx = document.getElementById('pieChart').getContext('2d');
                    var myChart = new Chart(ctx, {
                        type: 'bar',
                        data: {
                            labels: data.labels,
                            datasets: [{
                                label: 'Cantidad de Personas por Sexo',
                                data: data.data,
                                backgroundColor: [
                                    'rgba(255, 99, 132, 0.2)', // Color para Masculino
                                    'rgba(54, 162, 235, 0.2)', // Color para Femenino
                                    'rgba(255, 206, 86, 0.2)', // Color para Otros
                                    'rgba(75, 192, 192, 0.2)'  // Color para No Especificado
                                ],
                                borderColor: [
                                    'rgba(255, 99, 132, 1)',
                                    'rgba(54, 162, 235, 1)',
                                    'rgba(255, 206, 86, 1)',
                                    'rgba(75, 192, 192, 1)'
                                ],
                                borderWidth: 1
                            }]
                        },
                        options: {
                            scales: {
                                y: {
                                    beginAtZero: true
                                }
                            }
                        }
                    });
                }
            });
        });
</script>
        </div>

        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script>
                $(document).ready(function () {
                    $('#sidebarCollapse').on('click', function () {
                        $('#sidebar').toggleClass('collapsed');
                        $('#content').toggleClass('collapsed');
                    });

                    $('.nav-link').on('click', function () {
                        var contentId = $(this).data('content');
                        $('.content-section').hide();
                        $('#' + contentId).show();
                    });
                });
        </script>
    </body>
</html>








