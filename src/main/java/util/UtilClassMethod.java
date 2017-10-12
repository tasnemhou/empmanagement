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
	
	/** ��ֹ����+�Ż�ʱ��=�ܽ�ֹ����
	 * @param  ��ֹ����  endDt  
	 * @param  �Ż�ʱ��discountTime
	 * @return ���ս�ֹ���� lastDt 
	 */
	public String getLastDt(String endDt,String discountTime){
		
		String lastDt = "";
		if(endDt!=null&&!"".equals(endDt)) {
			endDt = endDt.substring(0,8);
			if(discountTime!=null&&!"".equals(discountTime)) {
				//������Ż�ʱ��
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
					System.out.println("����ת����ʽ����");
				}
			}else {
				//���û���Ż�����
				lastDt = endDt;
			}
			
		}
		return lastDt;
	}
	
	//�ͻ���ֵ �ͻ�ע��
	public Map<String,Object> depositAndUpcustomerinfo(HttpServletRequest req) {
		
		Map<String,Object> map = new HashMap<String, Object>();
		DepositInfo deposit = new DepositInfo();
		CusInfo cusInfo = new CusInfo();
		
		// ��ȡ������
		String cardType = req.getParameter("cardType");
		deposit.setCardType(cardType);
		cusInfo.setCardType(cardType);
		
		//�ͻ�Id
		String customerId;
		if(req.getSession().getAttribute("customerId")==null) {
			//���Ϊ�գ���˵���ǿͻ�ע��ʱ�Ĳ���������Id
			customerId = getId();
		}else {
			//�����Ϊ�գ���˵���ǿͻ���ֵʱ�Ĳ�����ֱ�ӵ���
			customerId = req.getSession().getAttribute("customerId").toString();
		}
		
		deposit.setCustomerId(customerId);
		cusInfo.setCustomerId(customerId);
		
		//��ֵ����  (���浱ǰ����)
		deposit.setDepositDate(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));		
		cusInfo.setDepositDt(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));

		//�Խ����д���
		String depositAMT = req.getParameter("depositAMT");
		if(depositAMT.length()!=0) {
			Double value = Double.valueOf(depositAMT);
			deposit.setAmount(value);
			cusInfo.setDepositAmt(value);
		}
		
		//�Դο��Ĵ���
		if("1".equals(cardType)) {
			deposit.setAddTimes(req.getParameter("addTimes"));
			deposit.setDiscountTimes(req.getParameter("discountTimes"));
			cusInfo.setAddTimes(req.getParameter("addTimes"));
			cusInfo.setDiscountTimes(req.getParameter("discountTimes"));
		}
		
		//����/�¿��Ĵ���
		if("2".equals(cardType)) {
			
			String startDt = getTime(req.getParameter("startDt"));
			String endDt = getTime(req.getParameter("endDt"));
			String discountTime = req.getParameter("discountTime");
			//��ȡ���ս�ֹ����
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
		
		//��¼���ʽ��׵����� 
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
	
	//��ѯ�ͻ���Ϣ
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
			
			//�����ڸ�ʽ����ת��
			String totalDate = transDate(cusInfo.getTotalDate());
			av.getModel().put("lastDt",totalDate);
			
			if("1".equals(cusInfo.getCardType())) {
				//��ȡ��ʹ���ܴ���
				String lastTimes = logInService.getLastTimes(cusInfo.getCustomerId());
				
				//��ȡ�ܴ���
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
	//��ѯ�ͻ������͵绰
	public Map<String,String> queryCusNameAndPhone(Map<String,String> map) {
		Map<String,String> resultMap = new HashMap<String, String>();
		CusInfo cusInfo = logInService.queryCusInfo(map);
		resultMap.put("cusName", cusInfo.getCustomerName());
		resultMap.put("phone",cusInfo.getPhoneNum());
		return resultMap;
	}
}
