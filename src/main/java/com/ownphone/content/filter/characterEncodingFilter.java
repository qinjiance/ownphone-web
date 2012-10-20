/**
 * 
 */
package com.ownphone.content.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author Jiance Qin
 *
 */
public class characterEncodingFilter implements Filter {

	private String characterEncoding = null;
	private boolean enabled = false;
	
	/* (non-Javadoc)
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
	public void destroy() {
		
		characterEncoding = null;
		enabled = false;
		
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {

		if( enabled && characterEncoding != null){
			arg0.setCharacterEncoding(characterEncoding);
			arg1.setCharacterEncoding(characterEncoding);
		}
		
		arg2.doFilter(arg0, arg1);
		
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig arg0) throws ServletException {

		enabled = arg0.getInitParameter("enabled").equalsIgnoreCase("true");
		
		characterEncoding = arg0.getInitParameter("characterEncoding");
		
	}

}
