<%@ include file="includes.jsp" %>
<div class="well" style="display: block; overflow: hidden;">
	<div class="form-titl">
	 	<h3>View Complaints:</h3>	
	</div>
	<hr/>
</div>
<div>
	<c:choose>
		<c:when test="${empty compList}">
			No complaints are registered..
		</c:when>
		<c:otherwise>		
		<table class="table table-stripped table-hover">
			<thead>
				<tr>
					<td>#</td>
					<td>Complaint By</td>
					<td>Complaint To</td>
					<td>Complaint Date</td>
					<td>Query</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${compList}" var="comp">
					<tr>
							<td>${comp.id}</td>
							<td>${comp.complaintBy}</td>
							<td>${comp.studentId}</td>
							<td>${comp.complaintDate}</td>
							<td>
								${comp.complaintQuery}
							</td>
					</tr>
				</c:forEach>
		</tbody>
		</table>
	</c:otherwise>
	</c:choose>  	
</div>