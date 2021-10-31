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
		<a href="courier?cr_id=0">Новый курьер</a>
		<table class="table">
			<thead>
			<tr>
				<%
					out.print("<th scope=col>Дата заказа</th>");
					out.print("<th scope=col>Общая сумма</th>");
					out.print("<th scope=col>ФИО клиента</th>");
				%>
			</tr>
			</thead>
			<tbody>
			<%
				out.println("<tr>");
				out.println("<td>" + order.getInp_date() + "</td>");
				out.println("<td>" + order.getFull_amount() + "</td>");
				out.println("<td>" + order.getCl_fio() + "</td>");
				out.println("<tr>");
			%>
			</tbody>
		</table>
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