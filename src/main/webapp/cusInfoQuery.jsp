<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>客户信息显示</title>	
	<link rel="stylesheet" type="text/css" href="cusInfoQuery.css" /> 
	
	<style type="text/css">
		
		#cus_consume,#cus_deposit {
			border: 1px solid #ccc;
			width: 400px;
			margin: auto;
		}
		#cus_deposit {
			border: 1px solid #ccc;
			width: 900px;
			margin: auto;
		}
		form p {
 		margin: 20 auto;
 		padding: 20px;
 		border: 1px solid #ccc;
	 	}
	 	form div {
	 		text-align: center;
	 		margin: 0;
	 		padding: 10px;
	 		border: 1px solid #ccc;
	 	}
 	
 		#cus_consume_sta,#myform ,#btn_table{
/*  			border-collapse:collapse; */
 			margin:5px auto;
 			width: 800px;
 			border:1px solid #ccc;
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
		
		td {
			text-align: center;	
		}
		
		.remind {
			font-size: 15px;
			color: red;
/* 			border:1px solid red; */
		}
	</style>
	<!-- 引入日历组件 -->
	<script type="text/javascript" src="calendar/WdatePicker.js"></script>
	
	<script type="text/javascript">
		function each_card() {
			document.getElementById("eachCard").style.display = "block";
			document.getElementById("yearMonthCard").style.display = "none";
		}
		
		function month_card() {
			document.getElementById("eachCard").style.display = "none";
			document.getElementById("yearMonthCard").style.display = "block";
		}
		function year_card() {
			document.getElementById("eachCard").style.display = "none";
			document.getElementById("yearMonthCard").style.display = "block";
		}
		
 		//页面加载时执行此方法 
		window.onload = function() {
			document.getElementById("cus_consume").style.display = "none";
			document.getElementById("cus_deposit").style.display = "none";
			document.getElementById("eachCard").style.display = "none";
			document.getElementById("yearMonthCard").style.display = "none";
			
			var flag = "${flag}";
			
			if(flag=="saveSuc") {
				document.getElementById("deposit_btn").disabled = false;
				alert("保存成功");
			}else if(flag=="saveFail") {
				document.getElementById("deposit_btn").disabled = false;
				alert("保存失败");
			}else if(flag=="consume_fail") { 
				document.getElementById("btn_con").disabled = false;
				alert("消费失败");
			}else if(flag=="consume_suc") {
				document.getElementById("btn_con").disabled = false;
				alert("消费成功");
			}else if(flag=="query_fail") {
				document.getElementsByName("query_con_dep").disabled = false;
				alert("查询结果为空!");
			}else if(flag=="date_exist"){
				var conDt = "${conDt}";
				document.getElementById("btn_con").disabled = false;
				alert("此卡 "+conDt+" 已有过消费记录，不需要重复记录");
			}else if(flag=="card_exist") {
				
				alert("该卡号已存在");
			}else if(flag=="phone_exist") {
				
				alert("该手机号已存在");
			}
			
			var cardType = "${cardType}";
			if(cardType=="2"||cardType=="3") {
				//消费次数不需要输入
				document.getElementById("consume_times").disabled = true;
			}
			
			//柜员显示
			var dis = session.getAttribute("user");
			alert(dis);
			
		};
		
		function consume() {
			document.getElementById("cus_consume").style.display = "block";
			document.getElementById("cus_deposit").style.display = "none";
		}
		
		function deposit() {
			document.getElementById("cus_consume").style.display = "none";
			document.getElementById("cus_deposit").style.display = "block";
		}
		
		function cusinfo_modify() {
			document.getElementById("cus_phoneNO").disabled = false;
			document.getElementById("card_num").disabled = false;
			document.getElementById("save").disabled = false;
			
			
		}
		
		function cus_save() {
			document.getElementById("cus_phoneNO").removeAttribute("disabled");
			document.getElementById("card_num").removeAttribute("disabled");
			
			/*****注：如下，disabled 取消灰显但不能向后台传值，需要上面做法才可以******/
// 			document.getElementById("cus_phoneNO").disabled = true;
// 			document.getElementById("card_num").disabled = true;
		}
		function pls_select() {
			document.getElementById("eachCard").style.display = "none";
			document.getElementById("yearMonthCard").style.display = "none";
		}
		
