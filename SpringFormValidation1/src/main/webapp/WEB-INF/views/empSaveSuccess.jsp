<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <title>Empleado guardado</title>
<style>
.h5{
	padding-top: 25px;
}
</style>

</head>
<body>
<div class="container">
  	<nav class="navbar navbar-default">
  		<div class="container-fluid">
    		<div class="span6" style="text-align:left" class="navbar-header">
      			<a class="navbar-brand" href="/spring/emp/save"><fmt:message key="reg.employee"/></a>
      		</div>
      		<div class="span6" style="text-align:right">
      				<h5><a class="btn btn-primary" href="/spring/"><fmt:message key="home"/></a></h5>
      		</div>
  		</div>
	</nav> 
<h3 align="center">
    <fmt:message key="emp.save.success"/>
</h3>
 
 <table class="table table-bordered table-hover table-condensed" id="tabla">
<tr>
	<th><fmt:message key="id"/></th>
	<th><fmt:message key="employee"/></th>
 	<th><fmt:message key="role"/>e</th>
</tr>
<c:forEach items="${employees}" var="employee">
<tr>
	<td>${employee.id}</td>
	<td>${employee.name}</td>
  	<td>${employee.role}</td>

</tr>
</c:forEach>
</table>
<a  href="/spring/emp/save"><input class="btn btn-primary" type="button" value="<fmt:message key="add.employee"/>"></a>
 </div>
</body>
</html>