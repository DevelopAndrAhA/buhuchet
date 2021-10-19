<%@ page import="kg.dor.models.Otdel" %>
<%@ page import="kg.dor.models.ChildOtdel" %>
<%@ page import="kg.dor.models.Client" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
		<%
			Otdel otdel = null;
			ChildOtdel childOtdel = null;
			Client client = null;
			try{
				 otdel = (Otdel) pageContext.getRequest().getAttribute("otdel");
			}catch (Exception e){}

			try{
				childOtdel = (ChildOtdel) pageContext.getRequest().getAttribute("childOtdel");
			}catch (Exception e){}

			String client_id="0";
			String fio="";
			String phone="";
			String balance="";


			try{
				client = (Client) pageContext.getRequest().getAttribute("client");
				 client_id = client.getCl_id()+"";
				 fio = client.getFio();
				 phone = client.getPhone();
				 balance = client.getSumma_dolga()+"";

			}catch (Exception e){}


		%>
		<form action="save_client" method="POST">

				<div class="form-group">
					<input type="text" style="display: none" name="department_id" value="<%if(otdel!=null){out.print(otdel.getOtdel_id());}else{out.print("0");}%>">
					<input type="text" style="display: none" name="childOtdelId" value="<%if(childOtdel!=null){out.print(childOtdel.getChild_otdel_id());}%>">
					<input type="text" style="display: none" name="client_id" value="<%out.print(client_id);%>">
					<div class="form-group">
						<label for="fio">ФИО</label>
						<input type="text" class="form-control" id="fio" aria-describedby="fio" placeholder="ФИО" name="fio"   value="<%out.print(fio);%>">
					</div>

					<div class="form-group">
						<label for="phone">Телефон</label>
						<input type="text" class="form-control" id="phone" placeholder="Телефон"  name="phone"  value="<%out.print(phone);%>">
					</div>

					<div class="form-group">
						<label for="balance">Задолженность</label>
						<input type="text" class="form-control" id="balance" placeholder="Задолженность"  name="balance"  value="<%out.print(balance);%>">
					</div>

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
