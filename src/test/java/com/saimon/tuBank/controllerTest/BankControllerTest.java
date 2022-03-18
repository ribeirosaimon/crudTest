package com.saimon.tuBank.controllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.saimon.tuBank.dto.BankInformationsDTO;
import com.saimon.tuBank.dto.BankUserDTO;
import com.saimon.tuBank.entity.model.BankUser;
import com.saimon.tuBank.service.BankUserService;
import com.saimon.tuBank.setUp.SetUpTest;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest
@AutoConfigureMockMvc
public class BankControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    BankUserService bankUserService;

    @Test
    @DisplayName("BankUser Controller Test")
    public void getBankUserTest() throws Exception {
        BDDMockito
                .given(bankUserService.getUser(SetUpTest.BANKUSER_ID))
                .willReturn(SetUpTest.createUser());

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(SetUpTest.BANKUSER_API.concat("/" + SetUpTest.BANKUSER_ID))
                .accept(MediaType.APPLICATION_JSON);

        mvc
                .perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("id").value(SetUpTest.BANKUSER_ID))
                .andExpect(MockMvcResultMatchers.jsonPath("name").value(SetUpTest.BANKUSER_NAME))
                .andExpect(MockMvcResultMatchers.jsonPath("old").value(SetUpTest.BANKUSER_OLD));
    }

    @Test
    @DisplayName("BankUser Save with DTO")
    public void saveUser() throws Exception {
        String json = new ObjectMapper().writeValueAsString(SetUpTest.createDTO());

        BDDMockito
                .given(bankUserService.saveUser(Mockito.any(BankUserDTO.class)))
                .willReturn(SetUpTest.createUser());

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(SetUpTest.BANKUSER_API.concat("/add"))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc
                .perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("id").value(SetUpTest.BANKUSER_ID))
                .andExpect(MockMvcResultMatchers.jsonPath("login").value(SetUpTest.BANKUSER_LOGIN))
                .andExpect(MockMvcResultMatchers.jsonPath("password").value(SetUpTest.BANKUSER_PASSWORD))
                .andExpect(MockMvcResultMatchers.jsonPath("name").value(SetUpTest.BANKUSER_NAME))
                .andExpect(MockMvcResultMatchers.jsonPath("old").value(SetUpTest.BANKUSER_OLD));
    }

    @Test
    @DisplayName("BankUser not Save")
    public void noSaveUserTest() throws Exception {

        BankUserDTO dtoError = SetUpTest.createDTO();
        Integer oldError = 15;
        dtoError.setOld(oldError);

        String json = new ObjectMapper().writeValueAsString(dtoError);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(SetUpTest.BANKUSER_API.concat("/add"))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc
                .perform(request)
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @DisplayName("Update BankUser")
    public void putUserBankTest() throws Exception {
        Integer changeOldUser = 20;

        BankUser user = SetUpTest.createUser();
        user.setOld(changeOldUser);

        BankUserDTO createDto = SetUpTest.createDTO();
        createDto.setOld(changeOldUser);

        BankInformationsDTO informationsDTO = SetUpTest.createInformationsDTO();
        informationsDTO.setOld(changeOldUser);

        String json = new ObjectMapper().writeValueAsString(createDto);

        BDDMockito
                .given(bankUserService.updateUser(Mockito.any(String.class), Mockito.any(BankUserDTO.class)))
                .willReturn(user);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .put(SetUpTest.BANKUSER_API.concat("/") + SetUpTest.BANKUSER_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc
                .perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("name").value(SetUpTest.BANKUSER_NAME))
                .andExpect(MockMvcResultMatchers.jsonPath("old").value(changeOldUser));
    }

    @Test
    @DisplayName("Delete User")
    public void deleteUser() throws Exception {
        BDDMockito.doNothing()
                .when(bankUserService.getUser(Mockito.any(String.class)));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .delete(SetUpTest.BANKUSER_API.concat("/") + SetUpTest.BANKUSER_ID);

        mvc
                .perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Delete Not found User")
    public void tryDeleteUser() throws Exception {
        BDDMockito.doNothing()
                .when(bankUserService.getUser(Mockito.any(String.class)));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .delete(SetUpTest.BANKUSER_API.concat("/") + new ObjectId());

        mvc
                .perform(request)
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
