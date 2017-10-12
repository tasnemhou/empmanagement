CREATE TABLE CM_DEPOSIT (
  CD_CUSTOMERID 			VARCHAR2(100),
  CD_DEPOSITDT				CHAR(14),
  CD_DEPOSITAMT 			NUMBER(7,2),
  CD_CARDTYPE 				VARCHAR2(1),
  CD_ADDTIMES				VARCHAR2(4),
  CD_DISCOUNTTIMES			VARCHAR2(4),
  CD_DISCOUNTTIME			VARCHAR2(4),
  CD_TELLER 				VARCHAR2(20 CHAR),
  CD_STARTDT				CHAR(14),
  CD_ENDDT					CHAR(14),
  CD_REMARK 				VARCHAR2(50 CHAR),
  CD_RECORDDT				CHAR(14),
  CC_TOTALCOUNT			  	VARCHAR2(10),
  CC_LASTDATE			  	CHAR(14)
);
COMMENT ON TABLE CM_DEPOSIT 						 IS '��ֵ��ϸ��';
COMMENT ON COLUMN CM_DEPOSIT.CD_DEPOSITDT 			 IS '��ֵ����';
COMMENT ON COLUMN CM_DEPOSIT.CD_DEPOSITAMT 		 IS '��ֵ���';
COMMENT ON COLUMN CM_DEPOSIT.CD_CARDTYPE   		 IS '������';
COMMENT ON COLUMN CM_DEPOSIT.CD_ADDTIMES   		 IS '��������';
COMMENT ON COLUMN CM_DEPOSIT.CD_DISCOUNTTIMES  	 IS '�Żݴ���';
COMMENT ON COLUMN CM_DEPOSIT.CD_DISCOUNTTIME   	 IS '����/�¿����Ż�ʱ��';
COMMENT ON COLUMN CM_DEPOSIT.CD_TELLE  			 IS '������Ա';
COMMENT ON COLUMN CM_DEPOSIT.CD_STARTDT   			 IS '��/�¿���ʼ�Ʒ�����';
COMMENT ON COLUMN CM_DEPOSIT.CD_ENDDT     			 IS '��/�¿���ֹ�Ʒ�����';
COMMENT ON COLUMN CM_DEPOSIT.CD_RECORDDT     		 IS '��������';
COMMENT ON COLUMN CM_DEPOSIT.CC_TOTALCOUNT    		 IS '��ֵ�ܴ��������ο���ʵ�ʴ���+�Żݴ���';
COMMENT ON COLUMN CM_DEPOSIT.CC_LASTDATE     		 IS '��ֵ��ʱ�䣺(�꿨/�¿�)ʵ��ʱ��+�Ż�ʱ��';