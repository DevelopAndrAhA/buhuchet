<%@ page import="kg.dor.models.Order" %>
<%@ page import="java.util.List" %>
<%@ page import="kg.dor.models.Product" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>О заказе</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script type="text/javascript" src="resources/js/jquery-3.3.1.min.js"></script>
	<link rel="stylesheet"  href="resources/css/mycss/mycss.css" />
	<link rel="stylesheet"  href="resources/css/bootstrap.css" />
	<link rel="shortcut icon" href="/resources/images/favicon.png" type="image/png"/>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container">
	<%
		Order  order = null;
		try{
			order = (Order)pageContext.getRequest().getAttribute("order");
		}catch (Exception e){}
	%>
	<div class="row">
		<table class="table">
			<thead>
			<tr>
				<%
					out.print("<th scope=col>Дата заказа</th>");
					out.print("<th scope=col>Дата возврата средств</th>");
					out.print("<th scope=col>Общая сумма</th>");
					out.print("<th scope=col>Изначальная сумма</th>");
					out.print("<th scope=col>ФИО клиента</th>");
				%>
			</tr>
			</thead>
			<tbody>
			<%
				out.println("<tr>");
				out.println("<td>" + order.getInp_date() + "</td>");
				out.println("<td>" + order.getReturn_cos_date() + "</td>");
				out.println("<td>" + order.getFull_amount() + "</td>");
				out.println("<td>" + order.getHistory_amount() + "</td>");
				out.println("<td>" + order.getCl_fio() + "</td>");
				out.println("<tr>");
			%>
			</tbody>
		</table>





		<form action="update_order" method="post">
			<input type="text" style="display: none" name="order_id" value="<%out.print(order.getOrder_id());%>">
			<input type="text" style="display: none" name="cl_id" value="<%out.print(order.getCl_id());%>">
			<div class="form-group">
				<label>Сумма:</label>
				<input type="text" class="form-control" id="summa" placeholder="Сумма"  name="summa"  value="<%out.print(order.getFull_amount());%>">
			</div>
			<div class="form-group">
				<label>Дата возврата:</label>
				<input type="text" id="return_date" name="return_date" class="form-control" placeholder="Дата возврата:" value="<%out.print(order.getReturn_cos_date());%>">
			</div>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"></script>
			<script>
				$(document).ready(function () {
					$('#return_date').datepicker({
						autoclose: true,
						format: "yyyy-mm-dd"
					});
				});
			</script>


			<button class="btn btn-lg btn-primary btn-block" type="submit" id="save">Сохранить</button>

		</form>










		<table class="table">
			<thead>
			<tr>
				<%
					out.print("<th scope=col>#</th>");
					out.print("<th scope=col>Продукт</th>");
					out.print("<th scope=col>Стоимость</th>");
					out.print("<th scope=col>Описание</th>");
				%>
			</tr>
			</thead>

			<tbody>
			<%
				if(order!=null) {
					for (int i = 0; i < order.getProducts().size(); i++) {
						Product product = order.getProducts().get(i);
						out.println("<tr>");
						out.println("<td>" + product.getProduct_id() + "</td>");
						out.println("<td>" + product.getName() + "</td>");
						out.println("<td>" + product.getPrice() + "</td>");
						out.println("<td>" + product.getProd_desc() + "</td>");
						out.println("<tr>");
					}
				}
			%>
			</tbody>
		</table>
	</div>


	<row>${error}</row>

	<%--<script  type="application/javascript">
		if(accessToRedirect){
			window.location.replace("download-xlsx");
		};
	</script>--%>

</div>
</body>
</html>