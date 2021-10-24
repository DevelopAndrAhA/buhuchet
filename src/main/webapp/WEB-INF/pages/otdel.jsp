<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="kg.dor.models.Otdel" %>
<html>
<head>
	<title>Отделы</title>
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
		<a href="newdepartment?otdel_id=0">Новый отдел</a>
		<table class="table">

			<thead>
				<tr>
					<%
						List<Otdel> otdels =  null;
						try{
							otdels = (List<Otdel>) pageContext.getRequest().getAttribute("otdels");
						}catch (Exception e){}
						out.print("<script type='application/javascript'> var accessToRedirect = false;</script>");
						out.print("<script type='application/javascript'>  accessToRedirect = true;</script>");
						out.print("<th scope=col>#</th>");
						out.print("<th scope=col>Название</th>");
						out.print("<th scope=col>Действие</th>");
					%>
				</tr>
			</thead>

			<tbody>
					<%
						if(otdels!=null) {
							if(otdels.size()!=0){
								for (int i = 0; i < otdels.size(); i++) {
									Otdel otdel = otdels.get(i);
									int k = i + 1;
									out.println("<tr>");
									out.println("<td>" + k + "</td>");
									out.println("<td><h4><a href=department_info?otdel_id="+otdel.getOtdel_id()+">" + otdel.getName() + "</a></h4></td>");
									out.println("<td><a href=newdepartment?otdel_id="+otdel.getOtdel_id()+">Редактировать</a></td>");
									out.println("<tr>");
								}
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
	<%--<a href="child_department?otdel_id="+otdel.getOtdel_id()+"/>--%>
	<row>${error}</row>

	<%--<script  type="application/javascript">
		if(accessToRedirect){
			window.location.replace("download-xlsx");
		};
	</script>--%>

</div>
</body>
</html>