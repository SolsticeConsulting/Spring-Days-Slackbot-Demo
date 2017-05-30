package com.solstice;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Justin Baumgartner on 5/18/17.
 */
@Repository
public interface CustomerRepository extends CrudRepository<Customer, String> {

    Customer findByUsername(String username);

}
