/**
 * 
 */
package com.ownphone.util;

/**
 * @author Jiance Qin
 *
 */
public class ValidatorException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7953815099700200439L;

	/**
	 * 
	 */
	public ValidatorException() {
	}

	/**
	 * @param message
	 */
	public ValidatorException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public ValidatorException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ValidatorException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public ValidatorException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
