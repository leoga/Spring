<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%-- <%@ page trimDirectiveWhitespaces="true" %> --%>
 

<t:navbarE>
<jsp:body> 
<div class="col-md-6 col-md-offset-3">
<div class="panel panel-default" id="wrapper">
	<!-- Default panel contents -->
	<div class="panel-heading">
		<h3 class="panel-tittle">
			<span class="glyphicon glyphicon-user"></span>
			<strong><fmt:message key="info"/></strong>
		</h3> 
	</div>
	<form:form modelAttribute="employeeGET" class="form-horizontal" method="post"> 
		<table class="table table-bordered">
			<tr>
                <th><fmt:message key="employee"/> <fmt:message key="user"/>:</th>
                <td><form:input type="text" class="form-control" path="user" placeholder="${employeeGET.user}"/></td>
                <td><form:errors path="user" cssClass="error" /></td>
            </tr>
            <tr>
                <th><fmt:message key="employee"/> <fmt:message key="name"/>:</th>
                <td><form:input type="text" class="form-control" path="name" placeholder="${employeeGET.name}"/></td>
                <td><form:errors path="name" cssClass="error" /></td>
            </tr>
            <tr>
                <th><fmt:message key="employee"/> <fmt:message key="role"/>:</th>
                <td><form:select class="form-control" path="role">
                        <form:option value="${employeeGET.role}" label="Current: ${employeeGET.role}" />
                        <form:option value="ceo" label="CEO" />
                        <form:option value="developer" label="Developer" />
                        <form:option value="manager" label="Manager" />
                    </form:select></td>
                <td><form:errors path="role" cssClass="error" /></td>
            </tr>
		</table>
		<input type="submit" class="btn btn-primary" value="<fmt:message key="save.changes"/>" />
		</form:form>
</div>
</div>
</jsp:body>
</t:navbarE>