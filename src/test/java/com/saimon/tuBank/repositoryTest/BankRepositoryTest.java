package com.saimon.tuBank.repositoryTest;

import com.saimon.tuBank.entity.model.BankUser;
import com.saimon.tuBank.entity.repository.BankUserRepository;
import com.saimon.tuBank.setUp.SetUpTest;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataMongoTest(properties = {"spring.mongodb.embedded.version=4.0.2"})
public class BankRepositoryTest {

    private BankUser bankUser;
    @Autowired
    BankUserRepository bankUserRepository;

    @BeforeEach
    public void setUp() {
        BankUser bankUser = new BankUser(SetUpTest.BANKUSER_LOGIN,
                SetUpTest.BANKUSER_PASSWORD,
                SetUpTest.BANKUSER_NAME,
                SetUpTest.BANKUSER_OLD,
                SetUpTest.BANKUSER_GENDER);
        bankUser.setId(SetUpTest.BANKUSER_ID);
        bankUser.setGender(SetUpTest.BANKUSER_GENDER);
        bankUser.setScore(SetUpTest.BANKUSER_SCORE);
        this.bankUser = bankUser;
    }

    @AfterEach
    public void tearDow() {
        bankUserRepository.deleteById(SetUpTest.BANKUSER_ID);
    }

    @Test
    @DisplayName("Get BankUser by Id")
    public void getBankUserById() {
        bankUserRepository.insert(bankUser);
        assertTrue(bankUserRepository.existsById(bankUser.getId()));
    }

    @Test
    @DisplayName("Save BankUser")
    public void saveBankUserTest(){
        bankUserRepository.save(bankUser);
        assertTrue(bankUserRepository.existsById(bankUser.getId()));
    }

    @Test
    @DisplayName("Update BankUser")
    public void updateBankUserTest(){
        bankUserRepository.save(bankUser);
        assertTrue(bankUserRepository.existsById(bankUser.getId()));
    }
}
