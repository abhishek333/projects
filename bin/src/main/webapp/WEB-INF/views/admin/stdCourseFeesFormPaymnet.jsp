<%@ include file="../includes.jsp" %>
<div class="form-titl">
	 	<h3>Add Course Fees Structure:</h3>	
	</div>
	<hr/>
	<c:if test="${not empty err}">
		 <div class="alert alert-error span10">
			<button type="button" class="close" data-dismiss="alert">&times;</button>
			<h4>Error :</h4>
				  ${err}<br/>				  		
		</div>
	</c:if>
		
<div class="well" style="display: block; overflow: hidden;">
<c:url value="/admin/stdCourseFeesForm" var="purl"/>
	<form:form method="POST" action='${purl}' class="form-horizontal" modelAttribute="stdFeesForm">
		<form:hidden path="fullFees"/>
		<form:hidden path="studentId"/>
		<form:hidden path="courseId"/>
		<form:hidden path="paidAmount"/>
		<form:hidden path="balanceAmount"/>
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
                <label class="control-label">Student Id:</label>                	
                	<div class="controls">
                		<span class="label" style="font-size: x-large; padding: 8px;">${stdFeesForm.studentId}</span>
					</div>
			</div>
			<div class="control-group">
                <label class="control-label">Full Name:</label>                	
                	<div class="controls">
                		<span class="label" style="font-size: x-large; padding: 8px;">${stdFeesForm.pers.firstName} ${stdFeesForm.pers.lastName}</span>
					</div>
			</div>
			<div class="control-group">
                <label class="control-label">Course Name:</label>                	
                	<div class="controls">
                		<span class="label" style="font-size: x-large; padding: 8px;">${stdFeesForm.courses.courseName}</span>
					</div>
			</div>
			<div class="control-group">
                <label class="control-label">Course Fees</label>                	
                	<div class="controls">
                		<div class="input-prepend">
                		<span class="add-on"><i><img height="14" width="14" src='<c:url value="/assets/img/currency_sign_rupee_pencil.png"/>'/></i></span>
	                		<span class="label" style="font-size: x-large; padding: 8px;">${stdFeesForm.fullFees}</span>	                		
	                	</div>	                	
					</div>
			</div>
			<div class="control-group">
                <label class="control-label">Balance Fees</label>                	
                	<div class="controls">
                		<div class="input-prepend">
	                		<span class="add-on"><i><img height="14" width="14" src='<c:url value="/assets/img/currency_sign_rupee_pencil.png"/>'/></i></span>
	                		<span class="label" style="font-size: x-large; padding: 8px;">${stdFeesForm.balanceAmount}</span>	                		
	                	</div>
					</div>
			</div>
			<div class="control-group">
                <label class="control-label">Paying Amount: </label>                	
                	<div class="controls">
                		<div class="input-append">
	                		<form:input path="payingAmount" placeholder="enter amount" class="input-medium"/>
	                		<span class="add-on"><i>.00</i></span>
	                	</div>
					</div>
			</div>
			<div class="control-group">
				<div class="controls">
				<input type="submit" value="pay fees" class="btn btn-primary"/>
				<input type="button" value="back" class="btn btn-danger"/>
				</div>
			</div>
   		</form:form>
</div>