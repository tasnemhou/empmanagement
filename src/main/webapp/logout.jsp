<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
   
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	
	<style type="text/css">
		#quit a:link {
			color: black;
		}
		#quit a {
			text-decoration:none;
			margin-right:20px;
		}
		#quit {
			text-align: right;
			margin-top: 20px;
		}
		#quit a:HOVER {
			font-weight: bold;
		}
	</style>
	
</head>
<body>
	<p id="quit">
		柜员：<span>${user } </span>
		<a href="${pageContext.request.contextPath }/tellerLogout.do">【退出】</a>
	</p>
</body>