package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import service.LogInService;

public class QueryCusExpController implements Controller{

	private LogInService logInService;
	
	public LogInService getLogInService() {
		return logInService;
	}

	public void setLogInService(LogInService logInService) {
		this.logInService = logInService;
	}

	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO Auto-generated method stub
		
		ModelAndView mav = new ModelAndView();
		
		Map<String,String> map = new HashMap<String,String>();
		
		map.put("customerId", req.getSession().getAttribute("customerId").toString());
		
		List<Map<String,Object>> list = logInService.queryCusExp(map);
		
		mav.getModel().put("queryCusExpMap", list);
		mav.getModel().put("flag","3");

//		mav.setViewName("queryCusExp");
		mav.setViewName("queryCusExpDetail");
		return mav;
	}
	
}
