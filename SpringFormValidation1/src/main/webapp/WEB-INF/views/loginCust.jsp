<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="springForm" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <title><fmt:message key="access.customer"/></title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
  <link rel="stylesheet" media="all" href="<c:url value="/resources/site.css"/>"> 
</head>
<body>

<springForm:form class="form-horizontal" method="post" commandName="customerLogin"  action="/spring/loginCust.do">
	<div class="container" align="center">
	    <div class="row">
	        <div class="col-xs-4 col-xs-offset-4">
	            <div id="logbox" class="panel-primary">
	            <div class="panel-heading" align="center"><h4><strong><fmt:message key="access.customer"/></strong></h4></div>
	            <p></p>
		                <table align="center">
		                	<tr>
		                		<td><span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
		                		<springForm:input class="form-control" path="user" placeholder="User" />
		                	</tr>
		                	<tr>
		                		<td><span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
		                		<springForm:input type="password" path="password" class="form-control" placeholder="Password"/>
		                	</tr>
		                	<tr>
	                			<td><p></p></td>
	                			<td><p></p></td>
							</tr>
		                	<tr>
		                		<td align="center"><button class="btn btn-primary" type="submit">Sign in</button></td>
		                		<!-- <td aling="right"><label class="checkbox pull-left"><input align="right" type="checkbox" value="remember-me">Remember me</label></td> -->
		                	</tr>
	                		<tr>
	                			<td><p></p></td>
	                			<td><p></p></td>
							</tr>
		                	<tr>
	                			<td align="center"><a href="/spring/cust/save" class="label label-primary"><strong><fmt:message key="create.account"/></strong> </a></td>
	                		</tr>
	                		<tr>
	                			<td><p></p></td>
	                			<td><p></p></td>
							</tr>
	                	</table>
	                
	            </div>
	            
	        </div>
	    </div>
	</div> 
</springForm:form>
	


</body>
</html>
