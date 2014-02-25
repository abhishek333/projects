<%@ include file="../includes.jsp" %>
<div class="form-titl">
	 	<h3>Course Fees:</h3>	
	</div>
	<hr/>
<div class="well">
	<c:choose>
		<c:when test="${empty courseFees}">
			<div class="alert alert-warning span10">
			<button type="button" class="close" data-dismiss="alert">&times;</button>
			<h4>Warning :</h4>
			  		No course fees are defined..<br/>				  		
			</div>
		</c:when>
		<c:otherwise>
			<div>
				<table class="table table-stripped">
				<thead>
					<tr>
						<td>Id</td>
						<td>Course Name</td>
						<td>Course Fees</td>
					</tr>
				</thead>
				<c:forEach items="${courseFees}" var="cours">
					<tr>
							<td>${cours.id}</td>
							<td>${cours.courseName}</td>
							<td>${cours.fullFees}</td>							
					</tr>
				</c:forEach>
				</table>
			</div>	
		</c:otherwise>
	</c:choose>
</div>