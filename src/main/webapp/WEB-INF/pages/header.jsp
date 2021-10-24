<%@ page import="kg.dor.models.Admin" %>
<%@ page import="kg.dor.models.Courier" %>
<%--
  Created by IntelliJ IDEA.
  User: altynbek.kochkonbaev
  Date: 27.09.2021
  Time: 9:45
  To change this template use File | Settings | File Templates.
--%>
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
<%
  Admin admin = null;
  Courier courier = null;
 try{
    admin = (Admin) session.getAttribute("admin");
 }catch (Exception e){}
  try{
     courier = (Courier) session.getAttribute("courier");
  }catch (Exception e){}

    int orderSize = 0;
  try{
      orderSize = Integer.parseInt(session.getAttribute("orderSize")+"");
  }catch (Exception e){}

  if(admin==null && courier==null){
    out.print("<script type=\"application/javascript\">\n" +
            "    $('.btn-secondary').click(function(){\n" +
            "        window.location.href = \"skladskoi-uchet\";\n" +
            "    });\n" +
            "</script>");
  }
    if(admin!=null){
        out.print("<nav class=\"navbar navbar-default navbar-static-top\">\n" +
                "    <div class=\"container\">\n" +
                "        <ul class=\"nav nav-pills\" role=\"tablist\">\n" +
                "            <li role=\"presentation\" class=\"active\"><a href=\"orders\">Заказы<span class=\"badge\">"+orderSize+"</span></a></li>\n" +
                "            <li role=\"presentation\"><a href=\"couriers\">Курьеры</a></li>\n" +
                "            <li role=\"presentation\"><a href=\"all_clients\">Клиенты </a></li>\n" +
                "            <li role=\"presentation\"><a href=\"departments\">Отделы </a></li>\n" +
                "            <li role=\"presentation\"><a href=\"#\">Заказ</a></li>\n" +
                "            <li role=\"presentation\"><a href=\"#\">Закуп</a></li>\n" +
                "            <li role=\"presentation\"><a href=\"balance\">Баланс</a></li>\n" +
                "        </ul>\n" +
                "    </div>\n" +
                "</nav>");
    }else if(courier!=null){
        out.print("<nav class=\"navbar navbar-default navbar-static-top\">\n" +
                "    <div class=\"container\">\n" +
                "        <ul class=\"nav nav-pills\" role=\"tablist\">\n" +
                "            <li role=\"presentation\" class=\"active\"><a href=\"#\">Заказы<span class=\"badge\">"+orderSize+"</span></a></li>\n" +
                "            <li role=\"presentation\"><a href=\"#\">Отделы </a></li>\n" +
                "            <li role=\"presentation\"><a href=\"#\">Склад<span class=\"badge\">3</span></a></li>\n" +
                "            <li role=\"presentation\"><a href=\"#\">Заказы</a></li>\n" +
                "        </ul>\n" +
                "    </div>\n" +
                "</nav>");
    }

%>




</body>
</html>
