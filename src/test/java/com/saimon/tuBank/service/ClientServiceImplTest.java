package com.saimon.tuBank.service;

import com.saimon.tuBank.dto.ClientInformationsDto;
import com.saimon.tuBank.entity.model.Client;
import com.saimon.tuBank.util.Creator;
import org.assertj.core.api.Assertions;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DisplayName("User Service Test")
class ClientServiceImplTest {

    @Mock
    private ClientService service;

    @BeforeEach
    void setUp() throws Exception {
        Client user = Creator.user();
        ClientInformationsDto informationsDTO = Creator.InfoUserDto();

        Client updatedUser = Creator.user();
        updatedUser.setOld(Creator.UPDATED_OLD);

        BDDMockito.when(service.getUser(ArgumentMatchers.any())).thenReturn(user);
        BDDMockito.when(service.saveUser(ArgumentMatchers.any())).thenReturn(informationsDTO);
        BDDMockito.when(service.updateUser(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(updatedUser);
        BDDMockito.doNothing().when(service).deleteUser(ArgumentMatchers.anyString());
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Get user service")
    void getUser_Successful() throws Exception {
        Client user = service.getUser(null);
        Assertions.assertThat(user.getId()).isEqualTo(Creator.BANKUSER_ID);
    }

    @Test
    @DisplayName("Save user service")
    void saveUser_Successful() throws Exception {
        ClientInformationsDto informationsDTO = service.saveUser(null);

        Assertions.assertThat(informationsDTO.getId()).isEqualTo(Creator.BANKUSER_ID);
        Assertions.assertThat(informationsDTO.getName()).isEqualTo(Creator.BANKUSER_NAME);
        Assertions.assertThat(informationsDTO.getOld()).isEqualTo(Creator.BANKUSER_OLD);
    }

    @Test
    @DisplayName("update User service")
    void updateUser_Successful() throws Exception {

        Client client = service.updateUser(Creator.BANKUSER_ID, null);

        Assertions.assertThat(client.getId()).isEqualTo(Creator.BANKUSER_ID);
        Assertions.assertThat(client.getName()).isEqualTo(Creator.BANKUSER_NAME);
        Assertions.assertThat(client.getOld()).isEqualTo(Creator.UPDATED_OLD);
    }

    @Test
    @DisplayName("Delete User service")
    void deleteUser_Successful() throws Exception {

        service.deleteUser(Creator.BANKUSER_ID);

        Assertions.assertThatCode(() -> service.deleteUser(new ObjectId().toString()))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Delete User service Exception")
    void deleteUser_Exception() throws Exception {
        BDDMockito.when( service.getUser(ArgumentMatchers.anyString()))
                .thenThrow(new Exception(Creator.OLD_ERROR_MESSAGE));

        service.deleteUser(Creator.BANKUSER_ID);

        Assertions.assertThatCode(() -> service.deleteUser(new ObjectId().toString()))
                .doesNotThrowAnyException();
    }
}