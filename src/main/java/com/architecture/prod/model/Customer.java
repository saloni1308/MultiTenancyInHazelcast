package com.architecture.prod.model;


import java.io.Serializable;
import java.util.Objects;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Field;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.IndexOptions;
import org.mongodb.morphia.annotations.Indexes;

/**
 * Structure of the customer data
 */
@Entity("customer")
// Compound unique index for faster db operations
@Indexes(@Index(fields = {@Field("code"), @Field("address")}, options = @IndexOptions(unique = true)))
public class Customer implements Serializable {

  private static final long serialVersionUID = 4853298355312750847L;
  @Id
  private final String id;
  private final String code;
  private final String name;
  private final String address;
  private final int phoneNumber;

  public Customer(final String id,
                  final String code,
                  final String name,
                  final String address,
                  final int phoneNumber) {
    this.id = id;
    this.code = code;
    this.name = name;
    this.address = address;
    this.phoneNumber = phoneNumber;
  }

  private Customer() {
    // for morphia
    this(null, null, null, null,0);
  }

  public String getId() {
    return id;
  }

  public String getCode() {
    return code;
  }

  public String getName() {
    return name;
  }

  public String getAddress() {
    return address;
  }

  public int getPhoneNumber() {
    return phoneNumber;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Customer object = (Customer) o;
    return Objects.equals(id, object.id) && Objects.equals(code, object.code)
        && Objects.equals(name, object.name) && Objects.equals(address, object.address)
        && Objects.equals(phoneNumber, object.phoneNumber);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, code, name, address, phoneNumber);
  }
}

