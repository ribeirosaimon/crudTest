package com.saimon.tuBank.service.impl;

import com.saimon.tuBank.entity.model.BankUser;
import com.saimon.tuBank.entity.repository.BankUserRepository;
import com.saimon.tuBank.service.BankUserService;
import org.springframework.stereotype.Service;

@Service
public class BankUserServiceImpl implements BankUserService {

    private final BankUserRepository bankUserRepository;

    public BankUserServiceImpl(BankUserRepository bankUserRepository) {
        this.bankUserRepository = bankUserRepository;
    }

    @Override
    public BankUser getUser(String id) throws Exception {
        return bankUserRepository.findById(id).orElseThrow(() -> new Exception("not found"));
    }
}
