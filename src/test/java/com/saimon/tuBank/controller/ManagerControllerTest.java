package com.saimon.tuBank.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.saimon.tuBank.dto.ClientDto;
import com.saimon.tuBank.dto.ClientInformationsDto;
import com.saimon.tuBank.entity.model.Client;
import com.saimon.tuBank.entity.model.Manager;
import com.saimon.tuBank.service.ClientService;
import com.saimon.tuBank.service.ManagerService;
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
@WebMvcTest(ManagerController.class)
@AutoConfigureMockMvc
class ManagerControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    ManagerService service;

    @BeforeEach
    void setUp() throws Exception {
        Manager manager = Creator.manager();
        Client user = Creator.user();

        BDDMockito.given(service.getManager(ArgumentMatchers.any())).willReturn(manager);

//        BDDMockito.doNothing().when(service).deleteUser(ArgumentMatchers.anyString());
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Get Manager Controller")
    void getManager_Successful() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(Creator.MANAGER_API.concat("/" + Creator.MANAGER_ID))
                .accept(MediaType.APPLICATION_JSON);
        mvc
                .perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("id").value(Creator.MANAGER_ID))
                .andExpect(MockMvcResultMatchers.jsonPath("name").value(Creator.MANAGER_NAME));
    }

    @Test
    @DisplayName("Give Card to Client")
    void giveCard_Succesful() {

    }

}