<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
  <title><fmt:message key="access.customer"/></title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.1/themes/base/jquery-ui.css" />
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://code.jquery.com/ui/1.10.1/jquery-ui.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
  <link rel="stylesheet" media="all" href="<c:url value="/resources/site.css"/>"> 
  
</head>
<body>
<div class="container">
  	<nav class="navbar navbar-default">
  		<div class="container-fluid">
    		<div class="span6" style="text-align:left" class="navbar-header">
      			<a class="navbar-brand"><fmt:message key="access.customer"/></a>
      		</div>
		    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		      <ul class="nav nav-pills nav-justified">
		        <li class="Active"><a href="/spring/cust/personalPage"><fmt:message key="home"/> <span class="sr-only">(current)</span></a></li>
		        <%-- <li class="Active"><a href="<c:url value="search?page=1"/>"><fmt:message key="search"/></a></li> --%>
		        <li><a href="<c:url value="/"/>"><fmt:message key="shutDown"/></a></li>
		      </ul>
		    </div>
  		</div>
	</nav>
</div>
<jsp:doBody/>
</body>
</html>