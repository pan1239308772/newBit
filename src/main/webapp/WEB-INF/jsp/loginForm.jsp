<%@ page pageEncoding="utf-8" 
contentType="text/html; charset=utf-8" %>
<html>
	<head><title>SSH Project</title></head>
	<body>
		<%@include file="header.jsp" %>
		
		<div id="content_wrapper">
			<div id="content">
				<h3>Login</h3>
				<h3>Welcome, ${session.username }!</h3>
				<div id="form">
					<form name="login" method="post" action="${pageContext.request.contextPath }/user/login.action">
						<div>
							<p>Username</p>
							<p><input type="text" name="user.username" maxlength="16" />
						</div>
						<div>
							<p>Password</p>
							<p><input type="text" name="user.password" maxlength="16" />
						</div>
						<div>
							<p><input type="submit" value="Submit" /></p>
						</div>
					</form>
				</div>
			</div>
		</div>
	</body>
</html>