package com.architecture.prod.cache;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

import com.architecture.prod.model.Customer;
import com.architecture.prod.repository.CustomerRepository;
import com.google.inject.Inject;
import com.hazelcast.core.MapStore;

/**
 * Interacts with repository if value is not present in the map or
 * when put operation is being applied in the map
 */
public class CustomerMapstore implements MapStore<String, Customer> {
  private final CustomerRepository customerRepository;

  @Inject
  CustomerMapstore(final CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }
  @Override
  public void store(final String s, final Customer customer) {
    customerRepository.addOrUpdateCustomer(customer);
  }

  @Override
  public void storeAll(final Map<String, Customer> map) {
    map.forEach(this::store);
  }

  @Override
  public void delete(final String s) {
    customerRepository.deleteCustomer(s);
  }

  @Override
  public void deleteAll(final Collection<String> collection) {
    collection.forEach(this::delete);
  }

  @Override
  public Customer load(final String s) {
    return customerRepository.get(s);
  }

  @Override
  public Map<String, Customer> loadAll(final Collection<String> collection) {
    return collection.stream().collect(Collectors.toMap(id-> id, this::load));
  }

  @Override
  public Iterable<String> loadAllKeys() {
    return customerRepository.findIds();
  }
}
