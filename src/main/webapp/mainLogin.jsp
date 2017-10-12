<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="logout.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>前台</title>
	<style type="text/css">
	
	 	form,div h2 {
	 		text-align: center;
	 		margin:0;
	 		padding: 5px;
	 		border: 1px solid #ccc;
	 	}
	 
		#phone,#card {
			text-align: center; 
 	 		margin: 0; 
 	 		padding: 20px; 
 	 		border: 1px solid #ccc; 
		}
		
		div p {
			margin: 0; 
 	 		padding: 20px; 
 	 		border: 1px solid #ccc;
		}
	 	#log_reg {
	 		text-align: center;
	 		margin: 0;
	 		padding: 10px;
	 		border: 1px solid #ccc;
	 	}
	 	
	 	.error_msg {
	 		border: 1px solid red;
	 		color: red;
	 	}
	 	
	 	#customer_login_regist,#admin,#adminId_1,#cash_customer{
	 		width:550px; 
 			border:1px solid #ccc; 
 			margin:100px auto;
	 	}
	 	
	 	#customerRegist {
	 		text-align: left;
	 		width:900px; 
 			border:1px solid #ccc; 
 			margin:70px auto;
	 	}
	 	
	 	#save_back,#admin{
	 		text-align: center;
	 	}
		
		.remind {
			font-size: 15px;
			color: red;
		}
	</style>
	
	<!-- 引入日期 js -->
	<script type="text/javascript" src="calendar/WdatePicker.js"></script>
	
	<script type="text/javascript">
		
		//页面加载时执行方法 
		window.onload = function() {
			document.getElementById("adminId_1").style.display = "none";
			document.getElementById("admin").style.display = "none";
			document.getElementById("eachCard").style.display = "none";
			document.getElementById("yearOrMonth").style.display = "none";
			document.getElementById("query_date").style.display = "none";
			document.getElementById("cash_customer").style.display = "none";
			document.getElementById("admin_operation").style.visibility = "hidden";
			
			//取标识
			var flag = "${flag}";
			if(flag=="N") {
				alert("该客户不存在");
				document.getElementById("commit").disabled = false;
			}else if(flag=="save_suc") {
				document.getElementById("customer_login_regist").style.display = "none";
				document.getElementById("customerRegist").style.display = "block";
				document.getElementById("cus_reg_save").disabled = false;
				alert("客户注册成功");
			}else if(flag=="save_fail") {
				document.getElementById("customer_login_regist").style.display = "none";
				document.getElementById("customerRegist").style.display = "block";
				document.getElementById("cus_reg_save").disabled = false;
				alert("客户注册失败");
			}else if(flag=="user_save_suc") {
				document.getElementById("customer_login_regist").style.display = "none";
				document.getElementById("adminId_1").style.display = "block";
				document.getElementById("user_reg_save").disabled = false;
				alert("柜员注册成功");
			}else if(flag=="exist") {
				document.getElementById("customer_login_regist").style.display = "none";
				document.getElementById("adminId_1").style.display = "block";
				document.getElementById("user_reg_save").disabled = false;
				alert("该用户已存在，请重新输入");
			}else if(flag=="phone_exist") {
				document.getElementById("customer_login_regist").style.display = "none";
				document.getElementById("customerRegist").style.display = "block";
				document.getElementById("cus_reg_save").disabled = false;
				alert("该手机号已存在，请重新输入");
			}else if(flag=="card_exist") {
				document.getElementById("customer_login_regist").style.display = "none";
				document.getElementById("customerRegist").style.display = "block";
				document.getElementById("cus_reg_save").disabled = false;
				alert("该卡号已存在，请重新输入");
			}
			else if(flag=="backToAdminOperation") {
				document.getElementById("customer_login_regist").style.display = "none";
				document.getElementById("admin").style.display = "block";
			}else if(flag=="cashCusSuc") {
				cashCustomer();
				document.getElementById("cashCus_con_rec").disabled = false;				
				alert("提交成功");
			}else if(flag=="queryCashCus"||flag=="querySingleCon"||flag=="querySingleDeposit") {
				document.getElementById("customer_login_regist").style.display = "none";
				document.getElementById("admin").style.display = "block";
				document.getElementsByName("exp_conditin").disabled = false;
				alert("没有查到相关记录");
			}else if(flag=="cus_empty") {
				document.getElementById("customer_login_regist").style.display = "none";
				document.getElementById("admin").style.display = "block";
				alert("没有查到客户记录");
			}else if(flag=="user_empty") {
				document.getElementById("customer_login_regist").style.display = "none";
				document.getElementById("admin").style.display = "block";
				alert("没有查到柜员信息");
			}
			
			//判断用户是管理员还是普通柜员
			var user_level = "${userLevel}"
			if(user_level=="0") {
				document.getElementById("admin_operation").style.visibility = "visible";
			}
		};
		
		function regist() {
			document.getElementById("customer_login_regist").style.display = "none";
			document.getElementById("customerRegist").style.display = "block";
		}
		
		function back() {
			document.getElementById("customerRegist").style.display = "none";
			document.getElementById("customer_login_regist").style.display = "block";
			
			//返回时清空数据	
			document.getElementById("start_date").value = "";
			document.getElementById("end_date").value = "";
			document.getElementById("dis_date").value = "";
			document.getElementById("addTimes").value = "";
			document.getElementById("disTimes").value = "";
			document.getElementById("totalTimes").value = "";
			document.getElementById("name").value = "";
			document.getElementById("birDay").value = "";
			document.getElementById("ph_no").value = "";
			document.getElementById("card_num").value = "";
			document.getElementById("de_amt").value = "";
			document.getElementById("re_mark").value = ""; 
		}
		
		function each_card() {
			
			document.getElementById("eachCard").style.display = "block";
			document.getElementById("yearOrMonth").style.display = "none";
		}
		
		function month_card() {
			document.getElementById("eachCard").style.display = "none";
			document.getElementById("yearOrMonth").style.display = "block";
		}
