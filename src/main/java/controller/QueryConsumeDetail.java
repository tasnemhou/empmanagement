package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import entity.ConsumeInfo;
import service.LogInService;
import util.UtilClassMethod;

public class QueryConsumeDetail implements Controller {

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
		ModelAndView mv = new ModelAndView();
		Map<String,String> map = new HashMap<String,String>();
		String customerId = req.getSession().getAttribute("customerId").toString();
		try{
			List<ConsumeInfo> consumeDetail = logInService.queryConsumeDetail(customerId);
			if(consumeDetail.size()==0||consumeDetail==null) {
				map.put("customerId",customerId);
				mv = util.queryCusInfo(map);
				mv.getModel().put("flag","query_fail");
				mv.setViewName("cusInfoQuery");
				
			}else {
				for(ConsumeInfo cif:consumeDetail) {
					cif.setConsumDT(util.transDate(cif.getConsumDT()));;
				}
				mv.getModel().put("queryConsumeDetail", consumeDetail);
				mv.getModel().put("flag","2");
				mv.setViewName("queryCusExpDetail");
			}
		}catch(Exception e) {
			e.printStackTrace();
			e.getMessage();
			
			mv.getModel().put("flag","2-2");
		}
		
		return mv;
	}

}
