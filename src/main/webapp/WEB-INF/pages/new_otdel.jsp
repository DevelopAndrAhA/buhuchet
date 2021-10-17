<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="kg.dor.models.Courier" %>
<%@ page import="kg.dor.models.Otdel" %>
<%@ page import="kg.dor.models.ChildOtdel" %>
<%@ page import="java.util.List" %>
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
		Otdel otdel = null;
		List<ChildOtdel> childOtdels = null;
		int otdel_id =0;
		String nazvanie="";
		try {
			otdel = (Otdel) pageContext.getRequest().getAttribute("otdel");
			otdel_id = (int)otdel.getOtdel_id();
			nazvanie = otdel.getName();
		}catch (Exception e){}
		try{
			childOtdels = (List<ChildOtdel>) pageContext.getRequest().getAttribute("childOtdels");
		}catch (Exception e){}
	%>
	<row>
		<a href="new_child_department?otdel_id=<%out.print(otdel_id+"&child_department_id=0");%>">Новый подотдел</a>
		<a href="new_client?otdel_id=<%out.print(otdel_id);%>">Новый клиент</a>
		<form action="savedepartment" method="POST">

			<input type="text" style="display: none" name="otdel_id" value="<%out.print(otdel_id);%>">
			<div class="form-group">
				<label for="nazvanie">Название</label>
				<input type="text" class="form-control" id="nazvanie" aria-describedby="nazvanie" placeholder="Название" name="nazvanie"

					    value="<%out.print(nazvanie);%>">
			</div>

			<button type="submit" class="btn btn-primary">Сохранить</button>

		</form>
	</row>


	<table class="table">
		<row>Список подотделов</row>
		<thead>
		<tr>
			<%
				out.print("<th scope=col>#</th>");
				out.print("<th scope=col>Название</th>");
				out.print("<th scope=col>Действие</th>");
			%>
		</tr>
		</thead>

		<tbody>
			<%
					if(childOtdels!=null){
						for(int i=0;i<childOtdels.size();i++){
							ChildOtdel childOtdel = childOtdels.get(i);
							out.println("<tr>");
							out.println("<td>" + i+1 + "</td>");
							out.println("<td>" + childOtdel.getName() + "</td>");
							out.println("<td><a href=\"new_child_department?otdel_id="+childOtdel.getParent_otdel()+"&child_department_id="+childOtdel.getChild_otdel_id()+"\">Редактировать</a></td>");
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