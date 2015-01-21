package au.com.aussle.webstore.exception;

public class InvalidCartException extends RuntimeException {

	private static final long serialVersionUID = -2010140722235626454L;
	
	private String cartID;

	public InvalidCartException(String cartID){
		this.cartID = cartID;
	}

	public String getCartID() {
		return cartID;
	}
	
}
