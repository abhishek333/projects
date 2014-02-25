<%@ include file="../includes.jsp" %>
<div class="form-titl">
	 	<h3>Delete Course:</h3>	
	</div>
	<hr/>
<div>
		<c:if test="${not empty succdel}">
	 			<div class="alert alert-success span10">
				<button type="button" class="close" data-dismiss="alert">&times;</button>
		  		<h4>Success :</h4>
			  		Successfully deleted course..<br/>				  		
				</div>
	 	</c:if> 
<c:choose>
		<c:when test="${empty allCourses}">
			No courses available
		</c:when>
		<c:otherwise>				
		<c:url value="/admin/deleteCourse/" var="delUrl"/>
		<table class="table table-stripped">
			<thead>
				<tr>
					<td>Id</td>
					<td>Course Name</td>
				</tr>
			</thead>
		<c:forEach items="${allCourses}" var="cours">
			<tr>
					<td>${cours.id}</td>
					<td>${cours.courseName}</td>
					<td>
						<a href='${delUrl}${cours.id}'>Delete</a>
					</td>
			</tr>
		</c:forEach>
		</table>
	</c:otherwise>
	</c:choose> 
</div>