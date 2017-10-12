package service;

import java.util.List;
import java.util.Map;

import entity.CashCustomer;
import entity.ConsumeInfo;
import entity.CusInfo;
import entity.DepositInfo;
import entity.UserInfo;

public interface LogInService {

	public UserInfo queryUserInfo(UserInfo userInfo);
	public CusInfo queryCusInfo(Map<String,String> map);
	public void insertConsumeInfo(Map<String,String> map);
	public List<ConsumeInfo> queryConsumeDetail(String customerId);
	public void deposit(DepositInfo depositInfo);
	public List<DepositInfo> queryDepositInfo (String customerId);
	public void cusRegist(CusInfo cusInfo);
	public void userRegist(UserInfo userInfo);
	public int checkConcumeDt(Map<String, String> map);
	public List<Map<String, Object>> queryCusExp(Map<String,String> map);
	public List<CusInfo> queryAllCusInfo();
	public List<UserInfo> queryTellerList();
	public void updateCusInfo(CusInfo cusInfo);
	public void updateUserStt(Map<String,String> map);
	public void updateCusDepInfo(CusInfo cusInfo);
	public String getLastTimes(String customerId);
	public String getTotalTimes(String customerId);
	public void insertCashCusConRec(CashCustomer cashCustomer);
	public List<CashCustomer> queryCashCusSingleExp(Map<String,String> map);
	public List<DepositInfo> queryCusSingleDepositExp(Map<String,String> map);
	public List<ConsumeInfo> queryCusSingleConExp(Map<String,String> map);
}
