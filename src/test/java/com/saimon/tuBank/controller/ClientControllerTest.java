package com.saimon.tuBank.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.saimon.tuBank.dto.ClientDto;
import com.saimon.tuBank.dto.ClientInformationsDto;
import com.saimon.tuBank.entity.model.Client;
import com.saimon.tuBank.service.ClientService;
import com.saimon.tuBank.util.Creator;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(SpringExtension.class)
@DisplayName("User Controller Test")
@WebMvcTest(ClientController.class)
@AutoConfigureMockMvc
class ClientControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    ClientService service;

    @BeforeEach
    void setUp() throws Exception {
        Client user = Creator.user();
        ClientInformationsDto informationsDTO = Creator.InfoUserDto();

        Client updatedUser = Creator.user();
        updatedUser.setOld(Creator.UPDATED_OLD);

        BDDMockito.given(service.getUser(ArgumentMatchers.any())).willReturn(user);
        BDDMockito.given(service.saveUser(ArgumentMatchers.any())).willReturn(informationsDTO);
        BDDMockito.given(service.updateUser(ArgumentMatchers.any(), ArgumentMatchers.any())).willReturn(updatedUser);
        BDDMockito.doNothing().when(service).deleteUser(ArgumentMatchers.anyString());
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Get user Controller")
    void getUser_Successful() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(Creator.BANKUSER_API.concat("/" + Creator.BANKUSER_ID))
                .accept(MediaType.APPLICATION_JSON);
        mvc
                .perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("id").value(Creator.BANKUSER_ID))
                .andExpect(MockMvcResultMatchers.jsonPath("name").value(Creator.BANKUSER_NAME))
                .andExpect(MockMvcResultMatchers.jsonPath("old").value(Creator.BANKUSER_OLD));
    }

    @Test
    @DisplayName("Save user Controller")
    void saveUser_Successful() throws Exception {
        String json = new ObjectMapper().writeValueAsString(Creator.userDto());

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(Creator.BANKUSER_API.concat("/add"))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc
                .perform(request)
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("id").value(Creator.BANKUSER_ID))
                .andExpect(MockMvcResultMatchers.jsonPath("name").value(Creator.BANKUSER_NAME))
                .andExpect(MockMvcResultMatchers.jsonPath("old").value(Creator.BANKUSER_OLD));
    }

    @Test
    @DisplayName("Exception Save user Controller")
    void updateUser_Exception() throws Exception {
        ClientDto dtoError = Creator.userDto();
        dtoError.setOld(Creator.EXCEPTION_OLD);

        String json = new ObjectMapper().writeValueAsString(dtoError);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .put(Creator.BANKUSER_API.concat("/" + Creator.BANKUSER_ID))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc
                .perform(request)
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @DisplayName("Delete User")
    void deleteUser_Successful() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .delete(Creator.BANKUSER_API.concat("/" + Creator.BANKUSER_ID));

        mvc
                .perform(request)
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    @DisplayName("Delete Exception User not found")
    void deleteUser_Exceptions() throws Exception {
        BDDMockito.doThrow(Exception.class).when(service).deleteUser(ArgumentMatchers.anyString());

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .delete(Creator.BANKUSER_API.concat("/" + new ObjectId()));

        mvc
                .perform(request)
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}