package au.com.aussle.webstore.domain;

public class CustomerCH2 {

	private String customerID;
	private String name;
	private String address;
	private long noOfOrderMade;
	
	public CustomerCH2() {
		super();
	}

	public CustomerCH2(String customerID, String name, long noOfOrderMade) {
		super();
		this.customerID = customerID;
		this.name = name;
		this.noOfOrderMade = noOfOrderMade;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((customerID == null) ? 0 : customerID.hashCode());
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
		CustomerCH2 other = (CustomerCH2) obj;
		if (customerID == null) {
			if (other.customerID != null)
				return false;
		} else if (!customerID.equals(other.customerID))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Customer [customerID=" + customerID + ", name=" + name + "]";
	}

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public long getNoOfOrderMade() {
		return noOfOrderMade;
	}

	public void setNoOfOrderMade(long noOfOrderMade) {
		this.noOfOrderMade = noOfOrderMade;
	}
	
	
}
