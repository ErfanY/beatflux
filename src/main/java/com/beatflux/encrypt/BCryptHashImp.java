package com.beatflux.encrypt;

import org.mindrot.jbcrypt.BCrypt;

public class BCryptHashImp implements IHashGenerator {
	public String generateSalt(int log_rounds ) {
		return BCrypt.gensalt(log_rounds);
	}
	public String hash(String password, String salt) {
		return BCrypt.hashpw(password, salt);
	}
	public String specialBcryptHash(String password, String salt) {
	   return BCrypt.hashpw(password, salt);
	}
	public boolean checkHash(String plaintext, String hashed) {
		return BCrypt.checkpw(plaintext, hashed);
	}
}
