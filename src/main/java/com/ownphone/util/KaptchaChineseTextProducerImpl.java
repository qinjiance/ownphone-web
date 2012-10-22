/**
 * 
 */
package com.ownphone.util;

import java.util.Random;

import com.google.code.kaptcha.text.TextProducer;

/**
 * @author Jiance Qin
 *
 */
public class KaptchaChineseTextProducerImpl implements TextProducer {

	/**
	 * 
	 */
	public KaptchaChineseTextProducerImpl() {
		
	}

	private String[] simplifiedChineseTexts = new String[]{
			"小淑淑","小可可","淑可可","可淑淑"
	};
	
	/* (non-Javadoc)
	 * @see com.google.code.kaptcha.text.TextProducer#getText()
	 */
	@Override
	public String getText() {
		return simplifiedChineseTexts[new Random().nextInt(simplifiedChineseTexts.length)];
	}

}
