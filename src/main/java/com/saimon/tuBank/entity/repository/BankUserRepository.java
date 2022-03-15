package com.saimon.tuBank.entity.repository;

import com.saimon.tuBank.entity.model.BankUser;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BankUserRepository extends MongoRepository<BankUser, String> {
}
