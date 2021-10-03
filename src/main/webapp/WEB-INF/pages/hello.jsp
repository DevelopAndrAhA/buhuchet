<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>Проверка данных по клиенту</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script type="text/javascript" src="resources/js/jquery-3.3.1.min.js"></script>
	<link rel="stylesheet"  href="resources/css/mycss/sign_in.css" />
	<link rel="stylesheet"  href="resources/css/bootstrap.css" />
	<link rel="shortcut icon" href="/resources/images/favicon.png" type="image/png"/>
</head>
<body>
<%
	String errorSignIn = (String) pageContext.getRequest().getAttribute("errorSignIn");
	if(errorSignIn!=null){
		if(errorSignIn.equals("errorSignIn")){
			out.print("<script type='application/javascript'> var globFlag = true;</script>");
		}
	}

%>
<div class="container">
	<div class="row">
		<form class="sign-in" action="sign-in" method="post">
			<h2 class="form-signin-heading">Войдите</h2>
			<label for="text" class="sr-only">Login</label>
			<input type="text" id="text" name="login" class="form-control" placeholder="Login" required autofocus>
			<label for="inputPassword" class="sr-only">Password</label>
			<input type="password" id="inputPassword" name="password" class="form-control" placeholder="Password" required>
			<button class="btn btn-lg btn-primary btn-block" type="submit">Войти</button>
		</form>
	</div>

	<%--<row>
		${errors}
	</row>--%>


</div>
<script type='application/javascript'>
	if(globFlag){
		alert('Не верный логин или пароль!!!');
	}
</script>
</body>
</html>