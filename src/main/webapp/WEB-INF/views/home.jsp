<%@ include file="includes.jsp" %>
<c:url value="/assets/img/" var="imgUrl"/>
<div class="form-titl">
	<security:authorize access="isAuthenticated()" >
		<label id="userId" style="display: none;"><security:authentication property="principal.username"/></label>
	</security:authorize>
	 <div class="form-horzontal">
	 	<div class="control-group span6">
            <label class="control-label">Stud Id:</label>                	
               	<div class="controls">
                	<label class="text-info">${pr.studentId}</label>
				</div>
		</div>
	 	<div class="control-group">
            <label class="control-label">Admission Num:</label>                	
               	<div class="controls">
                	<label class="text-info">${pr.admissionNum}</label>
				</div>
		</div>
	 	<div class="control-group span6">
            <label class="control-label">Name:</label>                	
               	<div class="controls">
                	<label class="text-info">${pr.firstName} ${pr.lastName}</label>
				</div>
		</div>
	 	<div class="control-group">
            <label class="control-label">Course:</label>                	
               	<div class="controls">
                	<label class="text-info">${pr.courses.courseName}</label>
				</div>
		</div>
	 	<div class="control-group span6">
            <label class="control-label">Section:</label>                	
               	<div class="controls">
                	<label class="text-info">${pr.section}</label>
				</div>
		</div>
	 	<div class="control-group">
            <label class="control-label">Father Name:</label>                	
               	<div class="controls">
                	<label class="text-info">${pr.fatherName}</label>
				</div>
		</div>
	 </div>
</div>	
	<div class="tabbable" style="margin-bottom: 8px;"> <!-- Only required for left/right tabs -->
	  <ul class="nav nav-tabs" id="student-tab">
	      <li class="active"><a href="#detai">Details</a></li>
	      <li><a href="#fee">Fees</a></li>
		  <li><a href="#attendence" id="attend">Attendence</a></li>
		  <li><a href="#exam">Exam</a></li>
		  <li><a href="#complaints">Complaints</a></li>
	  </ul>
	  <div class="tab-content">
	     <div class="tab-pane active" id="detai">
	     	<h4>Your Details</h4>
	     	<div class="span4">
	     		<div class="control-group">
	     			<label class="control-label">User Id</label>
	     			<div class="controls">
	     				<span class="label">${pr.userId}</span>
	     			</div>
	     		</div>
	     		<div class="control-group">
	     			<label class="control-label">Gender</label>
	     			<div class="controls">
	     				<span class="label">${pr.gender}</span>
	     			</div>
	     		</div>
	     		<div class="control-group">
	     			<label class="control-label">Date of Birth</label>
	     			<div class="controls">
	     				<span class="label">${pr.dateOfBirth}</span>
	     			</div>
	     		</div>
	     		<div class="control-group">
	     			<label class="control-label">Email</label>
	     			<div class="controls">
	     				<span class="label">${pr.email}</span>
	     			</div>
	     		</div>
	     		<div class="control-group">
	     			<label class="control-label">Mobile:</label>
	     			<div class="controls">
	     				<span class="label">${pr.mobile}</span>
	     			</div>
	     		</div>
	     	</div>
	     	<div class="span4">
	     		<div class="control-group">
	     			<label class="control-label">City:</label>
	     			<div class="controls">
	     				<span class="label">${pr.city}</span>
	     			</div>
	     		</div>
	     		<div class="control-group">
	     			<label class="control-label">state</label>
	     			<div class="controls">
	     				<span class="label">${pr.state}</span>
	     			</div>
	     		</div>
	     		<div class="control-group">
	     			<label class="control-label">country:</label>
	     			<div class="controls">
	     				<span class="label">${pr.country}</span>
	     			</div>
	     		</div>
	     		
	     	</div>
	     </div><!-- end fee -->
	     <div class="tab-pane" id="fee">	     
	     	<h4>Your Fees Details!</h4>
	     	<div class="control-group">
		            <label class="control-label">Total Fees:</label>                	
		               	<div class="controls">
		                	<label class="text-info">${pr.studentCourseFees.fullFees}</label>
						</div>
				</div>
		  		<div class="control-group">
		            <label class="control-label">Total Paid:</label>                	
		               	<div class="controls">
		                	<label class="text-info">${pr.studentCourseFees.paidAmount}</label>
						</div>
				</div>
		  		<div class="control-group">
		            <label class="control-label">Balance:</label>                	
		               	<div class="controls">
		                	<label class="text-info">${pr.studentCourseFees.balanceAmount}</label>
						</div>
				</div>
	     </div><!-- end fee -->
		  <div class="tab-pane" id="attendence">
		  <h4>Your Attendence Details!</h4>		  	
		  	<div class="span4">
		  		<div class="control-group">
		            <label class="control-label">Total Days:</label>                	
		               	<div class="controls">
		                	<label class="text-info">${attTotalDay}</label>
						</div>
				</div>
		  		<div class="control-group">
		            <label class="control-label">Present Days:</label>                	
		               	<div class="controls">
		                	<label class="text-info">${totalPnt}</label>
						</div>
				</div>
		  		<div class="control-group">
		            <label class="control-label">Absent Days:</label>                	
		               	<div class="controls">
		                	<label class="text-info">${totalAbnt}</label>
						</div>
				</div>
		  	</div>
		  	<div class="span6">		  	
		  		<div id="ChartDiv" style="width:500px;height:300px; display: block; overflow: hidden;"></div>
		  	</div>
		  </div><!-- end attendence-->
		  <div class="tab-pane" id="exam">
		  	<h4>Exam</h4>
		  	<c:choose>
				<c:when test="${empty pr.examDetails}">
					No exam are you given..
				</c:when>
				<c:otherwise>		
				<table class="table table-stripped table-hover">
					<thead>
						<tr>
							<td>#</td>
							<td>Exam Name</td>
							<td>Exam Date</td>
							<td>Full Mark</td>
							<td>Obtain Mark</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${pr.examDetails}" var="exam">
							<tr>
									<td>${exam.examId}</td>
									<td>${exam.exam.examDate}</td>
									<td>${exam.exam.examName} (${exam.exam.semeter} sem ${exam.courses.courseName})</td>									
									<td>${exam.exam.fullMark}</td>
									<td>
										${exam.obtainMark}
									</td>
							</tr>
						</c:forEach>
				</tbody>
				</table>
			</c:otherwise>
			</c:choose>
		  </div><!--end exam -->
		  <div class="tab-pane" id="complaints">
		  	<h4>Your Complaints</h4>		  	
			<c:choose>
				<c:when test="${empty pr.complaints}">
					No complaints are registered..
				</c:when>
				<c:otherwise>		
				<table class="table table-stripped table-hover">
					<thead>
						<tr>
							<td>#</td>
							<td>Complaint By</td>
							<td>Complaint To</td>
							<td>Complaint Date</td>
							<td>Query</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${pr.complaints}" var="comp">
							<tr>
									<td>${comp.id}</td>
									<td>${comp.complaintBy}</td>
									<td>
									<c:if test="${not empty comp.complainTo}">${comp.complainTo}</c:if>
									<c:if test="${empty comp.complainTo}">	${comp.studentId}</c:if>
									</td>
									<td>${comp.complaintDate}</td>
									<td>
										${comp.complaintQuery}
									</td>
							</tr>
						</c:forEach>
				</tbody>
				</table>
			</c:otherwise>
			</c:choose>
		  </div><!-- end complaints-->
	  </div><!-- end tab-content -->
	</div><!-- end tabbable -->

<label id="attChartUrl" style="display: none;"><c:url value="/getAttendenceMap"/></label>