// 		function get_add_times() {
// 			var addTimes = document.getElementById("addTimes").value;
// 			var disTimes = document.getElementById("disTimes").value;
// 			var totalTimes = document.getElementById("totalTimes");
// 			var count = Number(addTimes) + Number(disTimes);
// 			if(isNaN(count)) {
// 				totalTimes.value = "请正确输入数字";
// 			}else {
// 				totalTimes.value = count;
// 			}
// 		}
// 		function get_dis_times() {
// 			get_add_times();
// 		}

		function get_add_times() {
			var addTimes = document.getElementById("addTimes").value;
			var disTimes = document.getElementById("disTimes").value;
			if(isNaN(addTimes)) {
				totalTimes.value = "";
				document.getElementById("addTimes").value = "";
			}else if(isNaN(disTimes)) {
				totalTimes.value = "";
				document.getElementById("disTimes").value = "";
			}else {
				document.getElementById("totalTimes").value = Number(addTimes) + Number(disTimes);
			}
		}
		
// 		function startDate() {
// 			var start_date = document.getElementById("start_date").value;
// 			var st = new Date(start_date.substring(0,4)+"/"+start_date.substring(4,6)+"/"+start_date.substring(6,8));
// 			var end_date = document.getElementById("end_date").value;
// 			var ed = new Date(end_date.substring(0,4)+"/"+end_date.substring(4,6)+"/"+end_date.substring(6,8));
// 			var dis_date = document.getElementById("dis_date").value;
// 			var num = (ed.getTime()-st.getTime())/(1000*3600*24) + Number(dis_date);
// 			var total_date = document.getElementById("total_date");
// 			if(isNaN(num)) {
// 				total_date.value = "请正确输入日期";
// 			}else if(num<0) {
// 				total_date.value = "起始日期不能大于截止日期";
// 			}else {
// 				total_date.value = num;
// 			}
// 		}
		
// 		function endDate() {
// 			startDate();
// 		}
// 		function disDate() {
// 			startDate();
// 		}

		//客户消费 提交时，进行判断 
		function consume_submit() {
			//取卡性持
			//如果是年/月卡 获取当天是否已消费的标志
			var isConsume = "${flag}";
			var card_type = "${cardType}";
			var consume_times = document.getElementById("consume_times").value;
			if(card_type=="1"&&(consume_times==""||consume_times=="")) {
				return confirm("消费次数:默认为'1'次,确定消费吗？");
			}else if(card_type=="2"||card_type=="3"){
				return confirm("此卡今天每天消费只记录一次,确定消费吗？");
			}else if(card_type=="1"&&isConsume=="date_exist") {
				return confirm("此卡已经有过消费记录，确定要再次消费吗？");
			}
		}
		
		//客户储值提交时，进行验证
		function check_deposit() {
			var card_type = document.getElementById("card_type").value;
			var deposit_amt = document.getElementById("deposit_amt").value
			if(deposit_amt==""||deposit_amt==null||card_type==""||card_type==null) {
				alert("金额或卡性质不能为空");
				document.getElementById("deposit_btn").disabled = false;
				return false;
			}
			if(valiDate()) {
				document.getElementById("deposit_btn").disabled = false;
				return false;
			}
			return true;
		}
		function valiDate() {
			//判断起始日期是否大于截止日期
			var start_date = document.getElementById("start_date").value;
			var st = new Date(start_date.substring(0,4)+"/"+start_date.substring(5,7)+"/"+start_date.substring(8,10));
			var end_date = document.getElementById("end_date").value;
 			var ed = new Date(end_date.substring(0,4) + "/" + end_date.substring(5,7) + "/" + end_date.substring(8,10));
 			var num = ed.getTime()-st.getTime();
 			
 			var mySelect = document.getElementById("card_type");
			var index = mySelect.selectedIndex;
			var myValue = mySelect.options[index].value;
 			
 			if((myValue=="2")&&(start_date==""||start_date==null||end_date==""||end_date==null)) {
				alert("起止日期不能为空");
				return true;
			}
			if(num<0) {
				alert("起始日期不能大于截止日期");
				return true;
			}
			return false;
		}
		
		function modify() { 
			var phoneNo = document.getElementById("cus_phoneNO").value ;
			var cardNo = document.getElementById("card_num").value ;
			var phone = "${phoneNum}";
			var card = "${cardNo }"
			if(phoneNo==""||phoneNo==null) {
				alert("手机号不能为空");
				return false;
			}
			if(cardNo==""||cardNo==null) {
				alert("卡号不能为空");
				return false;
			}
			if(phoneNo==phone&&cardNo==card) {
				alert("您没有修改信息，不需要保存");
				return false;
			}
			//如果两个有一个修改信息了，将没有修改信息的那个的值置为空传给后台
			if(phoneNo==phone) {
				document.getElementById("cus_phoneNO").value = "";
			}
			if(cardNo==card) {
				document.getElementById("card_num").value = "";
			}
			return true;
		}
		
		function validate(num) {
			if(!isNaN(num)) {
				var formNum = null;
				if(num==""||num==null) {
					formNum = "0.00";					
				}
				else{ 
					var numStr = num.toString();
 					var index = numStr.indexOf(".");
 					
					if(index==-1) {
						formNum = numStr + ".00";
					}else if(index==numStr.length-2) {
						formNum = numStr + "0";
					}else if(index==numStr.length-3) {
						formNum = numStr;
					}else if(index==numStr.length-1) {
						formNum = numStr + "00";
					}else {
						formNum = numStr.substring(0,index+3);
					}
					if(formNum.indexOf(".")>5) {
 						alert("您输入的金额过大，请重新输入");
 						return "";
 					}
				}
				return formNum;
			}
				alert("请输入数字");
				return "";
		}
		
		function number_validate(value) {
			if(isNaN(value)) {
				return "";	
			}
			return value;
		}
		
	</script>
