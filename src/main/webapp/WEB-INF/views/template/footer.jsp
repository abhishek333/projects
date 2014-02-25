<%@ include file="../includes.jsp" %>
<div class="well">
       
        <hr/>
        
        <label class="pull-left">
        &#169; 2013 Abhishek Corporation.
        </label>
        <label class="pull-right">
        Passionately created by <b class="text-info">Abhishek</b>
        </label>    
</div>

<c:url value="/assets/bootstrap/js/jquery-1.8.2.js" var="jq"/>
<c:url value="/assets/bootstrap/js/bootstrap.js" var="bt"/>
<c:url value="/assets/bootstrap/js/bootstrap-datepicker.js" var="dtpic"/>
<c:url value="/assets/jChart/jchartfx.coreBasic.js" var="jChtCB"/>
<c:url value="/assets/jChart/jchartfx.system.js" var="jChSm"/>
<script src="${jq}" type="text/javascript"></script>
<script src="${bt}" type="text/javascript"></script>
<script src="${dtpic}" type="text/javascript"></script>
<script src="${jChSm}" type="text/javascript"></script>
<script src="${jChtCB}" type="text/javascript"></script>

 <script type="text/javascript">   

 		$(document).ready(function(e){
 			$('#dp3').datepicker();
 			$('#student-tab a:first').tab('show');
 			$('#student-tab a').click(function (e) {
 				  e.preventDefault(); 				   				
 				 $(this).tab('show');
 	 				if($(this).attr('id')=='attend')						
 	 	 				  sendAjax($("div.form-titl").find("label[id=userId]").text());
 				 
 				});
 	 	}); 
 	 	function sendAjax(email) {
 			var jsonStr = {"year" : 2013, "studentEmail" : email, "presentAttendence" : null, "absentAttendence" : null,};
 			$.ajax({
                type: "POST",
                url: $('label[id=attChartUrl]').text(),
				data: JSON.stringify(jsonStr),
                async: false,
                cache: false,
                processData:false,
                beforeSend: function(xhr) {
            		xhr.setRequestHeader("Accept", "application/json");
            		xhr.setRequestHeader("Content-Type", "application/json");
            	},
                success: function(response){		
                	                	
                	var dataByMonth = [
                	            { "Month": "Jan", "Absents": response.absentAttendence["Jan"], "Presents": response.presentAttendence["Jan"] },
                	            { "Month": "Feb", "Absents": response.absentAttendence["Feb"], "Presents": response.presentAttendence["Feb"] },
                	            { "Month": "Mar", "Absents": response.absentAttendence["Mar"], "Presents": response.presentAttendence["Mar"] },
                	            { "Month": "Apr", "Absents": response.absentAttendence["Apr"], "Presents": response.presentAttendence["Apr"] },
                	            { "Month": "May", "Absents": response.absentAttendence["May"], "Presents": response.presentAttendence["May"] },
                	            { "Month": "Jun", "Absents": response.absentAttendence["Jun"], "Presents": response.presentAttendence["Jun"] },
                	            { "Month": "Jul", "Absents": response.absentAttendence["Jul"], "Presents": response.presentAttendence["Jul"] },
                	            { "Month": "Aug", "Absents": response.absentAttendence["Aug"], "Presents": response.presentAttendence["Aug"] },
                	            { "Month": "Sep", "Absents": response.absentAttendence["Sep"], "Presents": response.presentAttendence["Sep"] },
                	            { "Month": "Oct", "Absents": response.absentAttendence["Oct"], "Presents": response.presentAttendence["Oct"] },
                	            { "Month": "Nov", "Absents": response.absentAttendence["Nov"], "Presents": response.presentAttendence["Nov"] },
                	            { "Month": "Dec", "Absents": response.absentAttendence["Dec"], "Presents": response.presentAttendence["Dec"] }
                	            ]; 
    	                               
                    loadChart(dataByMonth);                	
                },
                error: function(e){
                alert('Error: ' + e);
                }
                });
			
 	 	}	
		function readURL(input) {			
	        if (input.files && input.files[0]) {
	            var reader = new FileReader();

	            reader.onload = function (e) {
	                $('#pfPhoto')
	                    .attr('src', e.target.result)
	                    .width(150)
	                    .height(200);
	            };

	            reader.readAsDataURL(input.files[0]);
	        }
	    }    		
    	$('input[name^="att"]:radio').change(function (){        	
			var atDate =  $('input[name=attendenceDate]');
       	 	if(atDate.val().length < 1){
				alert('please select attendence date.');
				atDate.focus();
           	 	return;
       	 	}
       	 	//alert($(this).val());
			var presentSt = $(this).val();
    		var uId = $(this).parent("label").parent("div").parent("div").find("label[id=uId]").text();
    		var attendDate = atDate.val().replace( /(\d{2})-(\d{2})-(\d{4})/, "$2/$1/$3");
    		var id = null;    			
			//alert($('label[id=attUrl]').text()+' : uid: '+$('label[id=uId]').text()+' attendDate='+attendDate );
        	var jsonStr = {"id" :id, "studentId" : uId, "isPresent" : presentSt, "attnDate" : new Date(attendDate)};
        	$.ajax({
                type: "POST",
                url: $('label[id=attUrl]').text() ,
                data: JSON.stringify(jsonStr),
                async: false,
                cache: false,
                processData:false,
                beforeSend: function(xhr) {
            		xhr.setRequestHeader("Accept", "application/json");
            		xhr.setRequestHeader("Content-Type", "application/json");
            	},
                success: function(response){
                // we have the response                
                	if(response.isPresent) {                    		                
                    	$('div#'+uId).addClass("present");
                    	if($('div#'+uId).hasClass("absent"))               		
                			$('div#'+uId).removeClass("absent");                		
                	}
                	if(!response.isPresent){                         	                             
                		$('div#'+uId).addClass("absent");
                		if($('div#'+uId).hasClass("present"))
                			$('div#'+uId).removeClass("present");
                    }
                		
                },
                error: function(e){
                alert('Error: ' + e);
                }
                });
        	});
