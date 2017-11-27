<%@ page pageEncoding="utf-8" 
contentType="text/html; charset=utf-8" %>
<html>
	<head>
		<title>SSH Project</title>
		<style type="text/css">
			#content h3.error { color: red; }
		</style>
	</head>
	<body>
		<%@include file="header.jsp" %>
		
		<div id="content_wrapper">
			<div id="content">
				<h3 class="error">${message }</h3>
			</div>
		</div>
	</body>
</html>