<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="kg.dor.models.ChildOtdel" %>
<%@ page import="java.util.List" %>
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

	<%
		List<ChildOtdel> childOtdel = null;
		List<Client> clients = null;
		try{
			childOtdel = (List<ChildOtdel>) pageContext.getRequest().getAttribute("childOtdels");
		}catch (Exception e){}
		try{
			clients = (List<Client>) pageContext.getRequest().getAttribute("clients");
		}catch (Exception e){}


	%>



	<table class="table">
		<row>Список подотделов</row>
		<thead>
		<tr>
			<%
				out.print("<th scope=col>#</th>");
				out.print("<th scope=col>Название подотдела</th>");
			%>
		</tr>
		</thead>

		<tbody>
		<%
			if(childOtdel!=null){
				for(int i =0;i<childOtdel.size();i++){
					out.println("<tr>");
					out.println("<td>"+i+1+"</td>");
					out.println("<td><a href=\"clients?child_department_id="+childOtdel.get(i).getChild_otdel_id()+"\">"+childOtdel.get(i).getName()+"</a></td>");
					out.println("<tr>");
				}
			}
		%>
		</tbody>
	</table>


	<table class="table">
		<row>Список клиентов</row>
		<thead>
		<tr>
			<%
				out.print("<th scope=col>#</th>");
				out.print("<th scope=col>ФИО</th>");
				out.print("<th scope=col>Телефон</th>");
				out.print("<th scope=col>Сумма долга</th>");
				out.print("<th scope=col>Действие</th>");
			%>
		</tr>
		</thead>

		<tbody>
			<%
				if(clients!=null){
					for(int i =0;i<clients.size();i++){
						out.println("<tr>");
						out.println("<td><a href=\"clients?child_department_id="+clients.get(i).getCl_id()+"\">"+clients.get(i).getFio()+"</a></td>");
						out.println("<td><a href=tel:"+clients.get(i).getPhone()+">"+clients.get(i).getPhone()+"</a></td>");
						out.println("<td>"+clients.get(i).getSumma_dolga()+"</td>");
						out.println("<td><a href=\"client_balance?client_id="+clients.get(i).getCl_id()+"\">Редактировать</a></td>");
						out.println("<tr>");
					}
				}
			%>
		</tbody>
	</table>





















	<row>${error}</row>

	<%--<script  type="application/javascript">
		if(accessToRedirect){
			window.location.replace("download-xlsx");
		};
	</script>--%>

</div>
</body>
</html>