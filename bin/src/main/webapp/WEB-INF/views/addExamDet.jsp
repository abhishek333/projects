<%@ include file="includes.jsp" %>
<div class="form-titl">
	<h3>Add Student Exam Details.</h3>
</div>
<div class="well" style="display: block; overflow: hidden;">
<c:url value="/admin/exam/addExamDetails" var="pUrl"/>
	<form:form method="POST" action="${pUrl}" class="form-horizontal" commandName="/admin/exam/addExamDetails" modelAttribute="examDetModel">
	
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
                <label class="control-label">Exam Name</label>                	
                	<div class="controls">
                		<form:select path="examId" class="input-medium">
                        	<form:option value="0" label="--- exam ---" />
							<form:options items="${exams}" itemLabel="examName" itemValue="id"/>
                        </form:select>
                        <span class="label" id="eDet"></span>
					</div>
			</div>
			<div class="control-group">
                <label class="control-label">Course Name:</label>                	
                	<div class="controls">
                		<span id="crsNm" class="label">select exam to get course</span>
                	</div>
            </div>
			<div class="control-group">
                <label class="control-label">Student Id</label>                	
                	<div class="controls">
                		 <form:select path="studentId">
                        	<form:option value="0" label="--- student ---" />							
                        </form:select>               		
					</div>
			</div>
			
			<div class="control-group">
                <label class="control-label">Full Mark</label>                	
                	<div class="controls">
                		<span id="fullMrk" class="label"></span>
					</div>
			</div>
			<div class="control-group">
                <label class="control-label">Obtain Mark</label>                	
                	<div class="controls">
                		<form:input path="obtainMark" placeholder="Write obtain mark."/>
					</div>
			</div>
			<div class="control-group">
				<div class="controls">
				<input type="submit" value="Add Exam Details" class="btn btn-primary"/>
				</div>
			</div>
   		</form:form>
</div>
<label id="examUrl" style="display: none;"><c:url value="/admin/exam/getExam"/></label>
<div style="display: block; overflow: hidden;">
		<c:choose>
		<c:when test="${empty allExams}">
			No exams available
		</c:when>
		<c:otherwise>
		<table class="table table-stripped table-hover">
			<thead>
				<tr>
					<td>Exam Date</td>
					<td>Exam Name</td>					
					<td>Student Name</td>
					<td>Full Mark</td>
					<td>Obtain Mark</td>
				</tr>
			</thead>		
			<tbody>
		<c:forEach items="${allExams}" var="exm">
		<tr>
			<td>
				<label>${exm.exam.examDate}</label>
			</td>				
			<td id="${exm.id}">
				<label>${exm.exam.examName}</label>
				<label>${exm.exam.semeter}</label>
			</td>				
			<td>
			         <img height="50" width="50" id="pfPhoto" class="pull-left img-polaroid" alt="Polaroid Image"
			                	 src='<c:url value="/profilePhoto/${exm.personalDetails.imageName}"/>'/>
				<label class="text-info">${exm.personalDetails.firstName} ${exm.personalDetails.lastName}</label>				
							(${exm.personalDetails.studentId})
			</td>
			<td>
				<label>${exm.exam.fullMark}</label>
			</td>
			<td>
				<label>${exm.obtainMark}</label>
			</td>
			</c:forEach>
			</tbody>
		</table>
	</c:otherwise>
	</c:choose>
</div>