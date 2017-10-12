package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import entity.UserInfo;
import service.LogInService;
import util.UtilClassMethod;

public class UserRegistController implements Controller {

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
		
		UserInfo userInfo = new UserInfo();
		String userId = util.getId();
		userInfo.setUserId(userId);
		
		String userName = req.getParameter("userName");
		//�Ƚ��û��������ݿ����   �û��������ظ�
		userInfo.setUserNM(userName);
		UserInfo user = logInService.queryUserInfo(userInfo);
		
		if(user==null) {
			userInfo.setPwd(req.getParameter("pwd"));
			userInfo.setGender(req.getParameter("sex"));
			userInfo.setUserLevel(req.getParameter("userLevel"));
			userInfo.setPhoneNum(req.getParameter("phoneNum"));
			//ע�����û�  Ĭ��Ϊ��ְ״̬
			userInfo.setUserState("0");
			try {
				logInService.userRegist(userInfo);
				mav.getModel().put("flag","user_save_suc");
				mav.setViewName("mainLogin");
			}catch(Exception e) {
				e.printStackTrace();
				e.getMessage();
			}
		}else {
			//˵�����û����Ѵ���
			mav.getModel().put("flag","exist");
			mav.setViewName("mainLogin");
			return mav;
		}
		
		return mav;
	}

}
