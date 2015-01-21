package au.com.aussle.webstore.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class PromoCodeInterceptor extends HandlerInterceptorAdapter {

	Logger logger = Logger.getLogger(this.getClass());
	
	private String promoCode;
	private String errorRedirect;
	private String offerRedirect;
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String givenPromoCode = request.getParameterValues("promo") == null ? "" : request.getParameterValues("promo")[0];
		
//		logger.info("getting promoCode : " + givenPromoCode);
		
		if(request.getRequestURI().endsWith("products/specialOffer")){
			if(givenPromoCode.equals(promoCode)){
//				logger.info("Successful : " + request.getContextPath() + "/" + offerRedirect + "/all");
				response.sendRedirect(request.getContextPath() + "/" + offerRedirect + "/all");
			}else{
//				logger.info("Failed : " + errorRedirect);
				response.sendRedirect(errorRedirect);
			}
			return false;
		}
		return true;
	}

	public String getPromoCode() {
		return promoCode;
	}

	public void setPromoCode(String promoCode) {
		this.promoCode = promoCode;
	}

	public String getErrorRedirect() {
		return errorRedirect;
	}

	public void setErrorRedirect(String errorRedirect) {
		this.errorRedirect = errorRedirect;
	}

	public String getOfferRedirect() {
		return offerRedirect;
	}

	public void setOfferRedirect(String offerRedirect) {
		this.offerRedirect = offerRedirect;
	}

}
