package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import entity.UserInfo;
import service.LogInService;

public class QueryTellerList implements Controller {

	private LogInService logInService; 
	
	public LogInService getLogInService() {
		return logInService;
	}

	public void setLogInService(LogInService logInService) {
		this.logInService = logInService;
	}

	public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		
		ModelAndView mav = new ModelAndView();
		
		 List<UserInfo> tellerList = logInService.queryTellerList();
		 if(tellerList==null||tellerList.size()==0) {
			 mav.getModel().put("flag","user_empty");
			 mav.setViewName("mainLogin");
		 }else {
			 mav.getModel().put("tellerList", tellerList);
			 mav.getModel().put("flag", "tellerList");
			 
			 mav.setViewName("admin_query_tellerList_cusList");
		 }
		 
		return mav;
	}

}
