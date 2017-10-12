package controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import util.UtilClassMethod;

public class LogoutController implements Controller {

	private UtilClassMethod util;

	public UtilClassMethod getUtil() {
		return util;
	}

	public void setUtil(UtilClassMethod util) {
		this.util = util;
	}

	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO Auto-generated method stub
		
		req.setCharacterEncoding("utf-8");
		
		ModelAndView mav = new ModelAndView();
		Map<String,String> map = new HashMap<String, String>();
		String path = req.getServletPath();
		
		if("/tellerLogout.do".equals(path)) {
			//柜员退出
			req.getSession().invalidate(); 						//销毁session
			//res.sendRedirect(req.getContextPath()+"/login.jsp"); //重定向
			mav.setViewName("login");
		}else if("/customerLogout.do".equals(path)) {
			//客户退出
			req.getSession().removeAttribute("customerId");
			req.getSession().removeAttribute("customerName");
			//res.sendRedirect(req.getContextPath()+"/mainLogin.jsp"); //重定向
			mav.getModel().put("userLevel",req.getSession().getAttribute("userLevel"));
			mav.setViewName("mainLogin");
		}else if("/cus_back.do".equals(path)) {
		//	res.sendRedirect(req.getContextPath()+"/cusInfoQuery.jsp");
			map.put("customerId",req.getSession().getAttribute("customerId").toString());
			mav = util.queryCusInfo(map);
			mav.setViewName("cusInfoQuery");
		}else if("/back_to_adminOperation.do".equals(path)) {
			mav.getModel().put("flag","backToAdminOperation");
			mav.setViewName("mainLogin");
		}
		
		return mav;
	}

}
