package com.saimon.tuBank.entity.repository;

import com.saimon.tuBank.entity.model.Agency;
import com.saimon.tuBank.entity.model.Client;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AgencyRepository extends MongoRepository<Agency, String> {
    Optional<Client> findByAgencyNumber(String agency);
}
