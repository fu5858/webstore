package au.com.aussle.webstore.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import au.com.aussle.webstore.domain.CustomerCH2;

@Repository
public class InMemoryCustomerRepository implements CustomerRepository {
	
	private List<CustomerCH2> listOfCustomers = new ArrayList<CustomerCH2>();
	
	public InMemoryCustomerRepository() {
		CustomerCH2 cust1 = new CustomerCH2("C1", "Kiwon Lee", 3);
		cust1.setAddress("Australia");
		
		listOfCustomers.add(cust1);
	}

	public List<CustomerCH2> getAllCustomers() {
		return listOfCustomers;
	}

}
