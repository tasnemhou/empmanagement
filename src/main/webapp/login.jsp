<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>登陆</title>
<style type="text/css">

 	form {
 		width:300px; 
 		border:1px solid #ccc; 
 		margin:150px auto;
 	} 
 	form h2 {
 		text-align: center;
 		margin:0;
 		padding: 5px;
 		border: 1px solid #ccc;
 	}
 	form p {
 		text-align:center;
 		margin: 0;
 		padding: 20px;
 		border: 1px solid #ccc;
 	}
 	form div {
 		text-align: center;
 		margin: 0;
 		padding: 10px;
 		border: 1px solid #ccc;
 	}
 	.error_msg {
 		border: 1px solid red;
 		color: red;
 	}
</style>
<script type="text/javascript">
	function check_name() {
// // 		用正则判断输入是否合法
// 		var reg = /^\w{3,20}$/;
// 		if (reg.test(name)) {
// 			//如果不匹配，增加错误样式
// 			//className 属性等价于class
// 			span.className = "error_msg";
// 			return false;
// 		}else {
// 			//如果匹配，移除样式
// 			span.className = "";
// 			return true;
// 		}
		
	}
	
	window.onload = function() {
		var flag = "${flag}"
		if(flag=="cus_login_fail") {
			alert("用户名和密码不匹配");
			document.getElementById("commit").disabled = false;
		}else if(flag=="out_of_work") {
			alert("您已离职，不允许再登陆");
			document.getElementById("commit").disabled = false;
		}
	};
	
	function check_name_pwd() {
		//获取帐号文本框的值
		var name = document.getElementById("user_name").value;
		//用正则表达式去空格
		name = name.replace(/\s/gi,'');
		//获取输入框的值
		var pwd = document.getElementById("pwd").value;
		//用正则表达式去空格
		pwd = pwd.replace(/\s/gi,'');
		if(name==""||name==null||pwd==""||pwd==null) {
			alert("用户名或密码不能为空");
			document.getElementById("commit").disabled = false;
			return false;
		}
		return true;
	}
// 	// 	
// 	function trim() {
// 		var vl = document.all.pwd.value;
// 		document.all.pwd.value = vl.replace(/[]/g,"");
// 	}
	
</script>

</head>
	<body>
	
		<form action="login.do" method="post" onsubmit="return check_name_pwd();" >
			<h2 id="welcome">登陆</h2>
				<p> 
					用户名：<input type="text" id="user_name" name="username"  placeholder="请输入">
				</p>
				<p>
					密&nbsp; &nbsp;码：<input type="password" id="pwd" name="pwd" placeholder="请输入">
				</p>
			<div>
					<input  type="submit" id="commit" value="提交" onclick="javascript:{this.disabled=true}">
			</div>
		</form>
		
	</body>
	
</html>



