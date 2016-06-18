CREATE TABLE spots (
	spot_id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	username VARCHAR(20) NOT NULL,
	name VARCHAR(50) NOT NULL,
	equipement VARCHAR(50) NOT NULL,
	password VARCHAR(88) NOT NULL,
	password_salt VARCHAR(32),
	country_code CHAR(2) NOT NULL,
	email VARCHAR(50) NULL,
	phone_number VARCHAR(20) NOT NULL UNIQUE KEY,
	signup_timestamp TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
	last_online TIMESTAMP NULL,
	latitude DECIMAL(18,12),
	longitude DECIMAL(18,12),
	INDEX idx_01(spot_id),
	INDEX idx_02(email),
	INDEX idx_03(phone_number),
	INDEX idx_04(latitude, longitude)
)DEFAULT CHARSET=utf8mb4;