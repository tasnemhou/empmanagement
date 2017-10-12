package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import entity.UserInfo;
import service.LogInService;

public class LogInController implements Controller {

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
		String userName = req.getParameter("username");
		String password = req.getParameter("pwd");
		
		if(userName!=null&&!"".equals(userName)&&password!=null&&!"".equals(password)) {
			UserInfo userInfo = new UserInfo();
			userInfo.setUserNM(userName);
			userInfo.setPwd(password);
			
			userInfo = logInService.queryUserInfo(userInfo);
			
			if(userInfo!=null&&"0".equals(userInfo.getUserState())) {
				
				String userLevel = userInfo.getUserLevel();
				
				mav.getModel().put("userLevel", userLevel);
				mav.setViewName("mainLogin");
				
				/******设置session**********/
				req.getSession().setAttribute("user", userName);
				//客户退出时用
				req.getSession().setAttribute("userLevel", userLevel);
				
				return mav;
			}else if(userInfo!=null&&"1".equals(userInfo.getUserState())) {
				mav.getModel().put("flag","out_of_work");
			}else {
				mav.getModel().put("flag","cus_login_fail");
			}
		}
		mav.setViewName("login");
		
		return mav;
	}
}
