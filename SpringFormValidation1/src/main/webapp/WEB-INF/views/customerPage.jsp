<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page trimDirectiveWhitespaces="true" %>
 

<t:navbar>
<jsp:body>
<div class="col-md-6 col-md-offset-3">
<div class="panel panel-default" id="wrapper">
	<!-- Default panel contents -->
	<div class="panel-heading">
		<h3 class="panel-tittle">
			<span class="glyphicon glyphicon-user"></span>
			<strong><fmt:message key="info"/></strong>
			<a href="<c:url value="/cust/edit"/>"  class="panel-right btn bnt-default glyphicon glyphicon-pencil"></a>
		</h3> 
	</div>
		<table class="table table-bordered">
			<tr>
				<th><fmt:message key="user"/></th>
				<td>${customerDB.user}</td>
			</tr>
			<tr>
				<th><fmt:message key="name"/></th>
				<td>${customerDB.name}</td>
			</tr>
			<tr>
				<th><fmt:message key="age"/></th>
				<td>${customerDB.age}</td>
			</tr>
			<tr>
				<th><fmt:message key="birthday"/></th>
				<td><fmt:formatDate value="${customerDB.birthday}" type="date" /></td>
			</tr>
			<tr>
				<th><fmt:message key="email"/></th>
				<td>${customerDB.email}</td>
			</tr>
			<tr>
				<th><fmt:message key="gender"/></th>
				<td>${customerDB.gender}</td>
			</tr>

			<tr>
				<th><fmt:message key="phone"/></th>
				<td>${customerDB.phone}</td>
			</tr>

		</table>
</div>
</div>
</jsp:body>
</t:navbar>