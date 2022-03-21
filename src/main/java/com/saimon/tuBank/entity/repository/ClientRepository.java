package com.saimon.tuBank.entity.repository;

import com.saimon.tuBank.entity.model.Client;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ClientRepository extends MongoRepository<Client, String> {
    Optional<Client> findByLogin(String login);
}
