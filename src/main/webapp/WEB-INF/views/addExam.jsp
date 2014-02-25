<%@ include file="includes.jsp" %>
<div class="well" style="display: block; overflow: hidden;">
	<div class="form-titl">
	 	<h3>Add Exam:</h3>	
	</div>
	<hr/>
<div>   
   	<div>
   		<form:form method="POST" class="form-horizontal" modelAttribute="examModel">
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
				  		Exam added with id: ${succ}<br/>				  		
					</div>
		 	</c:if> 
			<div class="control-group">
                <label class="control-label">Exam Name</label>                	
                	<div class="controls">
                		<form:input path="examName" placeholder="enter exam name"/>
					</div>
			</div>
			<div class="control-group">
                <label class="control-label">Exam Date</label>                	
                	<div class="controls input-append date" style="margin-left: 16px;" id="dp3" data-date="01-01-2014" data-date-format="dd-mm-yyyy">
						<form:input path="examDate" class="input-medium" placeholder="dd-MM-yyyy"/>
						<span class="add-on"><i class="icon-calendar"></i></span>
                    </div>
                </div>
			<div class="control-group">
                <label class="control-label">Course Name</label>                	
                	<div class="controls">
                		<form:select path="courseId" class="input-medium">
                        	<form:option value="0" label="--- Course ---" />
							<form:options items="${courses}" itemLabel="courseName" itemValue="id"/>
                        </form:select>
					</div>
			</div>
			<div class="control-group">
                <label class="control-label">Exam Semester</label>                	
                	<div class="controls">
                		<form:select path="semeter" class="input-medium">
                        	<form:option value="0" label="--- Course ---" />
							<form:options items="${semesters}"/>
                        </form:select>
					</div>
			</div>
			<div class="control-group">
                <label class="control-label">Full Mark</label>                	
                	<div class="controls">
                		<form:input path="fullMark" placeholder="enter full mark"/>
					</div>
			</div>
			<div class="control-group">
				<div class="controls">
				<input type="submit" value="save exam" class="btn btn-primary"/>
				</div>
			</div>
   		</form:form>
	</div>
</div>
</div>
<div>
	<c:choose>
		<c:when test="${empty exams}">
			No exams available
		</c:when>
		<c:otherwise>		
		<c:url value="/admin/exam/deleteExam/" var="delUrl"/>
		<table class="table table-stripped table-hover">
			<thead>
				<tr>
					<td>Id</td>
					<td>Exam Name</td>
					<td>Exam Date</td>
					<td>Semester</td>
					<td>Full Mark</td>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${exams}" var="exm">
				<tr>
						<td>${exm.id}</td>
						<td>${exm.examName}</td>
						<td>${exm.examDate}</td>
						<td>${exm.semeter}</td>
						<td>${exm.fullMark}</td>
						<td>							
							<a href='${delUrl}${exm.id}'>Delete</a>
						</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</c:otherwise>
	</c:choose>
</div>