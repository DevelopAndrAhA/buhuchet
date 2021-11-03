<%@ page import="kg.dor.models.Client" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>Новый заказ</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script type="text/javascript" src="resources/js/jquery-3.3.1.min.js"></script>
	<link rel="stylesheet"  href="resources/css/mycss/mycss.css" />
	<link rel="stylesheet"  href="resources/css/bootstrap.css" />
	<link rel="shortcut icon" href="/resources/images/favicon.png" type="image/png"/>
	<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

	<!-- Optional theme -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

	<!-- Latest compiled and minified JavaScript -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container">
	<%List<Client> clients=null;
		try{
			clients = (List<Client>)pageContext.getRequest().getAttribute("clients");
		}catch (Exception e){}
	%>
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
		<select name="dropdown" class="dropdown form-control">
			<%
				if(clients!=null){
					for(int i=0;i<clients.size();i++){
						out.print("<option>"+ clients.get(i).getCl_id()+": "+clients.get(i).getFio()+"</option>");
					}
				}

			%>
		</select>

		</br>

		<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"></script>
		<div class="form-group">
			<label>Выбрать дату возврата средств:</label>
			<input type="text" id="return_cos_date" class="form-control" placeholder="Выбрать дату возврата средств">
		</div>
		</br>
		<div class="row">
			<div class="col-xs-4 col-md-4">
				<div class="input-group">
					<input type="text" class="form-control" placeholder="Поиск клиента" id="txtSearch"/>
					<div class="input-group-btn">
						<button class="btn btn-primary" id="search" type="submit">
							<span class="glyphicon glyphicon-search"></span>
						</button>
					</div>
				</div>
			</div>
		</div>

		<form action="save_order" method="post">
			<input type="text" style="display: none" name="json_data" id="json_data">
			<button class="btn btn-lg btn-primary btn-block" type="submit" id="save">Сохранить</button>

		</form>
	</div>
</div>

	<row>${error}</row>

	<%--<script  type="application/javascript">
		if(accessToRedirect){
			window.location.replace("download-xlsx");
		};
	</script>--%>
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
		var tmp_return_cos_date = document.getElementById('return_cos_date');
		order.return_cos_date = tmp_return_cos_date.value;
		var orderJSON = JSON.stringify(order);
		$("#json_data").val(orderJSON);
	});
</script>

<script  type="application/javascript">
	$("#search").click(function() {
		window.location.replace("search_client?fio="+document.getElementById("txtSearch").value);
	});
</script>
<script type="application/javascript">
	function splitString(stringToSplit, separator) {
		var arrayOfStrings = stringToSplit.split(separator);
		return arrayOfStrings;
	}
	var item = $(".dropdown").val();
	var tmpArr = splitString(item, ": ");
	order.cl_id = tmpArr[0];
	order.cl_fio = tmpArr[1];



	var today = new Date();
	var date = today.getFullYear()+'-'+(today.getMonth()+1)+'-'+today.getDate();
	order.return_cos_date = date;


	$('.dropdown').change(function(){
		item = $(this).val();
		tmpArr = splitString(item, ": ");
		order.cl_id = tmpArr[0];
		order.cl_fio = tmpArr[1];

	});

</script>
<script>
	$(document).ready(function () {
		$('#return_cos_date').datepicker({
			autoclose: true,
			format: "yyyy-mm-dd"
		});
	});
</script>

</div>
</body>
</html>






















