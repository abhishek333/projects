<%@ include file="../includes.jsp" %>

<div class="form-titl">
	 	<h3>Studnt Fees Payment:</h3>	
	</div>
	<hr/>
<div class="well" style="display: block; overflow: hidden;">
	<c:choose>
		<c:when test="${not empty listStudents}">
			<div>
				<table class="table table-stripped table-hover">
				<thead>
					<tr>
						<td>Student Id</td>
						<td>Student Name</td>
						<td>Course</td>
						<td>Fees</td>						
						<td>Paid</td>
						<td>Balance</td>
					</tr>
				</thead>
				<c:forEach items="${listStudents}" var="std">
					<tr>
							<td>${std.id}</td>
							<td>${std.firstName} ${std.lastName}</td>
							<td>${std.courseId}</td>							
							<td>				
								<c:choose><c:when test="${not empty std.studentCourseFees}">${std.studentCourseFees.fullFees}</c:when>
								<c:otherwise>n</c:otherwise></c:choose>
							</td>							
							<td>
								<c:choose><c:when test="${not empty std.studentCourseFees}">
									<span class="label label-success">${std.studentCourseFees.paidAmount}</span></c:when>
								<c:otherwise>n</c:otherwise></c:choose>
							</td>
							<td>							
								<c:choose><c:when test="${not empty std.studentCourseFees}">								
									<span class="label label-important">${std.studentCourseFees.balanceAmount}</span></c:when>
								<c:otherwise>n</c:otherwise></c:choose>
								<a href='<c:url value="/admin/stdCourseFeesForm/${std.id}/${std.courseId}"/>'>Add Fees</a>
							</td>						
					</tr>
				</c:forEach>
				</table>
			</div>

		</c:when>
		<c:otherwise>
			<div class="alert alert-warning span10">
				<button type="button" class="close" data-dismiss="alert">&times;</button>
				<h4>Warning :</h4>
				  		Oops, No Student fees payment are there..<br/>				  		
			</div>				
		</c:otherwise>
	</c:choose>
</div>