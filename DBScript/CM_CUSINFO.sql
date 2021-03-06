CREATE TABLE CM_CUSINFO(
  CC_CUSTOMERID     	  VARCHAR2(100) NOT NULL ,
  CC_NAME                 VARCHAR2(20 CHAR),
  CC_GENDER               VARCHAR2(1),
  CC_CUSBIRTHDAY          CHAR(8) ,
  CC_PHONENUM             VARCHAR2(11),
  CC_CARDNO               VARCHAR2(30),
  CC_CARDTYPE             VARCHAR2(1),   
  CC_TELLER		          VARCHAR2(20 CHAR), 
  CC_DEPOSITDT			  CHAR(14),
  CC_ADDTIMES			  VARCHAR2(4),
  CC_DISCOUNTTIMES		  VARCHAR2(4),
  CC_DEPOSITAMT			  NUMBER(7,2),
  CC_STARTDT			  CHAR(14),
  CC_ENDDT  			  CHAR(14),
  CC_DISCOUNTTIIME		  VARCHAR2(4),
  CC_REMARK               VARCHAR2(50 CHAR),
  CC_TOTALCOUNT			  VARCHAR2(10),
  CC_LASTDATE			  CHAR(14),
  CC_REGISTDT			  CHAR(14),
  primary key (CC_CUSTOMERID)  
);

COMMENT ON TABLE  CM_CUSINFO 							IS '客户信息表';
COMMENT ON COLUMN CM_CUSINFO.CC_CUSTOMERID 			IS '客户ID';
COMMENT ON COLUMN CM_CUSINFO.CC_NAME					IS '客户姓名';
COMMENT ON COLUMN CM_CUSINFO.CC_GENDER 				IS '性别 M:男 F:女';
COMMENT ON COLUMN CM_CUSINFO.CC_CUSBIRTHDAY 			IS '客户生日:年 月 日';
COMMENT ON COLUMN CM_CUSINFO.CC_PHONENUM 				IS '电话号码';
COMMENT ON COLUMN CM_CUSINFO.CC_CARDNO 				IS '卡号';
COMMENT ON COLUMN CM_CUSINFO.CC_CARDTYPE 				IS '卡的类型：1:次卡 2:月卡  3:年卡';
COMMENT ON COLUMN CM_CUSINFO.CC_TELLER 				IS '操作柜员ID';
COMMENT ON COLUMN CM_CUSINFO.CC_DEPOSITDT				IS '年/月卡 储值日期';
COMMENT ON COLUMN CM_CUSINFO.CC_ADDTIMES 				IS '（次卡）新增次数';
COMMENT ON COLUMN CM_CUSINFO.CC_DISCOUNTTIMES 			IS '（次卡）优惠次数';
COMMENT ON COLUMN CM_CUSINFO.CC_DEPOSITAMT				IS '（年/月卡）储值金额';
COMMENT ON COLUMN CM_CUSINFO.CC_STARTDT 				IS '(年/月卡)起始日期';
COMMENT ON COLUMN CM_CUSINFO.CC_ENDDT 					IS '(年/月卡)截止日期';
COMMENT ON COLUMN CM_CUSINFO.CC_DISCOUNTTIIME 			IS '(年/月卡)优惠时间（天）';
COMMENT ON COLUMN CM_CUSINFO.CC_REMARK 				IS '备注';
COMMENT ON COLUMN CM_CUSINFO.CC_TOTALCOUNT 			IS '储值总次数：（次卡）实际次数+优惠次数';
COMMENT ON COLUMN CM_CUSINFO.CC_LASTDATE 			    IS '最终截止日期：(年卡/月卡)实际时间+优惠时间';
COMMENT ON COLUMN CM_CUSINFO.CC_REGISTDT 			    IS '客户注册日期';