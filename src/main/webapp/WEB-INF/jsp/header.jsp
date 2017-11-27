<%@ page pageEncoding="utf-8" 
contentType="text/html; charset=utf-8" %>
<style type="text/css">
* { font-family: "微软雅黑";}
body, ul, li, ol { margin: 0; padding: 0; }
ul, ol, li { list-style: none; }
#header_wrapper {height: 80px; text-align: center; border-bottom: 3px solid #666; }
#header { width: 800px; height: 80px; margin: 0 auto; overflow: auto; }
#header b { font-size: 28px; float: left; background: #ddd; line-height: 80px; padding: 0 20px; }
#header ul { float: left; overflow: auto; margin: 0 0 0 20px; }
#header ul li { float: left; }
#header ul li a { display: block; padding: 0 15px; font-size: 20px; line-height: 80px; color: #333; text-decoration: none; }
#header ul li a:hover { background: #ccc; }

#content_wrapper { text-align: center; }
#content { margin: 0 auto; text-align: left; padding: 20px; width: 760px; border-left: 1px solid #ccc; border-right: 1px solid #ccc; }
</style>

<div id="header_wrapper">
	<div id="header">
		<b>SSH Project</b>
		<ul>
			<li><a href="${pageContext.request.contextPath }/main/index.action">Home</a></li>
			<li><a href="${pageContext.request.contextPath }/main/query.action?keyword=mvc">Query</a></li>
			<li><a href="${pageContext.request.contextPath }/user/loginForm.action">Login</a></li>
			<li><a href="${pageContext.request.contextPath }/main/about.action">About</a></li>
		</ul>
	</div>
</div>