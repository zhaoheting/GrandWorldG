CREATE TABLE zhtdb.user_info (
	user_id BIGINT auto_increment NOT NULL,
	username varchar(20) NOT NULL,
	password varchar(50) NOT NULL,
	create_time TIMESTAMP NOT NULL,
	last_update_time TIMESTAMP NOT NULL,
	domain_username varchar(30) DEFAULT It is used for encryption. NULL,
	CONSTRAINT user_info_PK PRIMARY KEY (user_id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_0900_ai_ci;