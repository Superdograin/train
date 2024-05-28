DROP TABLE IF EXISTS `member`;
CREATE TABLE `member`  (
  `id` bigint NOT NULL COMMENT 'id',
  `mobile` varchar(11) NULL COMMENT '手机号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `mobile_unique` (`mobile`)
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COMMENT = '会员表';

DROP TABLE IF EXISTS `passenger`;
CREATE TABLE `train_member`.`passenger`  (
  `id` bigint NOT NULL COMMENT 'id',
  `member_id` bigint NOT NULL COMMENT '会员id',
  `name` varchar(20) NOT NULL COMMENT '姓名',
  `id_card` varchar(18) NOT NULL COMMENT '身份证',
  `type` char(1) NOT NULL COMMENT '旅客类型 | 枚举[PassengerTypeEnum]',
  `create_time` datetime(3) NULL COMMENT '新增时间',
  `update_time` datetime(3) NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  INDEX `member_id_index`(`member_id`)
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COMMENT = '乘车人';