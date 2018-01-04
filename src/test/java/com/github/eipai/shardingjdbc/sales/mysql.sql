 CREATE TABLE sales_01.pay_order_01 (
	id BIGINT NULL AUTO_INCREMENT,
	request_no varchar(100) NULL,
	bank_seq varchar(100) NULL,
	amount BIGINT NULL,
	remark varchar(100) NULL,
	CONSTRAINT pay_order_PK PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8
COLLATE=utf8_general_ci ;