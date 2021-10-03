<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="kg.dor.models.Order" %>
<%@ page import="org.springframework.beans.factory.annotation.Autowired" %>
<%@ page import="kg.dor.service.CrudService" %>
<%@ page import="kg.dor.models.Client" %>
<html>
<head>
	<title>Главная</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script type="text/javascript" src="resources/js/jquery-3.3.1.min.js"></script>
	<link rel="stylesheet"  href="resources/css/mycss/mycss.css" />
	<link rel="stylesheet"  href="resources/css/bootstrap.css" />
	<link rel="shortcut icon" href="/resources/images/favicon.png" type="image/png"/>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container">

	<div class="row my_row">
		<form class="sign-in" action="get-data-f" method="post">
			<h2 class="form-signin-heading">Заказы на дату</h2>


			<div class="container" style="max-width:800px;margin:0 auto;margin-top:50px;margin-bottom: 50px;">
				<div class="row">
					<div class="form-group">
						<label>Выбрать начальную дату:</label>
						<input type="text" id="start_date" class="form-control" placeholder="Выбрать начальную дату">
					</div>
					<div class="form-group">
						<label>Выбрать конечную дату:</label>
						<input type="text" id="end_date" class="form-control" placeholder="Выбрать начальную дату">
					</div>
				</div>
			</div>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"></script>
			<script>
				$(document).ready(function () {
					$('#start_date').datepicker({
						autoclose: true,
						format: "dd/mm/yyyy"
					});
					$('#end_date').datepicker({
						autoclose: true,
						format: "dd/mm/yyyy"
					});
				});
			</script>


			<button class="btn btn-lg btn-primary btn-block" type="submit" id="date_data_btn">Вывести</button>
		</form>
	</div>
	<row>
		<table class="table">

			<thead>
				<tr>
					<%




						List<Order> orders = (List<Order>) pageContext.getRequest().getAttribute("orders");
						List<Client> clients = (List<Client>) pageContext.getRequest().getAttribute("clients");
						out.print("<script type='application/javascript'> var accessToRedirect = false;</script>");
						if(orders!=null) {
							out.print("<script type='application/javascript'>  accessToRedirect = true;</script>");
							out.print("<th scope=col>#</th>");
							out.print("<th scope=col>Клиент</th>");
							out.print("<th scope=col>Дата заказа</th>");
							out.print("<th scope=col>Сумма заказа</th>");
						}
					%>
				</tr>
			</thead>

			<tbody>
					<%
						for(int i =0;i<orders.size();i++){
							Order order = orders.get(i);
							int k = i+1;
							out.println("<tr>");
							out.println("<td>" + k + "</td>");
							out.println("<td>" + clients.get(i).getFio() + "</td>");
							out.println("<td>" + order.getInp_date() + "</td>");
							out.println("<td>" + order.getFull_amount() + "</td>");
							out.println("<td><a href=#>детально</a></td>");
							out.println("<tr>");
							}
					%>
			</tbody>
		</table>
		<%--<%
			if(ins_ws_ts!=null){
				out.println("<row><h4>Общее поголовьe : "+ins_ws_ts.size()+"</h4></row>");
			}
		%>--%>
	</row>

	<row>${error}</row>

	<%--<script  type="application/javascript">
		if(accessToRedirect){
			window.location.replace("download-xlsx");
		};
	</script>--%>

</div>
</body>
</html>