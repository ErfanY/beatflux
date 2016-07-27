package com.beatflux.encrypt;

public interface IHashGenerator {
	public String generateSalt(int log_rounds);
	public String hash(String password, String salt);
	public boolean checkHash(String plaintext, String hashed);
}
