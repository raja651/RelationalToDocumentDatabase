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
<%SqltoMongo sql = new SqltoMongo(); %>
<% ArrayList<String> list = sql.readPostgre(); %>
<%=list %>
</body>
</html>