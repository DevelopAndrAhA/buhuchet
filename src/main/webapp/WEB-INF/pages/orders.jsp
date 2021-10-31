<%@ page import="kg.dor.models.Order" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>Список заказов</title>
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
		List<Order> orders = null;
		try{
			orders = (List<Order>)pageContext.getRequest().getAttribute("orders");
		}catch (Exception e){}
	%>
	<div class="row">
		<a href="new_order">Новый заказ</a>
		<table class="table">

			<thead>
			<tr>
				<%
					out.print("<th scope=col>#</th>");
					out.print("<th scope=col>Дата заказа</th>");
					out.print("<th scope=col>Сумма заказа</th>");
					out.print("<th scope=col>ФИО клиента</th>");
					out.print("<th scope=col>Действие</th>");
				%>
			</tr>
			</thead>

			<tbody>
			<%
				if(orders!=null) {
					for (int i = 0; i < orders.size(); i++) {
						Order order = orders.get(i);
						out.println("<tr>");
						out.println("<td>" + order.getOrder_id() + "</td>");
						out.println("<td>" + order.getInp_date() + "</td>");
						out.println("<td>" + order.getFull_amount() + "</td>");
						out.println("<td>" + order.getCl_fio() + "</td>");
						out.println("<td><a href=\"order_info?order_id="+order.getOrder_id()+"\">Подробнее</a></td>");
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