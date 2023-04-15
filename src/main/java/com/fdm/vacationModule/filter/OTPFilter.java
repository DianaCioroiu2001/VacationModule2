

	package com.fdm.vacationModule.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fdm.vacationModule.dao.entities.UserAccountEntity;
import com.fdm.vacationModule.service.UserAccountService;

@WebFilter("/vacationRequest")
public class OTPFilter implements Filter {

    public OTPFilter() {
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response; 
		
		HttpSession session = req.getSession(false);
		if (session == null) {
			resp.setStatus(403);
			return;
		} else {
			String username = (String)session.getAttribute("username");
			if (username == null) {
				resp.setStatus(403);
				return;
			} else {
				String OTP = req.getHeader("otp");
				if (OTP != null) {
					UserAccountEntity uae = UserAccountService.getInstance().getUserAccount(username);
					if (!OTP.equals(uae.getOtp())) {
						resp.setStatus(403);
						return;
					}
				} else {
					resp.setStatus(403);
					return;
				}
			}
		}

		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
