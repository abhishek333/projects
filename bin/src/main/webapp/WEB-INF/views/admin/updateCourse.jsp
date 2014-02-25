<%@ include file="../includes.jsp" %>

	<div class="form-titl">
	 	<h3>Update Course:</h3>	
	</div>
	<hr/>
   
<div>
<c:if test="${not empty succupd}">
	<div class="alert alert-success span12">
		<button type="button" class="close" data-dismiss="alert">&times;</button>
		<h4>Success :</h4>
		Successfully updated course of id:${succupd}<br/>				  		
	</div>
</c:if>
<c:if test="${not empty course}">
<c:url value="/admin/updateCourse" var="purl"/>
	<form:form method="POST" action="${purl}" class="form-horizontal" commandName="/admin/update" modelAttribute="course">
   			<form:hidden path="id"/>
   			<c:set var="er"><form:errors path="*"/></c:set>
   			<c:if test="${not empty er}">
		 			<div class="alert alert-error span10">
					<button type="button" class="close" data-dismiss="alert">&times;</button>
			  		<h4>Validation Error :</h4>
				  		Please fillup correct information!<br/>
				  		${er}
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
				<input type="submit" value="update course" class="btn btn-primary"/>
				</div>
			</div>
   		</form:form>
   </c:if>
</div>
<div>
	<c:choose>
		<c:when test="${empty allCourses}">
			No courses available
		</c:when>
		<c:otherwise>				
		<c:url value="/admin/updateCourse/" var="updUrl"/>
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
						<a href='${updUrl}${cours.id}'>Update</a>
					</td>
			</tr>
		</c:forEach>
		</table>
	</c:otherwise>
	</c:choose> 
</div>