<%@ page import="kg.dor.models.Purchase" %>
<%@ page import="java.util.List" %>
<%@ page import="kg.dor.models.PProduct" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>Клиент</title>
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
		<form class="sign-in" action="history_purchase_d" method="post">
			<h2 class="form-signin-heading">История закупок на дату</h2>


			<div class="container" style="max-width:800px;margin:0 auto;margin-top:50px;margin-bottom: 50px;">
				<div class="row">
					<div class="form-group">
						<label>Выбрать начальную дату:</label>
						<input type="text" id="start_date" name="start_date" class="form-control" placeholder="Выбрать начальную дату">
					</div>
					<div class="form-group">
						<label>Выбрать конечную дату:</label>
						<input type="text" id="end_date" name="end_date" class="form-control" placeholder="Выбрать начальную дату">
					</div>
				</div>
			</div>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"></script>
			<script>
				$(document).ready(function () {
					$('#start_date').datepicker({
						autoclose: true,
						format: "yyyy-mm-dd"
					});
					$('#end_date').datepicker({
						autoclose: true,
						format: "yyyy-mm-dd"
					});
				});
			</script>


			<button class="btn btn-lg btn-primary btn-block" type="submit" id="date_data_btn">Показать</button>
		</form>
	</div>








	<%
		List<Purchase> purchases = null;
		try{
			purchases = (List<Purchase>) pageContext.getRequest().getAttribute("purchases");
		}catch (Exception e){}
	%>

	<%
		List<PProduct> pProducts = null;
		try{
			pProducts = (List<PProduct>) pageContext.getRequest().getAttribute("pProducts");
		}catch (Exception e){}
	%>

	<row>
		<table class="table">

			<thead>
				 <tr>
			<%
				if(purchases!=null) {
					if (purchases.size() != 0) {
						out.println("<th scope=col>#</th>");
						out.println("<th scope=col>Дата заказа</th>");
						out.println("<th scope=col>Общая сумма</th>");
						out.println("<th scope=col>Действие</th>");
					}
				}
			%>
			<%
				if(pProducts!=null) {
					if (pProducts.size() != 0) {
						out.println("<th scope=col>#</th>");
						out.println("<th scope=col>Название продукта</th>");
						out.println("<th scope=col>Описание</th>");
						out.println("<th scope=col>Стоимость</th>");
					}
				}
			%>
				</tr>

			</thead>

			<tbody>
			<%
				if(purchases!=null) {
					if(purchases.size()!=0){
						for (int i = 0; i < purchases.size(); i++) {
							Purchase purchase = purchases.get(i);
							int k = i + 1;
							out.println("<tr>");
							out.println("<td>" + k + "</td>");
							out.println("<td>" + purchase.getInp_date() + "</td>");
							out.println("<td>" + purchase.getAll_price() + "</td>");
							out.println("<td><h4><a href=purchase_info?purchase_id="+purchase.getP_id() + "> Детально </a></h4></td>");
							out.println("<tr>");
						}
					}

				}
			%>


			<%
				if(pProducts!=null) {
					if(pProducts.size()!=0){
						for (int i = 0; i < pProducts.size(); i++) {
							PProduct pProduct = pProducts.get(i);
							int k = i + 1;
							out.println("<tr>");
							out.println("<td>" + k + "</td>");
							out.println("<td>" + pProduct.getName() + "</td>");
							out.println("<td>" + pProduct.getProd_desc() + "</td>");
							out.println("<td>" + pProduct.getPrice() + "</td>");
							out.println("<tr>");
						}
					}

				}
			%>


			</tbody>
	</table>

	</row>












</div>
</body>
</html>