package com.saimon.tuBank.service;

import com.saimon.tuBank.dto.BankInformationsDTO;
import com.saimon.tuBank.dto.BankUserDTO;
import com.saimon.tuBank.entity.model.BankUser;
import com.saimon.tuBank.entity.repository.BankUserRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

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
    public BankInformationsDTO saveUser(BankUserDTO bankUserDTO) throws Exception {
        BankUser bankUser = new BankUser(bankUserDTO.getLogin(),
                bankUserDTO.getPassword(),
                bankUserDTO.getName(),
                bankUserDTO.getOld(),
                bankUserDTO.getGender());
        bankUser.setGender(bankUserDTO.getGender());
        bankUser.setCreatedAt(new Date());

        if (bankUserDTO.getOld() > 50){
            bankUser.setScore(BankUser.SCORE.GOOD);
        } else {
            bankUser.setScore(BankUser.SCORE.GREAT);
        }

        BankUser savedUser = bankUserRepository.save(bankUser);
        return new BankInformationsDTO(savedUser.getId(), savedUser.getName(), savedUser.getOld(), savedUser.getGender());
    }

    @Override
    public BankUser updateUser(String id, BankUserDTO bankUserDTO) throws Exception {
        BankUser user = this.getUser(id);
        user.setName(bankUserDTO.getName());
        user.setGender(bankUserDTO.getGender());
        user.setOld(bankUserDTO.getOld());
        user.setUpdatedAt(new Date());

        return bankUserRepository.save(user);
    }

    @Override
    public void deleteUser(String id) throws Exception {
        BankUser user = this.getUser(id);
        bankUserRepository.delete(user);
    }
}
