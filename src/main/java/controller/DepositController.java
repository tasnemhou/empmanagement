package controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import entity.CusInfo;
import entity.DepositInfo;
import entity.UserInfo;
import service.LogInService;
import util.UtilClassMethod;

public class DepositController implements Controller {

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
		
		//保存完再做查询操作
		Map<String,String> map = new HashMap<String, String>();
		map.put("customerId",req.getSession().getAttribute("customerId").toString());
		
		//更新客户信息表的同时，也给储值表里保存一次
		DepositInfo deposit = new DepositInfo();
		CusInfo cusInfo = new CusInfo();
		
		Map<String,Object> map1 = new HashMap<String, Object>();
		
		map1 = util.depositAndUpcustomerinfo(req);
		
		cusInfo = (CusInfo)map1.get("cusInfo");
		deposit = (DepositInfo)map1.get("deposit");
		
		try {
			logInService.deposit(deposit);
			
			logInService.updateCusDepInfo(cusInfo);
			
			//将session里的cardType的值更新为储值后的最新状态
			req.getSession().setAttribute("cardType",cusInfo.getCardType());
			
			mav = util.queryCusInfo(map);
			
			mav.getModel().put("flag","saveSuc");
		}catch(Exception e) {
			e.printStackTrace();
			e.getMessage();
			
			mav = util.queryCusInfo(map);
			
			mav.getModel().put("flag","saveFail");
		}
		
		mav.setViewName("cusInfoQuery");
		return mav;
	}

}