</script>

<script type="text/javascript">
    var chart1;
    function loadChart(data)
      {                
           chart1 = new cfx.Chart();
            chart1.getData().setSeries(2);
            chart1.getAxisY().setMin(1);
            chart1.getAxisY().setMax(34);
            var series1 = chart1.getSeries().getItem(0);
            var series2 = chart1.getSeries().getItem(1);
            series1.setGallery(cfx.Gallery.Lines);
            series2.setGallery(cfx.Gallery.Bar);                        
            chart1.setDataSource(data);            
            var divHolder = document.getElementById('ChartDiv');
        	chart1.create(divHolder);
                       
      }
</script>
<script type="text/javascript">
	$('select[id=examId]').change(function(){
		
		var examId = $(this).val();
		if(examId==0)
			return;		
		var jsonStr = {"id" :null, "studentId":null, "examId":examId, "obtainMark":null, "exam":null, "personalDetails":null, "courses":null};
    	$.ajax({
            type: "POST",
            url: $('label[id=examUrl]').text() ,
            data: JSON.stringify(jsonStr),
            async: false,
            cache: false,
            processData:false,
            beforeSend: function(xhr) {
        		xhr.setRequestHeader("Accept", "application/json");
        		xhr.setRequestHeader("Content-Type", "application/json");
        	},
            success: function(response){
            // we have the response
            	$('select[id=studentId]').html('<option value="0">--- student ---</option>');
            	$(response.studentList).each(function(index){					
					$('select[id=studentId]').append('<option value="'+this.id+'">'+this.firstName +' '+ this.lastName+' ('+this.studentId+')</option>');
            	});
            	var examDate = new Date(response.exam.examDate);                        		
            	$('span[id=eDet]').html('Exam date:'+(examDate.getMonth() + 1)+'-'+examDate.getDate()+'-'+examDate.getFullYear()+', Exam semeter:'+response.exam.semeter);
            	$('span[id=crsNm]').html(response.courses.courseName);
            	$('span[id=fullMrk]').html(response.exam.fullMark);
            },
            error: function(e){
            alert('Error: ' + e);
            }
            });
	});
</script>

</body>
</html>