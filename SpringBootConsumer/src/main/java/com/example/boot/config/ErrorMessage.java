package com.example.boot.config;

public class ErrorMessage {
	
	private Integer code;
	private String message;
	public ErrorMessage(Integer code, String message) {
		this.code = code;
		this.message = message;
	}
	/**
	 * @return the code
	 */
	public Integer getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(Integer code) {
		this.code = code;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ErrorMessage [code=" + code + ", message=" + message + "]";
	}
	
}
