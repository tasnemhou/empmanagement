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

public class CusInfoQuery implements Controller {
	
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
		
		ModelAndView av = new ModelAndView();
		
		Map<String,String> map = new HashMap<String,String>();
		
		String phoneNum = req.getParameter("phoneNum");
		
		String cardNum = req.getParameter("cardNum");
		
		//1.如果手机号或卡号不为空，说明是客户登陆操作
		//2. 有手机号就以手机号查，没有手机号就用卡号查
		//3. 从av中任取一值，若取到了，则说明getModelAndView进了首层if条件语句
		if(phoneNum!=null&&!"".equals(phoneNum)) {
			
			//获取客户基本信息和统计信息
			map.put("phoneNum",phoneNum);
			av = util.queryCusInfo(map);
			
			//if(av.getModel().get("registDt").toString().length()>0) {
			if(av==null) {
				ModelAndView mav = new ModelAndView();
				mav.getModel().put("flag","N");
				mav.setViewName("mainLogin");
				return mav;
			}
			if(av!=null) {
				//将客户Id存入session
				req.getSession().setAttribute("customerId", av.getModel().get("customerId").toString());
				//将客户姓名存入session
				req.getSession().setAttribute("customerName", av.getModel().get("name").toString());
				//将卡类型存入session
				req.getSession().setAttribute("cardType", av.getModel().get("cardType").toString());
				av.setViewName("cusInfoQuery");
			}
			return av;
		}
		
		if(cardNum!=null&&!"".equals(cardNum)) {
			
			//获取客户基本信息和统计信息
			map.put("cardNum",cardNum);
			av = util.queryCusInfo(map);
			
			if(av.getModel().get("registDt").toString().length()>0) {
				//将客户Id存入session
				req.getSession().setAttribute("customerId", av.getModel().get("customerId").toString());
				//将客户姓名存入session
				req.getSession().setAttribute("customerName", av.getModel().get("name").toString());
				//将卡类型存入session
				req.getSession().setAttribute("cardType", av.getModel().get("cardType").toString());
				
				av.setViewName("cusInfoQuery");
			}else {
				av.getModel().put("flag","N");
				av.setViewName("mainLogin");
			}
			return av;
		}
		
		//如果手机号和卡号都是空值，说明用户不是登陆操作（可能是返回之类的操作）
		if(req.getSession().getAttribute("customerId")!=null) {
			
			map.put("customerId",req.getSession().getAttribute("customerId").toString());
			av = util.queryCusInfo(map);
			
			av.setViewName("cusInfoQuery");
			return av;
		}
		
		//如果customerId也为空，说明用户在线时长超时，需要重新登陆
		av.setViewName("mainLogin");
		return av;
	}
//public ModelAndView queryCusInfo(Map<String,String> map) {
//		
//		ModelAndView av = new ModelAndView();
//		
//		CusInfo cusInfo = logInService.queryCusInfo(map);
//		
//		if(cusInfo!=null) {
//			
//			av.getModel().put("customerId",cusInfo.getCustomerId());
//			av.getModel().put("registDt",util.transDate(cusInfo.getCusRegistDt()));
//			av.getModel().put("phoneNum",cusInfo.getPhoneNum());
//			av.getModel().put("name",cusInfo.getCustomerName());
//			
//			av.getModel().put("teller",cusInfo.getTeller());
//			av.getModel().put("birthday",util.transDate(cusInfo.getCusBirthday()));
//			av.getModel().put("gender",cusInfo.getGender());
//			av.getModel().put("cardNo",cusInfo.getCardNO());
//			av.getModel().put("cardType",cusInfo.getCardType());
//			
//			//对日期格式进行转义
//			String totalDate = util.transDate(cusInfo.getTotalDate());
//			av.getModel().put("lastDt",totalDate);
//			
//			if("1".equals(cusInfo.getCardType())) {
//				//获取总使用总次数
//				String lastTimes = logInService.getLastTimes(cusInfo.getCustomerId());
//				
//				//获取总次数
//				String totalTimes = logInService.getTotalTimes(cusInfo.getCustomerId());
//				
//				if(lastTimes!=null&&!"".equals(lastTimes)&&totalTimes!=null&&!"".equals(totalTimes)) {
//					String displayTimes = String.valueOf(Integer.valueOf(totalTimes) - Integer.valueOf(lastTimes));
//					av.getModel().put("lastTimes",displayTimes);
//				}else if(totalTimes!=null&&!"".equals(totalTimes)) {
//					av.getModel().put("lastTimes",totalTimes);
//				}
//			}
//			return av;
//		}
//		return null;
//	}
}
