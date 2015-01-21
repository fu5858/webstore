package au.com.aussle.webstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import au.com.aussle.webstore.domain.CustomerCH2;
import au.com.aussle.webstore.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepo;
	
	public List<CustomerCH2> getAllCustomers() {
		// TODO Auto-generated method stub
		return customerRepo.getAllCustomers();
	}

}
