package util;

import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.web.servlet.ModelAndView;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

import dao.LogInDaoImpl;
import entity.CusInfo;
import entity.DepositInfo;
import service.LogInService;

public class UtilClassMethod {

	private LogInService logInService;
	
	public LogInService getLogInService() {
		return logInService;
	}

	public void setLogInService(LogInService logInService) {
		this.logInService = logInService;
	}

	public String getId() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}
	
	public SqlSession sqlSession() {
		String con = "SqlMapConfig.xml";
		InputStream inputStream = LogInDaoImpl.class.getClassLoader().getResourceAsStream(con);
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		SqlSessionFactory ssf = builder.build(inputStream);
		SqlSession selSession = ssf.openSession();
		
		return selSession;
	}
	
	/** 截止日期+优惠时间=总截止日期
	 * @param  截止日期  endDt  
	 * @param  优惠时间discountTime
	 * @return 最终截止日期 lastDt 
	 */
	public String getLastDt(String endDt,String discountTime){
		
		String lastDt = "";
		if(endDt!=null&&!"".equals(endDt)) {
			endDt = endDt.substring(0,8);
			if(discountTime!=null&&!"".equals(discountTime)) {
				//如果有优惠时间
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				Date date = new Date();
				try {
					if(endDt!=null&&endDt!="") {
						date = sdf.parse(endDt);
						
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(date);
						calendar.add(calendar.DATE, Integer.valueOf(discountTime));
						date = calendar.getTime();
						
						lastDt = sdf.format(date);
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					e.getMessage();
					System.out.println("日期转换格式错误");
				}
			}else {
				//如果没有优惠日期
				lastDt = endDt;
			}
			
		}
		return lastDt;
	}
	
	//客户储值 客户注册
	public Map<String,Object> depositAndUpcustomerinfo(HttpServletRequest req) {
		
		Map<String,Object> map = new HashMap<String, Object>();
		DepositInfo deposit = new DepositInfo();
		CusInfo cusInfo = new CusInfo();
		
		// 获取卡类型
		String cardType = req.getParameter("cardType");
		deposit.setCardType(cardType);
		cusInfo.setCardType(cardType);
		
		//客户Id
		String customerId;
		if(req.getSession().getAttribute("customerId")==null) {
			//如果为空，则说明是客户注册时的操作，创建Id
			customerId = getId();
		}else {
			//如果不为空，则说明是客户储值时的操作。直接调用
			customerId = req.getSession().getAttribute("customerId").toString();
		}
		
		deposit.setCustomerId(customerId);
		cusInfo.setCustomerId(customerId);
		
		//储值日期  (保存当前日期)
		deposit.setDepositDate(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));		
		cusInfo.setDepositDt(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));

		//对金额进行处理
		String depositAMT = req.getParameter("depositAMT");
		if(depositAMT.length()!=0) {
			Double value = Double.valueOf(depositAMT);
			deposit.setAmount(value);
			cusInfo.setDepositAmt(value);
		}
		
		//对次卡的处理
		if("1".equals(cardType)) {
			deposit.setAddTimes(req.getParameter("addTimes"));
			deposit.setDiscountTimes(req.getParameter("discountTimes"));
			cusInfo.setAddTimes(req.getParameter("addTimes"));
			cusInfo.setDiscountTimes(req.getParameter("discountTimes"));
		}
		
		//对年/月卡的处理
		if("2".equals(cardType)) {
			
			String startDt = getTime(req.getParameter("startDt"));
			String endDt = getTime(req.getParameter("endDt"));
			String discountTime = req.getParameter("discountTime");
			//获取最终截止日期
			String lastDt = getLastDt(endDt, discountTime);
			
			deposit.setStartDt(startDt);
			deposit.setEndDt(endDt);
			deposit.setDiscountTime(req.getParameter("discountTime"));
			
			cusInfo.setStartDt(startDt);
			cusInfo.setEndDt(endDt);
			cusInfo.setDiscountTime(req.getParameter("discountTime"));
			
			deposit.setTotalDate(lastDt);
			cusInfo.setTotalDate(lastDt);
		}
		
		cusInfo.setTeller(req.getSession().getAttribute("user").toString());
		deposit.setTeller(req.getSession().getAttribute("user").toString());
		cusInfo.setRemark(req.getParameter("remark"));
		deposit.setRemark(req.getParameter("remark"));
		cusInfo.setTotalCount(req.getParameter("totalCount"));
		deposit.setTotalCount(req.getParameter("totalCount"));
		
		//记录本笔交易的日期 
		deposit.setRecordDt(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
		cusInfo.setCusRegistDt(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
		
		map.put("deposit",deposit);
		map.put("cusInfo",cusInfo);
		
		return map;
	}
	
	public String getTime(String time) {
		if(time==null||"".equals(time)) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		try {
			date = sdf.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
			e.getMessage();
		}
		
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
		
		String dateTime = sdf1.format(date);
		return dateTime;
	}
	
	public String getDate(String time) {
		return time.replaceAll("-","");
	}
	
	public String transDate(String date) {
		if(date==null||"".equals(date)) {
			return "";
		}
		return date.substring(0, 4) + "-" + date.substring(4,6) + "-" + date.substring(6,8);
	}
	
	//查询客户信息
	public ModelAndView queryCusInfo(Map<String,String> map) {
		
		ModelAndView av = new ModelAndView();
		
		CusInfo cusInfo = logInService.queryCusInfo(map);
		
		if(cusInfo!=null) {
			
			av.getModel().put("customerId",cusInfo.getCustomerId());
			av.getModel().put("registDt",transDate(cusInfo.getCusRegistDt()));
			av.getModel().put("phoneNum",cusInfo.getPhoneNum());
			av.getModel().put("name",cusInfo.getCustomerName());
			
			av.getModel().put("teller",cusInfo.getTeller());
			av.getModel().put("birthday",transDate(cusInfo.getCusBirthday()));
			av.getModel().put("gender",cusInfo.getGender());
			av.getModel().put("cardNo",cusInfo.getCardNO());
			av.getModel().put("cardType",cusInfo.getCardType());
			
			//对日期格式进行转义
			String totalDate = transDate(cusInfo.getTotalDate());
			av.getModel().put("lastDt",totalDate);
			
			if("1".equals(cusInfo.getCardType())) {
				//获取总使用总次数
				String lastTimes = logInService.getLastTimes(cusInfo.getCustomerId());
				
				//获取总次数
				String totalTimes = logInService.getTotalTimes(cusInfo.getCustomerId());
				
				if(lastTimes!=null&&!"".equals(lastTimes)&&totalTimes!=null&&!"".equals(totalTimes)) {
					String displayTimes = String.valueOf(Integer.valueOf(totalTimes) - Integer.valueOf(lastTimes));
					av.getModel().put("lastTimes",displayTimes);
				}else if(totalTimes!=null&&!"".equals(totalTimes)) {
					av.getModel().put("lastTimes",totalTimes);
				}
			}
			return av;
		}
		return null;
	}
	
	public static void main(String[] args) throws ParseException {
		
	}
	//查询客户姓名和电话
	public Map<String,String> queryCusNameAndPhone(Map<String,String> map) {
		Map<String,String> resultMap = new HashMap<String, String>();
		CusInfo cusInfo = logInService.queryCusInfo(map);
		resultMap.put("cusName", cusInfo.getCustomerName());
		resultMap.put("phone",cusInfo.getPhoneNum());
		return resultMap;
	}
}
