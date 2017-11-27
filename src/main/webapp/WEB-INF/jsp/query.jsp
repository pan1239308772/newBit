<%@ page pageEncoding="utf-8" 
contentType="text/html; charset=utf-8" %>
<html>
	<head></head>
	<body>
		<%@include file="header.jsp" %>
	
		<div id="content_wrapper">
			<div id="content">
				<h3>Query keyword : ${keyword }</h3>
				<h3>Welcome, ${session.username }!</h3>
			</div>
		</div>
	</body>
</html>