package com.saimon.tuBank.service;

import com.saimon.tuBank.entity.model.Manager;

public interface ManagerService {
    Manager getManager(String id) throws Exception;
    void giveCard(String managerId, String clientId) throws Exception;
}
