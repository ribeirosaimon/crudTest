package com.saimon.tuBank.repositoryTest;

import com.saimon.tuBank.entity.model.BankUser;
import com.saimon.tuBank.entity.repository.BankUserRepository;
import com.saimon.tuBank.setUp.SetUpTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class BankRepositoryTest {
    static String BANKUSER_ID = "ID";
    static String BANKUSER_NAME = "Teste";
    static Integer BANKUSER_OLD = 18;
    static BankUser.GENDER BANKUSER_GENDER = BankUser.GENDER.MALE;
    static BankUser.SCORE BANKUSER_SCORE = BankUser.SCORE.EXCELLENT;
    static String BANKUSER_API = "/api/bankuser";

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    BankUserRepository bankUserRepository;

    @Test
    @DisplayName("Get BankUser by Id")
    public void getBankUserById() {
        entityManager.persist(SetUpTest.createUser());
        Optional<BankUser> foundTask = bankUserRepository.findById(SetUpTest.createUser().getId());
        Assertions.assertThat(foundTask.get().getId()).isNotNull();
        Assertions.assertThat(foundTask.isPresent()).isTrue();
    }
}
