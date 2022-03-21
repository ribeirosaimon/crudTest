package com.saimon.tuBank.entity.repository;

import com.saimon.tuBank.entity.model.Manager;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ManagerRepository extends MongoRepository<Manager, String> {
}
