<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="SqlToMongo.SqltoMongo" %>
    <%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1> Welcome to Citizens Bank</h1>
<h2>Give the following information for connecting a relational db</h2>
<form action="Main.jsp" method="post">
 <label>Enter your Database Name: </label>
<input name="dbname" type="text"></input><br>
<label>Enter your user name of Database</label>
<input name="usrname" type="text"></input><br>
<label>Enter your Password of Database</label>
<input name="passwd" type="password"></input><br>
<input type="submit" value="submit"></input>
<%session.setAttribute("dbName",request.getParameter("dbname"));%>
<%session.setAttribute("usrName",request.getParameter("usrname"));
session.setAttribute("passWord",request.getParameter("passwd"));%>
</form>
</body>
</html>