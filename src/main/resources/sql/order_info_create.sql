CREATE TABLE `order_info` (
  `order_id` bigint NOT NULL AUTO_INCREMENT,
  `order_price` bigint NOT NULL,
  `create_time` timestamp NOT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`order_id`),
  KEY `order_info_FK` (`user_id`),
  CONSTRAINT `order_info_FK` FOREIGN KEY (`user_id`) REFERENCES `user_info` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci