<%@page import="com.asn.util.Util"%>
<%@ include file="../includes.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<security:authorize var="loggedIn" access="isAuthenticated()" />
<div class="well">
	<div class="navbar">
	  <div class="navbar-inner head-nav-col">
    	<a class="brand" href="#">Student Information System</a>
			<ul class="nav">
				<li class="active">	
				 	<a href='<c:url value="/"/>'>Home</a>
				</li>			  
				<li>	
				 	<a href='<c:url value="/admin/attendence"/>'>Attendence</a>
				</li>	
				<li class="dropdown">
					<a href='<c:url value="/user/complaints"/>'>Complaint <span class="caret"></span></a>
					<ul class="dropdown-menu" id="menu1">
					<li><a tabindex="-1" href='<c:url value="/user/complaints"/>'>Add Complaint</a></li>
	        		<c:if test="${loggedIn}">		    	        		        				        		
					    <li><a tabindex="-1" href='<c:url value="/user/complaints/view"/>'>View Complaint</a></li>
					</c:if>
					</ul>
				</li>		  
				<li class="dropdown">
					<a data-toggle="dropdown" class="dropdown-toggle" href="#"> Exam <span class="caret"></span></a>
				 	<ul class="dropdown-menu" id="menu2">
					 	<li><a href='<c:url value="/admin/exam/addExamDetails"/>'>Add Exam Detail</a></li>
					 	<li><a href='<c:url value="/admin/exam/addExam"/>'>Add Exam</a></li>
				 	</ul>
				</li>			  
				<li class="dropdown">
	        		<a data-toggle="dropdown" class="dropdown-toggle" href="#"> My Account <span class="caret"></span></a>
	        		<ul class="dropdown-menu" id="menu1">
	        		<c:if test="${loggedIn}">		    	        	
	        		<security:authorize access="hasRole('ROLE_ADMIN')">			        	
		        		<li>
		        			<a href="#">Course <i class="icon-arrow-right"></i></a>	        		
		        			<ul class="dropdown-menu sub-menu">
		        				<li><a tabindex="-1" href='<c:url value="/admin/addcourse"/>'>Add Course</a></li>
					        	<li><a tabindex="-1" href='<c:url value="/admin/updateCourse"/>'>Update Course</a></li>
					        	<li><a tabindex="-1" href='<c:url value="/admin/deleteCourse"/>'>Delete Course</a></li>
					        </ul>
					    </li>
			        <li>
			        	<a tabindex="-1" href='#'>Fees <i class="icon-arrow-right"></i></a>
				        <ul class="dropdown-menu sub-menu">
				        	<li><a tabindex="-1" href='<c:url value="/admin/courseFees"/>'>Show Fees</a></li>				        	
				        	<li><a tabindex="-1" href='<c:url value="/admin/addCourseFees"/>'>Add Fees</a></li>				        	
				        	<li><a tabindex="-1" href='<c:url value="/admin/stdCourseFeesForm"/>'>Add Student Fees</a></li>
				        </ul>
				    </li>			       			        				   
				    </security:authorize>
				    	<li><a tabindex="-1" href='<c:url value="/user/edit"/>'><security:authentication property="principal.username"/></a></li>
				    	<li><a tabindex="-1" href='<c:url value="/j_spring_security_logout"/>'>Logout</a></li>
			    	</c:if>
				    <c:if test="${not loggedIn}">
				    	<li><a tabindex="-1" href='<c:url value="/login"/>'>Login</a></li>
				    	<li><a tabindex="-1" href='<c:url value="/register"/>'>Register</a></li>
				    </c:if>
			   </ul>
			   </li>	    
				
			</ul>
	  </div>
	</div>

 </div>
 <c:if test="${loggedIn}">
 <c:set var="photo"><%=Util.currentUserImageName %></c:set>
	 <div class="pfPhto">
	 <c:choose>
	 	<c:when test="${not empty photo and photo ne 'null'}">
	 		<c:url value="/profilePhoto/" var="profUrl"/>	 	
           <img id="pfPhoto" class="pull-left img-polaroid" alt="Polaroid Image"
                	 src='<c:url value="/profilePhoto/${photo}"/>'/>
           <p class="span2 text-info">
           	<a href='<c:url value="/user/edit"/>'><%=Util.currentUserFullName %></a>
           </p>
	 	</c:when>
	 	<c:otherwise>
	 		<c:url value="/assets/img/school_student.png" var="imUrl"/>	 	
           <img id="pfPhoto" class="pull-left img-polaroid" alt="Polaroid Image"
                	 src='${imUrl}'/>
           <p class="span2 text-info"><a href='<c:url value="/user/edit"/>'><security:authentication property="principal.username"/></a></p>
	 	</c:otherwise>
	 </c:choose>	 
	 </div>
 </c:if>