// 		function year_card() {
// 			month_card();
// 		}
		
		function business_query() {
			document.getElementById("query_date").style.display = "block";
		}
		
		function user_regist() {
			document.getElementById("admin").style.display = "none";
			document.getElementById("adminId_1").style.display = "block";
		}
		
		function admin_ope() {
			document.getElementById("customer_login_regist").style.display = "none";
			document.getElementById("admin").style.display = "block";
		}
		
		function return_mainLogin() {
			document.getElementById("customer_login_regist").style.display = "block";
			document.getElementById("admin").style.display = "none";
			document.getElementById("query_date").style.display = "none";

			//返回时清空数据
			document.getElementById("query_business_date").value = "";
		}
		
		function user_reg_back() {
			document.getElementById("adminId_1").style.display = "none";
			document.getElementById("admin").style.display = "block";
			
			//返回时清空数据
			document.getElementById("user_name").value = "";
			document.getElementById("password").value = "";
			document.getElementById("phone_num").value = "";
		}
		
		function check_phone_card() {
			var phone = document.getElementById("phoneNum").value;
			phone = phone.replace(/\s/gi,'');
			var card = document.getElementById("cardNo").value;
			card = card.replace(/\s/gi,'');
			if((phone==""||phone==null)&&(card==""||card==null)) {
				alert("请输入手机号或卡号");
				document.getElementById("commit").disabled = false;
				return false;
			}
			return true;
		}
		
		function pls_select() {
			document.getElementById("eachCard").style.display = "none";
			document.getElementById("yearOrMonth").style.display = "none";
		}
		
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
// 			var st = new Date(start_date.substring(0,4)+"/"+start_date.substring(4,6) + "/" + start_date.substring(6,8)); 
// 			var end_date = document.getElementById("end_date").value;
// 			var ed = new Date(end_date.substring(0,4) + "/" + end_date.substring(4,6) + "/" + end_date.substring(6,8));			
// 			var dis_date = document.getElementById("dis_date").value;
// 			var num = (ed.getTime()-st.getTime())/(1000*3600*24) + Number(dis_date);
// 			var total_date = document.getElementById("total_date");
// 			if(isNaN(num)) {
// 				total_date.value = "请正确输入日期";
// 			}else if(num<0){
// 				total_date.value = "起始日期不能大于截止日期";
// 			}else {
//  				total_date.value = num;
// 			}
// 		}
		//判断起始日期是否大于截止日期
		function valiDate() {
			var start_date = document.getElementById("start_date").value;
			var st = new Date(start_date.substring(0,4)+"/"+start_date.substring(5,7)+"/"+start_date.substring(8,10));
			var end_date = document.getElementById("end_date").value;
 			var ed = new Date(end_date.substring(0,4) + "/" + end_date.substring(5,7) + "/" + end_date.substring(8,10));
 			var num = ed.getTime()-st.getTime(); 
			if(num<0) {
				document.getElementById("cus_reg_save").disabled = false;
				alert("起始日期不能大于截止日期");
				return true;
			}
			return false;
		}
		
		function cus_reg() {
			
			var cus_reg_phone = document.getElementById("ph_no").value;
			var cus_reg_card = document.getElementById("card_num").value;
			//获取选中项的值
			var mySelect = document.getElementById("card_type");
			var index = mySelect.selectedIndex;
			var myValue = mySelect.options[index].value;
			var start_date = document.getElementById("start_date").value;
			var end_date = document.getElementById("end_date").value;
			var name = document.getElementById("name").value;
			var card_type = document.getElementById("card_type").value;
			
			if((cus_reg_phone==""||cus_reg_phone==null)&&(cus_reg_card==""||cus_reg_card==null)) {
				document.getElementById("cus_reg_save").disabled = false;
				alert("请输入手机号或卡号");
				return false;
			}
			
			if(myValue=="2") {
				if(start_date==""||start_date==null||end_date==""||end_date==null) {
					document.getElementById("cus_reg_save").disabled = false;
					alert("起止日期不能为空");
					return false;
				}else if(valiDate()) {
					document.getElementById("cus_reg_save").disabled = false;
					return false;
				}
			}
			
			if(name==""||name==null) {
				document.getElementById("cus_reg_save").disabled = false;
				alert("请输入客户姓名");
				return false;
			}
			if(card_type==""||card_type==null) {
				document.getElementById("cus_reg_save").disabled = false;
				alert("卡性质不能为空");
				return false;
			}
			
			return true;
		};
		
		function user_reg() {
			var user_name = document.getElementById("user_name").value;
			var password = document.getElementById("password").value;
			var userLevel = document.getElementsByName("userLevel");
			
			for(var i=0;i<userLevel.length;i++) {
				if(userLevel[i].checked) {
					var user_level = userLevel[i].value
				}	
			};
			
			if(user_name==""||user_name==null||password==""||password==null||user_level==""||user_level==null) {
				alert("请输入必输项");
				document.getElementById("user_reg_save").disabled = false;
				return false;
			}
			return true;
		}
		
		function cashCustomer() {
			document.getElementById("customer_login_regist").style.display = "none";
			document.getElementById("cash_customer").style.display = "block";
		}
		
		function cashCusBack() {
			document.getElementById("cash_customer").style.display = "none";
			document.getElementById("customer_login_regist").style.display = "block";
			
			//返回时，清空数据
			document.getElementById("cash_cus_name").value = "";
			document.getElementById("cash_cus_phone").value = "";
			document.getElementById("cash_cus_amount").value = "";
			document.getElementById("cash_cus_remark").value = "";
		}
		
		function cashCusSubmit() {
			var cashCusValue = document.getElementById("cash_cus_amount").value;
			if(cashCusValue==null||cashCusValue=="") {
				alert("金额不能为空");
				document.getElementById("cashCus_con_rec").disabled = false;	
				return false;
			}
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
		
		function day_bus() {
			document.getElementsByName("exp_conditin").disabled = true;
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
	<form action="cusInfoQuery.do" method="post" id="customer_login_regist" onsubmit="return check_phone_card()">
		<h2>客户登陆</h2>
		<p id="phone"> 
			手机号：<input type="text" id="phoneNum" maxlength="11" name="phoneNum"  
			  onkeyup="this.value=number_validate(this.value)" onafterpaste="this.value=number_validate(this.value)" onblur="this.value=number_validate(this.value)">
		</p>
		<p id="card">
			卡&nbsp; &nbsp;号：<input type="text" id="cardNo" maxlength="20" name="cardNum" 
			onkeyup="this.value=number_validate(this.value)" onafterpaste="this.value=number_validate(this.value)" onblur="this.value=number_validate(this.value)">
		</p>
		<div id="log_reg">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input  type="submit" value="登陆" id="commit" onclick="javascript:{this.disabled=true}" >
				&nbsp;&nbsp;
				<input  type="button" value="注册" onclick="regist()" >
				&nbsp;&nbsp;
				<input type="button" value="散客消费登记" onclick="cashCustomer()">
		</div>
		<p>
			<input  type="button" value="管理员操作" id="admin_operation" onclick="admin_ope()">			
		</p>
	</form>

	<form id="customerRegist" action="customerRegist.do" method="post" style="display:none;" onsubmit="return cus_reg()">
		<label>客户注册</label>
		<div id="regist" >
			<p>
				<span class="remind">*</span>
				姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：<input type="text" id="name" name="customerName" maxlength="20">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：<input type="radio" name="sex" value="M">男
					 <input type="radio" name ="sex" value="F">女
					 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				生&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日：
				<input type="text" id="birDay" name="cusBirthDay" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'});" readOnly class="Wdate" />
			</p>
			<p>
				<span class="remind">*</span>
				手&nbsp;机&nbsp;号：&nbsp;<input id="ph_no" type="text" name="phoneNum" maxlength="11" 
				onkeyup="this.value=number_validate(this.value)" onafterpaste="this.value=number_validate(this.value)" onblur="this.value=number_validate(this.value)">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<span class="remind">*</span>
				卡&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号：&nbsp;<input id="card_num" type="text" name="cardNO" maxlength="30" 
				onkeyup="this.value=number_validate(this.value)" onafterpaste="this.value=number_validate(this.value)" onblur="this.value=number_validate(this.value)"> 
 			</p>
			<p>
<!-- 				储值日期：<input type="text" id="de_date" name="depositDt" placeholder="默认保存当前日期"  -->
<!-- 	validate(this.value,8,2)	onkeypress="mustDigit()"					onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});" class="Wdate" style="width:190px"/> -->
<!-- 				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;          -->
				储值金额：<input type="text" id="de_amt" name="depositAMT" placeholder="请输入" maxlength="7" 
						  	onblur="this.value = validate(this.value)">
			</p>
			<span class="remind">*</span>
			<span>卡性质：</span>&nbsp;
			<select id="card_type" name="cardType">
				<option value="" onclick="pls_select()" >-请选择-</option>
				<option value="1"  onclick="each_card()">次卡</option>
				<option value="2" onclick="month_card()">月卡</option>
