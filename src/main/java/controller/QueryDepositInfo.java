package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import entity.DepositInfo;
import service.LogInService;
import util.UtilClassMethod;

public class QueryDepositInfo implements Controller {

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
		ModelAndView mav = new ModelAndView();
		String customerId = req.getSession().getAttribute("customerId").toString();
		List<DepositInfo> depositInfoList = logInService.queryDepositInfo(customerId);
		if(depositInfoList.size()==0||depositInfoList==null) {
			mav.getModel().put("flag","query_fail");
			mav.setViewName("cusInfoQuery");
		}else {
			for(DepositInfo dif:depositInfoList) {
				dif.setDepositDate(util.transDate(dif.getDepositDate()));
				dif.setStartDt(util.transDate(dif.getStartDt()));
				dif.setEndDt(util.transDate(dif.getEndDt()));
			}
			mav.getModel().put("depositInfo", depositInfoList);
			mav.getModel().put("flag","1");
			mav.setViewName("queryCusExpDetail");
		}
		
		return mav;
	}

}