</head>
<body>

		<p id="quit">
			客户：<span>${customerName}</span>
			<a href="${pageContext.request.contextPath }/customerLogout.do">【退出】</a>
		</p>
		
		<form action="cusInfo_save.do" method="post" id="myform" onsubmit="return modify();">		
			<table border="1px">
				<tr>
					<th colspan="5">客户基本信息表</th>
					<th><input type="button" value="修改" onclick="cusinfo_modify()">
						<input type="submit" value="保存" id="save" onclick="cus_save()" disabled>
					</th>
				</tr>
				<tr>
					<td width="115" align="center">姓名</td>
					<td width="115" >${name }</td>
					<td width="115">手机号</td>
					<td><input type="text"  id="cus_phoneNO" maxlength="11" value="${phoneNum}"  name="phoneNo" disabled onkeyup="this.value=number_validate(this.value)" onafterpaste="this.value=number_validate(this.value)" onblur="this.value=number_validate(this.value)"></td>
					<td width="115">注册日期</td>
					<td width="150">${registDt }</td>
				</tr>
				<tr>
					<td>性别</td>
					<td>
						<c:choose>
							<c:when test="${gender=='M'}">男</c:when>
							<c:when test="${gender=='F'}">女</c:when>
						</c:choose>
					</td>
					<td>卡号</td>
					<td><input type="text" value="${cardNo }" id="card_num" name="cardNo" disabled onkeyup="this.value=number_validate(this.value)" onafterpaste="this.value=number_validate(this.value)" onblur="this.value=number_validate(this.value)"></td>
					<td>注册柜员</td>
					<td>${teller }</td>
				</tr>
				<tr>
<!-- 					<td>卡号</td> -->
<%-- 					<td><input type="text" value="${cardNo }" id="card_num" name="cardNo" disabled onkeyup="this.value=number_validate(this.value)" onafterpaste="this.value=number_validate(this.value)" onblur="this.value=number_validate(this.value)"></td> --%>
<!-- 					<td></td> -->
<!-- 					<td></td> -->
					<td>出生日期</td>
					<td>${birthday}</td>
				</tr>
			</table>
		</form>
		
		<table border="1px solid #ccc" id="cus_consume_sta" >
			<tr>
				<th colspan="5">客户消费情况统计</th>
			</tr>
			<tr>
				<th>卡类型</th>
				<th>(年/月卡) 截止日期</th>
				<th>(次卡) 总剩余次数</th>
			</tr>
			<tr>
				<td height="25">
					<c:choose>
						<c:when test="${cardType=='1'}">次卡</c:when>
						<c:when test="${cardType=='2'}">月卡</c:when>
						<c:when test="${cardType=='3'}">年卡</c:when>
					</c:choose>
				</td>
				<td>${lastDt } </td>
				<td>${lastTimes }  </td>
			</tr>
		</table>
	
		<table id="btn_table">
			<tr>
				<th><input type="button" value="客户消费" onclick="consume()"></th>
				<th><input type="button" value="客户储值" onclick="deposit()"></th>
				<th>
					<form action="queryConsumeDetail.do" method="post">
						<input type="submit" value="消费明细查询" name="query_con_dep" onclick="javascript:{this.disabled=true}"/>
					</form>
				</th>
				<th>
					<form action="queryDeposit.do" method="post">
						<input type="submit" value="储值明细查询" name="query_con_dep" onclick="javascript:{this.disabled=true}">
					</form>
				</th>
