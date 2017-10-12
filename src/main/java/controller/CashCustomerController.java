package controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import entity.CashCustomer;
import service.LogInService;

public class CashCustomerController implements Controller {

	private LogInService logInService;
	
	public LogInService getLogInService() {
		return logInService;
	}

	public void setLogInService(LogInService logInService) {
		this.logInService = logInService;
	}

	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		req.setCharacterEncoding("utf-8");
		
		ModelAndView mav = new ModelAndView();
		CashCustomer cashCustomer = new CashCustomer();
		
		cashCustomer.setCustomerName(req.getParameter("cash_cus_name"));
		cashCustomer.setGender(req.getParameter("sex"));
		cashCustomer.setPhoneNum(req.getParameter("phone"));
		cashCustomer.setAmount(Double.valueOf(req.getParameter("amount")));
		cashCustomer.setConsumDt(new SimpleDateFormat("yyyyMMdd").format(new Date()));
		cashCustomer.setRemark(req.getParameter("cash_cus_remark"));
		cashCustomer.setTeller(req.getSession().getAttribute("user").toString());
		
		logInService.insertCashCusConRec(cashCustomer);
		
		mav.getModel().put("flag","cashCusSuc");
		mav.setViewName("mainLogin");
		return mav;
	}

}
