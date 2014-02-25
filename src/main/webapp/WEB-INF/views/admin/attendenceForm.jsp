<%@ include file="../includes.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div class="well" style="display: block; overflow: hidden;">	
	<div class="form-titl">
	 	<h3>Attendence:</h3>	
	</div>
	<hr/>
   <div>  
   	<div class="form-inline">
   		<form:form commandName="/admin/attendence" modelAttribute="attendenceRef">   	
   		<label class="control-label">Class</label>
   			<form:select path="courseId">
   				<form:options items="${classes}" itemLabel="courseName" itemValue="id"/>
   			</form:select>
   		<label class="control-label">Date</label>
   			<div class="controls input-append date" id="dp3" data-date="12-12-2013" data-date-format="dd-mm-yyyy">
   				<form:input path="attendenceDate" class="input-medium" placeholder="attendence date"/>
   				<span class="add-on"><i class="icon-calendar"></i></span>
   			</div>
   			<input type="submit" value="Refine" class="btn btn-mini btn-success"/>
   		</form:form>	
   		<hr/>
	</div>
	<c:choose>
		<c:when test="${empty students}">
			Oops, No students are registered yet!
		</c:when>
		<c:otherwise>
		
			<c:forEach items="${students}" var="std">
				<div id="${std.userId}" class='control-group span3 attn <c:if test="${std.attendence.isPresent}">present</c:if><c:if test="${not std.attendence.isPresent}">absent</c:if>'>
					<div class="span2">
						<label class="text-info">					
								<c:if test="${std.attendence.isPresent eq null}">Not taken</c:if>										
						</label>
						<label id="uId" style="display: none">${std.userId}</label>				
						<label class="control-label">Name:</label>
							<label class="text-info">${std.firstName} ${std.lastName}</label>
						<label class="control-label">Reg. No:</label>
							<label class="text-info" id="${std.studentId}">${std.studentId}</label>
						<label class="radio inline">
			                <input id="${std.studentId}" type="radio" name="att${std.studentId}" value="true" 
			                	<c:if test="${std.attendence.isPresent}">checked="checked"</c:if>/>Present	                
			             </label>
			             <label class="radio inline">
			                <input id="${std.studentId}" type="radio" name="att${std.studentId}" value="false" <c:if test="${not std.attendence.isPresent}">checked="checked"</c:if>/>Absent
			             </label>
					</div>
					<div class="span1">				 
			             <img id="pfPhoto" class="pull-left img-polaroid" alt="Polaroid Image"
			                	 src='<c:url value="/profilePhoto/${std.imageName}"/>'/>	             	       
					</div>
				</div>
			</c:forEach>	
  		</c:otherwise>
	</c:choose>
   </div>
   <label id="attUrl" style="display: none;"><c:url value="/admin/addAttendence"/></label>
   <label id="attChartUrl" style="display: none;"><c:url value="/getAttendenceMap"/></label>
   <div id="ChartDiv" style="width:600px;height:400px; display: block; overflow: hidden;"></div>
</div>