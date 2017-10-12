<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
   <%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>客户交易明细查询</title>
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
				document.getElementById("deposit_detail").style.display = "none";
				document.getElementById("consumeDetail").style.display = "none";
// 				document.getElementById("cus_exp_detail").style.display = "none";
				
				var flag = "${flag}"
				if(flag=="1") {
					document.getElementById("deposit_detail").style.display = "block";
				}else if (flag=="2") {
					document.getElementById("consumeDetail").style.display = "block";
				}
// 				else if(flag=="3") {
// 					document.getElementById("cus_exp_detail").style.display = "block";
// 				}
				else if(flag=="2-2") {
					alert("客户消费信息查询失败");
				}
					
			}
		</script>
	</head>
	<body>
		<p id="quit">
			客户：${customerName }
			<a href="${pageContext.request.contextPath }/cus_back.do">【返回】</a>
			<a href="${pageContext.request.contextPath }/customerLogout.do">【退出】</a>
		</p>
		<div id="deposit_detail">
			<table>
				<tr>
					<th colspan="12">储值明细表</th>
				</tr>
				<tr>
					<th>储值日期</th>
					<th>储值金额</th>
					<th>卡类型</th>
					<th>新增次数</th>
					<th>优惠次数</th>
					<th>优惠时间</th>
					<th>储值总次数</th>
					<th>操作柜员</th>
					<th>年/月卡起始日期</th>
					<th>年/月卡截止日期</th>
					<th width="300px">备注</th>
				</tr>
				<c:forEach items="${depositInfo}" var="depositInfo">
					<tr>
						<td>${depositInfo.depositDate}</td>
						<td>${depositInfo.amount}</td>
						<td>
							<c:choose>
								<c:when test="${depositInfo.cardType=='1'}">次卡</c:when>
								<c:when test="${depositInfo.cardType=='2'}">月卡</c:when>
								<c:when test="${depositInfo.cardType=='3'}">年卡</c:when>
							</c:choose>
						</td>
						<td>${depositInfo.addTimes}</td>
						<td>${depositInfo.discountTimes}</td>
						<td>${depositInfo.discountTime}</td>
						<td>${depositInfo.totalCount }</td>
						<td>${depositInfo.teller}</td>
						<td>${depositInfo.startDt}</td>
						<td>${depositInfo.endDt}</td>
						<td>${depositInfo.remark}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
			
		<div id="consumeDetail">
			<table>
				<tr>
					<th colspan="6">消费明细表</th>
				</tr>
				<tr>
					<th>消费日期</th>
					<th>消费次数</th>
					<th>消费人员</th>
					<th>操作柜员</th>
					<th>卡性质</th>
					<th width="400px">备注</th>
				</tr>
				<c:forEach items="${queryConsumeDetail}" var="consumeList">
					<tr>
						<td>${consumeList.consumDT }</td>
						<td>${consumeList.consumeTimes }</td>
						<td>${consumeList.customer }</td>
						<td>${consumeList.teller }</td>
						<td>
							<c:choose>
									<c:when test="${consumeList.cardType=='1'}">次卡</c:when>
									<c:when test="${consumeList.cardType=='2'}">月卡</c:when>
									<c:when test="${consumeList.cardType=='3'}">年卡</c:when>
								</c:choose>
						</td>
						<td>${consumeList.remark }</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	
<!-- 		<div id="cus_exp_detail"> -->
<!-- 			<table> -->
<!-- 				<tr> -->
<!-- 					<th colspan="14">客户交易明细表</th> -->
<!-- 				</tr> -->
<!-- 				<tr> -->
<!-- 					<th>储值日期</th> -->
<!-- 					<th>储值金额</th> -->
<!-- 					<th>(储值)卡类型</th> -->
<!-- 					<th>新增次数</th> -->
<!-- 					<th>优惠次数</th> -->
<!-- 					<th>优惠时间</th> -->
<!-- 					<th>(储值)操作柜员</th> -->
<!-- 					<th>年/月卡起始日期</th> -->
<!-- 					<th>年/月卡截止日期</th> -->
					
<!-- 					<th>消费日期</th> -->
<!-- 					<th>消费人员</th> -->
<!-- 					<th>消费次数</th> -->
<!-- 					<th>(消费)操作柜员</th> -->
<!-- 					<th>(消费)卡性质</th> -->
<!-- 				</tr> -->
<%-- 				<c:forEach items="${queryCusExpMap}" var="queryCusExpMap"> --%>
<!-- 					<tr> -->
<%-- 						<td>${queryCusExpMap.DEPOSITDATE}</td> --%>
<%-- 						<td>${queryCusExpMap.AMOUNT}</td> --%>
<!-- 						<td> -->
<%-- 							<c:choose> --%>
<%-- 								<c:when test="${queryCusExpMap.DEPOSITCARDTYPE=='1'}">次卡</c:when> --%>
<%-- 								<c:when test="${queryCusExpMap.DEPOSITCARDTYPE=='2'}">月卡</c:when> --%>
<%-- 								<c:when test="${queryCusExpMap.DEPOSITCARDTYPE=='3'}">年卡</c:when> --%>
<%-- 							</c:choose> --%>
<!-- 						</td> -->
<%-- 						<td>${queryCusExpMap.ADDTIMES }</td> --%>
<%-- 						<td>${queryCusExpMap.DISCOUNTTIMES }</td> --%>
<%-- 						<td>${queryCusExpMap.DISCOUNTTIME }</td> --%>
<%-- 						<td>${queryCusExpMap.DEPOSITTELLER }</td> --%>
<%-- 						<td>${queryCusExpMap.STARTDT }</td> --%>
<%-- 						<td>${queryCusExpMap.ENDDT}</td> --%>
						
<%-- 						<td>${queryCusExpMap.CONSUMDT}</td> --%>
<%-- 						<td>${queryCusExpMap.CUSTOMER}</td> --%>
<%-- 						<td>${queryCusExpMap.CONSUMETIMES}</td> --%>
<%-- 						<td>${queryCusExpMap.TELLER}</td> --%>
<!-- 						<td> -->
<%-- 							<c:choose> --%>
<%-- 								<c:when test="${queryCusExpMap.CARDTYPE=='1'}">次卡</c:when> --%>
<%-- 								<c:when test="${queryCusExpMap.CARDTYPE=='2'}">月卡</c:when> --%>
<%-- 								<c:when test="${queryCusExpMap.CARDTYPE=='3'}">年卡</c:when> --%>
<%-- 							</c:choose> --%>
<!-- 						</td> -->
<!-- 					</tr> -->
<%-- 				</c:forEach> --%>
<!-- 			</table> -->
<!-- 		</div> -->
	</body>
</html>