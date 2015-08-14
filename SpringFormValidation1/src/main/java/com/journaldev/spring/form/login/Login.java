package com.journaldev.spring.form.login;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Class for customer and employee login
 * <p>
 */
public class Login {
	
	/**
	 * User 
	 * <p>
	 */
	private String user;
	/**
	 * password 
	 * <p>
	 */
	private String password;
	
	/**
	 * Login constructor
	 * <p>
	 */
	public Login(final String user, final String password) {
		this.user = user;
		this.password = password;
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Empty constructor
	 * <p>
	 */
	public Login(){
		/**
		 * Empty constructor
		 * <p>
		 */
	}

	/**
	 * Getter for user 
	 * <p>
	 */
	public String getUser(){
		return user;
	}
	
	/**
	 * Setter for user 
	 * <p>
	 */
	public void setUser(final String user){
		this.user=user;
	}
	
	/**
	 * Getter for password 
	 * <p>
	 */
	public String getPassword(){
		return password;
	}
	
	/**
	 * Setter for password 
	 * <p>
	 */
	public void setPassword(final String password){
		this.password=password;
	}
	
	/**
	 * Return a MD5 hash, used for password 
	 * <p>
	 */
	 public String returnedHash(final String pass) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		byte[] bytesOfPass;
		MessageDigest messaged;
		messaged = MessageDigest.getInstance("MD5");
		bytesOfPass = pass.getBytes("UTF-8");
		messaged.reset();
		messaged.update(bytesOfPass);
		final byte[] thedigest = messaged.digest();
		final BigInteger bigInt = new BigInteger(1,thedigest);
		String hashtext = bigInt.toString(16);
		while(hashtext.length() < 32){
			hashtext = "0"+hashtext;
		}
		return hashtext;
	 }
}
