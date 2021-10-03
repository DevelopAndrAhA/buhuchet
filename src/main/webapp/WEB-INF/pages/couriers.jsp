<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="kg.dor.models.Order" %>
<%@ page import="org.springframework.beans.factory.annotation.Autowired" %>
<%@ page import="kg.dor.service.CrudService" %>
<%@ page import="kg.dor.models.Client" %>
<%@ page import="kg.dor.models.Courier" %>
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

	<row>
		<a href="courier?cr_id=0">Новый курьер</a>
		<table class="table">

			<thead>
				<tr>
					<%
						List<Courier> couriers = (List<Courier>) pageContext.getRequest().getAttribute("couriers");
						out.print("<script type='application/javascript'> var accessToRedirect = false;</script>");
						out.print("<script type='application/javascript'>  accessToRedirect = true;</script>");
						out.print("<th scope=col>#</th>");
						out.print("<th scope=col>Логин</th>");
						out.print("<th scope=col>Пароль</th>");
						out.print("<th scope=col>ФИО</th>");
						out.print("<th scope=col>Номер телефона</th>");
						out.print("<th scope=col>Статус</th>");
					%>
				</tr>
			</thead>

			<tbody>
					<%
						if(couriers!=null) {
							for (int i = 0; i < couriers.size(); i++) {
								Courier courier = couriers.get(i);
								int k = i + 1;
								String status = courier.getBlocked_status();
								if(status!=null){
									if(status.equals("unchecked")){
										status = "Активный";
									}else{
										status = "Заблокирован";
									}
								}else{
									status = "Активный";
								}
								out.println("<tr>");
								out.println("<td>" + k + "</td>");
								out.println("<td>" + courier.getLogin() + "</td>");
								out.println("<td>" + courier.getPassword() + "</td>");
								out.println("<td>" + courier.getFio() + "</td>");
								out.println("<td>" + courier.getPhone() + "</td>");
								out.println("<td>" + status + "</td>");
								out.println("<td><a href=\"courier?cr_id="+courier.getCr_id()+"\">Редактировать</a></td>");
								out.println("<tr>");
							}
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