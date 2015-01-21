package au.com.aussle.webstore.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

public class Cart implements Serializable {

	private static final long serialVersionUID = -738320457580833374L;

	private Logger logger = Logger.getLogger(this.getClass());

	private String cartID;
	private Map<String, CartItem> cartItems;
	private BigDecimal grandTotal;
	
	public Cart(){
		cartItems = new HashMap<String, CartItem>();
		grandTotal = new BigDecimal(0);
	}

	public Cart(String cartID) {
		this();
		this.cartID = cartID;
	}

	public String getCartID() {
		return cartID;
	}

	public void setCartID(String cartID) {
		this.cartID = cartID;
	}

	public Map<String, CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(Map<String, CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	public BigDecimal getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(BigDecimal grandTotal) {
		this.grandTotal = grandTotal;
	}
	
	public void addCartItem(CartItem item){
		String productID = item.getProduct().getProductID();
		
//		logger.info("Getting a product ID : " + productID);
//		System.out.println("whether a value is null or not => " + ((cartItems == null) ? "null" : "not null"));
//		System.out.println(cartItems.containsKey(productID));
		
		if(cartItems.containsKey(productID)){
			logger.info("cartItems object is null!!");
			CartItem existingCartItem = cartItems.get(productID);
			existingCartItem.setQuantity(existingCartItem.getQuantity() + item.getQuantity());
			existingCartItem.setTotalPrice(existingCartItem.getTotalPrice().add(item.getTotalPrice()));
			cartItems.put(productID, existingCartItem);
		}else{
			logger.info("cartItems object is not null!!");
			cartItems.put(productID, item);
		}
		
		logger.info(cartItems.get(productID));
		
		updateGrandTotal();
	}
	
	public void removeCartItem(CartItem item){
		String productID = item.getProduct().getProductID();
		cartItems.remove(productID);
		
		updateGrandTotal();
	}
	
	public void updateGrandTotal(){
		grandTotal = new BigDecimal(0);
		
		logger.info("cartItems Object's size => " + cartItems.size());
		logger.info("cartItems Object's value size => " + cartItems.values().size());
		
		for(CartItem item : cartItems.values()){
			logger.info("item.getTotalPrice(); => " + item.getTotalPrice());
			grandTotal = grandTotal.add(item.getTotalPrice());
		}

//		for(int i = 0; i < cartItems.values().size(); i++){
//			cartItems.values().iterator().
//		}
		
		logger.info("Total Price => " + grandTotal);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cartID == null) ? 0 : cartID.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cart other = (Cart) obj;
		if (cartID == null) {
			if (other.cartID != null)
				return false;
		} else if (!cartID.equals(other.cartID))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cart [cartID=" + cartID + ", cartItems=" + cartItems
				+ ", grandTotal=" + grandTotal + "]";
	}
	
	
}
