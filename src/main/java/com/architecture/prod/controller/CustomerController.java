package com.architecture.prod.controller;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.architecture.prod.model.Customer;
import com.architecture.prod.service.CustomerService;

@Path("/customer")
public class CustomerController {

    private final CustomerService customerService;
    
    /**
     * Use to test is Web Service is working.
     */
    @GET
    @Path("/test")
    @Produces(MediaType.TEXT_PLAIN)
    public String test() {
    	return "its working.....";
    }

    @Inject
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GET
    @Path("/{customerId}")
    @Produces(APPLICATION_JSON)
    public Customer getCustomer(@PathParam("customerId") String customerId) {
        return this.customerService.getCustomerById(customerId);
    }

    @POST
    @Produces(APPLICATION_JSON)
    public void addCustomer(Customer customer) {
        customerService.addCustomer(customer);
    }

    @PUT
    @Produces(APPLICATION_JSON)
    public Customer updateCustomer(Customer customer) {
        return customerService.updateCustomer(customer);
    }

    @DELETE
    @Path("/{customerId}")
    @Produces(APPLICATION_JSON)
    public void deleteCustomer(@PathParam("customerId") String customerId) {
        customerService.deleteCustomer(customerId);
    }

    @GET
    @Path("/code/{customerCode}")
    @Produces(APPLICATION_JSON)
    public Customer getCustomerByCode(@PathParam("customerCode") String customerCode) throws Exception{
        return this.customerService.getCustomerByCode(customerCode);
    }

    @GET
    @Path("/phoneNumber/{phoneNumber}")
    @Produces(APPLICATION_JSON)
    public List<Customer> getCustomerByPhoneNumber(@PathParam("phoneNumber") int phoneNumber) throws Exception{
        return this.customerService.getCustomerByPhoneNumber(phoneNumber);
    }

    @GET
    @Path("/address/{customerCode}")
    @Produces(APPLICATION_JSON)
    public String getCustomerAddress(@PathParam("customerCode") String customerCode) throws Exception{
        return this.customerService.getCustomerAddressByCode(customerCode);
    }
}
