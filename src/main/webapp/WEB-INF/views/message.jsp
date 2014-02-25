<%@ include file="includes.jsp" %>
<div class="well" style="margin-top: 150px;">
<c:if test="${not empty msg}">
	<div class="alert alert-success">
		<button type="button" class="close" data-dismiss="alert">&times;</button>
  		<h4>Success!</h4>
  		${msg}
	</div>
</c:if>
</div>