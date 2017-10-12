<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@include file="logout.jsp" %>
   <%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>单日交易明细查询</title>
		<style type="text/css">
			table {
				border-collapse: collapse;
				width: 100%;
			}
			th,td {
				border: 1px solid;
			}
			
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
		<script type="text/javascript">
			window.onload = function() {
				document.getElementById("cash_cus_exp").style.display = "none";
				document.getElementById("cus_sin_con").style.display = "none";
 				document.getElementById("cus_sin_deposit").style.display = "none";
				
				var flag = "${flag}"
				if(flag=="queryCashCus") {
					document.getElementById("cash_cus_exp").style.display = "block";
				}else if (flag=="querySingleCon") {
					document.getElementById("cus_sin_con").style.display = "block";
				}
 				else if(flag=="querySingleDeposit") {
 					document.getElementById("cus_sin_deposit").style.display = "block";
 				}
				else if(flag=="2-2") {
					alert("客户消费信息查询失败");
				}
			}
		</script>
	</head>
	<body>
		<div id="cash_cus_exp">
			<table>
				<tr>
					<th colspan="6">散客单日消费明细表</th>
					<th>
						<a href="${pageContext.request.contextPath }/back_to_adminOperation.do">返回</a>
					</th>
				</tr>
				<tr>
					<th>消费日期</th>
					<th>客户姓名</th>
					<th>性别</th>
					<th>电话</th>
					<th>消费金额 </th>
					<th>操作柜员</th>
					<th width="300px">备注</th>
				</tr>
				<c:forEach items="${cashCusSinConList}" var="cashCusSinConList">
					<tr>
						<td>${cashCusSinConList.consumDt}</td>
						<td>${cashCusSinConList.customerName}</td>
						<td>
							<c:choose>
								<c:when test="${cashCusSinConList.gender=='M' }">男</c:when>
								<c:when test="${cashCusSinConList.gender=='F' }">女</c:when>
							</c:choose>
						</td>
						<td>${cashCusSinConList.phoneNum}</td>
						<td>${cashCusSinConList.amount}</td>
						<td>${cashCusSinConList.teller}</td>
						<td>${cashCusSinConList.remark }</td>
					</tr>
				</c:forEach>
			</table>
		</div>
			
		<div id="cus_sin_con">
			<table>
				<tr>
					<th colspan="6">客户单日消费明细表</th>
					<th>
						<a href="${pageContext.request.contextPath }/back_to_adminOperation.do">返回</a>
					</th>
				</tr>
				<tr>
					<th>消费日期</th>
					<th>客户姓名</th>
					<th>客户电话</th>
					<th>消费人员</th>
					<th>消费次数</th>
					<th>操作柜员</th>
					<th>卡性质</th>
				</tr>
				<c:forEach items="${conSinList}" var="consumeList">
					<tr>
						<td>${consumeList.consumDT }</td>
						<td>${consumeList.carder }</td>
						<td>${consumeList.phone }</td>
						<td>${consumeList.customer }</td>
						<td>${consumeList.consumeTimes }</td>
						<td>${consumeList.teller }</td>
						<td>
							<c:choose>
									<c:when test="${consumeList.cardType=='1'}">次卡</c:when>
									<c:when test="${consumeList.cardType=='2'}">月卡</c:when>
									<c:when test="${consumeList.cardType=='3'}">年卡</c:when>
								</c:choose>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	
		<div id="cus_sin_deposit">
			<table>
				<tr>
					<th colspan="10">客户单日储值明细表</th>
					<th>
						<a href="${pageContext.request.contextPath }/back_to_adminOperation.do">返回</a>
					</th>
				</tr>
				<tr>
					<th>储值日期</th>
					<th>客户姓名</th>
					<th>客户电话</th>
					<th>储值金额</th>
					<th>卡类型</th>
					<th>新增次数</th>
					<th>优惠次数</th>
					<th>优惠时间</th>
					<th>(储值)操作柜员</th>
					<th>年/月卡起始日期</th>
					<th>年/月卡截止日期</th>
				</tr>
				<c:forEach items="${depositSinList}" var="depositList">
					<tr>
						<td>${depositList.depositDate}</td>
						<td>${depositList.customer}</td>
						<td>${depositList.phone}</td>
						<td>${depositList.amount}</td>
						<td>
							<c:choose>
								<c:when test="${depositList.cardType=='1'}">次卡</c:when>
								<c:when test="${depositList.cardType=='2'}">月卡</c:when>
								<c:when test="${depositList.cardType=='3'}">年卡</c:when>
							</c:choose>
						</td>
						<td>${depositList.addTimes }</td>
						<td>${depositList.discountTimes }</td>
						<td>${depositList.discountTime }</td>
						<td>${depositList.teller }</td>
						<td>${depositList.startDt }</td>
						<td>${depositList.endDt}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</body>
</html>