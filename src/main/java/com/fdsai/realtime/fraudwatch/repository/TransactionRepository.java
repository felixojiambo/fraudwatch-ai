package com.fdsai.realtime.fraudwatch.repository;
import com.fdsai.realtime.fraudwatch.model.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransactionRepository extends MongoRepository<Transaction, String> {
}