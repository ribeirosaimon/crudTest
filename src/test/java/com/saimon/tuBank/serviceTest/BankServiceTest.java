package com.saimon.tuBank.serviceTest;

import com.saimon.tuBank.dto.BankInformationsDTO;
import com.saimon.tuBank.dto.BankUserDTO;
import com.saimon.tuBank.entity.model.BankUser;
import com.saimon.tuBank.entity.repository.BankUserRepository;
import com.saimon.tuBank.service.BankUserService;
import com.saimon.tuBank.service.impl.BankUserServiceImpl;
import com.saimon.tuBank.setUp.SetUpTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class BankServiceTest {

    @MockBean
    private BankUserRepository bankUserRepository;

    private BankUserService bankUserService;


    @BeforeEach
    public void setUp() {
        this.bankUserService = new BankUserServiceImpl(bankUserRepository);
    }

    @Test
    @DisplayName("Get BankUser by id")
    public void getByIdTest() throws Exception {
        Mockito
                .when(bankUserRepository.findById(SetUpTest.BANKUSER_ID))
                .thenReturn(Optional.of(SetUpTest.createUser()));

        Optional<BankUser> user = Optional.of(bankUserService.getUser(SetUpTest.BANKUSER_ID));

        Assertions.assertThat(user.isPresent()).isTrue();
        Assertions.assertThat(user.get().getId()).isEqualTo(SetUpTest.BANKUSER_ID);
        Assertions.assertThat(user.get().getGender()).isEqualTo(SetUpTest.BANKUSER_GENDER);
        Assertions.assertThat(user.get().getName()).isEqualTo(SetUpTest.BANKUSER_NAME);
        Assertions.assertThat(user.get().getOld()).isEqualTo(SetUpTest.BANKUSER_OLD);
        Assertions.assertThat(user.get().getPassword()).isEqualTo(SetUpTest.BANKUSER_PASSWORD);
    }

    @Test
    @DisplayName("Save BankUSer")
    public void saveUserTest() throws Exception {
        BankUser saveUser = SetUpTest.createUser();

        Mockito
                .when(bankUserService.saveUser(SetUpTest.createDTO()))
                .thenReturn(saveUser);

        Assertions.assertThat(saveUser.getId()).isEqualTo(SetUpTest.BANKUSER_ID);
        Assertions.assertThat(saveUser.getGender()).isEqualTo(SetUpTest.BANKUSER_GENDER);
        Assertions.assertThat(saveUser.getName()).isEqualTo(SetUpTest.BANKUSER_NAME);
        Assertions.assertThat(saveUser.getOld()).isEqualTo(SetUpTest.BANKUSER_OLD);
        Assertions.assertThat(saveUser.getPassword()).isEqualTo(SetUpTest.BANKUSER_PASSWORD);
    }
}
