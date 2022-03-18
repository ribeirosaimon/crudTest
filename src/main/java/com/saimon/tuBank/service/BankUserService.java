package com.saimon.tuBank.service;

import com.saimon.tuBank.dto.BankUserDTO;
import com.saimon.tuBank.entity.model.BankUser;

public interface BankUserService {
    BankUser getUser(String id) throws Exception;
    BankUser saveUser(BankUserDTO bankUserDTO) throws Exception;
    BankUser updateUser(String id, BankUserDTO bankUserDTO) throws Exception;
    void deleteUser(String id) throws Exception;
}
