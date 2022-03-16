package com.saimon.tuBank.service;

import com.saimon.tuBank.entity.model.BankUser;

public interface BankUserService {
    BankUser getUser(String id) throws Exception;
}
