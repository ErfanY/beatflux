CREATE TABLE bookings (
	booking_id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	user_id INT NOT NULL,
	spot_id INT NOT NULL,
	booking_timestamp TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
	booking_duration DATETIME,
	INDEX idx_01(user_id),
	INDEX idx_02(spot_id),
	INDEX idx_03(booking_duration),
)DEFAULT CHARSET=utf8mb4;