package com.still.crm.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.still.crm.domain.Customer;

/**  
 * ClassName:CustomerService <br/>  
 * Function:  <br/>  
 * Date:     2018年3月19日 上午12:01:11 <br/>       
 */


@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)    
public interface CustomerService {

    @GET
    @Path("/findAll")
    List<Customer> findAll();
    
    @GET
    @Path("/findCustomersUnAssociated")
    List<Customer> findCustomersUnAssociated();
    
    @GET
    @Path("/findCustomersAssociated2FixedArea")
    List<Customer> findCustomersAssociated2FixedArea(@QueryParam("fixedAreaId")String fixedAreaId);
    
    @PUT
    @Path("/assignCustomers2FixedArea")
    void assignCustomers2FixedArea(@QueryParam("fixedAreaId")String fixedAreaId
            ,@QueryParam("customerIds")Long[] customerIds);
        
    //注册用户
    @POST
    @Path("/save")
    public void save(Customer customer);
    
    @PUT
    @Path("/active")
    public void active(@QueryParam("telephone")String  telephone);
    
    @GET
    @Path("/isActived")
    public Customer isActived(@QueryParam("telephone")String telephone);
    
    
}
  
