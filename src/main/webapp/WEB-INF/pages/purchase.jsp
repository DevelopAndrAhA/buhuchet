<%@ page import="kg.dor.models.Otdel" %>
<%@ page import="kg.dor.models.ChildOtdel" %>
<%@ page import="kg.dor.models.Client" %>
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
	<row>
		<a href="history_purchase">Список закупок за дату</a>

		<div class="row">
			<table class="table">

				<thead>
				<tr>
					<th scope=col>Название продукта</th>
					<th scope=col>Стоимость</th>
					<th scope=col>Описание</th>
				</tr>
				</thead>

				<tbody>
				<tr>
					<td><input type="text" class="form-control product_name" aria-describedby="product_name" placeholder="Название продукта" name="product_name"></td>
					<td><input type="text" class="form-control price" aria-describedby="price" placeholder="Стоимость" name="price"></td>
					<td><input type="text" class="form-control desc" aria-describedby="desc" placeholder="Описание" name="desc"></td>
				</tr>
				</tbody>
			</table>

			<button class="btn btn-lg btn-primary btn-block" type="submit" id="plus">+</button>

			<br>


			<form action="save_purchase" method="post">
				<input type="text" style="display: none" name="json_data" id="json_data">
				<button class="btn btn-lg btn-primary btn-block" type="submit" id="save">Сохранить</button>

			</form>
		</div>

	<row>${error}</row>
		<script  type="application/javascript">
			$("#plus").click(function(){
				$("tbody").append("<tr><td><input type=\"text\" class=\"form-control product_name\"  aria-describedby=\"product_name\" placeholder=\"Название продукта\" name=\"product_name\"></td>" +
				"<td><input type=\"text\" class=\"form-control price\" aria-describedby=\"price\" placeholder=\"Стоимость\" name=\"price\"></td>" +
				"<td><input type=\"text\" class=\"form-control desc\"  aria-describedby=\"desc\" placeholder=\"Описание\" name=\"desc\"></td></tr>");
			});
		</script>
		<script  type="application/javascript">
			var order = new Object();
			$("#save").click(function() {
				var pn = document.getElementsByClassName('product_name');
				var pr = document.getElementsByClassName('price');
				var pd = document.getElementsByClassName('desc');
				order.products = new Array();
				for (var i = 0; i < pn.length; i++) {
					var product = new Object();
					product.name = pn[i].value;
					product.price = Number(pr[i].value);
					product.prod_desc = pd[i].value;
					order.products[i]=product;
				}
				var orderJSON = JSON.stringify(order);
				$("#json_data").val(orderJSON);
			});
		</script>

</div>
</body>
</html>
