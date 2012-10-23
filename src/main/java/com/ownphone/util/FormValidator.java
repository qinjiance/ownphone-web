/**
 * 
 */
package com.ownphone.util;

import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.opensymphony.xwork2.ActionSupport;

/**
 * Form validator is a tool class, provide a lot of regular expressions and some
 * validate methods to validate a form's input field.
 * 
 * @author Jiance Qin
 * 
 */
public class FormValidator {

	/**
	 * Regular expression to check nickname field, only allows Chinese, English,
	 * numbers, underline. And blank in the middle. Also the length ranges from
	 * 1 to 10.
	 */
	public static final String CHECK_NICKNAME_REGEX = "^[\\s\\w\u4e00-\u9fa5]{1,10}$";

	/**
	 * Regular expression to check email field.
	 */
	public static final String CHECK_EMAIL_REGEX = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";

	/**
	 * Regular expression to check mobilephone field, only allows 11 numbers
	 * stated with 1.
	 */
	public static final String CHECK_MOBILEPHONE_REGEX = "^1\\d{10}$";

	/**
	 * Regular expression to check realname field, only allows Chinese or
	 * English and blanks in the middle.
	 */
	public static final String CHECK_REALNAME_REGEX = "(^[\u4e00-\u9fa5]+$)|(^[\\sa-zA-Z]+$)";

	/**
	 * Regular expression to check useraccount field, only allows English,
	 * numbers and underline. And only allow starts with underline or English.
	 * Also the length ranges from 1 to 12.
	 */
	public static final String CHECK_USERACCOUNT_REGEX = "^[a-zA-Z_]{1}\\w{0,11}$";

	/**
	 * Regular expression to check password field, only allows English, numbers
	 * and underline. And the length ranges from 6 to 18.
	 */
	public static final String CHECK_PASSWORD_REGEX = "^\\w{6,18}$";

	/**
	 * Regular expression to check bank name field, only allows Chinese,
	 * English, numbers and blanks in the middle.
	 */
	public static final String CHECK_BANKNAME_REGEX = "^[\\sa-zA-Z0-9\u4e00-\u9fa5]+$";

	/**
	 * Regular expression to check bank account field, only allows numbers and
	 * blanks in the middle.
	 */
	public static final String CHECK_BANKACCOUNT_REGEX = "^[\\s0-9]+$";

	/**
	 * 
	 */
	private FormValidator() {

	}

	/**
	 * Validate a field of a form in an action, and give the result.
	 * 
	 * @param actionToSetFieldError
	 *            action extends ActionSupport which to use addFieldError to set
	 *            the error message
	 * @param formBeanName
	 *            a string to represent the form bean's name
	 * @param formBeanInstance
	 *            a object instance containing the form's input data, which is
	 *            to be validate
	 * @param fieldName
	 *            a string to represent the field name of the form bean
	 * @param regularExpression
	 *            a string represent a regular expression which is used to
	 *            validate the field's value
	 * @param isAllowNull
	 *            A boolean to set if this field allows null value. If true,when
	 *            field's value is null, will set the field's value to null then
	 *            update to database; If false, will return false and set the
	 *            null error message to the form's field.
	 * @param nullErrorMessage
	 *            a string to represent the error message when the field's value
	 *            it null
	 * @param regexNotMatchedErrorMessage
	 *            a string to represent the error message when the field's value
	 *            is not matched the regular expression
	 * @return When parameter isAllowNull is true, method will return true if
	 *         field's value is null or field's value is matched the regular
	 *         expression; Method will return false if field's value is not
	 *         matched the regular expression.
	 *         <p>
	 *         When parameter isAllowNull is false, method will return true only
	 *         if field's value is matched the regular expression; Method will
	 *         return false if field's value is null or field's value is not
	 *         matched the regular expression.
	 *         </p>
	 *         <h1>
	 *         NOTE: Any exception occurs inner method will return false.</h1>
	 */
	public static boolean validateFieldUsingRegex(
			ActionSupport actionToSetFieldError, String formBeanName,
			Object formBeanInstance, String fieldName,
			String regularExpression, boolean isAllowNull,
			String nullErrorMessage, String regexNotMatchedErrorMessage) {

		// Get class
		Class<? extends Object> formClass = formBeanInstance.getClass();

		// Get private field reflect object
		Field field;
		try {
			field = formClass.getDeclaredField(fieldName);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
			return false;
		} catch (SecurityException e) {
			e.printStackTrace();
			return false;
		}

		// Open access
		field.setAccessible(true);
		// Get field value
		String fieldValue;
		try {
			fieldValue = (String) field.get(formBeanInstance);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return false;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			return false;
		}

		if (fieldValue == null || "".equals(fieldValue.trim())) {
			if (isAllowNull) {
				try {
					field.set(formBeanInstance, null);
					return true;
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
					return false;
				} catch (IllegalAccessException e) {
					e.printStackTrace();
					return false;
				}

			} else {
				actionToSetFieldError.addFieldError(formBeanName + "."
						+ fieldName, nullErrorMessage);
				return false;
			}

		} else {
			// Removes the blanks at the beginning and the end.
			String checkingString = fieldValue.trim();

			// Validates the characters.
			Pattern checkPattern = Pattern.compile(regularExpression);
			Matcher checkMatcher = checkPattern.matcher(checkingString);

			if (checkMatcher.matches()) {
				// Set the field value whose blanks had been removed above.
				try {
					field.set(formBeanInstance, checkingString);
					return true;
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
					return false;
				} catch (IllegalAccessException e) {
					e.printStackTrace();
					return false;
				}

			} else {
				actionToSetFieldError.addFieldError(formBeanName + "."
						+ fieldName, regexNotMatchedErrorMessage);
				return false;
			}
		}
	}

