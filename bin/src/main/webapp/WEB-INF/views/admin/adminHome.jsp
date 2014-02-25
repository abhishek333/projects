<%@ include file="../includes.jsp" %>
<c:url value="/assets/img/" var="imgUrl"/>
<div class="form-titl" style="margin-left: 200px;">
		<ul class="thumbnails">
		  <li class="span2">
		    <div class="thumbnail">
		      <img src='<c:url value="/assets/img/attendence.jpg"/>' alt="">
		      <h5>Attendance</h5>
		      <p><a href='<c:url value="/admin/attendence"/>'>Take Attendence</a></p>
		    </div>
		  </li>
		  <li class="span2">
		    <div class="thumbnail">
		      <img src='<c:url value="/assets/img/Fees_collect.jpg"/>' alt="">
		      <h5>Course Fees</h5>
		      <p><a href='<c:url value="/admin/courseFees"/>'>Fees Payment</a></p>
		    </div>
		  </li>
		  <li class="span2">
		    <div class="thumbnail">
		      <img src='<c:url value="/assets/img/exam.jpg"/>' alt="">
		      <h5>Exam settings</h5>
		      <p><a href='<c:url value="/admin/exam/addExamDetails"/>'>Add Exam Detail</a></p>
		    </div>
		  </li>
		  <li class="span2">
		    <div class="thumbnail">
		      <img src='<c:url value="/assets/img/complaint icon.jpg"/>' alt="">
		      <h5>Compalints</h5>
		      <p><a tabindex="-1" href='<c:url value="/user/complaints"/>'>Add Complaint</a></p>
		    </div>
		  </li>
		
		</ul>
</div>	 


<div class="tabbable"> <!-- Only required for left/right tabs -->
	  <ul class="nav nav-tabs" id="student-tab">
	      <li class="active"><a href="#fee">Fees</a></li>
		  <li><a href="#attendence" id="attend">Attendence</a></li>
		  <li><a href="#exam">Exam</a></li>
		  <li><a href="#complaints">Complaints</a></li>
	  </ul>
	  <div class="tab-content">
	     <div class="tab-pane active" id="fee">Fees</div><!-- end fee -->
		  <div class="tab-pane" id="attendence">		  	
		  	<div class="span4">
		  		<div class="control-group">
		            <label class="control-label">Total Days:</label>                	
		               	<div class="controls">
		                	<label class="text-info">${attTotalDay}</label>
						</div>
				</div>
		  		<div class="control-group">
		            <label class="control-label">Present Days:</label>                	
		               	<div class="controls">
		                	<label class="text-info">${totalPnt}</label>
						</div>
				</div>
		  		<div class="control-group">
		            <label class="control-label">Absent Days:</label>                	
		               	<div class="controls">
		                	<label class="text-info">${totalAbnt}</label>
						</div>
				</div>
		  	</div>
		  	<div class="span6">		  	
		  		<div id="ChartDiv" style="width:500px;height:300px; display: block; overflow: hidden;"></div>
		  	</div>
		  </div><!-- end attendence-->
		  <div class="tab-pane" id="exam">Exam</div><!--end exam -->
		  <div class="tab-pane" id="complaints">Complaints</div><!-- end complaints-->
	  </div><!-- end tab-content -->
	</div><!-- end tabbable -->


<label id="attChartUrl" style="display: none;"><c:url value="/getAttendenceMap"/></label>