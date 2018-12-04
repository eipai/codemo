CREATE TABLE `simple_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `remark` varchar(100) DEFAULT NULL,
  `time_long` bigint(20) DEFAULT NULL,
  `time_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
