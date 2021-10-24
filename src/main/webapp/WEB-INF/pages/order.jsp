<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>Баланс</title>
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
		<form action="save_balance" method="POST">
			<%
				float balance = 0;
				try{
					balance = Float.parseFloat(pageContext.getRequest().getAttribute("balance")+"");
				}catch (Exception e){}
			%>
			<div class="form-group">
				<div class="form-group">
					<label for="balance">Текущий баланс : <%out.print(balance);%></label>
					<input type="text" class="form-control" id="balance" placeholder="Баланс"  name="balance"  value="<%out.print(balance);%>">
				</div>

			</div>
			<button type="submit" class="btn btn-primary">Сохранить</button>

		</form>
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