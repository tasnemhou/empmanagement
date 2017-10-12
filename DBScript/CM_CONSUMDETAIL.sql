CREATE TABLE CM_CONSUMDETAIL(
  CC_CUSTOMERID			 VARCHAR(100),
  CC_CARDNO				 VARCHAR(30),
  CC_PHONENUM 			 CHAR(11),
  CC_CONSUMDT 			 CHAR(14),
  CC_CUSTOMER 			 VARCHAR(20 CHAR),
  CC_CONSUMTIMES		 VARCHAR(2),
  CC_TELLER				 VARCHAR(20 CHAR),
  CC_CARDTYPE 			 VARCHAR(1),
  CC_REMARK				 VARCHAR2(50 CHAR),
  CC_RECORDDT			 VARCHAR(14)
);

COMMENT ON TABLE CM_CONSUMDETAIL IS '消费明细表';
COMMENT ON COLUMN CM_CONSUMDETAIL.CC_CONSUMDT 		IS '消费日期:月 日 年';
COMMENT ON COLUMN CM_CONSUMDETAIL.CC_CUSTOMER 		IS '客户（消费人员）';
COMMENT ON COLUMN CM_CONSUMDETAIL.CC_CONSUMTIMES	IS '消费次数';
COMMENT ON COLUMN CM_CONSUMDETAIL.CC_TELLER 		IS '操作柜员';
COMMENT ON COLUMN CM_CONSUMDETAIL.CC_CARDTYPE 		IS '卡类型';
COMMENT ON COLUMN CM_CONSUMDETAIL.CC_RECORDDT 		IS '记帐日期';
