<!DOCTYPE html> <!-- PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%-- <%@ page trimDirectiveWhitespaces="true" %> --%>


<t:navbarE>
<jsp:body>
<p></p>
<body>
<div class="panel panel-default" id="custwrapp">

  <div class="panel-heading">
  	<h5 class="page-right"><fmt:message key="page"/> <strong id="stronger">${start}</strong> <fmt:message key="from"/> ${pages} <fmt:message key="pages"/></h5>
  	<strong>${currentemployee.name}</strong><strong>: </strong>${currentemployee.role} 

  </div>
  	<form:form modelAttribute="ArrayID" name="form" action="delete" method="post">
  		<form:input type="hidden" path="customers" />
  	</form:form>
  	<form:form modelAttribute="ArrayID" name="formM" action="modify" method="get">
  		<form:input type="hidden" path="customer" />
  	</form:form>
	<table id="customerstable" class="table table-bordered table-hover table-condensed tablesorter">
	<thead>
	<tr>
		<td><input type="checkbox" id="SelectAll" aria-label="..." onclick="AllCheck()"></td>
		<th><fmt:message key="name"/></th>
	 	<th><fmt:message key="email"/></th>
	 	<th><fmt:message key="age"/></th>
	 	<th><fmt:message key="gender"/></th>
	 	<th><fmt:message key="birthday"/></th>
	 	<th><fmt:message key="phone"/></th>
	</tr>
	</thead>
	<tbody>
	<c:forEach items="${customerDB}" var="customerdb">
	<tr>
		<td><input type="checkbox" value="${customerdb.id}" name="checkbox" onclick="showblocked()"></td>
		<td>${customerdb.name}</td>
	  	<td>${customerdb.email}</td>
	  	<td>${customerdb.age}</td>
	  	<td>${customerdb.gender}</td>
	  	<td><fmt:formatDate value="${customerdb.birthday}" type="date" /></td>
	 	<td>${customerdb.phone}</td>
	</tr>
	</c:forEach>
	</tbody>
	</table>
	<div class="panel-footer">
		<div class="rows row">
			<div class="col-md-4">
  				<a  href="/spring/add-customer" class="addbut btn btn-primary glyphicon glyphicon-plus" role="button"><fmt:message key="add.customer"/></a>
  				<button class="trashbut btn btn-danger glyphicon glyphicon-trash" id="deleteCustomer" style="display:none"></button>
  				<button class="modifybut btn btn-info glyphicon glyphicon-user" id="modifyCustomer" style="display:none" onClick="check(2)"></button>
  				<!-- <a  href="/spring/modify" class="modifybut btn btn-info glyphicon glyphicon-user" role="button" onClick="check()" id="modifyCustomer" style="display:none"></a> -->
  			</div>
			<div class="col-md-4"></div>
			<!-- Paginador aquí -->
			<div class="col-md-4" align="right">
				<t:paginator></t:paginator>
			</div>
		</div>
	</div>
</div>
<t:modal></t:modal>
</body>
</jsp:body>
</t:navbarE>

