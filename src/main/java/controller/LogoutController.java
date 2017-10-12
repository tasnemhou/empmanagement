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
			//��Ա�˳�
			req.getSession().invalidate(); 						//����session
			//res.sendRedirect(req.getContextPath()+"/login.jsp"); //�ض���
			mav.setViewName("login");
		}else if("/customerLogout.do".equals(path)) {
			//�ͻ��˳�
			req.getSession().removeAttribute("customerId");
			req.getSession().removeAttribute("customerName");
			//res.sendRedirect(req.getContextPath()+"/mainLogin.jsp"); //�ض���
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
