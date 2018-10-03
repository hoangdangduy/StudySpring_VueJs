package com.repo;

import com.dom.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    Customer findByUsernameAndPassword(String username, String password);
    Customer findByMailAndPassword(String mail, String password);
}
