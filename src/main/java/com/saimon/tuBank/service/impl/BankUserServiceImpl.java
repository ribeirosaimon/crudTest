package com.saimon.tuBank.service.impl;

import com.saimon.tuBank.dto.BankUserDTO;
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

    @Override
    public BankUser saveUser(BankUserDTO bankUserDTO) throws Exception {
        BankUser bankUser = new BankUser(bankUserDTO.getLogin(),
                bankUserDTO.getPassword(),
                bankUserDTO.getName(),
                bankUserDTO.getOld(),
                bankUserDTO.getGender());
        bankUser.setGender(bankUserDTO.getGender());

        if (bankUserDTO.getOld() > 50){
            bankUser.setScore(BankUser.SCORE.GOOD);
        } else {
            bankUser.setScore(BankUser.SCORE.GREAT);
        }

        return bankUserRepository.save(bankUser);
    }
}
