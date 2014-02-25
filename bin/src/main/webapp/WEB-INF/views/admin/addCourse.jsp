<%@ include file="../includes.jsp" %>
<div class="well" style="display: block; overflow: hidden;">
	<div class="form-titl">
	 	<h3>Add Course:</h3>	
	</div>
	<hr/>
<div>   
   	<div>
   		<form:form method="POST" class="form-horizontal" commandName="/admin/addcourse" modelAttribute="course">
   			<c:set var="er"><form:errors path="*"/></c:set>
   			<c:if test="${not empty er}">
		 			<div class="alert alert-error span10">
					<button type="button" class="close" data-dismiss="alert">&times;</button>
			  		<h4>Validation Error :</h4>
				  		Please fillup correct information!<br/>
				  		${er}
					</div>
		 	</c:if> 
   			<c:if test="${not empty succ}">
		 			<div class="alert alert-success span10">
					<button type="button" class="close" data-dismiss="alert">&times;</button>
			  		<h4>Success :</h4>
				  		Course name added with id: ${succ}<br/>				  		
					</div>
		 	</c:if> 
			<div class="control-group">
                <label class="control-label">Course Name</label>                	
                	<div class="controls">
                		<form:input path="courseName" placeholder="enter course name"/>
					</div>
			</div>
			<div class="control-group">
				<div class="controls">
				<input type="submit" value="save course" class="btn btn-primary"/>
				</div>
			</div>
   		</form:form>
	</div>
</div>
</div>
<div>
	<c:choose>
		<c:when test="${empty allCourses}">
			No courses available
		</c:when>
		<c:otherwise>		
		<c:url value="/admin/updateCourse/" var="updUrl"/>
		<c:url value="/admin/deleteCourse/" var="delUrl"/>
		<table class="table table-stripped table-hover">
			<thead>
				<tr>
					<td>Id</td>
					<td>Course Name</td>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${allCourses}" var="cours">
				<tr>
						<td>${cours.id}</td>
						<td>${cours.courseName}</td>
						<td>
							<a href='${updUrl}${cours.id}'>Edit</a>
							<a href='${delUrl}${cours.id}'>Delete</a>
						</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</c:otherwise>
	</c:choose>
</div>