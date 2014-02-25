<!DOCTYPE html>
<%@ include file="includes.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<c:url var="bootstrapStylesheetUrl" value="/assets/bootstrap/css/bootstrap.css" />
<c:url var="bootstrapResponsiveStylesheetUrl" value="/assets/bootstrap/css/bootstrap-responsive.css" />
<c:url var="projectStylesheetUrl" value="/assets/css/studentIS.css" />
<c:url var="datePicStylesheetUrl" value="/assets/bootstrap/css/datepicker.css" />
<c:url var="jChrtStylesheetUrl" value="/assets/jChart/jchartfx.css" />
<link rel="stylesheet" href="${bootstrapStylesheetUrl}" type="text/css"/>
<link rel="stylesheet" href="${bootstrapResponsiveStylesheetUrl}" type="text/css"/>
<link rel="stylesheet" href="${projectStylesheetUrl}" type="text/css">
<link rel="stylesheet" href="${datePicStylesheetUrl}" type="text/css">
<link rel="stylesheet" href="${jChrtStylesheetUrl}" type="text/css">

<title><tiles:insertAttribute name="title" ignore="true" /></title>
</head>
<body>
<div class="container">
	<tiles:insertAttribute name="menu" />
	<tiles:insertAttribute name="body" />
	<tiles:insertAttribute name="footer" />
</div>
</body>
</html>