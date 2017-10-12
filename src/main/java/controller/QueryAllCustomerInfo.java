package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import entity.CusInfo;
import service.LogInService;
import util.UtilClassMethod;

public class QueryAllCustomerInfo implements Controller {

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
	public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		List<CusInfo> list = logInService.queryAllCusInfo();
		if(list==null||list.size()==0) {
			mav.getModel().put("flag","cus_empty");
			mav.setViewName("mainLogin");
			
		}else {
			for(CusInfo cif:list) {
				cif.setCusBirthday(util.transDate(cif.getCusBirthday()));
				cif.setCusRegistDt(util.transDate(cif.getCusRegistDt()));
			}
			mav.getModel().put("allCusInfo", list);
			mav.getModel().put("flag","customerList");
			
			mav.setViewName("admin_query_tellerList_cusList");
		}
		
		
		return mav;
	}

}
