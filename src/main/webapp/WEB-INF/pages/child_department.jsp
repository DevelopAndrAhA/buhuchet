<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="kg.dor.models.Otdel" %>
<%@ page import="kg.dor.models.ChildOtdel" %>
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
		long otdel_id=0;
		long child_otdel_id=0;
		String child_otdel_name = "";
		ChildOtdel childOtdel = null;
		try{

			childOtdel = (ChildOtdel) pageContext.getRequest().getAttribute("childOtdel");
			child_otdel_id = childOtdel.getChild_otdel_id();
			child_otdel_name = childOtdel.getName();
		}catch (Exception e){}
		try{
			Otdel otdel = (Otdel) pageContext.getRequest().getAttribute("otdel");
			otdel_id = otdel.getOtdel_id();
		}catch (Exception e){}

	%>
	<row>
		<a href="new_client?otdel_id=<%out.print(otdel_id);%>">Новый клиент</a>
		<form action="savechildtdel" method="POST">
			<input type="text" style="display: none" name="otdel_id" value="<%out.print(otdel_id);%>">
			<input type="text" style="display: none" name="child_otdel_id" value="<%out.print(child_otdel_id);%>">
			<div class="form-group">
				<label for="childOtdel">Название</label>
				<input type="text" class="form-control" id="childOtdel" aria-describedby="group" placeholder="Название" name="name"   value="<%out.print(child_otdel_name);%>">
			</div>
			<button type="submit" class="btn btn-primary">Сохранить</button>

		</form>

	<row>${error}</row>

	<%--<script  type="application/javascript">
		if(accessToRedirect){
			window.location.replace("download-xlsx");
		};
	</script>--%>

</div>
</body>
</html>