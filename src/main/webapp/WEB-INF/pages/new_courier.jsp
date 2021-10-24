<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="kg.dor.models.Order" %>
<%@ page import="org.springframework.beans.factory.annotation.Autowired" %>
<%@ page import="kg.dor.service.CrudService" %>
<%@ page import="kg.dor.models.Client" %>
<%@ page import="kg.dor.models.Courier" %>
<html>
<head>
	<title>Курьер</title>
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
		<form action="savecourier" method="POST">
			<%
				Courier courier = null;
				int cr_id =0;
				 String login="";
				 String password="";
				 String fio="";
				 String phone="";
				 String blocked_status="unchecked";
				try {
					courier = (Courier) pageContext.getRequest().getAttribute("courier");
					cr_id = (int)courier.getCr_id();
					login = courier.getLogin();
					password = courier.getPassword();
					fio = courier.getFio();
					phone = courier.getPhone();
					blocked_status = courier.getBlocked_status();
				}catch (Exception e){}

			%>
			<input type="text" style="display: none" name="cr_id" value="<%out.print(cr_id);%>">
			<div class="form-group">
				<label for="courier_name">ФИО</label>
				<input type="text" class="form-control" id="courier_name" aria-describedby="fio" placeholder="ФИО" name="fio"   value="<%out.print(fio);%>">
			</div>
			<div class="form-group">
				<label for="login">Логин</label>
				<input type="text" class="form-control" id="login" placeholder="Логин"  name="login"  value="<%out.print(login);%>">
			</div>
			<div class="form-group">
				<label for="password">Пароль</label>
				<input type="password" class="form-control" id="password" placeholder="Пароль" name="password"  value="<%out.print(password);%>">
			</div>
			<div class="form-group">
				<label for="phone">Телефон</label>
				<input type="text" class="form-control" id="phone" placeholder="Телефон"  name="phone"  value="<%out.print(phone);%>">
			</div>
			<div class="form-check">
				<input type="checkbox" class="form-check-input" id="exampleCheck1" name="blocked_status"  <%out.print(blocked_status);%> >
				<label class="form-check-label" for="exampleCheck1">Заблокировать</label>
			</div>
			<button type="submit" class="btn btn-primary">Сохранить</button>
		</form>
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