package com.still.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.still.crm.dao.CustomerRepository;
import com.still.crm.domain.Customer;
import com.still.crm.service.CustomerService;

/**  
 * ClassName:CustomerServiceImpl <br/>  
 * Function:  <br/>  
 * Date:     2018年3月19日 上午12:03:53 <br/>       
 */
@Transactional
@Service
public class CustomerServiceImpl implements CustomerService {

    
    @Autowired
    private CustomerRepository customerRepository;
    
    @Override
    public List<Customer> findAll() {

        return customerRepository.findAll();
    }

}
  
