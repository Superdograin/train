DROP TABLE IF EXISTS `station`;
CREATE TABLE `train_business`.`station`  (
  `id` bigint NOT NULL COMMENT 'id',
  `name` varchar(20) NOT NULL COMMENT '站名',
  `name_pinyin` varchar(50) NOT NULL COMMENT '站名拼音',
  `name_py` varchar(20) NOT NULL COMMENT '站名拼音首字母',
  `create_time` datetime(3) NULL COMMENT '新增时间',
  `update_time` datetime(3) NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_unique` (`name`)
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COMMENT = '车站';