package service;

import java.util.List;
import java.util.Map;

import dao.LogInDao;
import entity.CashCustomer;
import entity.ConsumeInfo;
import entity.CusInfo;
import entity.DepositInfo;
import entity.UserInfo;


public class LogInServiceImpl implements LogInService {

	private LogInDao logInDao;
	
	public LogInDao getLogInDao() {
		return logInDao;
	}

	public void setLogInDao(LogInDao logInDao) {
		this.logInDao = logInDao;
	}
	
	public CusInfo queryCusInfo(Map<String,String> map) {
		// TODO Auto-generated method stub
		CusInfo cusInfo = logInDao.queryCusInfo(map);
		return cusInfo;
	}

	public UserInfo queryUserInfo(UserInfo userInfo) {
		// TODO Auto-generated method stub
		UserInfo userinfo = logInDao.queryUserInfo(userInfo);
		return userinfo;
	}

	public void insertConsumeInfo(Map<String, String> map) {
		// TODO Auto-generated method stub
		logInDao.insertConsumeInfo(map);
	}

	public List<ConsumeInfo> queryConsumeDetail(String customerId) {
		// TODO Auto-generated method stub
		List<ConsumeInfo> consumeDetail = logInDao.queryConsumeDetail(customerId);
		return consumeDetail;
	}

	public void deposit(DepositInfo depositInfo) {
		// TODO Auto-generated method stub
		logInDao.deposit(depositInfo);
	}

	public List<DepositInfo> queryDepositInfo(String customerId) {
		// TODO Auto-generated method stub
		List<DepositInfo> depositInfo = logInDao.queryDepositInfo(customerId);
		return depositInfo;
	}

	public void cusRegist(CusInfo cusInfo) {
		// TODO Auto-generated method stub
		logInDao.cusRegist(cusInfo);
	}

	public void userRegist(UserInfo userInfo) {
		// TODO Auto-generated method stub
		logInDao.userRegist(userInfo);
	}

	public int checkConcumeDt(Map<String, String> map) {
		// TODO Auto-generated method stub
		return logInDao.checkConcumeDt(map);
	}

	public List<Map<String, Object>> queryCusExp(Map<String,String> map) {
		return logInDao.queryCusExp(map);
	}

	public List<CusInfo> queryAllCusInfo() {
		// TODO Auto-generated method stub
		return logInDao.queryAllCustomerInfo();
	}

	public List<UserInfo> queryTellerList() {
		// TODO Auto-generated method stub
		return logInDao.queryTellerList();
	}

	public void updateCusInfo(CusInfo cusInfo) {
		// TODO Auto-generated method stub
		logInDao.updateCusInfo(cusInfo);
	}

	public void updateUserStt(Map<String, String> map) {
		// TODO Auto-generated method stub
		logInDao.updateUserStt(map);
	}

	public void updateCusDepInfo(CusInfo cusInfo) {
		// TODO Auto-generated method stub
		logInDao.updateCusDepInfo(cusInfo);
	}

	public String getLastTimes(String customerId) {
		// TODO Auto-generated method stub
		return logInDao.getLastTimes(customerId);
	}

	public String getTotalTimes(String customerId) {
		// TODO Auto-generated method stub
		return logInDao.getTotalTimes(customerId);
	}

	public void insertCashCusConRec(CashCustomer cashCustomer) {
		// TODO Auto-generated method stub
		logInDao.insertCashCusConRec(cashCustomer);
	}

	public List<CashCustomer> queryCashCusSingleExp(Map<String, String> map) {
		return logInDao.queryCashCusSingleExp(map);
	}

	public List<DepositInfo> queryCusSingleDepositExp(Map<String, String> map) {
		// TODO Auto-generated method stub
		return logInDao.queryCusSingleDepositExp(map);
	}

	public List<ConsumeInfo> queryCusSingleConExp(Map<String, String> map) {
		// TODO Auto-generated method stub
		return logInDao.queryCusSingleConExp(map);
	}

}
