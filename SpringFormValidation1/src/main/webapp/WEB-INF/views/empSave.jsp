<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%-- <%@ page trimDirectiveWhitespaces="true" %> --%>
<html>
<head>
<title><fmt:message key="emp.save"/></title>
  <!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">

<!-- Jquery -->
<script src="<c:url value="/resources/jquery-1.11.3.min.js"/>"></script>

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<link rel="stylesheet" media="all" href="<c:url value="/resources/site.css"/>">
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
</div>
<form:form class="form-horizontal" method="POST" commandName="employee" action="save.do">
	<div class="col-md-6 col-md-offset-3">
		<div class="panel panel-default" id="wrapper">
			<div class="panel-body">  
				<div class="col-xs-8 col-xs-offset-2">  
					<div class="form-group">        
						<label class="control-label"><fmt:message key="employee"/> <fmt:message key="user"/>:</label>                  
						<form:input type="text" class="form-control" path="user" placeholder="Introduce your user" /> 
						<form:errors path="user" element="span" cssClass="error"/>     
					</div>    
				</div>
				<div class="col-xs-8 col-xs-offset-2">  
					<div class="form-group">        
						<label class="control-label"><fmt:message key="employee"/> <fmt:message key="password"/>:</label>                  
						<form:input type="password" class="form-control" path="password" placeholder="Introduce your password" /> 
						<form:errors path="password" element="span" cssClass="error"/>     
					</div>    
				</div>
				<div class="col-xs-8 col-xs-offset-2">  
					<div class="form-group">        
						<label class="control-label"><fmt:message key="employee"/> <fmt:message key="name"/>:</label>                  
						<form:input type="text" class="form-control" path="name" placeholder="Introduce your name" /> 
						<form:errors path="name" element="span" cssClass="error"/>     
					</div>    
				</div>
				<div class="col-xs-4 col-xs-offset-2">  
					<div class="form-group">        
						<label class="control-label"><fmt:message key="employee"/> <fmt:message key="role"/>:</label>                    
						<form:select class="form-control" path="role">                
							<form:option value="" label="Select Role" />  
							<form:option value="Developer" label="Developer" />
							<form:option value="Manager" label="Manager" />   
							<form:option value="CEO" label="CEO" />  
							<form:option value="Executive" label="Executive" />                                   
						</form:select> 
						<form:errors path="role" element="span" cssClass="error"/>       
					</div>
				</div>
				<p></p>
				<div class="col-md-6 col-md-offset-4"> 
					<input type="submit" class="btn btn-primary" value="<fmt:message key="save.employee"/>" />
				</div>
			</div>
		</div>  
	</div>
</form:form> 

</body>
</html>
