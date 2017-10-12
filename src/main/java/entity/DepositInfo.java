package entity;

public class DepositInfo {

	private String customerId;
	private String depositDate;
	private Double amount;
	private String cardType;
	private String addTimes;
	private String discountTimes;
	private String discountTime;
	private String teller;
	private String startDt;
	private String endDt;
	private String remark;
	private String recordDt;
	private String totalCount;
	private String totalDate;
	
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getDepositDate() {
		return depositDate;
	}
	public void setDepositDate(String depositDate) {
		this.depositDate = depositDate;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	
	public String getAddTimes() {
		return addTimes;
	}
	public void setAddTimes(String addTimes) {
		this.addTimes = addTimes;
	}
	public String getDiscountTimes() {
		return discountTimes;
	}
	public void setDiscountTimes(String discountTimes) {
		this.discountTimes = discountTimes;
	}
	public String getDiscountTime() {
		return discountTime;
	}
	public void setDiscountTime(String discountTime) {
		this.discountTime = discountTime;
	}
	
	public String getTeller() {
		return teller;
	}
	public void setTeller(String teller) {
		this.teller = teller;
	}
	public String getStartDt() {
		return startDt;
	}
	public void setStartDt(String startDt) {
		this.startDt = startDt;
	}
	public String getEndDt() {
		return endDt;
	}
	public void setEndDt(String endDt) {
		this.endDt = endDt;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getRecordDt() {
		return recordDt;
	}
	public void setRecordDt(String recordDt) {
		this.recordDt = recordDt;
	}
	public String getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}
	public String getTotalDate() {
		return totalDate;
	}
	public void setTotalDate(String totalDate) {
		this.totalDate = totalDate;
	}
	
}
