package com.architecture.prod.service;

import java.util.List;

import com.architecture.prod.model.Customer;

public interface CustomerService {

  Customer getCustomerById(String id);

  Customer addCustomer(Customer customer);

  Customer updateCustomer(Customer customer);

  void deleteCustomer(String id);

  Customer getCustomerByCode(String code) throws Exception;

  List<Customer> getCustomerByPhoneNumber(int number) throws Exception;

  String getCustomerAddressByCode(String code) throws Exception;
}