<%@ include file="includes.jsp" %>
<div class="form-titl">
	<h3>Register a complaint.</h3>
</div>
<div class="well" style="display: block; overflow: hidden;">
<c:url value="/user/complaints" var="pUrl"/>
	<form:form method="POST" action="${pUrl}" class="form-horizontal" commandName="/user/complaints" modelAttribute="complaintModel">
	<form:hidden path="studentId"/>
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
                <label class="control-label">Student Id</label>                	
                	<div class="controls">
                		<form:input path="studentId" placeholder="student id or select beleow"/>                		
					</div>
			</div>
			<div class="control-group">
                <label class="control-label">Complaint Query</label>                	
                	<div class="controls">
                		<form:textarea rows="8" cols="8" path="complaintQuery" placeholder="Write your complaints here."/>
					</div>
			</div>
			<div class="control-group">
				<div class="controls">
				<input type="submit" value="Post complaint" class="btn btn-primary"/>
				</div>
			</div>
   		</form:form>
</div>
<div style="display: block; overflow: hidden;">
		<c:choose>
		<c:when test="${empty allStud}">
			No students available
		</c:when>
		<c:otherwise>
		<c:url value="/user/complaints/" var="indStdUrl"/>
		<c:forEach items="${allStud}" var="std">
				<div id="${std.userId}" class='control-group span4'>
					<div class="span2">
						<label class="control-label">Student Id:</label>
							<label id="uId">${std.userId}</label>				
						<label class="control-label">Name:</label>
							<label class="text-info">${std.firstName} ${std.lastName}</label>
						<label class="control-label">Reg. No:</label>
							<label class="text-info" id="${std.studentId}">${std.studentId}</label>
												
					</div>
					<div class="span1">				 
			             <img id="pfPhoto" class="pull-left img-polaroid" alt="Polaroid Image"
			                	 src='<c:url value="/profilePhoto/${std.imageName}"/>'/>
			             <a href="${indStdUrl}${std.id}">complaint him</a>	             	       
					</div>
				</div>
			</c:forEach>
	</c:otherwise>
	</c:choose>
</div>