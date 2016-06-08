-- All the SQL commands should be written in capitals to ease the differentiation of names
-- All the column names in small cases with underscore for two worded columns
-- This examples includes different types of columns
-- VARCHAR and CHAR are two different types, both represent a String. CHAR column always 
-- have a fixed length data whereas VARCHAR column data could be shorted than defined.
-- This example is present in the database on Amazon to test with.
CREATE TABLE example (
	example_id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	username VARCHAR(20) NOT NULL,
	firstname VARCHAR(50) NOT NULL,
	lastname VARCHAR(50) NOT NULL,
	country_code CHAR(2) NOT NULL,
	birthdate DATE NOT NULL,
	email VARCHAR(50) NULL,
	mobile_number VARCHAR(20) NOT NULL UNIQUE KEY,
	signup_timestamp TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
	last_online TIMESTAMP NULL,
	latitude DECIMAL(18,12),
	longitude DECIMAL(18,12),
	INDEX idx_01(email),
	INDEX idx_02(mobile_number),
	INDEX idx_03(latitude, longitude)
)