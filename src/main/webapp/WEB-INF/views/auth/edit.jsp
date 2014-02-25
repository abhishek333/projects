<%@ include file="../includes.jsp" %>
<div class="well">	
	<c:if test="${empty personalDetails}">
		<div class="form-titl">
		 	<h3>Student Information:</h3>	
		 	<hr class="span10"/>
		 </div>	 	
		<div class="span8">
			<label class="text-error">
			No user details found.</label>
		</div>
	</c:if>
	<c:if test="${not empty personalDetails}">
		<form:form  method="POST" enctype="multipart/form-data" commandName="/user/edit" modelAttribute="personalDetails">
		 <fieldset>
		  <div class="form-titl">
		 	<h3>Student Information:</h3>	
		 	<form:hidden path="id"/>
		 	<form:hidden path="userId"/>
		 	<hr class="span10"/>
		 </div>	 		 
				<c:set var="em_er"><form:errors path="email"/></c:set>
				<c:set var="pwd_er"><form:errors path="user.password"/></c:set>
				<c:set var="fnm_er"><form:errors path="firstName"/></c:set>
				<c:set var="gender_er"><form:errors path="gender"/></c:set>
				<c:set var="mobile_er"><form:errors path="mobile"/></c:set>
			<div class="span3">
				<div class="control-group edit-prof">
                <label class="control-label">Photo</label>                	
                	<div class="controls">
                	<c:url value="/profilePhoto/" var="profUrl"/> 
                	<img id="pfPhoto" class="pull-left img-polaroid" alt="Polaroid Image" src="${profUrl}/${personalDetails.imageName}"/>
					<input type="file" onchange="readURL(this);"
							   name="profilePhoto"/>
					</div>
				</div>
            </div>
            <div class="span8">
            	<div class='control-group <c:if test="${not empty em_er}">error</c:if> span4'>
                <label class="control-label">Email</label>                	
                	<div class="controls">
                    	<form:input path="email" cssErrorClass="error" placeholder="Email"/>
                    </div>
                </div>               
             
                <div class='control-group <c:if test="${not empty fnm_er}">error</c:if>'>
                <label class="control-label">Name</label>                	
                	<div class="controls">
                        <form:input path="firstName" placeholder="First" class="input-mini"/>
                        <form:input path="lastName" placeholder="Last" class="input-mini"/>
                    </div>
                </div>
                <div class='control-group <c:if test="${not empty fnm_er}">error</c:if> span4'>
                <label class="control-label">Student Id</label>                	
                	<div class="controls">
                        <form:input path="studentId" placeholder="Student Id" class="input-medium"/>                        
                    </div>
                </div>
                <div class='control-group <c:if test="${not empty fnm_er}">error</c:if> span4'>
                <label class="control-label">Admission Num</label>                	
                	<div class="controls">
                        <form:input path="admissionNum" placeholder="Admission Number" class="input-medium"/>                        
                    </div>
                </div>
                <div class='control-group <c:if test="${not empty fnm_er}">error</c:if> span4'>
                <label class="control-label">Course</label>                	
                	<div class="controls">
                        <form:select path="courseId" class="input-medium">
                        	<form:option value="0" label="--- Course ---" />
							<form:options items="${courses}" itemLabel="courseName" itemValue="id"/>
                        </form:select>                                              
                    </div>
                    
                </div>
                
                <div class='control-group <c:if test="${not empty fnm_er}">error</c:if>'>
                <label class="control-label">Father Name</label>                	
                	<div class="controls">
                        <form:input path="fatherName" placeholder="Father name" class="input-medium"/>                        
                    </div>
                </div>
                
                <div class='control-group <c:if test="${not empty gender_er}">error</c:if>'>
                	<label class="control-label">Section</label>                	                	
                      <div class="controls">
                        <form:select path="section" class="input-medium">
                        	<form:option value="NONE" label="- Section -" />
                        	<form:option value="A" label="A" />
                        	<form:option value="B" label="B" />
                        	<form:option value="C" label="C" />
                        	<form:option value="D" label="D" />
                        </form:select>
                     </div>
                </div>
           </div>
           <div class="span12">
                <div class='control-group <c:if test="${not empty gender_er}">error</c:if> span3'>
                	<div class="controls-row">
                   <label class="control-label" style="margin-right:10px;">Gender</label>
                      <label class="radio inline">
                        <form:radiobutton path="gender" name="gender" value="M"/>Male
                      </label>
                      <label class="radio inline">
                        <form:radiobutton path="gender" name="gender" value="F"/>Female
                      </label>
                    </div>
                </div>
                <div class="control-group span4">
                <label class="control-label">Date of Birth</label>                	
                	<div class="controls input-append date" id="dp3" data-date="12-12-2013" data-date-format="dd-mm-yyyy">
						<form:input path="dateOfBirth" class="input-medium" placeholder="dd-MM-yyyy"/>
						<span class="add-on"><i class="icon-calendar"></i></span>
                    </div>
                </div>
				<div class="control-group">
                 <label class="control-label">Country</label>                	
                	<div class="controls">
						<form:select path="country">
                        	<form:option value="NONE" label="--- Select Country---" />
							<form:options items="${countrys}"/>
                        </form:select>						
                    </div>
                </div> 
				<div class="control-group span3">
                 <label class="control-label">State</label>                	
                	<div class="controls">
						<form:input path="state" placeholder="state name" class="input-medium"/>
                    </div>
                </div> 
				<div class="control-group span4">
                 <label class="control-label">City</label>                	
                	<div class="controls">
						<form:input path="city" placeholder="city name" class="input-medium"/>
                    </div>
                </div> 
                <div class='control-group <c:if test="${not empty mobile_er}">error</c:if>'>
                <label class="control-label">Phone No</label>                	
                	<div class="controls">
						<form:input path="mobile"  placeholder="your mobile number"/>
                    </div>
                </div>
                <div class="control-group form-actions span10">                              
	               	<div class="controls">
		                <label class="checkbox">
		                <input type="checkbox" checked="checked"/>I agree to the Privacy Policy and T & C.</label>                                         
		                <input type="submit" value="update" class="btn btn-primary" />
		        	</div>
        		</div>
            </div>
            
        		</fieldset>
		</form:form>
	</c:if>
</div>