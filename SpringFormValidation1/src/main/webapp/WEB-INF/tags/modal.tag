<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- Modal HTML -->

<div id="myModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title"><fmt:message key="delete.conf"/></h4>
            </div>
            <div class="modal-body">
                <p><fmt:message key="delete"/> <span id="modalCustomersNumber"></span> <fmt:message key="customers"/>?</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal"><fmt:message key="close"/></button>
                <button type="button" class="btn btn-danger" onClick="check(1)"><fmt:message key="delete"/> <fmt:message key="customers"/></button>
            </div>
        </div>
    </div>
</div>