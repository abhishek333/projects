<%@ include file="../includes.jsp" %>
<c:url value="/login" var="lurl"/>
<div class="well">
<c:choose>
<c:when test="${not empty forgotPwd}">
	<label class="label label-success">${forgotPwd}</label>
	<a href='${lurl}'>Login</a>
</c:when>
<c:otherwise>	
<form:form commandName="forgotPwd" modelAttribute="user" class="form-horizontal" method="post" >
	 <form:hidden path="id"/>
	 <fieldset>
	 <legend>Change Password</legend>		
	 	<label class="text-error">${ferr}</label> 	
			<div class="control-group">
                <label for="username" class="control-label">Email</label>                	
                <div class="controls">			
				<form:input path="username" size="20" maxlength="50" 
						placeholder="Email"/>
				</div>
			</div>
			<div class="control-group">
                <label for="password" class="control-label">Password</label>                	
                <div class="controls">			
				<form:password path="password"	placeholder="new password"/>
				</div>
			</div>
			<div class="control-group">                                
                <div class="controls">
					<input type="submit" value="Change Password" class="btn btn-primary"/>
				</div>
			</div>
	</fieldset>
</form:form>
</c:otherwise>
</c:choose>
</div>