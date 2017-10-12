package controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import service.LogInService;
import util.UtilClassMethod;

public class ConsumController implements Controller {

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
		
		String date = req.getParameter("date");
		String customer = req.getParameter("customer");
		String times = req.getParameter("times");
		String remark = req.getParameter("consume_remark");
		
		//用于向数据库传值
		Map<String,String> map = new HashMap<String,String>();
		
		if(date==null||date=="") {
			//如果日期为空，传当前日期
			map.put("date", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
		}else {
			map.put("date", util.getTime(date));
		}
		
		//获取客户ID
		String customerId = req.getSession().getAttribute("customerId").toString();
		
		map.put("customerId",customerId);
		
		//获取客户信息
		mav = util.queryCusInfo(map);
		
		//获取卡类型
		String cardtype = req.getSession().getAttribute("cardType").toString();
		
		map.put("cardType", cardtype);
		
		//如果是年/月卡 查询前台传的日期,每天只能消费一次
		if("2".equals(cardtype)) {
			//确认当日月卡是否重复消费时，时间才用"年月日"的格式
			if(date==null||date=="") {
				//如果日期为空，传当前日期
				map.put("date", new SimpleDateFormat("yyyyMMdd").format(new Date()));
			}else {
				map.put("date", util.getTime(date).substring(0,8));
			}
			
			int i = logInService.checkConcumeDt(map);
			if(i!=0) {
				mav.getModel().put("flag","date_exist");
				mav.getModel().put("conDt",util.transDate(map.get("date")));
				mav.setViewName("cusInfoQuery");
				return mav;
			}
		}
		
		map.put("customer", customer);
		if("".equals(times)||times==null) {
			//消费次数如果不输入，默认为1次
			map.put("times", "1");
		}else {
			map.put("times", times);
		}
		map.put("teller", req.getSession().getAttribute("user").toString());
		map.put("remark", remark);
		map.put("recordDt",new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
		
		try{
			//存储消费信息
			logInService.insertConsumeInfo(map);
			mav.getModel().put("flag","consume_suc");
		}catch(Exception e) {
			e.printStackTrace();
			e.getMessage();
			
			mav.getModel().put("flag","consume_fail");
			
		}
		
//		mav = util.queryCusInfo(map);
		mav.setViewName("cusInfoQuery");
		return mav;
	}

}
