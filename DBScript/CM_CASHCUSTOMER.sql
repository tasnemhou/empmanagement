CREATE TABLE CM_CASHCUSTOMER(
  CC_NAME 				VARCHAR2(20 CHAR),
  CC_GENDER				VARCHAR2(1),
  CC_PHONENUM 			VARCHAR2(11),
  CC_AMOUNT				NUMBER(7,2),
  CC_CONSUMEDT			VARCHAR2(14),
  CC_REMARK				VARCHAR2(100 CHAR),
  CC_TELLER				VARCHAR2(20 CHAR)
);
COMMENT ON TABLE CM_CASHCUSTOMER 					IS 'ɢ�����Ѽ�¼��';
COMMENT ON COLUMN CM_CASHCUSTOMER.CC_NAME 			IS '����';
COMMENT ON COLUMN CM_CASHCUSTOMER.CC_GENDER  			IS '��Ա�Ա�: F:Ů M:��';
COMMENT ON COLUMN CM_CASHCUSTOMER.CC_PHONENUM 		IS '�绰����';
COMMENT ON COLUMN CM_CASHCUSTOMER.CC_AMOUNT 		IS '���ѽ��';
COMMENT ON COLUMN CM_CASHCUSTOMER.CC_CONSUMEDT 	IS '��������';
COMMENT ON COLUMN CM_CASHCUSTOMER.CC_REMARK 		IS '��ע';
COMMENT ON COLUMN CM_CASHCUSTOMER.CC_TELLER 		IS '������Ա';