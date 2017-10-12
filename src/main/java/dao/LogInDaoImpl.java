package dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import entity.CashCustomer;
import entity.ConsumeInfo;
import entity.CusInfo;
import entity.DepositInfo;
import entity.UserInfo;
import util.UtilClassMethod;

public class LogInDaoImpl implements LogInDao {

	private UtilClassMethod util; 
	
	public UtilClassMethod getUtil() {
		return util;
	}

	public void setUtil(UtilClassMethod util) {
		this.util = util;
	}

	public CusInfo queryCusInfo(Map<String,String> map) {
		SqlSession selSession = util.sqlSession();
 		CusInfo cusInfo = selSession.selectOne("iii.cusInfo",map);
 		selSession.close();
		return cusInfo;
	}

	public UserInfo queryUserInfo(UserInfo userInfo) {
		// TODO Auto-generated method stub
		SqlSession selSession = util.sqlSession();
		UserInfo userinfo = selSession.selectOne("userInfo",userInfo);
		selSession.close();
		return userinfo;
	}

	public void insertConsumeInfo(Map<String, String> map) {
		// TODO Auto-generated method stub
		SqlSession selSession = util.sqlSession();
		selSession.insert("consumeInfo", map);
		selSession.close();
		selSession.commit();
	}

	public List<ConsumeInfo> queryConsumeDetail(String customerId) {
		SqlSession selSession = util.sqlSession();
		List<ConsumeInfo> consumeDetail = selSession.selectList("queryConsumeDetail", customerId);
		selSession.close();
		return consumeDetail;
	}

	public void deposit(DepositInfo depositInfo) {
		// TODO Auto-generated method stub
		SqlSession selSession = util.sqlSession();
		selSession.insert("deposit", depositInfo);
		selSession.close();
		selSession.commit();
	}

	public List<DepositInfo> queryDepositInfo(String customerId) {
		// TODO Auto-generated method stub
		SqlSession selSession = util.sqlSession();
		List<DepositInfo> depositInfo = selSession.selectList("queryDepositInfo", customerId);
		selSession.close();
		return depositInfo;
	}

	public void cusRegist(CusInfo cusInfo) {
		// TODO Auto-generated method stub
		SqlSession selSession = util.sqlSession();
		selSession.insert("cusRegist", cusInfo);
		selSession.close();
		selSession.commit();
	}

	public void userRegist(UserInfo userInfo) {
		// TODO Auto-generated method stub
		SqlSession selSession = util.sqlSession();
		selSession.insert("userRegist", userInfo);
		selSession.close();
		selSession.commit();
	}

	public int checkConcumeDt(Map<String, String> map) {
		// TODO Auto-generated method stub
		SqlSession selSession = util.sqlSession();
		int i = selSession.selectOne("checkConcumeDt", map);
		selSession.close();
		return i;
	}

	public List<Map<String,Object>> queryCusExp(Map<String,String> map) {
		SqlSession selSession = util.sqlSession();
		List<Map<String,Object>> list = selSession.selectList("queryCusExp", map);
		selSession.close();
		return list;
	}

	public List<CusInfo> queryAllCustomerInfo() {
		// TODO Auto-generated method stub
		SqlSession selSession = util.sqlSession();
		List<CusInfo> allCusInfo = selSession.selectList("queryAllCustomerInfo");
		selSession.close();
		return allCusInfo;
	}
	public List<UserInfo> queryTellerList() {
		// TODO Auto-generated method stub
		SqlSession selSession = util.sqlSession();
		List<UserInfo> tellerList = selSession.selectList("queryTellerList");
		selSession.close();
		return tellerList;
	}

	public void updateCusInfo(CusInfo cusInfo) {
		// TODO Auto-generated method stub
		SqlSession selSession = util.sqlSession();
		selSession.update("updateCusInfo", cusInfo);
		selSession.close();
		selSession.commit();
	}

	public void updateUserStt(Map<String, String> map) {
		// TODO Auto-generated method stub
		SqlSession selSession = util.sqlSession();
		selSession.update("updateUserStt", map);
		selSession.close();
		selSession.commit();
	}

	public void updateCusDepInfo(CusInfo cusInfo) {
		// TODO Auto-generated method stub
		SqlSession selSession = util.sqlSession();
		selSession.update("updateCusDepInfo", cusInfo);
		selSession.close();
		selSession.commit();
	}

	public String getLastTimes(String customerId) {
		// TODO Auto-generated method stub
		SqlSession selSession = util.sqlSession();
		String lastTimes = selSession.selectOne("getLastTimes", customerId);
		selSession.close();
		return lastTimes;
	}

	public String getTotalTimes(String customerId) {
		SqlSession selSession = util.sqlSession();
		String totalTimes = selSession.selectOne("getTotalTimes", customerId);
		selSession.close();
		return totalTimes;
	}

	public void insertCashCusConRec(CashCustomer cashCustomer) {
		
		SqlSession selSession = util.sqlSession();
		selSession.insert("insertCashCusConRec", cashCustomer);
		selSession.close();
		selSession.commit();
	}

	public List<CashCustomer> queryCashCusSingleExp(Map<String, String> map) {
		SqlSession selSession = util.sqlSession();
		List<CashCustomer> cashCusList = selSession.selectList("queryCashCusSingleExp", map);
		selSession.close();
		return cashCusList;
	}

	public List<DepositInfo> queryCusSingleDepositExp(Map<String, String> map) {
		SqlSession selSession = util.sqlSession();
		List<DepositInfo> list = selSession.selectList("queryCusSingleDepositExp", map);
		selSession.close();
		return list;
	}

	public List<ConsumeInfo> queryCusSingleConExp(Map<String, String> map) {
		SqlSession selSession = util.sqlSession();
		
		List<ConsumeInfo> list = selSession.selectList("queryCusSingleConExp", map);
		selSession.close();
		return list;
	}
}
