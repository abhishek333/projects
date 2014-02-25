<%@ include file="../includes.jsp" %>
<div class="form-titl">
	 	<h3>Add Course Fees Structure:</h3>	
	</div>
	<hr/>
<div class="well" style="display: block; overflow: hidden;">
	<form:form method="POST" class="form-horizontal" commandName="/admin/addCourseFees" modelAttribute="courseFeeModel">
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
                		<form:select path="courseId" class="input-medium">
                        	<form:option value="0" label="--- Course ---" />
							<form:options items="${courses}" itemLabel="courseName" itemValue="id"/>
                        </form:select>
					</div>
			</div>
			<div class="control-group">
                <label class="control-label">Course Fees</label>                	
                	<div class="controls">
                		<div class="input-append">
	                		<form:input path="fullFees" class="input-medium"/>
	                		<span class="add-on"><i><img height="18" width="18" src='<c:url value="/assets/img/currency_sign_rupee_pencil.png"/>'/></i></span>
	                	</div>
					</div>
			</div>
			<div class="control-group">
				<div class="controls">
				<input type="submit" value="save course fees" class="btn btn-primary"/>
				</div>
			</div>
   		</form:form>

	<c:choose>
		<c:when test="${not empty courseFees}">
			<div>
				<table class="table table-stripped table-hover">
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
							<td>
							<i><img height="18" width="18" src='<c:url value="/assets/img/currency_sign_rupee_pencil.png"/>'/></i>
							${cours.fullFees}							
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
				  		No course fees are defined..<br/>				  		
			</div>				
		</c:otherwise>
	</c:choose>
</div>