<!-- 				<th> -->
<!-- 					<form action="queryCusExp.do" method="post"> -->
<!-- 						<input type="submit" value="客户交易明细查询"> -->
<!-- 					</form> -->
<!-- 				</th> -->
			</tr>
		</table>
		
		<form action="consumSubmit.do" method="post" id="cus_consume" onsubmit="return consume_submit();">
<!-- 			<p>消费日期 <input type="text" name="date" placeholder="请输入" readOnly -->
<!-- 				onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});" class="Wdate" style="width:190px"/> -->
			<p>消费人员 <input type="text" name="customer" placeholder="请输入" maxlength="20"></p>
			<p>消费次数 <input type="text" id="consume_times" name="times" placeholder="年/月卡 不需要输入" onkeyup="this.value=number_validate(this.value)" onafterpaste="this.value=number_validate(this.value)" onblur="this.value=number_validate(this.value)"></p>
			<p>
				备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：
				<textarea rows="2" cols="30" name="consume_remark" maxlength="50" placeholder="请输入备注信息" ></textarea>
			</p>
			<div>
				<input type="submit" id="btn_con" value="提交" onclick="javascript:{this.disabled=true}">
			</div>
		</form>

		<form action="deposit.do" method="post" id="cus_deposit" onsubmit="return check_deposit();">
			<p>
<!-- 				储值日期：<input type="text" name="depositDt" placeholder="默认保存当前日期"  -->
<!-- 				onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});" class="Wdate" style="width:190px"> -->
<!-- 				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; -->
				<span class="remind">*</span>
				储值金额：<input type="text" id="deposit_amt" name="depositAMT" maxlength="7" 
							onblur="this.value=validate(this.value)" placeholder="请输入">
				
			</p>
			&nbsp;&nbsp;&nbsp;&nbsp;<span class="remind">*</span>			
			<span>卡&nbsp;性&nbsp;质：</span>&nbsp;
			<select id="card_type" name="cardType">
				<option value="" onclick="pls_select()">-请选择-</option>
				<option value="1" onclick="each_card()">次卡</option>
				<option value="2" onclick="month_card()">月卡</option>
<!-- 				<option value="3" onclick="year_card()">年卡</option> -->
			</select>
			
			<p id="eachCard">
				新增次数：<input type="text" id="addTimes" maxlength="4" name="addTimes" onkeyup="this.value=number_validate(this.value)" onafterpaste="this.value=number_validate(this.value)" onblur="get_add_times()">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				优惠次数：<input type="text" id="disTimes" maxlength="4" name="discountTimes" onkeyup="this.value=number_validate(this.value)" onafterpaste="this.value=number_validate(this.value)" onblur="get_add_times()">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				总使用次数：<input id="totalTimes" name="totalCount" readonly>
			</p>
			<p id="yearMonthCard">
				起始日期：<input type="text" name="startDt"  id="start_date" readOnly
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});" class="Wdate" style="width:190px">
				&nbsp;&nbsp;
				截止日期：<input type="text" name="endDt" id="end_date" readOnly
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});" class="Wdate" style="width:190px">
				&nbsp;&nbsp;
				优惠时间：<input type="text" name="discountTime" placeholder="单位:天" id="dis_date" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')">
			</p>
			
			<p>
				备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：
				<textarea rows="2" cols="80" name="remark" placeholder="请输入备注信息" maxlength="50"></textarea>
			</p>
			<div>
				<input type="submit" value="提交" id="deposit_btn" onclick="javascript:{this.disabled=true}">
			</div>
		</form>
</body>
</html>