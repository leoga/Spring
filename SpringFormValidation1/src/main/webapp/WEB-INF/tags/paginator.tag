<%@tag description="Simple Wrapper Tag" pageEncoding="UTF-8"%> 
<%@attribute name="EmployeeName" fragment="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<nav>
  <ul class="pagination pagination-default">
    <li>
      <a href="?page=1" aria-label="Fisrt">
        <span aria-hidden="true">&laquo;</span>
      </a>
    </li>
    <c:choose>
    	<c:when test="${pages==1}">
    		<li class="active"><a href="?page=${start}">${start}</a></li>
    	</c:when>
    	<c:when test="${pages==2}">
    		<c:choose>
    			<c:when test="${start == 1}">
    				<li class="active"><a href="?page=${start}">${start}</a></li>
    				<li class="inactive"><a href="?page=${start+1}">${start+1}</a></li>
    			</c:when>
    			<c:otherwise>
    				<li class="inactive"><a href="?page=${start-1}">${start-1}</a></li>
    				<li class="active"><a href="?page=${start}">${start}</a></li>
    			</c:otherwise>
    		</c:choose>
    	</c:when>
    	<c:when test="${pages>=3}">
    		<c:choose>
    			<c:when test="${start == 1}">
    				<li class="active"><a href="?page=${start}">${start}</a></li>
   					<li class="inactive"><a href="?page=${start+1}">${start+1}</a></li>
    				<li class="inactive"><a href="?page=${start+2}">${start+2}</a></li>
    			</c:when>
    			<c:when test="${start == pages}">
    				<li class="inactive"><a href="?page=${start-2}">${start-2}</a></li>
    				<li class="inactive"><a href="?page=${start-1}">${start-1}</a></li>
    				<li class="active"><a href="?page=${start}">${start}</a></li>
    			</c:when>
    			<c:otherwise>
    				<li class="inactive"><a href="?page=${start-1}">${start-1}</a></li>
   					<li class="active"><a href="?page=${start}">${start}</a></li>
    				<li class="inactive"><a href="?page=${start+1}">${start+1}</a></li>
    			</c:otherwise>
    		</c:choose>
    	</c:when>
    </c:choose>
    <li>
      <a href="?page=${pages}" aria-label="Last">
        <span aria-hidden="true">&raquo;</span>
      </a>
    </li>
  </ul>
</nav>
