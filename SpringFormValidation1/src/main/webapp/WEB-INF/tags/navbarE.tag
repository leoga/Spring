<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
  <title><fmt:message key="access.employee"/></title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">

  <%-- <script type="text/javascript" src="<c:url value="/resources/jquery-latest.js"/>"></script> --%>
  <script type="text/javascript" src="<c:url value="/resources/jquery-1.11.3.min.js"/>"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
  <script type="text/javascript" src="<c:url value="/resources/jquery.tablesorter.min.js"/>"></script>
  <script type="text/javascript" src="<c:url value="/resources/functions.js"/>"></script>
  
  <link rel="stylesheet" href="<c:url value="/resources/blue/style.css"/>" type="text/css" media="print, projection, screen" />
  

  <link rel="stylesheet" media="all" href="<c:url value="/resources/site.css"/>"> 
</head>

<body>

<div class="container">
  	<nav class="navbar navbar-default">
  		<div class="container-fluid">
    		<div class="span6" style="text-align:left" class="navbar-header">
      			<a class="navbar-brand" href="/spring/cust/personalPage"><fmt:message key="access.employee"/></a>
      		</div>
		    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		      <ul class="nav nav-pills nav-justified">
		        <li class="Active"><a href="/spring/emp/personalPage"><fmt:message key="home"/> <span class="sr-only">(current)</span></a></li>
		        <li class="Active"><a href="<c:url value="/add-customer"/>"><fmt:message key="add.customer"/></a></li>
		        <li class="Active"><a href="<c:url value="/mycustomers?page=1"/>"><fmt:message key="list"/></a></li>
		        <li class="Active"><a href="<c:url value="/search?page=1"/>"><fmt:message key="search"/></a></li>
		        <li class="Active"><fmt:message key="add.employee"/></li>
		        <li><a href="<c:url value="/"/>"><fmt:message key="shutDown"/></a></li>
		      </ul>
		    </div>
  		</div>
	</nav>
</div>
<jsp:doBody/>
</body>
</html>
