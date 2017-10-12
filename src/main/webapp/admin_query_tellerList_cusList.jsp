<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@include file="logout.jsp" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>管理员查询明细列表</title>
	<style type="text/css">
		table { 
	 			border-collapse: collapse; 
	 			text-align: center;
	 			width: 100%;
	 	} 
		th,td {
			border: 1px solid ;
		}
 		
	</style>
	<script type="text/javascript">
		window.onload = function() {
			document.getElementById("customerList").style.display = "none";
			document.getElementById("tellerList").style.display = "none";
			//document.getElementById("dayBusiness").style.display = "none";
			
			var flag = "${flag}";
			if(flag=="tellerList") {
				document.getElementById("tellerList").style.display = "block";
			}else if(flag=="customerList") {
				document.getElementById("customerList").style.display = "block";
			}
// 			else if(flag=="dayBusiness") {
// 				document.getElementById("dayBusiness").style.display = "block";
// 			}
			
		};
		
		function user_state(e) {
			
// 			var obj = e.srcElement || e.target;
			
// 			//obj.getElementById("tellerStt").removeAttribute("disabled");
// 			//var table = document.getElementById("tellerList");
			
// 			var tr = e.parentNode.parentNode;
// 			alert(tr);
// // 			var tds = tr.getElementsByTagName("td");
// 			var tds = tr.childNodes;
// 			var childs = tds[4].childNodes;
			
			
// 			//childs[0].getElementById("tellerStt").removeAttribute("disabled");
// // 			childs[0].getElementById("tellerStt").disabled = "";
// 			childs[0].removeAttribute('disabled');
			
		}
	</script>
</head>
<body>
<div id="tellerList">
	<form action="update_userStt.do" method="post">
		<table >
			<tr>
				<th colspan="5">柜员明细列表</th>
				<th>
					<a href="${pageContext.request.contextPath }/back_to_adminOperation.do">返回</a>
				</th>
			</tr>
			<tr>
				<th >柜员姓名</th>
				<th >性别</th>
				<th >柜员级别</th>
				<th >手机号</th>
				<th >柜员状态</th>
				<th >操作</th>
			</tr>
			<c:forEach items="${tellerList}" var="tellerList">
				<tr>
					<td>${tellerList.userNM }</td>
					<td>
						<c:choose>
							<c:when test="${tellerList.gender=='M' }">男</c:when>
							<c:when test="${tellerList.gender=='F' }">女</c:when>
						</c:choose>
					</td>
					<td>
						<c:choose>
							<c:when test="${tellerList.userLevel=='0' }">管理员</c:when>
							<c:when test="${tellerList.userLevel=='1' }">柜员</c:when>
						</c:choose>
					</td>
					<td>${tellerList.phoneNum }</td>
					<td>
						<c:choose>
							<c:when test="${tellerList.userState=='0' }">在职 </c:when>
							<c:when test="${tellerList.userState=='1' }">离职 </c:when>
						</c:choose>
					</td>
					<td>
						<input type="button" id="modify" value="修改" onclick="user_state(this);">
						<input type="submit" id="save" value="保存">
					</td>
				</tr>
			</c:forEach>
			
		   </table>
		</form>
	</div>
		
	<div id="customerList">
		<table >
			<tr >
				<th colspan="7">客户明细列表</th>
				<th>
					<a href="${pageContext.request.contextPath }/back_to_adminOperation.do">返回</a>
				</th>
			</tr>
			<tr>
				<th >客户姓名</th>
				<th >性别</th>
				<th >生日</th>
				<th >手机号</th>
				<th>卡号</th>
				<th >卡性质</th>
				<th >操作柜员</th>
				<th >注册日期</th>
			</tr>
			<c:forEach items="${allCusInfo}" var="allCusInfo">
				<tr>
					<td>${allCusInfo.customerName }</td>
					<td>
						<c:choose>
							<c:when test="${allCusInfo.gender=='M' }">男</c:when>
							<c:when test="${allCusInfo.gender=='F' }">女</c:when>
						</c:choose>
					</td>
					<td>${allCusInfo.cusBirthday }</td>
					<td>${allCusInfo.phoneNum }</td>
					<td>${allCusInfo.cardNO }</td>
					<td>
						<c:choose>
								<c:when test="${allCusInfo.cardType=='1'}">次卡</c:when>
								<c:when test="${allCusInfo.cardType=='2'}">月卡</c:when>
								<c:when test="${allCusInfo.cardType=='3'}">年卡</c:when>
							</c:choose>
					</td>
					<td>${allCusInfo.teller }</td>
					<td>${allCusInfo.cusRegistDt }</td>
				</tr>
			</c:forEach>
		</table>
	</div>	
</body>
</html>