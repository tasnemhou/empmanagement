package controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import entity.CusInfo;
import service.LogInService;
import util.UtilClassMethod;

public class UpdateController implements Controller {

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
		Map<String,String> map = new HashMap<String, String>();
		
		String path = req.getServletPath();
		
		String customerId = req.getSession().getAttribute("customerId").toString();
		if("/cusInfo_save.do".equals(path)) {
			try {
				//�жϿ����Ƿ����
				if(req.getParameter("cardNo")!=null&&req.getParameter("cardNo")!="") {
					map.put("cardNum", req.getParameter("cardNo"));
					CusInfo cusInfo = logInService.queryCusInfo(map);
					if(cusInfo!=null) {
						//��ѯ�ͻ���Ϣ����ҳ�渳ֵ
						map.clear();
						map.put("customerId", customerId);
						mav = util.queryCusInfo(map);
						
						mav.getModel().put("flag","card_exist");
						mav.setViewName("cusInfoQuery");
						return mav;
					}
				}
				//�ж��ֻ����Ƿ����
				if(req.getParameter("phoneNo")!=null&&req.getParameter("phoneNo")!="") {
					map.clear();//��map��Ŀ������
					map.put("phoneNum", req.getParameter("phoneNo"));
					CusInfo cusInfo = logInService.queryCusInfo(map);
					if(cusInfo!=null) {
						//��ѯ�ͻ���Ϣ����ҳ�渳ֵ
						map.clear();
						map.put("customerId", customerId);
						mav = util.queryCusInfo(map);
						
						mav.getModel().put("flag","phone_exist");
						mav.setViewName("cusInfoQuery");
						return mav;
					}
				}
				CusInfo ci = new CusInfo();;
				//�����ֻ��š�����
				ci.setCardNO(req.getParameter("cardNo"));
				ci.setPhoneNum(req.getParameter("phoneNo"));
				ci.setCustomerId(customerId);
				logInService.updateCusInfo(ci);
				
				//��ѯ�ͻ���Ϣ����ҳ�渳ֵ
				map.put("customerId", customerId);
				mav = util.queryCusInfo(map);
				
				mav.getModel().put("flag","saveSuc");
			}catch(Exception e) {
				e.printStackTrace();
				e.getMessage();
				
				//��ѯ�ͻ���Ϣ����ҳ�渳ֵ
				map.clear();
				map.put("customerId", customerId);
				mav = util.queryCusInfo(map);
				
				mav.getModel().put("flag","saveFail");
			}
			
			mav.setViewName("cusInfoQuery");
		}else if("/update_userStt.do".equals(path)) {
			map.put("state",req.getParameter("state"));
			map.put("userName",req.getSession().getAttribute("user").toString());
			
			try{
				logInService.updateUserStt(map);
				mav.getModel().put("flag","saveSuc");
			}catch(Exception e) {
				e.printStackTrace();
				e.getMessage();
				mav.getModel().put("flag","saveFail");
			}
		}
		
		return mav;
	}

}
