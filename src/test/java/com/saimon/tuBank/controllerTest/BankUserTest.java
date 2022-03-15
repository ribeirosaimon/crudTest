package com.saimon.tuBank.controllerTest;

import com.saimon.tuBank.entity.model.BankUser;
import com.saimon.tuBank.service.BankUserService;
import com.saimon.tuBank.setUp.SetUpTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
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
public class BankUserTest {
    static String BANKUSER_ID = "ID";
    static String BANKUSER_NAME = "Teste";
    static Integer BANKUSER_OLD = 18;
    static BankUser.GENDER BANKUSER_GENDER = BankUser.GENDER.MALE;
    static BankUser.SCORE BANKUSER_SCORE = BankUser.SCORE.EXCELLENT;
    static String BANKUSER_API = "/api/bankuser";

    @Autowired
    MockMvc mvc;
    @MockBean
    BankUserService bankUserService;

    public BankUser createUser() {
        BankUser bankUser = new BankUser(BANKUSER_NAME, BANKUSER_OLD);
        bankUser.setId(BANKUSER_ID);
        bankUser.setGender(BANKUSER_GENDER);
        bankUser.setScore(BANKUSER_SCORE);
        return bankUser;
    }

    @Test
    @DisplayName("BankUser Controller Test")
    public void getBankUserTest() throws Exception {
        BDDMockito.given(bankUserService.getUser(BANKUSER_ID)).willReturn(SetUpTest.createUser());

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(BANKUSER_API.concat("/" + BANKUSER_ID))
                .accept(MediaType.APPLICATION_JSON);

        mvc
                .perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("id").value(BANKUSER_ID))
                .andExpect(MockMvcResultMatchers.jsonPath("name").value(BANKUSER_NAME))
                .andExpect(MockMvcResultMatchers.jsonPath("old").value(BANKUSER_OLD))
                .andExpect(MockMvcResultMatchers.jsonPath("Gender").value(BANKUSER_GENDER))
                .andExpect(MockMvcResultMatchers.jsonPath("Score").value(BANKUSER_SCORE));
    }
}
