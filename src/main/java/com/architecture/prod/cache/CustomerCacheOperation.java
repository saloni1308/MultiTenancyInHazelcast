package com.architecture.prod.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.architecture.prod.model.Customer;
import com.architecture.prod.module.CustomerMapProvider;
import com.google.inject.Inject;
import com.hazelcast.query.EntryObject;
import com.hazelcast.query.Predicate;
import com.hazelcast.query.PredicateBuilder;

/**
 * Perform operations on Hazelcast map
 */

public class CustomerCacheOperation {

  private final CustomerMapProvider customerMapProvider;

  /**
   * Gets the Object of Hazelcast map thru Guice bindings in CustomerCacheModule.java
   * @parammap
   */

  @Inject
  CustomerCacheOperation(final CustomerMapProvider customerMapProvider) {
    this.customerMapProvider = customerMapProvider;
  }

  public Customer getCustomerById(String id) {
    /**
     * customerMapProvider.get() provide the Hazelcast map on the basis of user context
     */
    return customerMapProvider.get().get(id);
  }

  public Customer addCustomerObject(final Customer customer) {
	  customerMapProvider.get().put(customer.getId(), customer);
    return customer;
  }

  public Customer updateCustomer(Customer customer) {
	  customerMapProvider.get().put(customer.getId(), customer);
    return customer;
  }

  public Customer getCustomerByCode(final String code) throws Exception{
    final EntryObject entryObject = new PredicateBuilder().getEntryObject();
    final Predicate predicate = entryObject.get("code").equal(code);
    return customerMapProvider.get().values(predicate).parallelStream()
        .findFirst()
        .orElseThrow(() -> new Exception("code not found" + "\n" + code));
  }

  public List<Customer> getCustomerByPhoneNumber (final int phoneNumber) {
    final EntryObject entryObject = new PredicateBuilder().getEntryObject();
    final Predicate predicate = entryObject.get("phoneNumber").equal(phoneNumber);
    return new ArrayList<>(customerMapProvider.get().values(predicate));
  }

  public String getCustomerAddressByCode(final String code) throws Exception{
    final EntryObject entryObject = new PredicateBuilder().getEntryObject();
    final Predicate predicate = entryObject.get("code").equal(code);
    Customer customer =  customerMapProvider.get().values(predicate).parallelStream()
        .findFirst()
        .orElseThrow(() -> new Exception("code not found" + "\n" + code));
    return customer.getAddress();
  }

  public void deleteCustomer(String id) {
	  customerMapProvider.get().remove(id);
  }
}
