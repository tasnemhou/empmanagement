package controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import entity.CusInfo;
import entity.DepositInfo;
import service.LogInService;
import util.UtilClassMethod;

public class CustomerRegistController implements Controller {

	private LogInService logInService;

	private UtilClassMethod util;
	
	public LogInService getLogInService() {
		return logInService;
	}

	public void setLogInService(LogInService logInService) {
		this.logInService = logInService;
	}

	public UtilClassMethod getUtil() {
		return util;
	}

	public void setUtil(UtilClassMethod util) {
		this.util = util;
	}

	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		req.setCharacterEncoding("utf-8");
		
		ModelAndView mav = new ModelAndView();
		
		Map<String,String> map = new HashMap<String, String>();
		CusInfo cusInfo = new CusInfo();
		
		//查询手机号是否已存在
		if(req.getParameter("phoneNum")!=null&&req.getParameter("phoneNum")!="") {
			map.put("phoneNum", req.getParameter("phoneNum"));
			cusInfo = logInService.queryCusInfo(map);
			if(cusInfo!=null) {
				mav.getModel().put("flag","phone_exist");
				mav.setViewName("mainLogin");
				return mav;
			}
		}
		//清空map，查卡号是否已存在
		if(req.getParameter("cardNO")!=null&&req.getParameter("cardNO")!="") {
			map.clear();
			map.put("cardNum", req.getParameter("cardNO"));
			cusInfo = logInService.queryCusInfo(map);
			if(cusInfo!=null) {
				mav.getModel().put("flag","card_exist");
				mav.setViewName("mainLogin");
				return mav;
			}
		}
		
		DepositInfo deposit = new DepositInfo();
		
		Map<String,Object> map1 = new HashMap<String, Object>();
		
		map1 = util.depositAndUpcustomerinfo(req);
		
		cusInfo = (CusInfo)map1.get("cusInfo");
		deposit = (DepositInfo)map1.get("deposit");
		
		cusInfo.setCustomerName(req.getParameter("customerName"));
		
		cusInfo.setGender(req.getParameter("sex"));
		cusInfo.setCusBirthday(util.getDate(req.getParameter("cusBirthDay")));
		cusInfo.setPhoneNum(req.getParameter("phoneNum"));
		cusInfo.setCardNO(req.getParameter("cardNO"));
		
		try {
			logInService.cusRegist(cusInfo);
			logInService.deposit(deposit);
			mav.getModel().put("flag","save_suc");
		}catch(Exception e) {
			e.printStackTrace();
			e.getMessage();
			mav.getModel().put("flag","save_fail");
		}
		mav.setViewName("mainLogin");
		return mav;
	}
	
}
