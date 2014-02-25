<%@ include file="../includes.jsp" %>
<c:url value="/forgotPwd" var="fpwd"/>
<div class="well">
<label class="label label-success">${message}</label>
<c:set var="errMsg" value="${sessionScope['SPRING_SECURITY_LAST_EXCEPTION'].message}" />
<c:if test="${not empty errMsg}">
	<div class="alert alert-error">
		<button type="button" class="close" data-dismiss="alert">&times;</button>
  		<h4>Login failed Caused :</h4>
  		${errMsg}
	</div>
</c:if>
<c:if test="${not empty accessDeni}">
	<div class="alert alert-warning form-titl">
		<button type="button" class="close" data-dismiss="alert">&times;</button>
  		<h4>Access Denied :</h4>
  		${accessDeni}
	</div>
</c:if>
	<form class="form-horizontal" action="j_spring_security_check" method="post" >
	 <fieldset>
	 <legend>Login</legend>		 	
			<div class="control-group">
                <label for="j_username" class="control-label">Email</label>                	
                <div class="controls">			
				<input id="j_username" name="j_username" size="20" maxlength="50" 
						placeholder="Email" type="text"/>
				</div>
			</div>
			
			<div class="control-group">
                <label for="j_password" class="control-label">Password</label>
                <div class="controls">			
				<input id="j_password" name="j_password" placeholder="password" size="20" maxlength="50" type="password"/>
				<a href="${fpwd}">forgot password</a>
				</div>
			</div>
			<div class="control-group">
				<div class="controls">
				<input type="submit" class="btn btn-primary" value="Login"/>
				<a href='<c:url value="/register"/>'>New User</a>
				</div>
			</div>	
		</fieldset>	
	</form>
</div>