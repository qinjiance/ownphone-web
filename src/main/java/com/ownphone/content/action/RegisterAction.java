/**
 * 
 */
package com.ownphone.content.action;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import com.opensymphony.xwork2.ActionSupport;
import com.ownphone.content.bean.CommonUserRegisterForm;
import com.ownphone.content.dao.AdministratorDAO;
import com.ownphone.content.dao.CommonUserDAO;
import com.ownphone.content.dao.impl.AdministratorDAOImpl;
import com.ownphone.content.dao.impl.CommonUserDAOImpl;
import com.ownphone.content.po.CommonUser;
import com.ownphone.util.FormValidator;

/**
 * @author Jiance Qin
 * 
 */
public class RegisterAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3175269986341356751L;

	static private Map<String, String> captchaMap = new HashMap<String, String>();
	private InputStream captchaImageIS;

	/**
	 * a form bean instance submitted by register.jsp
	 */
	private CommonUserRegisterForm commonUserRegister;

	private CommonUserDAO commonUserDAO = new CommonUserDAOImpl();

	private AdministratorDAO administratorDAO = new AdministratorDAOImpl();

	/**
	 * 
	 */
	public RegisterAction() {

	}

	/**
	 * @return the captchaImageIS
	 */
	public InputStream getCaptchaImageIS() {
		return captchaImageIS;
	}

	/**
	 * @param captchaImageIS
	 *            the captchaImageIS to set
	 */
	public void setCaptchaImageIS(InputStream captchaImageIS) {
		this.captchaImageIS = captchaImageIS;
	}

	/**
	 * @return the commonUserRegister
	 */
	public CommonUserRegisterForm getCommonUserRegister() {
		return commonUserRegister;
	}

	/**
	 * @param commonUserRegister
	 *            the commonUserRegister to set
	 */
	public void setCommonUserRegister(CommonUserRegisterForm commonUserRegister) {
		this.commonUserRegister = commonUserRegister;
	}

	/**
	 * @return the commonUserDAO
	 */
	public CommonUserDAO getCommonUserDAO() {
		return commonUserDAO;
	}

	/**
	 * @param commonUserDAO
	 *            the commonUserDAO to set
	 */
	public void setCommonUserDAO(CommonUserDAO commonUserDAO) {
		this.commonUserDAO = commonUserDAO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@Override
	public String execute() throws Exception {
		
		// Validates captcha
		if (!validateCaptcha()){
			return "input";
		}

		// Validates register info form
		if (!validateRegisterForm()) {
			return "input";
		}

		try {
			// Check if the new useraccount is exist in CommonUser and
			// Administrator table in database
			if (commonUserDAO.isUseraccountExist(commonUserRegister
					.getUseraccount())
					|| administratorDAO.isAdminaccountExist(commonUserRegister
							.getUseraccount())) {
				this.addFieldError("commonUserRegister.useraccount",
						"用户名已存在，请使用其他用户名！");
				return "input";
			}

		} catch (Exception e) {
			this.addActionMessage("操作失败，请重新注册！");
			return "registerfailed";
		}

		// Set new CommonUser instantce
		CommonUser newCommonUser = new CommonUser();
		newCommonUser.setUseraccount(commonUserRegister.getUseraccount());
		newCommonUser.setPassword(commonUserRegister.getPassword());
		newCommonUser.setNickname(commonUserRegister.getNickname());
		newCommonUser.setEmail(commonUserRegister.getEmail());
		newCommonUser.setMobilephone(commonUserRegister.getMobilephone());
		newCommonUser.setRealname(commonUserRegister.getRealname());
		newCommonUser.setPrivilege("common");
		newCommonUser.setRegistertimemillis(Long.valueOf(System
				.currentTimeMillis()));

		if (!commonUserDAO.addNewCommonUser(newCommonUser)) {
			this.addActionMessage("操作失败，请重新注册！");
			return "registerfailed";
		} else {
			this.addActionMessage("恭喜您，注册成功！请登录!");
			return "registersuccess";
		}
	}

	/**
	 * Validate register form.
	 * 
	 * @return true if no error, otherwise, false
	 */
	private boolean validateRegisterForm() {

		boolean checkPassed = true;

		String formBeanName = "commonUserRegister";

		// Validate the user account field
		if (!FormValidator.validateFieldUsingRegex(this, formBeanName,
				commonUserRegister, "useraccount",
				FormValidator.CHECK_USERACCOUNT_REGEX, false, "请输入用户名！",
				"用户名只能由1-12位的英文、数字和下划线组成，且只能以下划线或英文开头！")) {
			checkPassed = false;
		}

		// Validate the password field
		if (!FormValidator.validateFieldUsingRegex(this, formBeanName,
				commonUserRegister, "password",
				FormValidator.CHECK_PASSWORD_REGEX, false, "请输入密码！",
				"密码只能由6-18位的英文、数字和下划线组成！")) {
			checkPassed = false;
		}

		// Validate the repeatpassword field
		if (!commonUserRegister.getPassword().equals(
				commonUserRegister.getRepeatpassword())) {
			checkPassed = false;
			this.addFieldError(formBeanName + ".repeatpassword", "重复密码与密码不一致！");
		}

		// Validate the nickname field
		if (!FormValidator.validateFieldUsingRegex(this, formBeanName,
				commonUserRegister, "nickname",
				FormValidator.CHECK_NICKNAME_REGEX, true, null,
				"昵称只能由1到10个字，包括中文、英文、数字、下划线和它们中间的空格组成！")) {
			checkPassed = false;
		}

		// Validate the email field
		if (!FormValidator.validateFieldUsingRegex(this, formBeanName,
				commonUserRegister, "email", FormValidator.CHECK_EMAIL_REGEX,
				true, null, "电子邮箱地址格式有误！")) {
			checkPassed = false;
		}

		// Validate the mobilephone field
		if (!FormValidator.validateFieldUsingRegex(this, formBeanName,
				commonUserRegister, "mobilephone",
				FormValidator.CHECK_MOBILEPHONE_REGEX, true, null,
				"手机号码只能由以1开头的11个数字组成！")) {
			checkPassed = false;
		}

		// Validate the realname field
		if (!FormValidator.validateFieldUsingRegex(this, formBeanName,
				commonUserRegister, "realname",
				FormValidator.CHECK_REALNAME_REGEX, true, null,
				"中文姓名只能由中文组成，英文名只能由英文和它们中间的空格组成！")) {
			checkPassed = false;
		}

		return checkPassed;
	}

	/**
	 * Get a captcha image into a response outputstream.
	 * 
	 * @return string "getcaptchaimagesuccess" point to a struts2 stream
	 *         response
	 */
	public String getCaptchaImage() {

		// Generate captcha image
		captchaImageIS = new ByteArrayInputStream(generateCaptchaImage());

		return "getcaptchaimagesuccess";
	}

	/**
	 * Generate a captcha image using com.google.code.kaptcha.kaptcha lib.
	 * 
	 * @return a byte array containing the image data
	 */
	private byte[] generateCaptchaImage() {

		// Use session id as captcha key
		String captchaKey = ServletActionContext.getRequest().getSession()
				.getId();

		if (captchaKey != null && !captchaKey.isEmpty()) {
			// Generate captcha text
			DefaultKaptcha captchaProducer = new DefaultKaptcha();

			// Set image parameters
			Properties captchaProp = new Properties();
			captchaProp.put("kaptcha.image.width", "120");
			captchaProp.put("kaptcha.image.height", "40");
			captchaProp.put("kaptcha.textproducer.font.size", "30");
			captchaProp.put("kaptcha.noise.color", "255,0,0");
			captchaProp.put("kaptcha.background.clear.from", "127,127,127");
			captchaProp.put("kaptcha.background.clear.to", "255,255,255");
			captchaProp.put("kaptcha.textproducer.char.string",
					"0123456789abcdefghijklmnopqrstuvwxyz");
			captchaProp.put("kaptcha.textproducer.char.length", "5");
			captchaProp.put("kaptcha.textproducer.impl",
					"com.google.code.kaptcha.text.impl.DefaultTextCreator");
			captchaProp.put("kaptcha.obscurificator.impl",
					"com.google.code.kaptcha.impl.FishEyeGimpy");

			// Set image
			captchaProducer.setConfig(new Config(captchaProp));

			String captchaText = captchaProducer.createText();
			captchaMap.put(captchaKey, captchaText);

			// Generate captcha image
			BufferedImage captchaImage = captchaProducer
					.createImage(captchaText);

			ByteArrayOutputStream os = new ByteArrayOutputStream();

			try {
				ImageIO.write(captchaImage, "jpg", os);
			} catch (IOException e) {
				e.printStackTrace();
			}

			return os.toByteArray();
		} else {
			return null;
		}
	}

	private boolean validateCaptcha() {

		HttpServletRequest request = ServletActionContext.getRequest();

		String captchaKey = request.getSession().getId();

		if (captchaKey != null & !captchaKey.isEmpty()) {

			String captchaText = captchaMap.get(captchaKey);

			String inputCaptcha = request.getParameter("captchatext");

			if (inputCaptcha == null || inputCaptcha.isEmpty()) {

				this.addFieldError("captchatext", "请输入验证码！");
				captchaMap.remove(captchaKey);
				return false;
			} else if (inputCaptcha.trim().equals(captchaText)) {

				captchaMap.remove(captchaKey);
				return true;
			} else {

				this.addFieldError("captchatext", "验证码错误！");
				captchaMap.remove(captchaKey);
				return false;
			}
		} else {

			this.addFieldError("captchatext", "验证码错误！");
			return false;
		}
	}
}
