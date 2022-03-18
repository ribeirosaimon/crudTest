package com.saimon.tuBank.repository;

import com.saimon.tuBank.entity.model.BankUser;
import com.saimon.tuBank.entity.repository.BankUserRepository;
import com.saimon.tuBank.util.Creator;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataMongoTest(properties = {"spring.mongodb.embedded.version=4.0.2"})
public class BankUserRepositoryTest {

    private BankUser bankUser;

    @Autowired
    BankUserRepository bankUserRepository;

    @BeforeEach
    public void setUp() {
        this.bankUser = Creator.user();
    }

    @AfterEach
    public void tearDow() {
        bankUserRepository.deleteById(Creator.BANKUSER_ID);
    }

    @Test
    @DisplayName("Get BankUser by Id")
    public void getBankUserById() {
        bankUserRepository.insert(bankUser);
        Assertions.assertTrue(bankUserRepository.existsById(bankUser.getId()));
    }

    @Test
    @DisplayName("Save BankUser")
    public void saveBankUserTest() {
        bankUserRepository.save(bankUser);
        Assertions.assertTrue(bankUserRepository.existsById(bankUser.getId()));
    }

    @Test
    @DisplayName("Update BankUser")
    public void updateBankUserTest() {
        bankUser.setOld(20);
        BankUser save = bankUserRepository.save(bankUser);
        Assertions.assertTrue(bankUserRepository.existsById(bankUser.getId()));
        Assertions.assertEquals(save.getOld(), Creator.UPDATED_OLD);
    }
}