	/**
	 * Validate a radio of a form in an action, and give the result.
	 * 
	 * @param actionToSetFieldError
	 *            action extends ActionSupport which to use addFieldError to set
	 *            the error message
	 * @param formBeanName
	 *            a string to represent the form bean's name
	 * @param formBeanInstance
	 *            a object instance containing the form's input data, which is
	 *            to be validate
	 * @param fieldName
	 *            a string to represent the field name of the form bean
	 * @param isAllowNull
	 *            A boolean to set if this field allows null value. If true,
	 *            when field's value is null, will set the field's value to null
	 *            then update to database; If false, will return false and set
	 *            the null error message to the form's field.
	 * @param nullErrorMessage
	 *            a string to represent the error message when the field's value
	 *            it null
	 * @return When parameter isAllowNull is true, method will return true if
	 *         field's value is null or field's value is not null.
	 *         <p>
	 *         When parameter isAllowNull is false, method will return true only
	 *         if field's value is not null; Method will return false if field's
	 *         value is null.
	 *         </p>
	 *         <h1>
	 *         NOTE: Any exception occurs inner method will return false.</h1>
	 */
	public static boolean validateRadio(ActionSupport actionToSetFieldError,
			String formBeanName, Object formBeanInstance, String fieldName,
			boolean isAllowNull, String nullErrorMessage) {

		// Get class
		Class<? extends Object> formClass = formBeanInstance.getClass();

		// Get private field reflect object
		Field field;
		try {
			field = formClass.getDeclaredField(fieldName);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
			return false;
		} catch (SecurityException e) {
			e.printStackTrace();
			return false;
		}

		// Open private accessing
		field.setAccessible(true);
		// Get field value
		String fieldValue;
		try {
			fieldValue = (String) field.get(formBeanInstance);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return false;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			return false;
		}

		if (fieldValue == null || "".equals(fieldValue.trim())) {
			if (isAllowNull) {
				try {
					field.set(formBeanInstance, null);
					return true;
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
					return false;
				} catch (IllegalAccessException e) {
					e.printStackTrace();
					return false;
				}

			} else {
				actionToSetFieldError.addFieldError(formBeanName + "."
						+ fieldName, nullErrorMessage);
				return false;
			}

		} else {
			// Removes the blanks at the beginning and the end.
			String checkingString = fieldValue.trim();

			try {
				field.set(formBeanInstance, checkingString);
				return true;
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
				return false;
			} catch (IllegalAccessException e) {
				e.printStackTrace();
				return false;
			}
		}
	}

	/**
	 * Validates a String using regular expression.
	 * 
	 * @param validatingString
	 *            String will be validated
	 * @param regularExpression
	 *            regular expression
	 * 
	 * @return true if the String matches the regularExpression, if not, false.
	 */
	public static boolean validateStringUsingRegex(String validatingString,
			String regularExpression) {

		if (validatingString == null || validatingString.trim().isEmpty()) {

			return false;
		} else {
			// Removes the blanks at the beginning and the end.
			String checkingString = validatingString.trim();

			// Validates the characters.
			Pattern checkPattern = Pattern.compile(regularExpression);
			Matcher checkMatcher = checkPattern.matcher(checkingString);

			if (checkMatcher.matches()) {

				return true;

			} else {

				return false;
			}
		}
	}

}