<!-- 				<option value="3" onclick="year_card()">年卡</option> -->
			</select>
						
			<p id="eachCard">
				新增次数：<input type="text" id="addTimes" name="addTimes" maxlength="4"  onkeyup="this.value=number_validate(this.value)" onafterpaste="this.value=number_validate(this.value)" onblur="get_add_times()">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				优惠次数：<input type="text" id="disTimes" name="discountTimes" maxlength="4"  onkeyup="this.value=number_validate(this.value)" onafterpaste="this.value=number_validate(this.value)" onblur="get_add_times()">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				总使用次数：<input id="totalTimes" name="totalCount" readonly>
			</p>
			<p id="yearOrMonth">
				起始日期：<input type="text" id="start_date"  name="startDt" readOnly
				onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});" class="Wdate" style="width:190px">
				&nbsp;&nbsp;&nbsp;
				截止日期：<input type="text" id="end_date" name="endDt"  readOnly
				onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});" class="Wdate" style="width:190px">
				&nbsp;&nbsp;&nbsp;
				优惠时间：<input type="text" id="dis_date" name="discountTime" placeholder="单位:天" 
					onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')">
				
			</p>
			<p>
				备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：
				<textarea rows="3" id="re_mark" cols="80" name="remark" maxlength="50" placeholder="请输入备注信息" ></textarea>
			</p>
			<div id="save_back">
 				<input type="submit" id="cus_reg_save" value="保存" onclick="javascript:{this.disabled=true}"> 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" value="返回" onclick="back()">
			</div>
		</div>
	</form>
		
	<form action="userRegist.do" method="post" id="adminId_1" onsubmit="return user_reg()">
		<label>柜员注册：</label>
		<div>
			<p>
				<span class="remind">*</span>
				用&nbsp;户&nbsp;名：<input id="user_name" type="text" name="userName" maxlength="20">
			</p>
			<p>
				<span class="remind">*</span>
				密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码：<input id="password" type="password" name="pwd">
			</p>
			<p>
				性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：<input type="radio" name="sex" value="M">男
					  <input type="radio" name="sex" value="F">女
			</p>
			<p>
				电&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;话：<input type="text" id="phone_num" name="phoneNum" maxlength="11" 
				onkeyup="this.value=number_validate(this.value)" onafterpaste="this.value=number_validate(this.value)" onblur="this.value=number_validate(this.value)">
			</p>
			<p>
				<span class="remind">*</span>
				柜员级别：<input type="radio" name="userLevel" value="0">管理员
						  <input type="radio" name="userLevel" value="1">柜员
			</p>
			<input type="submit" id="user_reg_save" value="保存" onclick="javascript:{this.disabled=true}">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="button" value="返回" onclick="user_reg_back()">
		</div>
	</form>
	
	<div id="admin">
		<h2>管理员权限</h2>
		<form>
			<input type="button" value="柜员注册"  onclick="user_regist()" >
		</form>
		<form action="allCusInfo.do" method="post">
			<input  type="submit" value="客户信息查询">
		</form>
		<form action="queryTellerList.do" method="post">
			<input  type="submit" value="柜员信息查询"  onclick="teller_query()" > 
		</form>
		<input  type="button" value="日营业情况查询"  onclick="business_query()" >
		<form id="query_date" action="queryDayBusiness.do" method="post" onsubmit="return day_bus();">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			请输入查询日期：<input type="text" id="query_business_date" name="queryDayBusiness" readOnly
						onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'});" class="Wdate" style="width:100px">
			<table style="margin:auto">
					<tr>
						<td>
							<input type="submit" value="散客消费情况查询"  name="exp_conditin" >
							&nbsp;&nbsp;
							<input type="submit" value="客户消费情况查询"  name="exp_conditin" >
							&nbsp;&nbsp;
							<input type="submit" value="客户储值情况查询"  name="exp_conditin" >
						</td>
					</tr>
			</table>
		</form>
		<form>
			<input type="button" value="返回" onclick="return_mainLogin()">
		</form>
	</div>
	
	<form action="cash_customer_action.do" method="post" id="cash_customer" onsubmit="return cashCusSubmit();">
		<h2 >散客消费记录</h2>
		<div>
			<p>
			    <label>姓&nbsp;&nbsp;&nbsp;&nbsp;名:&nbsp;&nbsp;</label>
				<input type="text" maxlength="20" id="cash_cus_name" name="cash_cus_name">
			</p>
			<p>
				<label>性&nbsp;&nbsp;&nbsp;&nbsp;别:&nbsp;&nbsp;</label>
				<input type="radio" name="sex" value="M">男
				<input type="radio" name ="sex" value="F">女
			</p>
			<p>
				<label>电&nbsp;&nbsp;&nbsp;&nbsp;话:&nbsp;&nbsp;</label>
				<input type="text" id="cash_cus_phone" maxlength="11" name="phone" 
				onkeyup="this.value=number_validate(this.value)" onafterpaste="this.value=number_validate(this.value)" onblur="this.value=number_validate(this.value)">
			</p >
			<p>
				<span class="remind">*</span>
				<label>金&nbsp;&nbsp;&nbsp;&nbsp;额:&nbsp;&nbsp;</label>
				<input type="text" id="cash_cus_amount" name="amount" maxlength="7" onblur="this.value = validate(this.value)">
			</p>
			<p>
				<label>备&nbsp;&nbsp;&nbsp;&nbsp;注:&nbsp;&nbsp;</label>
				<textarea rows="2" id="cash_cus_remark" cols="50" name="cash_cus_remark" maxlength="50" placeholder="请输入备注信息"></textarea>
			</p>
			<p>
				<input type="submit" value="提交" id="cashCus_con_rec" onclick="javascript:{this.disabled=true}"> 
				&nbsp;&nbsp;
				<input type="button" value="返回" onclick="cashCusBack()">
			</p>
		</div>
	</form> 
</body>
</html> 