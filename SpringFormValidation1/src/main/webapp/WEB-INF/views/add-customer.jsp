<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%-- <%@ page trimDirectiveWhitespaces="true" %> --%>

<head>
<title><fmt:message key="cust.save"/></title>
</head>
<t:navbarE>
<jsp:body> 
<form:form class="form-horizontal" method="POST" commandName="customer">
	<div class="col-md-6 col-md-offset-3">
		<div class="panel panel-default" id="wrapper">
			<div class="panel-body">  
				<div class="col-xs-8 col-xs-offset-2">  
					<div class="form-group">        
						<label class="control-label"><fmt:message key="customer"/> <fmt:message key="user"/>:</label>                  
						<form:input type="text" class="form-control" path="user" placeholder="Introduce your customer's user" /> 
						<form:errors path="user" element="span" cssClass="error"/>     
					</div>    
				</div>
				<div class="col-xs-8 col-xs-offset-2">  
					<div class="form-group">        
						<label class="control-label"><fmt:message key="customer"/> <fmt:message key="name"/>:</label>                  
						<form:input type="text" class="form-control" path="name" placeholder="Introduce your customer's name" /> 
						<form:errors path="name" element="span" cssClass="error"/>     
					</div>    
				</div>
				<div class="col-xs-8 col-xs-offset-2"> 
					<div class="form-group">        
						<label class="control-label"><fmt:message key="customer"/> <fmt:message key="email"/>:</label>                  
						<form:input type="email" class="form-control" path="email" placeholder="Introduce your customer's email" /> 
						<form:errors path="email" element="span" cssClass="error"/>
					</div>    
				</div>
				<div class="col-xs-8 col-xs-offset-2">   
					<div class="form-group">        
						<label class="control-label"><fmt:message key="customer"/> <fmt:message key="age"/>:</label>                 
						<form:input class="form-control" path="age" placeholder="Introduce your customer's age" /> 
						<form:errors path="age" element="span" cssClass="error"/>
					</div>    
				</div> 
				<div class="col-xs-4 col-xs-offset-2">  
					<div class="form-group">        
						<label class="control-label"><fmt:message key="customer"/> <fmt:message key="gender"/>:</label>                    
						<form:select class="form-control" path="gender">                
							<form:option value="" label="Select Gender" />  
							<form:option value="MALE" label="Male" />
							<form:option value="FEMALE" label="Female" />                                       
						</form:select> 
						<form:errors path="gender" element="span" cssClass="error"/>       
					</div>
				</div>
				<div class="col-xs-8 col-xs-offset-2">   
					<div class="form-group">        
						<label class="control-label"><fmt:message key="customer"/> <fmt:message key="birthday"/>:</label>                 
						<form:input class="form-control" type="date" path="birthday" placeholder="MM/dd/yyyy" /> 
						<form:errors path="birthday" element="span" cssClass="error"/>
					</div>    
				</div>
				<div class="col-xs-8 col-xs-offset-2"> 
					<div class="form-group">        
						<label class="control-label"><fmt:message key="customer"/> <fmt:message key="phone"/>:</label>                 
						<form:input type="telephone" class="form-control" path="phone" placeholder="Introduce your customer's phone" /> 
						<form:errors path="phone" element="span" cssClass="error"/>
					</div>    
				</div>
				<p></p>
				<div class="col-md-6 col-md-offset-4"> 
					<input type="submit" class="btn btn-primary" value="<fmt:message key="save.customer"/>" />
				</div>
			</div>
		</div>  
	</div>
</form:form> 
</jsp:body>
</t:navbarE>