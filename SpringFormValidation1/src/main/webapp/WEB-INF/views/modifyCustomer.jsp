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
			<strong><fmt:message key="info.customer"/></strong>
		</h3> 
	</div>
	<form:form modelAttribute="customerGET" class="form-horizontal" method="post"> 
		<table class="table table-bordered">
			<tr>
				<th><fmt:message key="user"/></th>
				<td><form:input type="text" class="form-control" path="user" placeholder="${customerGET.user}" /> 
					<form:errors path="user" element="span" cssClass="error"/></td>
			</tr>
			<tr>
				<th><fmt:message key="name"/></th>
				<td><form:input type="text" class="form-control" path="name" placeholder="${customerGET.name}" /> 
					<form:errors path="name" element="span" cssClass="error"/></td>
			</tr>
			<tr>
				<th><fmt:message key="age"/></th>
				<td><form:input type="text" class="form-control" path="age" placeholder="${customerGET.age}" /> 
					<form:errors path="age" element="span" cssClass="error"/></td>
			</tr>
			<tr>
				<th><fmt:message key="birthday"/></th>
				<td><fmt:formatDate value="${customerGET.birthday}"  
                type="date" 
                pattern="yyyy-MM-dd"
                var="FormattedDate" />
				<form:input type="date" class="form-control" path="birthday" value="${FormattedDate}" placeholder="${FormattedDate}"/> 
					<form:errors path="birthday" element="span" cssClass="error"/></td>
			</tr>
			<tr>
				<th><fmt:message key="email"/></th>
				<td><form:input type="text" class="form-control" path="email" placeholder="${customerGET.email}" /> 
					<form:errors path="email" element="span" cssClass="error"/></td>
			</tr>
            <tr>
                <th><fmt:message key="gender"/>:</th>
                <td><form:select class="form-control" path="gender">
                        <form:option value="${customerGET.gender}" label="Current: ${customerGET.gender}" />
                        <form:option value="MALE" label="Male" />
                        <form:option value="FEMALE" label="Female" />
                    </form:select></td>
                <td><form:errors path="gender" cssClass="error" /></td>
            </tr>
			<tr>
				<th><fmt:message key="phone"/></th>
				<td><form:input type="telephone" class="form-control" path="phone" placeholder="${customerGET.phone}" /> 
					<form:errors path="phone" element="span" cssClass="error"/></td>
			</tr>
		</table>
		<input type="submit" class="btn btn-primary" value="<fmt:message key="save.changes"/>" />
		</form:form>
</div>
</div>
</jsp:body>
</t:navbarE>