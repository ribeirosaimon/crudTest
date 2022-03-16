package com.saimon.tuBank.repositoryTest;

import com.saimon.tuBank.entity.model.BankUser;
import com.saimon.tuBank.entity.repository.BankUserRepository;
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
    static String BANKUSER_ID = new ObjectId().toString();
    static String BANKUSER_NAME = "Teste";
    static Integer BANKUSER_OLD = 18;
    static BankUser.GENDER BANKUSER_GENDER = BankUser.GENDER.MALE;
    static BankUser.SCORE BANKUSER_SCORE = BankUser.SCORE.EXCELLENT;
    static String BANKUSER_API = "/api/bankuser";

    private BankUser bankUser;
    @Autowired
    BankUserRepository bankUserRepository;

    @BeforeEach
    public void setUp() {
        BankUser bankUser = new BankUser(BANKUSER_NAME, BANKUSER_OLD);
        bankUser.setId(BANKUSER_ID);
        bankUser.setGender(BANKUSER_GENDER);
        bankUser.setScore(BANKUSER_SCORE);
        this.bankUser = bankUser;
    }

    @AfterEach
    public void tearDow() {
        bankUserRepository.deleteById(BANKUSER_ID);
    }

    @Test
    @DisplayName("Get BankUser by Id")
    public void getBankUserById() {
        bankUserRepository.insert(bankUser);
        assertTrue(bankUserRepository.existsById(bankUser.getId()));
    }
}
