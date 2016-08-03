
DROP TABLE IF EXISTS `bm_seq_generator`;
DROP TABLE IF EXISTS `bm_user_account`;
DROP TABLE IF EXISTS `bm_trans_info`;
DROP TABLE IF EXISTS `bm_account_detail`;

CREATE TABLE `bm_seq_generator` (
  `id` int(11) PRIMARY KEY NOT NULL,
  `code` varchar(32) NOT NULL,
  `recurring` char(1) NOT NULL DEFAULT '0' COMMENT '0-false;1-true;',
  `latest_value` bigint(20) NOT NULL,
  `begin_value` bigint(20) NOT NULL,
  `end_value` bigint(20) NOT NULL,
  `step` bigint(20) NOT NULL,
  `remark` varchar(64) DEFAULT NULL,
  `version` int(11) NOT NULL DEFAULT 0,
  `insert_time` datetime NOT NULL,
  `update_time` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `bm_user_account` (
  `id` bigint(20) PRIMARY KEY NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `money` decimal(16,4) NOT NULL DEFAULT 0,
  `locked` char(1) NOT NULL DEFAULT '0' COMMENT '0-false;1-true;',
  `remark` varchar(64) DEFAULT NULL,
  `version` int(11) NOT NULL DEFAULT 0,
  `insert_time` datetime NOT NULL,
  `update_time` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `bm_trans_info` (
  `id` bigint(20) PRIMARY KEY NOT NULL,
  `order_id` varchar(32) NOT NULL,
  `amount` decimal(16,4) NOT NULL DEFAULT 0,
  `payer` bigint(20) NOT NULL,
  `receiver` bigint(20) DEFAULT NULL,
  `receivers_info` varchar(1000) DEFAULT NULL,
  `account_start` char(1) NOT NULL DEFAULT '0' COMMENT '0-false;1-true;',
  `account_finish` char(1) NOT NULL DEFAULT '0' COMMENT '0-false;1-true;',
  `tran_success` char(1) NOT NULL DEFAULT '0' COMMENT '0-false;1-true;',
  `resp_code` varchar(16) DEFAULT NULL,
  `acct_proc_time` datetime DEFAULT NULL,
  `remark` varchar(64) DEFAULT NULL,
  `version` int(11) NOT NULL DEFAULT 0,
  `insert_time` datetime NOT NULL,
  `update_time` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `bm_account_detail` (
  `id` bigint(20) PRIMARY KEY NOT NULL,
  `tran_id` bigint(20) NOT NULL,
  `indx` int(11) NOT NULL,
  `acct_id` bigint(20) NOT NULL,
  `amount` decimal(16,4) NOT NULL,
  `cd_type` char(2) NOT NULL,
  `balance` decimal(16,4) NOT NULL,
  `related_acct_id` bigint(20) DEFAULT NULL,
  `remark` varchar(64) DEFAULT NULL,
  `insert_time` datetime NOT NULL,
  `update_time` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO bm_seq_generator (id,code,recurring,latest_value,begin_value,end_value,step,remark,version,insert_time,update_time) VALUES (1,'tran_info','0',1,1,100000000,10,NULL,0,now(),now());
INSERT INTO bm_seq_generator (id,code,recurring,latest_value,begin_value,end_value,step,remark,version,insert_time,update_time) VALUES (2,'tran_detail','0',1,1,100000000,10,NULL,0,now(),now());

INSERT INTO bm_user_account (id,user_id,money,remark,locked,version,insert_time,update_time) VALUES (1,1,10000.0000,NULL,'0',0,now(),now());
INSERT INTO bm_user_account (id,user_id,money,remark,locked,version,insert_time,update_time) VALUES (2,2,10000.0000,NULL,'0',0,now(),now());
