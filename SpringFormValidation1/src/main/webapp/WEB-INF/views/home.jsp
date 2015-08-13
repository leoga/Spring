<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="springForm" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <title><fmt:message key="home"/> <fmt:message key="page"/></title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
  <link rel="stylesheet" media="all" href="<c:url value="/resources/site.css"/>"> 

</head>
<body>
  
<div class="container">
  	<nav class="navbar navbar-default">
  		<div class="container-fluid">
    		<div class="span6" style="text-align:left" class="navbar-header">
      			<a class="navbar-brand" href="/spring/"><fmt:message key="register"/></a>
      		</div>
  		</div>
	</nav> 
<h1 align="center">

	<fmt:message key="indra.register"/>
	
</h1>

<h3 align="center">
	<fmt:message key="access"/>

</h3>
<table align="center"   width="50%">
	<tr>
		<td align="left"><a  href="loginCust"><input class="btn btn-primary" type="button" value="<fmt:message key="customer"/>"></a></td>
		<td align="right"><a  href="loginEmp"><input class="btn btn-primary" type="button" value="<fmt:message key="employee"/>"></a></td>
	</tr>
</table>
</div>
</body>
</html>
