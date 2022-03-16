package com.saimon.tuBank.serviceTest;

import com.saimon.tuBank.dto.BankUserDTO;
import com.saimon.tuBank.entity.model.BankUser;
import com.saimon.tuBank.entity.repository.BankUserRepository;
import com.saimon.tuBank.service.BankUserService;
import com.saimon.tuBank.service.impl.BankUserServiceImpl;
import com.saimon.tuBank.setUp.SetUpTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class BankServiceTest {

    private BankUserService bankUserService;

    @MockBean
    private BankUserRepository bankUserRepository;

    @BeforeEach
    public void setUp(){
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
        BankUser saveUSer = SetUpTest.createUser();

        Mockito
                .when(bankUserService.saveUser(SetUpTest.createDTO()))
                .thenReturn(saveUSer);

        Assertions.assertThat(saveUSer.getId()).isEqualTo(SetUpTest.BANKUSER_ID);
        Assertions.assertThat(saveUSer.getGender()).isEqualTo(SetUpTest.BANKUSER_GENDER);
        Assertions.assertThat(saveUSer.getName()).isEqualTo(SetUpTest.BANKUSER_NAME);
        Assertions.assertThat(saveUSer.getOld()).isEqualTo(SetUpTest.BANKUSER_OLD);
        Assertions.assertThat(saveUSer.getPassword()).isEqualTo(SetUpTest.BANKUSER_PASSWORD);
    }
}
