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
		
		//������������ѯ����
		Map<String,String> map = new HashMap<String, String>();
		map.put("customerId",req.getSession().getAttribute("customerId").toString());
		
		//���¿ͻ���Ϣ���ͬʱ��Ҳ����ֵ���ﱣ��һ��
		DepositInfo deposit = new DepositInfo();
		CusInfo cusInfo = new CusInfo();
		
		Map<String,Object> map1 = new HashMap<String, Object>();
		
		map1 = util.depositAndUpcustomerinfo(req);
		
		cusInfo = (CusInfo)map1.get("cusInfo");
		deposit = (DepositInfo)map1.get("deposit");
		
		try {
			logInService.deposit(deposit);
			
			logInService.updateCusDepInfo(cusInfo);
			
			//��session���cardType��ֵ����Ϊ��ֵ�������״̬
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
