package com.saimon.tuBank.service.impl;

import com.saimon.tuBank.entity.model.Manager;
import com.saimon.tuBank.entity.repository.ManagerRepository;
import com.saimon.tuBank.service.ManagerService;
import org.springframework.stereotype.Service;

@Service
public class ManageServiceImpl implements ManagerService {
    private final ManagerRepository managerRepository;

    public ManageServiceImpl(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    @Override
    public Manager getManager(String id) throws Exception {
        return managerRepository.findById(id).orElseThrow(() -> new Exception("Manager not found"));

    }
}
