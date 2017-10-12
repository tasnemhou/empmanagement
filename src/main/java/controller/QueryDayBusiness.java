package controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import entity.CashCustomer;
import entity.ConsumeInfo;
import entity.DepositInfo;
import service.LogInService;
import util.UtilClassMethod;

public class QueryDayBusiness implements Controller {

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
		
		String date = util.getDate(req.getParameter("queryDayBusiness"));
		String flag = req.getParameter("exp_conditin");
		if(date.length()==0) {
			String recordDt = new SimpleDateFormat("yyyyMMdd").format(new Date());
			map.put("recordDt", recordDt);
		}else {
			map.put("recordDt", date);
		}
		
		if("散客消费情况查询".equals(flag)) {
			List<CashCustomer> cashCusSinConList = logInService.queryCashCusSingleExp(map);
			mav.getModel().put("flag","queryCashCus");
			if(cashCusSinConList.size()==0||cashCusSinConList==null) {
				mav.setViewName("mainLogin");
			}else {
				for(CashCustomer cc:cashCusSinConList) {
					cc.setConsumDt(util.transDate(cc.getConsumDt()));
				}
				mav.getModel().put("cashCusSinConList", cashCusSinConList);
				mav.setViewName("admin_query_dayBusiness");
			}
		}else if("客户消费情况查询".equals(flag)) {
			List<ConsumeInfo> consumeList = logInService.queryCusSingleConExp(map);
			mav.getModel().put("flag","querySingleCon");
			if(consumeList.size()==0||consumeList==null) {
				mav.setViewName("mainLogin");
			}else { 
				List<Map<String,String>> list= new ArrayList<Map<String,String>>();
				for(ConsumeInfo cc:consumeList) {
					Map<String,String> map1 = new HashMap<String, String>();
					map.put("customerId", cc.getCustomerId());
					map1.put("consumDT",util.transDate(cc.getConsumDT()));
					map1.put("carder",util.queryCusNameAndPhone(map).get("cusName"));
					map1.put("phone",util.queryCusNameAndPhone(map).get("phone"));
					map1.put("customer",cc.getCustomer());
					map1.put("consumeTimes",cc.getConsumeTimes());
					map1.put("teller",cc.getTeller());
					map1.put("cardType",cc.getCardType());
					list.add(map1);
				}
				mav.getModel().put("conSinList", list);
				//mav.getModel().put("conSinList", consumeList);
				mav.setViewName("admin_query_dayBusiness");
			}
		}else if("客户储值情况查询".equals(flag)){
			List<DepositInfo> depositList = logInService.queryCusSingleDepositExp(map);
			mav.getModel().put("flag","querySingleDeposit");
			if(depositList.size()==0||depositList==null) {
				mav.setViewName("mainLogin");
			}else {
				List<Map<String,String>> list= new ArrayList<Map<String,String>>();
				for(DepositInfo cc:depositList) {
					Map<String,String> map1 = new HashMap<String, String>();
					map.put("customerId", cc.getCustomerId());
					map1.put("depositDate",util.transDate(cc.getDepositDate()) );
					map1.put("customer",util.queryCusNameAndPhone(map).get("cusName") );
					map1.put("phone", util.queryCusNameAndPhone(map).get("phone"));
					map1.put("amount",String.valueOf(cc.getAmount()) );
					map1.put("cardType",cc.getCardType() );
					map1.put("addTimes",cc.getAddTimes());
					map1.put("discountTimes",cc.getDiscountTimes() );
					map1.put("discountTime",cc.getDiscountTime() );
					map1.put("teller",cc.getTeller() );
					map1.put("startDt",cc.getStartDt() );
					map1.put("endDt",cc.getEndDt() );
					list.add(map1);
				}
				mav.getModel().put("depositSinList", list);
//				mav.getModel().put("depositSinList", depositList);
				mav.setViewName("admin_query_dayBusiness");
			}
		}
		
		//List<Map<String,Object>> list = logInService.queryCusExp(map);
		
		//mav.getModel().put("queryCusExpMap", list);
		//mav.getModel().put("flag","dayBusiness");
		
		return mav;
	}

}
