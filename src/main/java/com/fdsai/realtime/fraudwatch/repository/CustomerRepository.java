package com.fdsai.realtime.fraudwatch.repository;
import com.fdsai.realtime.fraudwatch.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {
}