package au.com.aussle.webstore.interceptor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuditingInterceptor extends HandlerInterceptorAdapter {
	
	Logger logger = Logger.getLogger("auditLogger");
	private String user;
	private String productID;

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if(request.getRequestURI().endsWith("products/add") && request.getMethod().equals("POST")){
			user = request.getRemoteUser();
			productID = request.getParameterValues("productID")[0];
		}
		
		return true;
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		if(request.getRequestURI().endsWith("products/add") && response.getStatus() == 302){
			logger.info(String.format("A New product [%s] Added by %s on %s", productID, user, getCurrentTime()));
		}
	}

	private String getCurrentTime() {
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy 'at' hh:mm:ss");
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
			
		return formatter.format(calendar.getTime());
	}

}
