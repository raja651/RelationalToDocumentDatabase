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
<h1> Please select the table name that needs to be pushed to MongoDB</h1>
<%String dbName= request.getParameter("dbname");%>
<% String usrName= request.getParameter("usrname");%>
<% String passWord= request.getParameter("passwd");%>

<%SqltoMongo sql = new SqltoMongo(); %>
<% ArrayList<String> list = sql.readPostgre(dbName,usrName,passWord); %>
<form action="Conversion.jsp" method="post">
<select name="tables" >
<option value="">--- Please Select a Table ---</option>
<%
String tableName;
for(String listTable : list){
	tableName = listTable;

%>
<option value=<%=tableName %>><%=tableName %></option>
<%} %>
</select>

<%=(String)session.getAttribute("dbname")%>
<input type="submit" value="submit"></input>
</form>
</body>
</html>