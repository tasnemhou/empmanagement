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
		
		//1.����ֻ��Ż򿨺Ų�Ϊ�գ�˵���ǿͻ���½����
		//2. ���ֻ��ž����ֻ��Ų飬û���ֻ��ž��ÿ��Ų�
		//3. ��av����ȡһֵ����ȡ���ˣ���˵��getModelAndView�����ײ�if�������
		if(phoneNum!=null&&!"".equals(phoneNum)) {
			
			//��ȡ�ͻ�������Ϣ��ͳ����Ϣ
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
				//���ͻ�Id����session
				req.getSession().setAttribute("customerId", av.getModel().get("customerId").toString());
				//���ͻ���������session
				req.getSession().setAttribute("customerName", av.getModel().get("name").toString());
				//�������ʹ���session
				req.getSession().setAttribute("cardType", av.getModel().get("cardType").toString());
				av.setViewName("cusInfoQuery");
			}
			return av;
		}
		
		if(cardNum!=null&&!"".equals(cardNum)) {
			
			//��ȡ�ͻ�������Ϣ��ͳ����Ϣ
			map.put("cardNum",cardNum);
			av = util.queryCusInfo(map);
			
			if(av.getModel().get("registDt").toString().length()>0) {
				//���ͻ�Id����session
				req.getSession().setAttribute("customerId", av.getModel().get("customerId").toString());
				//���ͻ���������session
				req.getSession().setAttribute("customerName", av.getModel().get("name").toString());
				//�������ʹ���session
				req.getSession().setAttribute("cardType", av.getModel().get("cardType").toString());
				
				av.setViewName("cusInfoQuery");
			}else {
				av.getModel().put("flag","N");
				av.setViewName("mainLogin");
			}
			return av;
		}
		
		//����ֻ��źͿ��Ŷ��ǿ�ֵ��˵���û����ǵ�½�����������Ƿ���֮��Ĳ�����
		if(req.getSession().getAttribute("customerId")!=null) {
			
			map.put("customerId",req.getSession().getAttribute("customerId").toString());
			av = util.queryCusInfo(map);
			
			av.setViewName("cusInfoQuery");
			return av;
		}
		
		//���customerIdҲΪ�գ�˵���û�����ʱ����ʱ����Ҫ���µ�½
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
//			//�����ڸ�ʽ����ת��
//			String totalDate = util.transDate(cusInfo.getTotalDate());
//			av.getModel().put("lastDt",totalDate);
//			
//			if("1".equals(cusInfo.getCardType())) {
//				//��ȡ��ʹ���ܴ���
//				String lastTimes = logInService.getLastTimes(cusInfo.getCustomerId());
//				
//				//��ȡ�ܴ���
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
