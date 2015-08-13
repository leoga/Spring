<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
<%@ page session="false" import="com.journaldev.spring.form.controllers.CustomerController"
						 import="com.journaldev.spring.form.model.Customer"
						 import="java.util.List"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<html>
<head>

<t:navbar>
<jsp:body>

 <div class="container">
<!--  <nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="span6" style="text-align:left" class="navbar-header">
      <a class="navbar-brand" href="/spring/cust/save.do">Registro Consumidores</a>
    </div>
    <div class="span6" style="text-align:right">
      <h5><a class="btn btn-primary" href="/spring/">Home</a></h5>
    </div>
  </div>
  </nav> -->

<h3 align="center">
    <fmt:message key="cust.save.success"/>
</h3>
<table class="table table-bordered">
<tr>
	<th><fmt:message key="name"/></th>
 	<th><fmt:message key="email"/></th>
 	<th><fmt:message key="age"/></th>
 	<th><fmt:message key="gender"/></th>
 	<th><fmt:message key="birthday"/></th>
 	<th><fmt:message key="phoner"/></th>
</tr>
<c:forEach items="${customerDB}" var="customerdb">
<tr>
	<td>${customerdb.name}</td>
  	<td>${customerdb.email}</td>
  	<td>${customerdb.age}</td>
  	<td>${customerdb.gender}</td>
  	<td><fmt:formatDate value="${customerdb.birthday}" type="date" /></td>
 	<td>${customerdb.phone}</td>
</tr>
</c:forEach>
</table>

<a  href="/spring/cust/save"><input class="btn btn-primary" type="button" value="<fmt:message key="add.customer"/>"></a>

</div>
</jsp:body>
</t:navbar>