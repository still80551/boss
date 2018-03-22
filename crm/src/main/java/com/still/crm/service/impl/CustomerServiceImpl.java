package com.still.crm.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
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

    @Override
    public List<Customer> findCustomersUnAssociated() {
          
        return customerRepository.findByFixedAreaIdIsNull();
    }

    @Override
    public List<Customer> findCustomersAssociated2FixedArea(String fixedAreaId) {
          
        return customerRepository.findByFixedAreaId(fixedAreaId);
    }

    @Override
    public void assignCustomers2FixedArea(String fixedAreaId, Long[] customerIds) {
          
        
        if(StringUtils.isNotEmpty(fixedAreaId)){
         // 把关联到定区ID的客户全部解绑
            customerRepository.unbindByFixedAreaId(fixedAreaId);
         // 再次进行绑定
            if(customerIds != null && customerIds.length > 0 ){
                for (Long id : customerIds) {
                    customerRepository.bindFixedAreaById(fixedAreaId,id);
                }
            }
            
        }
    }

    
    
    
}
  
