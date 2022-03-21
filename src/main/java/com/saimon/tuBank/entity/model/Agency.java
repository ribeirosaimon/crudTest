package com.saimon.tuBank.entity.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Agency {
    @Id
    private String id;
    private String agencyNumber;

    public Agency(String agencyNumber) {
        this.agencyNumber = agencyNumber;
    }

    public String getId() {
        return id;
    }

    public String getAgencyNumber() {
        return agencyNumber;
    }
}
