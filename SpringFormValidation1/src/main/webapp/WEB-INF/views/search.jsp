<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%-- <%@ page trimDirectiveWhitespaces="true" %> --%>
 

<t:navbarE>
<jsp:body>
<body>
<p></p>
<form:form modelAttribute="Searchfields" name="form" action="deletes" method="post">
  	<form:input type="hidden" path="customers" />
</form:form>
<form:form modelAttribute="Searchfields" name="formM" action="modifys" method="get">
  	<form:input type="hidden" path="customer" />
</form:form>
<form:form modelAttribute="Searchfields" class="form-horizontal" method="post"> 
<div class="panel panel-default" id="custwrapp">
	<div class="panel heading" id="wrapp">
		<h5><fmt:message key="hello"/> <strong>${currentemployee.name}</strong> <s></s> <fmt:message key="set.filters"/></h5>
	</div>
	<div class="form-inline" role="form">
	<div class="form-group" id="wrapp">
    		<input type="checkbox" name="checkname" aria-label="..." onClick="byname.disabled = !this.checked" >
    	<label class="control-label"><fmt:message key="search.name"/>:</label> 
    	<p></p>                
		<form:input name="byname" type="text" class="formlarge form-control" path="byname" placeholder="Introduce your customer's name" disabled="true"/> 
    </div>
    <div class="form-group" id="wrapp">
    		<input type="checkbox" name="checkage" aria-label="..." onClick="byagehigh.disabled = !this.checked ; byagelow.disabled = !this.checked">
    	<label class="control-label"><fmt:message key="search.age"/>:</label>    
    	<p></p>         
		<form:input name="byagelow" type="number" class="form-size form-control" path="byagelow" placeholder="Introduce your customer's age" disabled="true"/>
    	<form:input name="byagehigh" type="number" class="form-size form-control" path="byagehigh" placeholder="Introduce your customer's age" disabled="true"/>
    </div>
    <div class="form-group" id="wrapp">
    	<input type="checkbox" name="checkdate" aria-label="..." onClick="bydatehigh.disabled = !this.checked ; bydatelow.disabled = !this.checked">
    	<label class="control-label"><fmt:message key="search.date"/>:</label>    
    	<p></p> <fmt:formatDate value="${timehigh}"  
         type="date" 
         pattern="yyyy-MM-dd"
         var="timehigh" />
         <fmt:formatDate value="${timelow}"  
         type="date" 
         pattern="yyyy-MM-dd"
         var="timelow" />   
             
		<form:input name="bydatelow" type="date" class="form-size form-control" path="bydatelow"  disabled = "true" value="${timelow}"/>
    	<form:input name="bydatehigh" type="date" class="form-size form-control" path="bydatehigh"  disabled = "true" value="${timehigh}"/>
    </div>
    </div>
    <input type="submit" class="btn btn-primary" value="<fmt:message key="search"/>" id="searchbut"/>

</div>
</form:form>

<div class="panel panel-default" id="custwrapp">
	<!-- Default panel contents -->
	<div class="panel-heading">
		<h5 class="page-right"><fmt:message key="page"/> <strong id="stronger">${start}</strong> <fmt:message key="from"/> ${pages} <fmt:message key="pages"/></h5>
		<a href="search?page=1" class="btn btn-default glyphicon glyphicon-refresh" id="refreshbut"></a> ${currentemployee.role}
	</div>
	<!-- Table -->
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
	<c:forEach items="${listing}" var="customer">
	<tr>
		<td><input type="checkbox" value="${customer.id}" name="checkbox" onclick="showblocked()"></td>
		<td>${customer.name}</td>
		<td>${customer.email}</td>
		<td>${customer.age}</td>
		<td>${customer.gender}</td>
		<td><fmt:formatDate value="${customer.birthday}" type="date" /></td>
		<td>${customer.phone}</td>
	</tr>
	</c:forEach>
	</tbody>
	</table>
	<div class="panel-footer">
		<div class="rows row">
			<div class="col-md-4">
  				<button class="trashbut btn btn-danger glyphicon glyphicon-trash" id="deleteCustomer" style="display:none"></button>
  				<button class="modifybut btn btn-info glyphicon glyphicon-user" id="modifyCustomer" style="display:none" onClick="check(2)"></button>
  			</div>
			<div class="col-md-4"></div>
			<!-- Paginador aquí -->
			<div class="col-md-4" align="right">
				<t:paginator></t:paginator>
			</div>
		</div>
	</div>
	<h5 id="wrapp"><fmt:message key="showing"/> <strong>${fn:length(listing)}</strong> <fmt:message key="from"/> ${ncustomers} <fmt:message key="registers"/></h5>
</div>
<t:modal></t:modal>
</body>
</jsp:body>
</t:navbarE>