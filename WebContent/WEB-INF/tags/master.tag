<%@tag description="Master Page" pageEncoding="UTF-8"
%><%@attribute name="header" fragment="true"
%><%@attribute name="content" fragment="true"
%><%@attribute name="footer" fragment="true"
%><%@attribute name="title" required="true"
%><!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<base href="${pageContext.request.contextPath}/">
		<!-- [if IE]>
			<script type="text/javascript">
				(function)() {
					var baseTag = document.getElementsByTagName('base')[0];
					baseTag.href = baseTag.href;
				})();
			</script>
		<! [endif]-->		
		<link rel="stylesheet" href="styles/master.css" type="text/css" media="screen">
		<script src="scripts/angular.min.js"></script>
		<title>PEST- ${title}</title>
		<jsp:invoke fragment="header"/>
	</head>
	<body>
		<div class="main">
			<header class="pageHeader">
				<h3>PEST Lab</h1>		
				<hr/>		
				<jsp:invoke fragment="header"/>
			</header>
			<div class="pageContent">
				<jsp:invoke fragment="content"/>
			</div>
			<footer class="pageFooter">
				<p>Copyright&copy; 2014 <span><a href="http://www.um.edu.mt">UoM</a></span></p>
				<jsp:invoke fragment="footer"/>
			</footer>
		</div>		
	</body>
</html>
